FROM mongo

COPY movies.json /movies.json
COPY init.sh /init.sh
#CMD mongoimport --host mongodb --db reach-engine --collection MyDummyCollection --type json --file /init.json --jsonArray
#CMD  bash init.sh
CMD mongod & sleep5 & mongoimport --host localhost --db MOVIE_DB --collection movies --type json --file movies.json
