package moe.leer.tomato.cycle;

import moe.leer.tomato.core.annotation.AutoWired;
import moe.leer.tomato.core.annotation.Controller;
import moe.leer.tomato.mvc.RequestContext;

/**
 * @author leer
 * Created at 7/2/19 12:57 AM
 */
@Controller
public class TestController {

  @AutoWired
  private TestService testService;

  public void pingHandler(final RequestContext context) {
    context.text(testService.hello());
  }
}
