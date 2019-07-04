package moe.leer.tomato.mvc.render;

import moe.leer.tomato.utils.ValidUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author leer
 * Created at 7/2/19 8:19 PM
 * <p>
 * Implement text render, render string
 */
public class TextRender extends AbstractRender {
  @Override
  public void render(HttpServletRequest request, HttpServletResponse response, Object result) throws IOException {
    if (ValidUtil.isNull(request) || !(result instanceof String)) return;
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    try (PrintWriter writer = response.getWriter()) {
      writer.write((String) result);
      writer.flush();
    }
  }
}
