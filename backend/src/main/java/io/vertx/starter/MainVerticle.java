package io.vertx.starter;


import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    vertx.createHttpServer()
      .requestHandler(req -> req.response().end("Hello Vert.x!"))
      .listen(8080);
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    RxHelper.deployVerticle(vertx, new MainVerticle());
  }

}
