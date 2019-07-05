package moe.leer.tomato;

import moe.leer.tomato.core.IOC;
import moe.leer.tomato.core.TomatoContainer;
import moe.leer.tomato.cycle.TestController;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leer
 * Created at 7/2/19 12:54 AM
 */
public class IOCTest {

  /**
   * Test Ioc and handle with cycle dependency
   */
  @Test
  public void testIOC() {
    TomatoContainer tomatoContainer = TomatoContainer.CONTAINER;
    tomatoContainer.loadTomatoes("moe.leer.tomato");
    new IOC().ioc();
    TestController controller = tomatoContainer.getTomato(TestController.class);
    Assert.assertNotNull(controller);
  }
}
