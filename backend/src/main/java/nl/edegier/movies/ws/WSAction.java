package nl.edegier.movies.ws;

/**
 * Created by Erwin on 15/09/2017.
 */
public class WSAction {
  public String action;
  public String body;

  public static final String SEARCH_ACTION = "search";
  public static final String GET_ACTION = "get";

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getAction() {
    return action.toString();
  }

  public void setAction(String action) {
    this.action = action;
  }

  public boolean isSearch(){
    return SEARCH_ACTION.equalsIgnoreCase(this.action);
  }

  public boolean isGet() {
    return GET_ACTION.equalsIgnoreCase(this.action);
  }
}
