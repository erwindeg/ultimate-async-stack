package nl.edegier.movies;

import io.vertx.rxjava.core.Vertx;
import nl.edegier.movies.movies.MovieService;
import org.junit.Before;

import java.io.IOException;

//@RunWith(VertxUnitRunner.class)
public class MovieServiceTest {
  private Vertx vertx;
  private MovieService movieService;

  @Before
  public void before() throws IOException {
    this.vertx = Vertx.vertx();
    movieService = new MovieService(vertx);
  }

//  @Test
//  public void testSaveAndFindMovies(TestContext context) {
//    Async async = context.async();
//    movieService.saveMovie(new JsonObject().put("title", "Alien 3"), result -> {
//      if (result.failed()) {
//        result.cause().printStackTrace();
//        context.fail();
//      } else {
//        Observable<JsonObject> movies = this.movieService.findAllMovies();
//
//        movies.toList().subscribe(list -> {
//          context.assertFalse(list.isEmpty());
//          async.complete();
//        });
//      }
//    });
//  }
}
