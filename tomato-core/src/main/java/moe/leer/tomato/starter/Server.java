package moe.leer.tomato.starter;

/**
 * @author leer
 * Created at 7/3/19 6:53 PM
 */
public interface Server {
  /**
   * Start embed server
   */
  void startServer() throws Exception;

  /**
   * Stop embed srever
   */
  void stopServer() throws Exception;
}
