package nl.edegier.movies;

import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class MainVerticleTest {

//  private Vertx vertx;
//  private MovieAppMain mainVerticle;
//
//  @Before
//  public void setUp(TestContext tc) {
//    this.vertx = Vertx.vertx();
//    this.mainVerticle = new MovieAppMain();
//    vertx.deployVerticle(mainVerticle, tc.asyncAssertSuccess());
//  }
//
//  @After
//  public void tearDown(TestContext tc) {
//    vertx.close(tc.asyncAssertSuccess());
//  }

//  @Test
//  public void testThatTheServerIsStarted(TestContext tc) {
//    Async async = tc.async();
//    vertx.createHttpClient().getNow(8080, "localhost", "/", response -> {
//      tc.assertEquals(response.statusCode(), 200);
//      response.bodyHandler(body -> {
//        tc.assertTrue(body.length() > 0);
//        async.complete();
//      });
//    });
//  }

  @Test
  public void test(){

  }
}
