package io.vertx.starter.movies;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.starter.database.MongoClientPool;
import rx.Emitter;
import rx.Observable;


/**
 * Created by Erwin on 28/07/2017.
 */
public class MovieServiceImpl implements MovieService {

  MongoClient mongoClient;

  public MovieServiceImpl(Vertx vertx) {
    this.mongoClient = new MongoClientPool(vertx).getInstance();
  }

  public Observable<JsonObject> findMovies(String keyword) {
    return Observable.<JsonObject>create(emitter -> {
      mongoClient.findBatch("movies", new JsonObject().put("genre", keyword), ar -> {
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
}
