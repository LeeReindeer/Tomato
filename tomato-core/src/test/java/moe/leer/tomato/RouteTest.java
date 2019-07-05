package moe.leer.tomato;

import moe.leer.tomato.mvc.route.HttpMethod;
import moe.leer.tomato.mvc.route.Route;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leer
 * Created at 7/4/19 3:43 PM
 */
public class RouteTest {

  @Test
  public void testGroupRoute() {
    Route route = new Route();
    // api/ping
    // api/test
    // api/user/name
    // api/user/id
    route.group("/api")
        .get("/ping", context -> {
        })
        .get("/test", context -> {
        })
        .group("/user")
        .get("/name", context -> {
        })
        .get("/id", context -> {
        });
    Assert.assertNotNull(route.getMatchedHandler(HttpMethod.GET, "/api/ping"));
    Assert.assertNotNull(route.getMatchedHandler(HttpMethod.GET, "/api/test"));
    Assert.assertNotNull(route.getMatchedHandler(HttpMethod.GET, "/api/user/name"));
    Assert.assertNotNull(route.getMatchedHandler(HttpMethod.GET, "/api/user/id"));
  }
}
