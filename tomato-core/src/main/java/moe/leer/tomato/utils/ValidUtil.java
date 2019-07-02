package moe.leer.tomato.utils;

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
}
