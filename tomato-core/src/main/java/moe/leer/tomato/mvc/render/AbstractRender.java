package moe.leer.tomato.mvc.render;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author leer
 * Created at 7/2/19 8:19 PM
 */
public abstract class AbstractRender {
  public abstract void render(HttpServletRequest request, HttpServletResponse response, Object result) throws IOException;
}
