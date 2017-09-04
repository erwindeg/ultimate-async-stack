package io.vertx.starter;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpClient;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.starter.movies.MovieService;


/**
 * Created by Erwin on 04/09/2017.
 */
public class MovieGrabber extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    String api_key = System.getenv("API_KEY");

    HttpClient client = vertx.createHttpClient();
    client.getAbs("https://api.themoviedb.org/3/discover/movie?api_key="+api_key+"&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=3&with_genres=27", result -> {
      result.bodyHandler(resultBody -> {
        String resultBodyString = resultBody.getString(0, resultBody.length());
        JsonObject json = new JsonObject(resultBodyString);
        JsonArray movies = json.getJsonArray("results");
        new MovieService(vertx).saveMovies(movies);
        System.out.println(movies);
      });
    }).end();
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.getDelegate().deployVerticle(new MovieGrabber());
  }
}
