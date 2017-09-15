conn = new Mongo();
db = conn.getDB("MOVIE_DB");

for (i = 0; i < 100; i++) {
  db.movies.insert({movieID: i,name:"name", genre:"horror"});
}

for (i = 100; i < 200; i++) {
  db.movies.insert({movieID: i,name:"name", genre:"romance"});
}
