package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"os"
	"strconv"
	"time"

	"github.com/golang-jwt/jwt/v5"
	_ "github.com/lib/pq"
	"golang.org/x/crypto/bcrypt"
)

type User struct {
	Id       int    `json:"id"`
	Username string `json:"username"`
	Password string `json:"password"`
	Email    string `json:"email"`
}

type Claims struct {
	UserId   string `json:"user_id"`
	Username string `json:"username"`
	jwt.RegisteredClaims
}

type Article struct {
	Id      int    `json:"id"`
	Title   string `json:"title"`
	Content string `json:"content"`
}

type ArticleInput struct {
	Title   string `json:"title"`
	Content string `json:"content"`
}

var tokenDuration = 24 * time.Hour
var jwtSecretKey = os.Getenv("JWT_SECRET")
var db *sql.DB

var dbUser = os.Getenv("PGUSER")
var dbPassword = os.Getenv("PGPASSWORD")
var dbHost = os.Getenv("PGHOST")
var dbName = os.Getenv("PGDATABASE")

// var dbUser = "postgres"
// var dbPassword = "postgres"
// var dbHost = "localhost"
// var dbName = "curiosity"

func main() {
	// Connect to PostgreSQL database
	var err error
	connectionString := fmt.Sprintf("postgres://%s:%s@%s:5432/%s?sslmode=disable", dbUser, dbPassword, dbHost, dbName)
	log.Println("connection string", connectionString)
	db, err = sql.Open("postgres", connectionString)
	if err != nil {
		log.Fatal("Error connecting to the database: ", err)
	}
	defer db.Close()

	http.HandleFunc("/register", registerHandler)
	http.HandleFunc("/login", loginHandler)
	http.HandleFunc("/authenticate", authenticateHandler)
	http.HandleFunc("/all_articles", getArticlesHandler)
	http.HandleFunc("/user_articles", getUserArticlesHandler)
	http.HandleFunc("/add_article", addArticleHandler)

	log.Println("Server started at :8082")
	log.Fatal(http.ListenAndServe(":8082", nil))
}

func registerHandler(w http.ResponseWriter, r *http.Request) {
	var user User
	err := json.NewDecoder(r.Body).Decode(&user)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	var dbUsername string
	row := db.QueryRow("SELECT * FROM users WHERE username=$1", user.Username)
	err = row.Scan(&dbUsername)
	if err != nil && err != sql.ErrNoRows {
		w.WriteHeader(http.StatusUnauthorized)
		json.NewEncoder(w).Encode(map[string]string{"error": "username already exists"})
		return
	}

	// Hash the password
	hashedPassword, err := bcrypt.GenerateFromPassword([]byte(user.Password), bcrypt.DefaultCost)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	// Insert user into database
	_, err = db.Exec("INSERT INTO users(username, password, email) VALUES ($1, $2, $3)", user.Username, hashedPassword, user.Email)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	log.Println("Added user", user.Username, "to the database")
	w.WriteHeader(http.StatusCreated)
}

func loginHandler(w http.ResponseWriter, r *http.Request) {
	var user User
	err := json.NewDecoder(r.Body).Decode(&user)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	// Query the database for the user
	row := db.QueryRow("SELECT id, username, password, email FROM users WHERE username=$1", user.Username)

	var storedPassword string
	err = row.Scan(&user.Id, &user.Username, &storedPassword, &user.Email)
	if err != nil {
		w.WriteHeader(http.StatusUnauthorized)
		json.NewEncoder(w).Encode(map[string]string{"error": "invalid user"})
		return
	}

	// Compare passwords
	err = bcrypt.CompareHashAndPassword([]byte(storedPassword), []byte(user.Password))
	if err != nil {
		w.WriteHeader(http.StatusUnauthorized)
		json.NewEncoder(w).Encode(map[string]string{"error": "invalid password"})
		return
	}

	// Generate JWT token
	token := jwt.NewWithClaims(jwt.SigningMethodHS256, Claims{
		strconv.Itoa(user.Id),
		user.Username,
		jwt.RegisteredClaims{
			ExpiresAt: jwt.NewNumericDate(time.Now().Add(tokenDuration)),
		},
	})

	tokenString, err := token.SignedString([]byte(jwtSecretKey))
	if err != nil {
		w.WriteHeader(http.StatusUnauthorized)
		json.NewEncoder(w).Encode(map[string]string{"error": "could not generate token"})
		return
	}

	log.Println("Authenticated user", user.Username)
	log.Println("Token string:", tokenString)

	// Send the token in the response
	json.NewEncoder(w).Encode(map[string]string{"token": tokenString})
}

