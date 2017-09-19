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
Vert.x documentation: http://vertx.io/docs/vertx-core/java/
Eventbus: http://vertx.io/docs/vertx-core/java/#event_bus
## 1. Hello World
### a) Create a new Verticle which displays "Hello World" on the console.
### b) Change the Verticle, it should send "Hello World over the eventbus".
### c) Create another Verticle which listens to the eventbus and prints the contents on the console.


## 2. File importer
Filesystem: http://vertx.io/docs/vertx-core/java/#_using_the_file_system_with_vert_x
RecordParser: http://vertx.io/docs/apidocs/io/vertx/core/parsetools/RecordParser.html
### a) Create a new Verticle which reads the db/mongo-seed/movies.json file line by line and sends each movie over the eventbus. This verticle needs to run standalone (create a Main method).
### b) Create a new Verticle which listens to the eventbus and prints the movies on the console.

## 3. MovieService and MongoDB.


# Building a REST route for movie by id
We are going to build a REST route /api/movies/:id to retrieve a single movie in the MovieService class

# Saving a movie with a POST request

# Build file reader
# Store movies in database
Test with http://localhost:8080/api/movies

# Search for movies REST

# Websockets getting started

# Websockets get all movies

# Websockets Search for Movies

# Converting movies with RX

# (optional) Eventbus between WebSocketHandler and MovieService


# Troubleshooting

To start MongoDB with a prefilled movie database, run the following:
* cd backend/db
* docker-compose up (to start the MongoDB with docker and import the data)
