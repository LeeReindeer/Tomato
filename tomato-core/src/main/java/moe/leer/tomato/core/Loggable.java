package moe.leer.tomato.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leer
 * Created at 7/1/19 8:59 PM
 * <p>
 * Class implement Loggable will have a default {@code logger()} to use.
 */
public interface Loggable {
  default Logger logger() {
    return LoggerFactory.getLogger(this.getClass());
  }
}
