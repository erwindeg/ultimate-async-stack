package nl.edegier.movies.ws;

import io.vertx.core.Handler;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.http.ServerWebSocket;
import nl.edegier.movies.movies.MovieService;
import rx.Subscription;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erwin on 04/09/2017.
 */
public class MovieWebSocketHandler implements Handler<ServerWebSocket> {
  MovieService movieService;

  Map<String, Subscription> subscriptions = new HashMap<>();

  public MovieWebSocketHandler(MovieService movieService) {
    this.movieService = movieService;
  }

  @Override
  public void handle(ServerWebSocket ws) {
    ws.writeTextMessage(new JsonObject().put("status", "connected").encode());
    ws.textMessageHandler(message -> search(message,ws));
  }

  private void search(String message, ServerWebSocket ws) {
      WSAction action = null;

      try {
        action = Json.decodeValue(message, WSAction.class);
      } catch (DecodeException e) {
        ws.writeTextMessage(new JsonObject().put("status", "invalid request").encode());
      }

      if (action != null && action.isSearch()) {
        Subscription existingSearch = subscriptions.get(ws.textHandlerID());
        if (existingSearch != null) {
          existingSearch.unsubscribe();
        }

        Subscription newSearch = movieService.findMovies(action.getBody()).subscribe(movie -> {
          ws.writeTextMessage(movie.encode());
        });

        subscriptions.put(ws.textHandlerID(), newSearch);
      }

  }




}


