package nl.edegier.movies;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erwin on 12/04/2017.
 */
public class MongoClientMock implements MongoClient {

  List<JsonObject> movies = new ArrayList<>();

  public MongoClientMock() {
    movies.add(new JsonObject());

  }

  public io.vertx.rxjava.ext.mongo.MongoClient getRxClient() {
    return new io.vertx.rxjava.ext.mongo.MongoClient(this);
  }


  @Override
  public MongoClient save(String s, JsonObject jsonObject, Handler<AsyncResult<String>> handler) {
    handler.handle(Future.succeededFuture(""));
    return this;
  }

  @Override
  public MongoClient saveWithOptions(String s, JsonObject jsonObject, WriteOption writeOption, Handler<AsyncResult<String>> handler) {
    handler.handle(Future.succeededFuture(""));
    return this;
  }

  @Override
  public MongoClient insert(String s, JsonObject jsonObject, Handler<AsyncResult<String>> handler) {
    handler.handle(Future.succeededFuture(""));
    return this;
  }

  @Override
  public MongoClient insertWithOptions(String s, JsonObject jsonObject, WriteOption writeOption, Handler<AsyncResult<String>> handler) {
    handler.handle(Future.succeededFuture(""));
    return this;
  }

  @Override
  public MongoClient update(String s, JsonObject jsonObject, JsonObject jsonObject1, Handler<AsyncResult<Void>> handler) {
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public MongoClient updateCollection(String s, JsonObject jsonObject, JsonObject jsonObject1, Handler<AsyncResult<MongoClientUpdateResult>> handler) {
    return null;
  }

  @Override
  public MongoClient updateWithOptions(String s, JsonObject jsonObject, JsonObject jsonObject1, UpdateOptions updateOptions, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient updateCollectionWithOptions(String s, JsonObject jsonObject, JsonObject jsonObject1, UpdateOptions updateOptions, Handler<AsyncResult<MongoClientUpdateResult>> handler) {
    return null;
  }

  @Override
  public MongoClient replace(String s, JsonObject jsonObject, JsonObject jsonObject1, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient replaceDocuments(String s, JsonObject jsonObject, JsonObject jsonObject1, Handler<AsyncResult<MongoClientUpdateResult>> handler) {
    return null;
  }

  @Override
  public MongoClient replaceWithOptions(String s, JsonObject jsonObject, JsonObject jsonObject1, UpdateOptions updateOptions, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient replaceDocumentsWithOptions(String s, JsonObject jsonObject, JsonObject jsonObject1, UpdateOptions updateOptions, Handler<AsyncResult<MongoClientUpdateResult>> handler) {
    return null;
  }

  @Override
  public MongoClient bulkWrite(String s, List<BulkOperation> list, Handler<AsyncResult<MongoClientBulkWriteResult>> handler) {
    return null;
  }

  @Override
  public MongoClient bulkWriteWithOptions(String s, List<BulkOperation> list, BulkWriteOptions bulkWriteOptions, Handler<AsyncResult<MongoClientBulkWriteResult>> handler) {
    return null;
  }

  @Override
  public MongoClient find(String s, JsonObject jsonObject, Handler<AsyncResult<List<JsonObject>>> handler) {
    handler.handle(Future.succeededFuture(new ArrayList<>()));
    return this;
  }

  @Override
  public MongoClient findBatch(String s, JsonObject jsonObject, Handler<AsyncResult<JsonObject>> handler) {
    handler.handle(Future.succeededFuture(new JsonObject()));
    return this;
  }

  @Override
  public MongoClient findWithOptions(String s, JsonObject jsonObject, FindOptions findOptions, Handler<AsyncResult<List<JsonObject>>> handler) {
    handler.handle(Future.succeededFuture(new ArrayList<>()));
    return this;
  }

  @Override
  public MongoClient findBatchWithOptions(String s, JsonObject jsonObject, FindOptions findOptions, Handler<AsyncResult<JsonObject>> handler) {
    handler.handle(Future.succeededFuture(new JsonObject()));
    return this;
  }

  @Override
  public MongoClient findOne(String s, JsonObject jsonObject, JsonObject jsonObject1, Handler<AsyncResult<JsonObject>> handler) {
    handler.handle(Future.succeededFuture(null));
    return this;
  }

  @Override
  public MongoClient findOneAndUpdate(String s, JsonObject jsonObject, JsonObject jsonObject1, Handler<AsyncResult<JsonObject>> handler) {
    return null;
  }

  @Override
  public MongoClient findOneAndUpdateWithOptions(String s, JsonObject jsonObject, JsonObject jsonObject1, FindOptions findOptions, UpdateOptions updateOptions, Handler<AsyncResult<JsonObject>> handler) {
    return null;
  }

  @Override
  public MongoClient findOneAndReplace(String s, JsonObject jsonObject, JsonObject jsonObject1, Handler<AsyncResult<JsonObject>> handler) {
    return null;
  }

  @Override
  public MongoClient findOneAndReplaceWithOptions(String s, JsonObject jsonObject, JsonObject jsonObject1, FindOptions findOptions, UpdateOptions updateOptions, Handler<AsyncResult<JsonObject>> handler) {
    return null;
  }

  @Override
  public MongoClient findOneAndDelete(String s, JsonObject jsonObject, Handler<AsyncResult<JsonObject>> handler) {
    return null;
  }

  @Override
  public MongoClient findOneAndDeleteWithOptions(String s, JsonObject jsonObject, FindOptions findOptions, Handler<AsyncResult<JsonObject>> handler) {
    return null;
  }

  @Override
  public MongoClient count(String s, JsonObject jsonObject, Handler<AsyncResult<Long>> handler) {
    return null;
  }

  @Override
  public MongoClient remove(String s, JsonObject jsonObject, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient removeDocuments(String s, JsonObject jsonObject, Handler<AsyncResult<MongoClientDeleteResult>> handler) {
    return null;
  }

  @Override
  public MongoClient removeWithOptions(String s, JsonObject jsonObject, WriteOption writeOption, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient removeDocumentsWithOptions(String s, JsonObject jsonObject, WriteOption writeOption, Handler<AsyncResult<MongoClientDeleteResult>> handler) {
    return null;
  }

  @Override
  public MongoClient removeOne(String s, JsonObject jsonObject, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient removeDocument(String s, JsonObject jsonObject, Handler<AsyncResult<MongoClientDeleteResult>> handler) {
    return null;
  }

  @Override
  public MongoClient removeOneWithOptions(String s, JsonObject jsonObject, WriteOption writeOption, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient removeDocumentWithOptions(String s, JsonObject jsonObject, WriteOption writeOption, Handler<AsyncResult<MongoClientDeleteResult>> handler) {
    return null;
  }

  @Override
  public MongoClient createCollection(String s, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient getCollections(Handler<AsyncResult<List<String>>> handler) {
    return null;
  }

  @Override
  public MongoClient dropCollection(String s, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient createIndex(String s, JsonObject jsonObject, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient createIndexWithOptions(String s, JsonObject jsonObject, IndexOptions indexOptions, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient listIndexes(String s, Handler<AsyncResult<JsonArray>> handler) {
    return null;
  }

  @Override
  public MongoClient dropIndex(String s, String s1, Handler<AsyncResult<Void>> handler) {
    return null;
  }

  @Override
  public MongoClient runCommand(String s, JsonObject jsonObject, Handler<AsyncResult<JsonObject>> handler) {
    return null;
  }

  @Override
  public MongoClient distinct(String s, String s1, String s2, Handler<AsyncResult<JsonArray>> handler) {
    return null;
  }

  @Override
  public MongoClient distinctBatch(String s, String s1, String s2, Handler<AsyncResult<JsonObject>> handler) {
    return null;
  }

  @Override
  public void close() {

  }

}
