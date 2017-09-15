#!/bin/sh
mongod
sleep 5
mongoimport --host mongo --db MOVIE_DB --collection movies --type json --file movies.json
