package moe.leer.tomato.starter;

import moe.leer.tomato.core.Loggable;
import moe.leer.tomato.mvc.DispatcherServlet;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;

/**
 * @author leer
 * Created at 7/3/19 6:54 PM
 * <p>
 * TomcatServer using {@code Configuration}, init the base dir and register the {@code DispatcherServlet}.
 */
public class TomcatServer implements Server, Loggable {

  private Tomcat tomcat;

  public TomcatServer(Configuration configuration) {
    try {
      this.tomcat = new Tomcat();
      tomcat.setBaseDir(configuration.getDocBase());
      tomcat.setPort(configuration.getServerPort());

      File root = getRootFolder();
      File webContentFolder = new File(root.getAbsolutePath(), configuration.getResourcePath());
      if (!webContentFolder.exists()) {
        webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
      }
      logger().info("Tomcat:configuring app with basedir: [{}]", webContentFolder.getAbsolutePath());
      StandardContext ctx = (StandardContext) tomcat.addWebapp(configuration.getContextPath(), webContentFolder.getAbsolutePath());
      logger().info("getAbsolutePath: {}", webContentFolder.getAbsolutePath());
      ctx.setParentClassLoader(this.getClass().getClassLoader());
      WebResourceRoot resources = new StandardRoot(ctx);
      ctx.setResources(resources);
//      tomcat.addServlet("", "jspServlet", new JspServlet()).setLoadOnStartup(3);
      tomcat.addServlet("", "defaultServlet", new DefaultServlet()).setLoadOnStartup(1);
      tomcat.addServlet("", "dispatcherServlet", new DispatcherServlet()).setLoadOnStartup(0);
//      ctx.addServletMappingDecoded("/templates/" + "*", "jspServlet");
      ctx.addServletMappingDecoded("/static/" + "*", "defaultServlet");
      ctx.addServletMappingDecoded("/*", "dispatcherServlet");
      ctx.addServletMappingDecoded("/*", "dispatcherServlet");
    } catch (Exception e) {
      logger().error("Tomcat init failed");
      throw new RuntimeException(e);
    }
  }

  @Override
  public void startServer() throws Exception {
    tomcat.start();
    String address = tomcat.getServer().getAddress();
    int port = tomcat.getConnector().getPort();
    logger().info("Listening and serving HTTP on {}:{}", address, port);
    tomcat.getServer().await();
  }

  @Override
  public void stopServer() throws Exception {
    tomcat.stop();
  }

  /**
   * @return root folder of the jar
   */
  private File getRootFolder() {
    try {
      File root;
      String runningJarPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
      int lastIndexOf = runningJarPath.lastIndexOf("/target/");
      if (lastIndexOf < 0) {
        root = new File("");
      } else {
        root = new File(runningJarPath.substring(0, lastIndexOf));
      }
      logger().info("Tomcat:application resolved root folder: [{}]", root.getAbsolutePath());
      return root;
    } catch (URISyntaxException ex) {
      throw new RuntimeException(ex);
    }
  }
}
