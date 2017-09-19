package nl.edegier.movies.database;

import io.vertx.rxjava.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.mongo.MongoClient;

/**
 * Created by Erwin on 01/03/2017.
 */
public class MongoClientPool {

    String databaseHost;
    String databasePort;
    String dbName;
    String dbUserName;
    String dbPassword;
    Vertx vertx;

    public MongoClientPool(Vertx vertx) {
        this.vertx = vertx;
        databaseHost = System.getProperty("MONGODB_SERVICE_HOST");
        databasePort = System.getProperty("MONGODB_SERVICE_PORT");
        dbName = System.getProperty("MONGODB_DATABASE");
        dbUserName = System.getProperty("MONGODB_USER");
        dbPassword = System.getProperty("MONGODB_PASSWORD");
    }

    public MongoClient getInstance() {
       return MongoClient.createShared(vertx, new JsonObject()
                .put("connection_string", "mongodb://" + (databaseHost != null ? databaseHost : "localhost") + ":" + (databasePort != null ? databasePort : 27017))
                .put("db_name", (dbName != null ? dbName : "MOVIE_DB"))
                .put("user", dbUserName)
                .put("password", dbPassword));
    }
}
