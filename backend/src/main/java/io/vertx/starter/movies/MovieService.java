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
public class MovieService {

  MongoClient mongoClient;

  public MovieService(Vertx vertx){
    this.mongoClient = new MongoClientPool(vertx).getInstance();
  }

  public Observable<JsonObject> findMovies() {
    Observable<JsonObject> resultObservable =  Observable.<JsonObject>create(emitter -> {
      mongoClient.findBatch("movies", new JsonObject(), ar -> {
        if (ar.succeeded()) {
          JsonObject result = ar.result();
          if (result == null) {
            emitter.onCompleted();
            System.out.println("completed");
          } else {
            emitter.onNext(result);
          }
        } else {
          emitter.onError(ar.cause());
        }
      });
    }, Emitter.BackpressureMode.BUFFER);

    return resultObservable;
  }
}
