package moe.leer.tomato.mvc.render;

import com.google.gson.Gson;
import moe.leer.tomato.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author leer
 * Created at 7/14/19 11:03 AM
 * <p>
 * Render POJO to JSON
 */
public class JSONRender extends AbstractRender {

  // whether escape non-ASCII characters.
  private boolean ascii;

  public JSONRender() {
  }

  public JSONRender(boolean ascii) {
    this.ascii = ascii;
  }

  @Override
  public void render(HttpServletRequest request, HttpServletResponse response, Object result) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    Gson gson = new Gson();
    String json = gson.toJson(result);
    if (ascii) {
      json = StringUtil.escapeNonAscii(json);
    }
    try (PrintWriter writer = response.getWriter()) {
      writer.write(json);
      writer.flush();
    }
  }

  public boolean isAscii() {
    return ascii;
  }

  public void setAscii(boolean ascii) {
    this.ascii = ascii;
  }
}
