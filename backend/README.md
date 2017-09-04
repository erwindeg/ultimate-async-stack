# start mongo
docker run -p 27017:27017 -d mongo

# import movie data
From the backend folder run
mongoimport --db MOVIE_DB --collection movies --file movies.json  //TODO: needs mongo installed, create
java class for this which reads from file and stores in mongo

# Starting the server
Run MainVerticle.java

# REST API
* GET /api/movies
returns all movies
* GET /api/movies/:id
returns the movie by its id
* GET /api/movies?keyword=<keyword>
search for movies by their title




