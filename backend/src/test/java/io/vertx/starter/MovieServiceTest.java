package io.vertx.starter;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.rxjava.core.Vertx;
import io.vertx.starter.movies.MovieService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Observable;
import rx.Observer;

@RunWith(VertxUnitRunner.class)
public class MovieServiceTest {

  private Vertx vertx;
  private MovieService movieService;

  @Before
  public void setUp(TestContext context) {
    this.vertx = Vertx.vertx();
    this.movieService = new MovieService(vertx);
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testFindMovies(TestContext context) {
    Async async = context.async();
    Observable<JsonObject> movies = this.movieService.findMovies("horror");
    movies.subscribe(new Observer<JsonObject>() {
      @Override
      public void onCompleted() {
        async.complete();
      }

      @Override
      public void onError(Throwable throwable) {
        throwable.printStackTrace();
      }

      @Override
      public void onNext(JsonObject entries) {
        System.out.println(entries);
      }
    });
  }
}
