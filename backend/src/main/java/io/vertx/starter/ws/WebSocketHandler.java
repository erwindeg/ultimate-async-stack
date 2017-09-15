package io.vertx.starter.ws;

import io.vertx.core.Handler;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.http.ServerWebSocket;
import io.vertx.starter.movies.MovieService;
import rx.Subscription;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erwin on 04/09/2017.
 */
public class WebSocketHandler implements Handler<ServerWebSocket> {
  MovieService movieService;

  Map<String, Subscription> subscriptions = new HashMap<>();

  public WebSocketHandler(MovieService movieService) {
    this.movieService = movieService;
  }

  @Override
  public void handle(ServerWebSocket ws) {
    ws.writeTextMessage(new JsonObject().put("status","connected").encode());
    ws.textMessageHandler(message -> {
      try {
        WSAction action = Json.decodeValue(message, WSAction.class);
        System.out.println(action.getAction());
        if (action.isSearch()) {
          Subscription existingSearch = subscriptions.get(ws.textHandlerID());
          if (existingSearch != null) {
            existingSearch.unsubscribe();
          }
          Subscription newSearch = movieService.findMovies(action.getBody()).subscribe(movie -> {
            try {
              Thread.sleep(300);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            ws.writeTextMessage(movie.encode());
          });
          subscriptions.put(ws.textHandlerID(), newSearch);
        }
      } catch (DecodeException e) {
        ws.writeTextMessage(new JsonObject().put("status","invalid request").encode());
      }
    });
  }
}

  //Observable observable = Observable.interval(1000, TimeUnit.MILLISECONDS).take(numbers.length).map(t -> numbers[t.intValue()]);
