package io.vertx.starter.movies;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;
import rx.Observable;

/**
 * Created by Erwin on 28/07/2017.
 */
public interface MovieService {

  static final String ADDRESS_MOVIE_SERVICE = "movie.service";

  static MovieService create(Vertx vertx) {
    return new MovieServiceImpl(vertx);
  }

  static MovieService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(MovieService.class, vertx.getDelegate(), address);
  }

  Observable<JsonObject> findMovies(String keyword);
}
