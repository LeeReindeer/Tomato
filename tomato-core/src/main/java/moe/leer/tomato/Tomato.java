package moe.leer.tomato;

import moe.leer.tomato.core.IOC;
import moe.leer.tomato.core.TomatoContainer;
import moe.leer.tomato.mvc.route.Route;
import moe.leer.tomato.starter.Configuration;
import moe.leer.tomato.starter.Server;
import moe.leer.tomato.starter.TomcatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leer
 * Created at 7/1/19 7:23 PM
 */
public class Tomato {

  private static Logger logger = LoggerFactory.getLogger(Tomato.class);

  public static final Route ROUTE = new Route();
  private static final Tomato TOMATO = new Tomato();
  public static final TomatoContainer CONTAINER = TomatoContainer.CONTAINER;

  public static final Configuration DEFAULT_CONFIGURATION = new Configuration();
  private static Configuration configuration;


  private static boolean initialed = false;

  private static Server server;

  private Tomato() {

  }

  public static <T> T provide(Class<T> clz) {
    return CONTAINER.getTomato(clz);
  }

  public static Configuration getConfiguration() {
    return configuration;
  }

  public static Server getServer() {
    return server;
  }


  public static void run() {
    if (!initialed) {
      logger.error("Tomato not initialed");
    } else {
      TOMATO.start(Tomato.configuration);
    }
  }

  public static void run(Class<?> bootClazz) {
    run(bootClazz, DEFAULT_CONFIGURATION.getServerPort());
  }

  public static void run(Class<?> bootClazz, int port) {
    DEFAULT_CONFIGURATION.setBootClass(bootClazz);
    run(DEFAULT_CONFIGURATION);
  }

  public static void run(Configuration configuration) {
    Tomato.configuration = configuration;
    TOMATO.start(configuration);
  }

  public static void init(Configuration configuration) {
    if (initialed) {
      logger.info("Tomato already initialed");
      return;
    }
    try {
      Tomato.configuration = configuration;
      configuration.setBootClass(configuration.getBootClass());
      String basePackage = configuration.getBootClass().getPackage().getName();
      TomatoContainer.CONTAINER.loadTomatoes(basePackage);
      //todo AOP
      new IOC().ioc();
    } catch (Exception e) {
      logger.error("Tomato failed to start", e);
    }

    initialed = true;
  }

  public static void init(Class<?> bootClass) {
    DEFAULT_CONFIGURATION.setBootClass(bootClass);
    init(DEFAULT_CONFIGURATION);
  }

  /**
   * Start tomcat serve with specific configuration
   * Your boot class should put at the top level of package in order to scan all tomatoes in the package.
   */
  private void start(Configuration configuration) {
    try {
      init(configuration);
      server = new TomcatServer(configuration);
      server.startServer();
    } catch (Exception e) {
      logger.error("Tomcat server failed to start", e);
    }
  }
}
