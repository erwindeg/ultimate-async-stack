package io.vertx.starter.importer;


import io.vertx.core.VertxOptions;
import io.vertx.core.file.OpenOptions;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.file.AsyncFile;
import io.vertx.rxjava.core.file.FileSystem;
import io.vertx.rxjava.core.parsetools.RecordParser;
import io.vertx.starter.movies.MovieService;

/**
 * Created by Erwin on 16/09/2017.
 */
public class FileImporterVerticle extends AbstractVerticle{

  MovieService movieService;
  final int max = 400;
  int requests = 0;
  AsyncFile file;
  private static final String FILE_NAME = "db/mongo-seed/movies.json";
  boolean isReading;

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions(), res -> {
      RxHelper.deployVerticle(res.result(), new FileImporterVerticle());
    });
  }

  @Override
  public void start() throws Exception {
    this.readFile();
    movieService = new MovieService(vertx);
  }

  private void readFile() {
    this.isReading = true;
    final RecordParser parser = RecordParser.newDelimited("\n", movie -> {
      vertx.eventBus().send("movies",movie.toJsonObject());
    });
    FileSystem fs = vertx.fileSystem();
    fs.open(FILE_NAME, new OpenOptions(), contents -> {
      if (contents.succeeded()) {
        this.file = contents.result();
        this.file.handler(buffer -> {
          parser.handle(buffer.copy());
        });

        this.file.endHandler(result -> {
          System.out.println("Done");
          this.isReading = false;
        });

      } else {
        System.out.println(contents.cause());
      }
    });
  }

}
