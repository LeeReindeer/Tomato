package moe.leer.tomato.utils;

/**
 * @author leer
 * Created at 7/2/19 5:08 PM
 */
public class CastUtil {

  /**
   * Convert string value to only primitive Objects(Integer, Long, Double, Float, Boolean, Short, Byte, String)
   * If the string is null or empty, it will return zero values.
   */
  @SuppressWarnings("unchecked")
  public static <T> T convert(Class<T> type, String value) {
    if (isPrimitive(type)) {
      if (ValidUtil.isEmptyOrNull(value)) {
        return (T) primitiveZero(type);
      }
      if (type.equals(int.class) || type.equals(Integer.class)) {
        return (T) (Integer) Integer.parseInt(value);
      } else if (type.equals(String.class)) {
        return (T) value;
      } else if (type.equals(Double.class) || type.equals(double.class)) {
        return (T) (Double) Double.parseDouble(value);
      } else if (type.equals(Float.class) || type.equals(float.class)) {
        return (T) (Float) Float.parseFloat(value);
      } else if (type.equals(Long.class) || type.equals(long.class)) {
        return (T) (Long) Long.parseLong(value);
      } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
        return (T) (Boolean) Boolean.parseBoolean(value);
      } else if (type.equals(Short.class) || type.equals(short.class)) {
        return (T) (Short) Short.parseShort(value);
      } else if (type.equals(Byte.class) || type.equals(byte.class)) {
        return (T) (Byte) Byte.parseByte(value);
      }
      return (T) value;
    } else {
      throw new RuntimeException("Type " + type.getName() + " not supported!");
    }
  }

  public static Object primitiveZero(Class<?> type) {
    if (type.equals(int.class) || type.equals(double.class) ||
        type.equals(short.class) || type.equals(long.class) ||
        type.equals(byte.class) || type.equals(float.class)) {
      return 0;
    }
    if (type.equals(boolean.class)) {
      return false;
    }
    if (type.equals(String.class)) {
      return "";
    }
    return null;
  }


  public static Integer castInt(Object object) {
    if (object instanceof Integer) {
      return (Integer) object;
    }
    return null;
  }

  public static Long castLong(Object object) {
    if (object instanceof Long) {
      return (Long) object;
    }
    return null;
  }

  public static Double castDouble(Object object) {
    if (object instanceof Double) {
      return (Double) object;
    }
    return null;
  }

  public static String castString(Object object) {
    if (object instanceof String) {
      return (String) object;
    }
    return null;
  }

  public static boolean isPrimitive(Class<?> type) {
    return isNumber(type)
        || type == String.class
        || type == byte.class
        || type == Byte.class
        || type == char.class
        || type == Character.class;
  }

  public static boolean isNumber(Class<?> type) {
    return type == double.class
        || type == Double.class
        || type == float.class
        || type == Float.class
        || type == short.class
        || type == Short.class
        || type == int.class
        || type == Integer.class
        || type == long.class
        || type == Long.class;
  }
}
