# Prerequisits
* Java JDK 8
* Maven 3
* Docker or MongoDB
* IDE

# Getting started
* git clone https://github.com/erwindeg/ultimate-async-stack
* git checkout backend-assignments
* cd backend
* mvn clean install
* docker run -p 27017:27017 mongo
* Run MovieAppMain from your IDE
* go to http://localhost:8080/api/hello

# Assignments
[Vert.x documentation](http://vertx.io/docs/vertx-core/java/)
[Eventbus](http://vertx.io/docs/vertx-core/java/#event_bus)
## 1. Hello World
1. Create a new Verticle which displays "Hello World" on the console. You need to add a main method to deploy this verticle.
1. Change the Verticle, it should send "Hello World over the eventbus".
1. Add functionality to this Verticle to listen to the eventbus and print the contents on the console.


## 2. File importer
[Filesystem](http://vertx.io/docs/vertx-core/java/#_using_the_file_system_with_vert_x)
[RecordParser](http://vertx.io/docs/apidocs/io/vertx/core/parsetools/RecordParser.html)
1. Change the FileImporterVerticle which reads the db/mongo-seed/movies.json file line by line. It should send each movie over the eventbus.
1. Create a new Verticle with the name MovieListener which listens to the eventbus and prints the movies on the console.

## 3. MovieService and MongoDB.
1. Create save method in the MovieService
1. Create a method for find all movies in the MovieService with unittest
1. Change the Verticle created in step 2b. It should save each received movie with the MovieService save method.

## 4. Search for movies REST
a)We will build a route GET /api/movies We use this to search for all movies
b)We will build a route GET /api/movies?keyword=<keyword>. We use this keyword to search for movies using the public Observable<JsonObject> findMovies(String keyword) method.
c) Test with http://localhost:8080/api/movies


## 5. Websockets getting started
[Websockets](http://vertx.io/docs/vertx-core/java/#_websockets)
a) Attach the existing MovieWebSocketHandler to the HttpServer in the MovieAppMain class.
b) Create a websockets hander which prints "hello world" on connection

## 6. Websockets get all movies
Create a websockets handler which listens for a Json message:
```json
{
  action : "get"
}
```
When this message is received, it should send all movies back over the websocket connection
You can use WebSocketClientTest to test this (with a little change)

## 7. Websockets Search for Movies
Create a websockets hander which listens for a Json message:
```json
{
  action : "search",
  body: "<keyword"
}
```
When this message is received, it should send all movies back that matches the search criterium. Use the public Observable<JsonObject> findMovies(String keyword) method in MovieService.
You can use WebSocketClientTest to test this

## 8. Subscription cancelling
In the current version, once you launch a new search request on the websocket, it keeps returning the previous result.
We can fix this by storing the current search as an RX Subscription, which we unsubscribe from when a new search is started.

## (Optional) 8. Saving a movie with a POST request, find movie by id
a) We are going to build a REST route POST /api/movies to save single movie with the MovieService class
b) Create a method for find by id  in the MovieService
c)We are going to build a REST route GET /api/movies/:id to retrieve a single movie with the MovieService class

## Troubleshooting

To start MongoDB with a prefilled movie database, run the following:
* cd backend/db
* docker-compose up (to start the MongoDB with docker and import the data)
