CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS articles (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  author_id INT NOT NULL,
  FOREIGN KEY (author_id) REFERENCES users(id)
);

INSERT INTO articles (title, content) 
VALUES ('Article 1', 'This is the data for article 1');

INSERT INTO articles (title, content) 
VALUES ('Article 2', 'This is the data for article 2');

INSERT INTO articles (title, content) 
VALUES ('Article 3', 'This is the data for article 3');