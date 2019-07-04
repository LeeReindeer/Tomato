package moe.leer.tomato.starter;

/**
 * @author leer
 * Created at 7/3/19 6:50 PM
 */
public class Configuration {
  /**
   * Boot Class
   */
  private Class<?> bootClass;

  private String resourcePath = "src/main/resources/";

  private String assetPath = "/static/";

  private int serverPort = 8080;

  private String docBase = "";

  private String contextPath = "";

  public Class<?> getBootClass() {
    return bootClass;
  }

  public String getAssetPath() {
    return assetPath;
  }

  public String getResourcePath() {
    return resourcePath;
  }

  public int getServerPort() {
    return serverPort;
  }

  public String getDocBase() {
    return docBase;
  }

  public String getContextPath() {
    return contextPath;
  }

  public void setBootClass(Class<?> bootClass) {
    this.bootClass = bootClass;
  }

  public void setResourcePath(String resourcePath) {
    this.resourcePath = resourcePath;
  }

  public void setAssetPath(String assetPath) {
    this.assetPath = assetPath;
  }

  public void setServerPort(int serverPort) {
    this.serverPort = serverPort;
  }

  public void setDocBase(String docBase) {
    this.docBase = docBase;
  }

  public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
  }
}
