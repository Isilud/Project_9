# Project 9

## Test coverage

La couverture de test peut-être consultée ici : https://isilud.github.io/Project_9/
Pour la mettre à jour, lancer la CI "ci-coverage"

## Application

Pour lancer l'application, utiliser la commande "docker compose up -d --build" depuis la racine sur une machine sur laquelle docker est installé.
Les services commencent tous en profil "dev", cette option peut-être retirée dans le fichier "application.propertie" de chaque service.

## Green Code

- Conserver une UI allégée pour la partie front.
- Limiter autant que possible les appels entre services.
- Pour le moment, la liste des mot-clés desquel dépend le diagnostic est codé "en dur". Si cela venait a changer, mettre en cache la nouvelle liste peut-être une bonne idée.
- Avec l'évolution de l'application, conserver un code sans partie morte et limiter l'application aux fonctionnalités utiles pour le client.
