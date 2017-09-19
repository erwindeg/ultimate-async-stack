package nl.edegier.movies;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.rxjava.core.Vertx;
import nl.edegier.movies.movies.MovieService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Observable;

import java.io.IOException;

@RunWith(VertxUnitRunner.class)
public class MovieServiceTest {

  private static MongodExecutable mongodExecutable;
  private static Vertx vertx;
  private static MovieService movieService;


  @BeforeClass
  public static void before() throws IOException {
    MongodStarter starter = MongodStarter.getDefaultInstance();

    String bindIp = "localhost";
    int port = 12345;
    System.setProperty("MONGODB_SERVICE_PORT", port + "");
    IMongodConfig mongodConfig = new MongodConfigBuilder()
      .version(Version.Main.PRODUCTION)
      .net(new Net(bindIp, port, Network.localhostIsIPv6()))
      .build();

    mongodExecutable = starter.prepare(mongodConfig);
    mongodExecutable.start();

    vertx = Vertx.vertx();
    movieService = new MovieService(vertx);
  }

  @AfterClass
  public static void after() {
    if (mongodExecutable != null)
      mongodExecutable.stop();
    vertx.close();
  }


  @Test
  public void testFindMovies(TestContext context) {
    Async async = context.async();
    movieService.saveMovie(new JsonObject().put("title", "Alien 3"), result -> {
      if (result.failed()) {
        result.cause().printStackTrace();
        context.fail();
      } else {
        Observable<JsonObject> movies = this.movieService.findAllMovies();

        movies.toList().subscribe(list -> {
          context.assertFalse(list.isEmpty());
          async.complete();
        });
      }
    });


  }
}
