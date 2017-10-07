package nl.edegier.movies.api;

import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.Router;
import nl.edegier.movies.movies.MovieService;

/**
 * Created by Erwin on 04/09/2017.
 */
public class MovieRestService {
  Vertx vertx;
  MovieService movieService;

  public MovieRestService(MovieService movieService, Vertx vertx){
    this.vertx = vertx;
    this.movieService = movieService;
  }

  public Router getRouter(){
    Router apiRouter = Router.router(vertx);
    apiRouter.get("/hello").handler(rc -> {
      rc.response().end("hello world");
    });


    apiRouter.get("/movies").handler(rc -> {
      String keyword = rc.request().getParam("keyword");

      if(keyword == null){
       //TODO: implement find all movies
      } else {
       //TODO: implement search with keyword
      }
    });


    return apiRouter;
  }
}
