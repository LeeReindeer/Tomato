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


  //https://stackoverflow.com/questions/5733931/java-string-unicode-value
  public static String escapeNonAscii(String str) {
    StringBuilder retStr = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      int cp = Character.codePointAt(str, i);
      int charCount = Character.charCount(cp);
      if (charCount > 1) {
        i += charCount - 1; // 2.
        if (i >= str.length()) {
          throw new IllegalArgumentException("truncated unexpectedly");
        }
      }

      if (cp < 128) {
        retStr.appendCodePoint(cp);
      } else {
        retStr.append(String.format("\\u%x", cp));
      }
    }
    return retStr.toString();
  }
}
