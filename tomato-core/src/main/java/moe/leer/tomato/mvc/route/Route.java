package moe.leer.tomato.mvc.route;

import moe.leer.tomato.mvc.Handler;
import moe.leer.tomato.utils.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leer
 * Created at 7/3/19 4:08 PM
 * <p>
 * Mapping HTTP Request to Handler(Controller)
 * <p>
 * The URL must start with "/", but a "/" at the end is ok, because it will be remove
 */
public class Route {

//  private String prefixURL;
//  private Route sourceRoute;

  private Map<RequestInfo, Handler> requestHandlerMap;

  public Route() {
    this.requestHandlerMap = new ConcurrentHashMap<>();
  }

//  public Route(String prefixURL, Route sourceRoute) {
//    this.prefixURL = prefixURL;
//    this.sourceRoute = sourceRoute;
//  }

  public void mapping(HttpMethod httpMethod, String path, Handler handler) {
    requestHandlerMap.put(new RequestInfo(httpMethod, path), handler);
  }

  public Handler getMatchedHandler(HttpMethod httpMethod, String path) {
    return requestHandlerMap.get(new RequestInfo(httpMethod, path));
  }

  /**
   * For grouped URL mapping
   */
  public static class GroupRoute extends Route {
    private Route route;
    private String prefixURL;

    public GroupRoute(Route route, String prefixURL) {
      this.route = route;
      this.prefixURL = prefixURL;
    }

    @Override
    public GroupRoute group(String prefixURL) {
      prefixURL = URLCheck(prefixURL);
      // pass raw route and append prefix
      return new GroupRoute(this.route, this.prefixURL + prefixURL);
    }

    @Override
    public Route get(String URL, Handler handler) {
      return httpMethodTemplate(HttpMethod.GET, URL, handler);
    }

    @Override
    public Route post(String URL, Handler handler) {
      return httpMethodTemplate(HttpMethod.POST, URL, handler);
    }

    @Override
    public Route put(String URL, Handler handler) {
      return httpMethodTemplate(HttpMethod.PUT, URL, handler);
    }

    @Override
    public Route delete(String URL, Handler handler) {
      return httpMethodTemplate(HttpMethod.DELETE, URL, handler);
    }

    @Override
    public Route patch(String URL, Handler handler) {
      return httpMethodTemplate(HttpMethod.PATCH, URL, handler);
    }

    @Override
    public Route options(String URL, Handler handler) {
      return httpMethodTemplate(HttpMethod.OPTIONS, URL, handler);
    }

    @Override
    public Route head(String URL, Handler handler) {
      return httpMethodTemplate(HttpMethod.HEAD, URL, handler);
    }

    @Override
    public Route trace(String URL, Handler handler) {
      return httpMethodTemplate(HttpMethod.TRACE, URL, handler);
    }

    /**
     * Method template for convenience.
     * THe prefixURL must start with "/"
     *
     * @param URL must NOT end with "/"
     */
    protected Route httpMethodTemplate(HttpMethod httpMethod, String URL, Handler handler) {
      URL = URLCheck(URL);
      route.mapping(httpMethod, this.prefixURL + URL, handler);
      return this;
    }
  }

  /**
   * @param prefixURL should start with "/", but the "/" at the end is ok, because it will be remove
   * @return grouped route with prefix
   */
  public GroupRoute group(String prefixURL) {
    prefixURL = URLCheck(prefixURL);
    return new GroupRoute(this, prefixURL);
  }

  public Route get(String URL, final Handler handler) {
    return httpMethodTemplate(HttpMethod.GET, URL, handler);
  }

  public Route post(String URL, final Handler handler) {
    return httpMethodTemplate(HttpMethod.POST, URL, handler);
  }

  public Route put(String URL, final Handler handler) {
    return httpMethodTemplate(HttpMethod.PUT, URL, handler);
  }

  public Route delete(String URL, final Handler handler) {
    return httpMethodTemplate(HttpMethod.DELETE, URL, handler);
  }

  public Route patch(String URL, final Handler handler) {
    return httpMethodTemplate(HttpMethod.PATCH, URL, handler);
  }

  public Route options(String URL, final Handler handler) {
    return httpMethodTemplate(HttpMethod.OPTIONS, URL, handler);
  }

  public Route head(String URL, final Handler handler) {
    return httpMethodTemplate(HttpMethod.HEAD, URL, handler);
  }

  public Route trace(String URL, final Handler handler) {
    return httpMethodTemplate(HttpMethod.TRACE, URL, handler);
  }

  protected Route httpMethodTemplate(HttpMethod httpMethod, String URL, final Handler handler) {
    URL = URLCheck(URL);
    this.mapping(httpMethod, URL, handler);
    return this;
  }

  /**
   * Make sure a prefix "/", remove suffix "/".
   */
  protected String URLCheck(String URL) {
    if (!StringUtil.checkPrefix(URL, "/")) {
      throw new IllegalArgumentException("URL should start with \"/\"");
    }
    URL = StringUtil.removeSuffix(URL, "/");
    return URL;
  }
}
