package moe.leer.tomato.mvc;

/**
 * @author leer
 * Created at 7/1/19 7:39 PM
 * <p>
 * Request handler for function style URL mapping
 */
@FunctionalInterface
public interface Handler {
  void handle(final RequestContext context);
}
