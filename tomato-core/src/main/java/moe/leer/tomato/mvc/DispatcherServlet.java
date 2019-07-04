package moe.leer.tomato.mvc;

import moe.leer.tomato.Tomato;
import moe.leer.tomato.core.Loggable;
import moe.leer.tomato.mvc.route.HttpMethod;
import moe.leer.tomato.mvc.route.Route;
import moe.leer.tomato.utils.ValidUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leer
 * Created at 7/2/19 5:25 PM
 * <p>
 * Dispatch HTTP request to Handlers
 */
public class DispatcherServlet extends HttpServlet implements Loggable {
  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    // construct RequestContext
    HttpMethod requestMethod = HttpMethod.getMethod(request.getMethod());
    String requestPath = request.getPathInfo();
    if (requestPath.endsWith("/")) {
      requestPath = requestPath.substring(0, requestPath.length() - 1);
    }
    Map<String, String> rawQueryParams = getQueryParams(request);
    QueryParams queryParams = new QueryParams(rawQueryParams);
    PathParams pathParams = null;
    logger().debug("query params: {}", queryParams.toString());
    Route route = Tomato.ROUTE;
    Handler handler = route.getMatchedHandler(requestMethod, requestPath);
    RequestContext requestContext = new RequestContext(request, response, pathParams, queryParams, handler);
    if (handler == null) {
      //todo 404 handler
      logger().info("404 | {} {}", requestMethod.name(), requestPath);
    } else {
      handler.handle(requestContext);
      logger().info("200 | {} {}", requestMethod.name(), requestPath);
    }
  }

  protected Map<String, String> getQueryParams(HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    //GET和POST方法是这样获取请求参数的
    request.getParameterMap().forEach((paramName, paramsValues) -> {
      if (ValidUtil.isNotEmpty(paramsValues)) {
        paramMap.put(paramName, paramsValues[0]);
      }
    });
    // TODO: Body、Path、Header's param
    return paramMap;
  }
}
