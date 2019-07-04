package moe.leer.tomato.mvc.route;

import java.util.Objects;

/**
 * @author leer
 * Created at 7/3/19 4:11 PM
 *
 * ReeustInfo contains HttpMethod and request path
 */
public class RequestInfo {

  public RequestInfo(HttpMethod httpMethod, String pathPattern) {
    this.httpMethod = httpMethod;
    this.pathPattern = pathPattern;
  }

  private HttpMethod httpMethod;

  private String pathPattern;

  public String getPathPattern() {
    return pathPattern;
  }

  public HttpMethod getHttpMethod() {
    return httpMethod;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RequestInfo)) return false;
    RequestInfo that = (RequestInfo) o;
    return httpMethod == that.httpMethod &&
        pathPattern.equals(that.pathPattern);
  }

  @Override
  public int hashCode() {
    return Objects.hash(httpMethod, pathPattern);
  }
}
