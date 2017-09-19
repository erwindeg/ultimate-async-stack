package io.vertx.starter.importer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.starter.movies.MovieService;

/**
 * Created by Erwin on 18/09/2017.
 */
public class MovieListener extends AbstractVerticle {
  private MovieService movieService;

  public MovieListener(MovieService movieService) {
    this.movieService = movieService;
  }

  @Override
  public void start() throws Exception {
    vertx.eventBus().consumer("movies", message -> movieService.saveMovie((JsonObject) message.body()));
  }
}
