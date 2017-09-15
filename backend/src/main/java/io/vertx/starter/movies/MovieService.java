package io.vertx.starter.movies;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.starter.database.MongoClientPool;
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

  private JsonObject convertMovie(JsonObject movie) {
      JsonObject movieDTO = new JsonObject(movie.encode());
      movieDTO.put("poster_path",POSTER_BASE_URL + movie.getString("poster_path"));
      return movieDTO;
  }

  public Observable<JsonObject> findMovies(String keyword) {
    return Observable.<JsonObject>create(emitter -> {

      mongoClient.findBatch("movies", new JsonObject().put("$or", new JsonArray().add(new JsonObject().put("title", new JsonObject().put("$regex", ".*" + keyword + ".*").put("$options", "i"))).add(new JsonObject().put("overview", new JsonObject().put("$regex", ".*" + keyword + ".*").put("$options", "i")))), ar -> {
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

  public void saveMovies(JsonArray movies){
    movies.forEach(movie ->  mongoClient.save("movies",(JsonObject) movie, System.out::println));
  }


  public Single<JsonObject> findMovie(String id) {
      return mongoClient.rxFindOne("movies", new JsonObject().put("_id",id), new JsonObject());
  }
}
