# start mongo
docker-compose up

# Starting the server
Run MainVerticle.java

# REST API
* GET /api/movies
returns all movies
* GET /api/movies/:id
returns the movie by its id
* GET /api/movies?keyword=<keyword>
search for movies by their title

# Websockets
Connect to ws://localhost:8080.
Send a request in json:
{
  action : "movies",
  body : "keyword"
}

The body contains the search keyword. A list of movies will be returned in json if they are found.
Example:
[
{
"_id": "59ad23ffa240b26e2d9a6705",
"vote_count": 476,
"id": 353491,
"video": false,
"vote_average": 5.6,
"title": "The Dark Tower",
"popularity": 186.569414,
"poster_path": "/i9GUSgddIqrroubiLsvvMRYyRy0.jpg",
"original_language": "en",
"original_title": "The Dark Tower",
"genres": [
"Horror",
"Romance"
],
"backdrop_path": "/2n7Zn6WxJ76ccOwnuQHuhSFMbqt.jpg",
"adult": false,
"overview": "The last Gunslinger, Roland Deschain, has been locked in an eternal battle with Walter Oâ€™Dim, also known as the Man in Black, determined to prevent him from toppling the Dark Tower, which holds the universe together. With the fate of the worlds at stake, good and evil will collide in the ultimate battle as only Roland can defend the Tower from the Man in Black.",
"release_date": "2017-08-03"
}
]

# Instructions for MongoDB without Docker
install Mongodb 3
mkdir data
mongod -dbpath data
mongoimport --host localhost --db MOVIE_DB --collection movies --type json --file /movies.json

