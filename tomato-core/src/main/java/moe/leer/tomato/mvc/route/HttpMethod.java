package moe.leer.tomato.mvc.route;

/**
 * @author leer
 * Created at 7/3/19 4:16 PM
 * Indicates HTPP methods
 */
public enum HttpMethod {
  GET,
  POST,
  PUT,
  PATCH,
  DELETE,
  HEAD,
  OPTIONS,
  TRACE;

  public static HttpMethod getMethod(String name) {
    switch (name) {
      case "GET":
        return GET;
      case "POST":
        return POST;
      case "PUT":
        return PUT;
      case "PATCH":
        return PATCH;
      case "DELETE":
      return DELETE;
      case "HEAD":
        return HEAD;
      case "OPTIONS":
        return OPTIONS;
      case "TRACE":
        return TRACE;
    }
    throw new RuntimeException("HTTP method " + name + " not supported");
  }

  public String getName() {
    return this.name();
  }
}
