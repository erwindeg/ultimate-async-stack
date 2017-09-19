package nl.edegier.movies.movies;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.mongo.MongoClient;
import nl.edegier.movies.database.MongoClientPool;
import rx.Emitter;
import rx.Observable;
import rx.Single;


/**
 * Created by Erwin on 28/07/2017.
 */
public class MovieService {

  private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/original";
  MongoClient mongoClient;

  public MovieService(Vertx vertx) {
    this.mongoClient = new MongoClientPool(vertx).getInstance();
  }

  /**
   * Returns an Observable which returns all movies from the datastore
   * @return
   */
  public Observable<JsonObject> findAllMovies() {
    return Observable.<JsonObject>create(emitter -> {
      mongoClient.findBatch("movies", new JsonObject(), ar -> {
        if (ar.succeeded()) {
          JsonObject result = ar.result();
          if (result == null) {
            emitter.onCompleted();
          } else {
            emitter.onNext(result);
          }
        } else {
          emitter.onError(ar.cause());
        }
      });
    }, Emitter.BackpressureMode.BUFFER).map(this::convertMovie);
  }

  public Observable<JsonObject> findMovies(String keyword) {
    return Observable.<JsonObject>create(emitter -> {
      JsonArray searches = new JsonArray();
      searches.add(new JsonObject().put("title", new JsonObject().put("$regex", ".*" + keyword + ".*").put("$options", "i")));
      searches.add(new JsonObject().put("overview", new JsonObject().put("$regex", ".*" + keyword + ".*").put("$options", "i")));

      mongoClient.findBatch("movies", new JsonObject().put("$or", searches), ar -> {
        if (ar.succeeded()) {
          JsonObject result = ar.result();
          if (result == null) {
            emitter.onCompleted();
          } else {
            emitter.onNext(result);
          }
        } else {
          emitter.onError(ar.cause());
        }
      });
    }, Emitter.BackpressureMode.BUFFER).map(this::convertMovie);
  }

  public void saveMovies(JsonArray movies) {
    movies.forEach(movie -> mongoClient.save("movies", (JsonObject) movie, System.out::println));
  }

  public void saveMovie(JsonObject movie) {
     mongoClient.save("movies", movie, result ->{
       if(result.failed()){
         result.cause().printStackTrace();
       }
     });
  }



  public Single<JsonObject> findMovie(String id) {
    return mongoClient.rxFindOne("movies", new JsonObject().put("_id", id), new JsonObject());
  }

  private JsonObject convertMovie(JsonObject movie) {
    JsonObject movieDTO = new JsonObject(movie.encode());
    movieDTO.put("poster_path", POSTER_BASE_URL + movie.getString("poster_path"));
    JsonArray genres = new JsonArray();
    movieDTO.put("genres", genres);
    movie.getJsonArray("genre_ids").stream().map(genreID -> Genre.genres.get(genreID)).forEach(genre -> genres.add(genre));
    return movieDTO;
  }
}
