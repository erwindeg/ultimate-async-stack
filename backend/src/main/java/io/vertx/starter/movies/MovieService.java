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
    }, Emitter.BackpressureMode.BUFFER);
  }

  public Observable<JsonObject> findMovies(String keyword) {
    return Observable.<JsonObject>create(emitter -> {

      mongoClient.findBatch("movies", new JsonObject().put("title", new JsonObject().put("$regex",".*"+keyword+".*").put("$options","i")), ar -> {
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
    }, Emitter.BackpressureMode.BUFFER);
  }

  public void saveMovies(JsonArray movies){
    movies.forEach(movie ->  mongoClient.save("movies",(JsonObject) movie, System.out::println));
  }


  public Single<JsonObject> findMovie(String id) {
      return mongoClient.rxFindOne("movies", new JsonObject().put("_id",id), new JsonObject());
  }
}
