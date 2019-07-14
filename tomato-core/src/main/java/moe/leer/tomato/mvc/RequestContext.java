package moe.leer.tomato.mvc;

import moe.leer.tomato.mvc.render.JSONRender;
import moe.leer.tomato.mvc.render.TextRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author leer
 * Created at 7/1/19 7:40 PM
 */
public class RequestContext {

  private Handler handler;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private PathParams pathParams;
  private QueryParams queryParams;

  public RequestContext() {
  }

  public RequestContext(HttpServletRequest request, HttpServletResponse response, PathParams pathParams, QueryParams queryParams, Handler handler) {
    this.request = request;
    this.response = response;
    this.pathParams = pathParams;
    this.queryParams = queryParams;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public HttpServletResponse getResponse() {
    return response;
  }

  public PathParams pathParams() {
    return pathParams;
  }

  public QueryParams queryParams() {
    return queryParams;
  }

  // Render JSON
  public void JSON(Object object) {
    try {
      new JSONRender().render(request, response, object);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Render ASCII-only JSON with escaped non-ASCII chracters.
  public void asciiJSON(Object object) {
    try {
      new JSONRender(true).render(request, response, object);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  // Render text
  public void text(String string) {
    try {
      new TextRender().render(request, response, string);
    } catch (IOException e) {
      // todo handle exception
      e.printStackTrace();
    }
  }
}
