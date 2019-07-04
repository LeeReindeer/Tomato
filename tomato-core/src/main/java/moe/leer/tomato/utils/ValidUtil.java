package moe.leer.tomato.utils;

import java.util.Objects;

/**
 * @author leer
 * Created at 7/1/19 10:25 PM
 */
public class ValidUtil {
  /**
   * @return null if {@code obj1} is null, else return {@code obj2}
   */
  public static <T> T returnNullElse(Object obj1, T obj2) {
    if (obj1 == null) return null;
    return obj2;
  }

  public static boolean isNotEmpty(String string) {
    return string != null && !string.isEmpty();
  }

  public static boolean isNotEmpty(Object[] objects) {
    return !isEmptyOrNull(objects);
  }

  public static boolean isEmptyOrNull(Object[] objects) {
    return objects == null || objects.length == 0;
  }


  public static boolean isNull(Object object) {
    return Objects.isNull(object);
  }

  public static boolean isEmptyOrNull(String string) {
    return string == null || string.isEmpty();
  }
}
