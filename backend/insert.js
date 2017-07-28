conn = new Mongo();
db = conn.getDB("MOVIE_DB");

for (i = 0; i < 10000; i++) {
  db.movies.insert({movieID: i},{name:"name"});
}
