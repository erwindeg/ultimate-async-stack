package nl.edegier.movies;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.core.http.ServerWebSocket;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import io.vertx.rxjava.ext.web.handler.CorsHandler;
import nl.edegier.movies.api.MovieRestService;
import nl.edegier.movies.importer.MovieListener;
import nl.edegier.movies.movies.MovieService;
import nl.edegier.movies.ws.WSAction;
import rx.Observable;

public class MovieAppMain extends AbstractVerticle {

  public static void main(String[] args) {
        RxHelper.deployVerticle(Vertx.vertx(), new MovieAppMain());
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


    Observable<ServerWebSocket> wsStreamObservable
      = server.websocketStream().toObservable();
    wsStreamObservable.subscribe(
      socket -> {
        socket.toObservable()
          .map(buffer ->
            Json.decodeValue(buffer.toString("UTF-8"), WSAction.class))
          .filter(action -> action.isSearch())
          .map(action -> action.getBody())
          .filter(searchTerm -> searchTerm.length() >= 3)
          .switchMap(movieService::findMovies)
          .map(movie -> movie.encode())
          .subscribe(socket::writeTextMessage);
      }
    );

    server.requestHandler(router::accept).listen(8080);
  }

  public Handler createCorsHandler() {
    return CorsHandler.create("*")
      .allowedMethod(HttpMethod.GET)
      .allowedMethod(HttpMethod.POST)
      .allowedMethod(HttpMethod.PUT)
      .allowedMethod(HttpMethod.DELETE)
      .allowedHeader("Authorization")
      .allowedHeader("Content-Type")
      .allowedHeader("Authorization")
      .allowedHeader("www-authenticate")
      .allowedHeader("Content-Type")
      .allowedHeader("Access-Control-Request-Method")
      .allowedHeader("Access-Control-Allow-Credentials")
      .allowedHeader("Access-Control-Allow-Origin");
  }

}
