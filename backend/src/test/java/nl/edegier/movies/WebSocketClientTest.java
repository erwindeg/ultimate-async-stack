package nl.edegier.movies;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpClient;

/**
 * Created by Erwin on 03/09/2017.
 */
public class WebSocketClientTest extends AbstractVerticle {


  @Override
  public void start() throws Exception {
    HttpClient client = vertx.createHttpClient();

    client.websocket(8080, "localhost", "", websocket -> {

      websocket.handler(data -> {
        System.out.println(data.toString("ISO-8859-1"));
      });
      JsonObject request = new JsonObject();
      request.put("action", "search");
      request.put("body", "the");
      websocket.writeTextMessage(request.toString());
      vertx.setTimer(1000, i -> {
        JsonObject request2 = new JsonObject();
        request2.put("action", "search");
        request2.put("body", "Tower");
        websocket.writeTextMessage(request2.toString());
      });
    });

  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    RxHelper.deployVerticle(vertx, new WebSocketClientTest());
  }
}
