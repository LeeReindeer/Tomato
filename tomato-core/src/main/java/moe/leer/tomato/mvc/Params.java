package moe.leer.tomato.mvc;

import moe.leer.tomato.utils.CastUtil;

import java.util.Map;

/**
 * @author leer
 * Created at 7/2/19 5:02 PM
 * Abstract params
 */
public abstract class Params {
  private Map<String, String> paramsMap;

  public Params(Map<String, String> paramsMap) {
    this.paramsMap = paramsMap;
  }

  public boolean exits(String name) {
    return paramsMap.containsKey(name);
  }

  public Integer getInt(String name) {
    String value = paramsMap.get(name);
    if (value == null) return null;
    return CastUtil.convert(Integer.class, value);
  }

  public Long getLong(String name) {
    String value = paramsMap.get(name);
    if (value == null) return null;
    return CastUtil.convert(Long.class, value);

  }

  public Double getDouble(String name) {
    String value = paramsMap.get(name);
    if (value == null) return null;
    return CastUtil.convert(Double.class, value);
  }

  public String getString(String name) {
    String value = paramsMap.get(name);
    if (value == null) return null;
    return CastUtil.convert(String.class, value);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(String name, Class<T> clazz) {
    String value = paramsMap.get(name);
    if (value == null) return null;
    return (T) CastUtil.convert(clazz, value);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    paramsMap.forEach((k, v) -> sb.append(k).append("==>").append(v).append(" "));
    return sb.toString();
  }
}
