package nl.edegier.movies.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * Created by Erwin on 25/09/2017.
 */
public class HelloWorldRestVerticle extends AbstractVerticle{

  @Override
  public void start() {
    Router router = Router.router(vertx);
    router.get("/hello").handler(routingContext -> {
      routingContext.response()
        .end(new JsonObject().put("message", "Hello World").encode());
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
