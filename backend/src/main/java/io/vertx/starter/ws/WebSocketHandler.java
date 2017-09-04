package io.vertx.starter.ws;

import io.vertx.core.Handler;
import io.vertx.core.json.DecodeException;
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
    ws.writeTextMessage("connected");
    ws.textMessageHandler(message -> {
      try {
        JsonObject request = new JsonObject(message);
        if ("search".equals(request.getString("action"))) {  //TODO: implement some nice routing
          Subscription existingSearch = subscriptions.get(ws.textHandlerID());
          if (existingSearch != null) {
            existingSearch.unsubscribe();
          }
          Subscription newSearch = movieService.findMovies(request.getString("body")).subscribe(movie -> {
            ws.writeTextMessage(movie.toString());
          });
          subscriptions.put(ws.textHandlerID(), newSearch);
        }
      } catch (DecodeException e) {
        ws.writeTextMessage("invalid request");
      }
    });
  }
}
