FROM mongo

COPY movies.json /movies.json
CMD mongoimport --host mongodb --db MOVIE_DB --collection movies --type json --file /movies.json
