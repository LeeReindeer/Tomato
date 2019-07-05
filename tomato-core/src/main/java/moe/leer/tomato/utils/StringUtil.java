package moe.leer.tomato.utils;

/**
 * @author leer
 * Created at 7/4/19 3:16 PM
 */
public class StringUtil {
  /**
   * Return source string deleted suffix, if suffix not exits return origin string
   *
   * @param source
   * @param suffix
   * @return
   */
  public static String removeSuffix(String source, String suffix) {
    if (suffix.length() > source.length()) return source;
    if (source.endsWith(suffix)) {
      source = source.substring(0, source.length() - suffix.length());
      return source;
    }
    return source;
  }

  public static boolean checkPrefix(String source, String prefix) {
    if (prefix.length() > source.length()) return false;
    return source.startsWith(prefix);
  }
}