// TODO: extract authenticateHandler to a middleware
func authenticateHandler(w http.ResponseWriter, r *http.Request) {
	tokenString := r.Header.Get("Authorization")
	if tokenString == "" {
		http.Error(w, "Authorization token is missing", http.StatusUnauthorized)
		return
	}

	// Parse token
	token, err := jwt.ParseWithClaims(tokenString[len("Bearer "):], &Claims{}, func(token *jwt.Token) (interface{}, error) {
		return []byte(jwtSecretKey), nil
	})

	// Verify token
	if err != nil || !token.Valid {
		w.WriteHeader(http.StatusUnauthorized)
		json.NewEncoder(w).Encode(map[string]string{"isAuthenticated": "false"})
		return
	}

	json.NewEncoder(w).Encode(map[string]string{"isAuthenticated": "true", "userId": token.Claims.(*Claims).UserId, "username": token.Claims.(*Claims).Username})
}

func getArticlesHandler(w http.ResponseWriter, r *http.Request) {
	tokenString := r.Header.Get("Authorization")
	if tokenString == "" {
		http.Error(w, "Authorization token is missing", http.StatusUnauthorized)
		return
	}

	token, err := jwt.ParseWithClaims(tokenString[len("Bearer "):], &Claims{}, func(token *jwt.Token) (interface{}, error) {
		return []byte(jwtSecretKey), nil
	})

	// Verify token
	if err != nil || !token.Valid {
		w.WriteHeader(http.StatusUnauthorized)
		json.NewEncoder(w).Encode(map[string]string{"isAuthenticated": "false"})
		return
	}

	rows, err := db.Query("SELECT id, title, content FROM articles WHERE author_id IS NULL")
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	defer rows.Close()

	var articles []Article = []Article{}
	for rows.Next() {
		var article Article
		err := rows.Scan(&article.Id, &article.Title, &article.Content)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		articles = append(articles, article)
	}

	json.NewEncoder(w).Encode(articles)
}

func getUserArticlesHandler(w http.ResponseWriter, r *http.Request) {
	tokenString := r.Header.Get("Authorization")
	if tokenString == "" {
		http.Error(w, "Authorization token is missing", http.StatusUnauthorized)
		return
	}

	token, err := jwt.ParseWithClaims(tokenString[len("Bearer "):], &Claims{}, func(token *jwt.Token) (interface{}, error) {
		return []byte(jwtSecretKey), nil
	})

	// Verify token
	if err != nil || !token.Valid {
		w.WriteHeader(http.StatusUnauthorized)
		json.NewEncoder(w).Encode(map[string]string{"isAuthenticated": "false"})
		return
	}

	rows, err := db.Query(fmt.Sprintf("SELECT id, title, content FROM articles WHERE author_id=%s", token.Claims.(*Claims).UserId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	defer rows.Close()

	var articles []Article = []Article{}
	for rows.Next() {
		var article Article
		err := rows.Scan(&article.Id, &article.Title, &article.Content)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		articles = append(articles, article)
	}

	json.NewEncoder(w).Encode(articles)
}

func addArticleHandler(w http.ResponseWriter, r *http.Request) {
	tokenString := r.Header.Get("Authorization")
	if tokenString == "" {
		http.Error(w, "Authorization token is missing", http.StatusUnauthorized)
		return
	}

	token, err := jwt.ParseWithClaims(tokenString[len("Bearer "):], &Claims{}, func(token *jwt.Token) (interface{}, error) {
		return []byte(jwtSecretKey), nil
	})

	// Verify token
	if err != nil || !token.Valid {
		w.WriteHeader(http.StatusUnauthorized)
		json.NewEncoder(w).Encode(map[string]string{"isAuthenticated": "false"})
		return
	}

	var articleInput ArticleInput
	err = json.NewDecoder(r.Body).Decode(&articleInput)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	// Insert user into database
	var articleId int
	err = db.QueryRow("INSERT INTO articles(title, content, author_id) VALUES ($1, $2, $3) RETURNING id", articleInput.Title, articleInput.Content, token.Claims.(*Claims).UserId).Scan(&articleId)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	var article Article
	article.Id = articleId
	article.Title = articleInput.Title
	article.Content = articleInput.Content

	log.Println("Added article", articleInput.Title, "to the database")
	json.NewEncoder(w).Encode(article)
}
