package nl.edegier.movies;

import io.vertx.core.Handler;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import io.vertx.rxjava.ext.web.handler.CorsHandler;
import nl.edegier.movies.api.MovieRestService;
import nl.edegier.movies.importer.MovieListener;
import nl.edegier.movies.movies.MovieService;
import nl.edegier.movies.ws.MovieWebSocketHandler;

public class MovieAppMain extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions(), res -> {
        RxHelper.deployVerticle(res.result(), new MovieAppMain());
    });
  }


  @Override
  public void start() {
    MovieService movieService = new MovieService(vertx);
    RxHelper.deployVerticle(vertx, new MovieListener(movieService));

    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    router.route().handler(createCorsHandler());
    router.route().handler(BodyHandler.create());
    router.mountSubRouter("/api",new MovieRestService(movieService,vertx).getRouter());
    server.websocketHandler(new MovieWebSocketHandler(movieService));
    server.requestHandler(router::accept).listen(8080);
  }

  public Handler createCorsHandler() {
    CorsHandler corsHandler = CorsHandler.create("*");
    corsHandler.allowedMethod(HttpMethod.GET);
    corsHandler.allowedMethod(HttpMethod.POST);
    corsHandler.allowedMethod(HttpMethod.PUT);
    corsHandler.allowedMethod(HttpMethod.DELETE);
    corsHandler.allowedHeader("Authorization");
    corsHandler.allowedHeader("Content-Type");
    corsHandler.allowedHeader("Authorization");
    corsHandler.allowedHeader("www-authenticate");
    corsHandler.allowedHeader("Content-Type");
    corsHandler.allowedHeader("Access-Control-Request-Method");
    corsHandler.allowedHeader("Access-Control-Allow-Credentials");
    corsHandler.allowedHeader("Access-Control-Allow-Origin");
    corsHandler.allowedHeader("Access-Control-Allow-Headers");
    return corsHandler;
  }

}
