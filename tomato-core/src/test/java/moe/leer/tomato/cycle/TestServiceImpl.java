package moe.leer.tomato.cycle;

import moe.leer.tomato.core.Loggable;
import moe.leer.tomato.core.annotation.AutoWired;
import moe.leer.tomato.core.annotation.Service;

/**
 * @author leer
 * Created at 7/2/19 12:56 AM
 */
@Service
public class TestServiceImpl implements TestService, Loggable {

  @AutoWired
  private CycleService cycleService;

  @Override
  public String hello() {
    if (cycleService != null) {
      logger().debug("Cycle injection succeed");
      cycleService.testCycle();
    }
    return "hello IOC";
  }
}
