package moe.leer.tomato.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author leer
 * Created at 7/1/19 8:36 PM
 * <p>
 * Util for class and reflection
 */
public class ClassUtil {
  public static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

  public static ClassLoader getClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  @SuppressWarnings("unchecked")
  public static <T> Class<T> loadClass(String className) {
    try {
      return (Class<T>) Class.forName(className, true, getClassLoader());
    } catch (ClassNotFoundException e) {
      logger.error("class {} not find", e.getMessage());
      throw new RuntimeException();
    }
  }

  /**
   * @return a classes Set contains all class in the {@code packageName}
   */
  public static Set<Class<?>> getPackageClassSet(String packageName) {
    URL url = getClassLoader().getResource(packageName.replace(".", "/"));
    if (null == url) {
      throw new RuntimeException("unable to get class");
    }
    try {
      // get class name from a file path
      if (url.getProtocol().equalsIgnoreCase("file")) {
        File file = new File(url.getFile());
        Path basePath = file.toPath();
        return Files.walk(basePath)
            .filter(path -> path.toFile().getName().endsWith(".class"))
            .map(path -> {
              String remainName = path.toString().replace(basePath.toString(), "");
              String className = (packageName + remainName)
                  .replace("/", ".")
                  .replace("\\", ".")
                  .replace(".class", "");
              // remove '.' from class name
              className = className.charAt(0) == '.' ? className.substring(1) : className;
              return loadClass(className);
            })
            .collect(Collectors.toSet());
      } else if (url.getProtocol().equalsIgnoreCase("jar")) {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        return jarURLConnection.getJarFile()
            .stream()
            .filter(jarEntry -> jarEntry.getName().endsWith(".class"))
            .map(jarEntry -> {
              String jarEntryName = jarEntry.getName();
              String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
              return loadClass(className);
            })
            .collect(Collectors.toSet());
      }
      return Collections.emptySet();
    } catch (IOException e) {
      logger.error("load package error", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * @return a new instance from class name
   */
  public static <T> T newInstance(String className) {
    try {
      Class<T> clazz = loadClass(className);
      //fixme use Constructor#newInstance instead
      return clazz.newInstance();
    } catch (Exception e) {
      logger.error("newInstance error", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * @return a instance from class
   */
  public static <T> T newInstance(Class<T> clazz) {
    try {
      return clazz.newInstance();
    } catch (Exception e) {
      logger.error("newInstance error", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Set {@code target}'s {@code field} as {@code value}, the private field is accessible.
   */
  public static void setField(Field field, Object target, Object value) {
    setField(field, target, value, true);
  }

  public static void setField(Field field, Object target, Object value, boolean accessible) {
    field.setAccessible(accessible);
    try {
      field.set(target, value);
    } catch (IllegalAccessException e) {
      logger.error("setField error", e);
      throw new RuntimeException(e);
    }
  }
}
