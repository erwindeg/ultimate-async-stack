# Prerequisits
* Java JDK 8
* Maven 3
* Docker or MongoDB
* IDE

# Getting started
* git clone https://github.com/erwindeg/ultimate-async-stack
* cd backend
* mvn clean install
* docker run -p 27017:27017 mongod
* Run MainWebVerticle from your IDE
* go to http://localhost:8080/api/movies

# Assignments
[Vert.x documentation](http://vertx.io/docs/vertx-core/java/)
[Eventbus](http://vertx.io/docs/vertx-core/java/#event_bus)
## 1. Hello World
### a) Create a new Verticle which displays "Hello World" on the console.
### b) Change the Verticle, it should send "Hello World over the eventbus".
### c) Create another Verticle which listens to the eventbus and prints the contents on the console.


## 2. File importer
[Filesystem](http://vertx.io/docs/vertx-core/java/#_using_the_file_system_with_vert_x)
[RecordParser](http://vertx.io/docs/apidocs/io/vertx/core/parsetools/RecordParser.html)
### a) Change the FileImporterVerticle which reads the db/mongo-seed/movies.json file line by line. It should send each movie over the eventbus.
### b) Create a new Verticle with the name MovieListener which listens to the eventbus and prints the movies on the console.

## 3. MovieService and MongoDB.
### a) Create save method with unittest in the MovieService
### b) Create a method for find by id  in the MovieService
### c) Create a method for find all movies in the MovieService
### d) Change the Verticle created in step 2b. It should save each received movie with the MovieService save method.
### e) Test with http://localhost:8080/api/movies

## 4. Building a REST route for movie by id
We are going to build a REST route GET /api/movies/:id to retrieve a single movie with the MovieService class

## 6. Search for movies REST
### a)We will build a route GET /api/movies We use this to search for all movies
### b)We will build a route GET /api/movies?keyword=<keyword>. We use this keyword to search for movies using the public Observable<JsonObject> findMovies(String keyword) method.


# 7. Websockets getting started
[Websockets](http://vertx.io/docs/vertx-core/java/#_websockets)
Create a websockets hander which prints "hello world" on connection

# 8. Websockets get all movies
Create a websockets hander which listens for a Json message:
{
  action : "get"
}
When this message is received, it should send all movies back over the websocket connection


# 9. Websockets Search for Movies
Create a websockets hander which listens for a Json message:
{
  action : "search",
  body: "<keyword"
}
When this message is received, it should send all movies back that matches the search criterium. Use the public Observable<JsonObject> findMovies(String keyword) method in MovieService.


## (Optional) 10. Saving a movie with a POST request
We are going to build a REST route POST /api/movies to save single movie with the MovieService class


# Troubleshooting

To start MongoDB with a prefilled movie database, run the following:
* cd backend/db
* docker-compose up (to start the MongoDB with docker and import the data)
