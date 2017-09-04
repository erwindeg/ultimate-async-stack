package io.vertx.starter;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.CorsHandler;
import io.vertx.starter.movies.MovieServiceImpl;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    router.route().handler(createCorsHandler());
    server.websocketHandler(new WebSocketHandler(new MovieServiceImpl(vertx)));
    server.requestHandler(router::accept).listen(8080);
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    RxHelper.deployVerticle(vertx, new MainVerticle());
  }

  public Handler createCorsHandler() {
    CorsHandler corsHandler = CorsHandler.create("*");
    corsHandler.allowedMethod(HttpMethod.GET);
    corsHandler.allowedMethod(HttpMethod.POST);
    corsHandler.allowedMethod(HttpMethod.PUT);
    corsHandler.allowedMethod(HttpMethod.DELETE);
    corsHandler.allowedHeader("Authorization");
    corsHandler.allowedHeader("Content-Type");
    return corsHandler;
  }

}
