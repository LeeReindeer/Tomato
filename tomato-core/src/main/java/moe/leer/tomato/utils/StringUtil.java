package moe.leer.tomato.utils;

/**
 * @author leer
 * Created at 7/4/19 3:16 PM
 */
public class StringUtil {
  public static boolean removeSuffix(String source, String suffix) {
    if (suffix.length() > source.length()) return false;
    if (source.endsWith(suffix)) {
      source = source.substring(0, source.length() - suffix.length());
      return true;
    }
    return false;
  }
}
