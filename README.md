# Curiosity

Aplicatie care funcitioneaza ca o enciclopedie/google search. Este o incercare de a replica aplicatia deja existenta [Perplexity](https://www.perplexity.ai/).

Utilizatorul isi poate crea un cont si se poate autentifica pentru a accesa functionalitatile. In ecranul principal exista o lista de articole gata scrise. Utilizatorul poate selecta unul dintre articolele pre-definite, sau poate genera unul nou in ecranul de search. In functie de prompt-ul utilizatorului se va genera un articol nou, care va fi arhivat si poate fi recitit ulterior.

## Arhitectura

Diagrama de arhitectura:

![diagrama_de_arhitectura](/media/arhitectura.jpg)

### Aplicatie Android

Aplicatia este implementata in Android folosind Kotlin si Jetpack Compose. Urmareste bunele practici ale arhitecturilor Android moderne, cum ar fi navigatie moderna, viewmodels, repository pattern etc...

### Gemini

Articolele sunt generate folosind LLM-ul Gemini prin intermediul unei biblioteci special implementata pentru ecosistemul Android. Token-ul de autentificare pentru a folosi AI-ul nu este disponibil in repo (initial voiam sa folosesc varianta free, dar am aflat ulterior ca varianta free nu e disponibila in Europa), dar pot oferi acces in cazul in care doreste cineva.

### API Service

Articolele pre-definite si cele generate sunt salvate in serverul implementat in Go, intr-o baza de date PostgreSQL. Acesta functioneaza ca un REST API. De asemenea API-ul se ocupa de inregistrarea si autentificarea utilizatorilor prin tokeni JWT.

## Media

Ecran inregistrare cont:

![inregistrare](/media/register_screen.jpeg)

Ecran autentificare cont:

![autentificare](/media/login_screen.jpeg)

Ecran principal (lista de articole predefinite):

![ecran_principal](/media/article_list.jpeg)

Articol vizionat in detaliu:

![article_detail](/media/article_detail.jpeg)

Ecran generare articol:

![ecran_generare_articol](/media/search_article.jpeg)

Artiolul generat:

![articol generat](/media/generated_article.jpeg)

Articole arhivate:

![articole arhivate](/media/archived_articles.jpeg)