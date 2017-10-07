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
    //TODO: send a message on connect
    ws.textMessageHandler(message -> search(message,ws));
  }

  private void search(String message, ServerWebSocket ws) {
      WSAction action = null;

      try {
        action = Json.decodeValue(message, WSAction.class);
      } catch (DecodeException e) {
        ws.writeTextMessage(new JsonObject().put("status", "invalid request").encode());
      }

    if (action != null && action.isGet()) {
      //TODO: return all movies over the websocket connection
    } else if (action != null && action.isSearch()) {
      //TODO: when we get a valid search action we want to search for movies
      // and return the result over the websocket connection.
      //BONUS: how do we handle cancelling the old search when we get a new search request?
    }

  }




}


