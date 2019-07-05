package moe.leer.tomato.cycle;

import moe.leer.tomato.core.annotation.ALogger;
import moe.leer.tomato.core.annotation.AutoWired;
import moe.leer.tomato.core.annotation.Service;
import org.slf4j.Logger;

/**
 * @author leer
 * Created at 7/2/19 2:59 PM
 */
@Service
public class CycleService {

  @ALogger
  private Logger logger;

  @AutoWired
  private TestService testService;

  public void testCycle() {
    if (testService != null) {
      logger.debug("Cycle injection succeed");
    }
  }

}
