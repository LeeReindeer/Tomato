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

  public static class GroupRoute extends Route {
    private Route route;
    private String prefixURL;

    public GroupRoute(Route route, String prefixURL) {
      this.route = route;
      this.prefixURL = prefixURL;
    }

    @Override
    public GroupRoute group(String prefixURL) {
      // pass raw route and append prefix
      return new GroupRoute(this.route, this.prefixURL + prefixURL);
    }

    @Override
    public Route get(String URL, Handler handler) {
      route.get(prefixURL + URL, handler);
      return this;
    }

    @Override
    public Route post(String URL, Handler handler) {
      route.post(prefixURL + URL, handler);
      return this;
    }

    @Override
    public Route put(String URL, Handler handler) {
      route.put(prefixURL + URL, handler);
      return this;
    }

    @Override
    public Route delete(String URL, Handler handler) {
      route.delete(URL, handler);
      return this;
    }

    @Override
    public Route patch(String URL, Handler handler) {
      route.patch(URL, handler);
      return this;
    }

    @Override
    public Route options(String URL, Handler handler) {
      route.options(URL, handler);
      return this;
    }

    @Override
    public Route head(String URL, Handler handler) {
      route.head(URL, handler);
      return this;
    }

    @Override
    public Route trace(String URL, Handler handler) {
      route.trace(URL, handler);
      return this;
    }
  }

  /**
   * @param prefixURL should start with '/'
   * @return grouped route with prefix
   */
  public GroupRoute group(String prefixURL) {
    // remove '/' in the end
    StringUtil.removeSuffix(prefixURL, "/");
    return new GroupRoute(this, prefixURL);
  }

  public Route get(String URL, final Handler handler) {
    mapping(HttpMethod.GET, URL, handler);
    return this;
  }

  public Route post(String URL, final Handler handler) {
    mapping(HttpMethod.POST, URL, handler);
    return this;
  }

  public Route put(String URL, final Handler handler) {
    this.mapping(HttpMethod.PUT, URL, handler);
    return this;
  }

  public Route delete(String URL, final Handler handler) {
    this.mapping(HttpMethod.DELETE, URL, handler);
    return this;
  }

  public Route patch(String URL, final Handler handler) {
    this.mapping(HttpMethod.PATCH, URL, handler);
    return this;
  }

  public Route options(String URL, final Handler handler) {
    this.mapping(HttpMethod.OPTIONS, URL, handler);
    return this;
  }

  public Route head(String URL, final Handler handler) {
    this.mapping(HttpMethod.HEAD, URL, handler);
    return this;
  }

  public Route trace(String URL, final Handler handler) {
    this.mapping(HttpMethod.TRACE, URL, handler);
    return this;
  }
}
