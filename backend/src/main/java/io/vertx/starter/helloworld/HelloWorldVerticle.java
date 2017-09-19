package io.vertx.starter.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;


public class HelloWorldVerticle extends AbstractVerticle{

  @Override
  public void start() throws Exception {
    vertx.eventBus().consumer("hello-channel",message -> System.out.println(message.body()));

    vertx.eventBus().send("hello-channel","Hello world!");
  }

  public static void main(String[] args) {
    Vertx.vertx().deployVerticle(new HelloWorldVerticle());
  }
}
