package moe.leer.tomato.core;

import moe.leer.tomato.core.annotation.Controller;
import moe.leer.tomato.core.annotation.Repository;
import moe.leer.tomato.core.annotation.Service;
import moe.leer.tomato.core.annotation.Tomato;
import moe.leer.tomato.utils.ClassUtil;
import moe.leer.tomato.utils.ValidUtil;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author leer
 * Created at 7/1/19 9:49 PM
 */
public enum TomatoContainer implements Loggable {
  CONTAINER;

  // All tomato in a map, all tomatoes are singleton
  private final Map<Class<?>, Object> tomatoMap = new ConcurrentHashMap<>();

  // With these annotations, the object is a Tomato
  private static final Class[] TOMATO_ANNOTATIONS = new Class[]{
      Tomato.class, Controller.class, Repository.class, Service.class
  };

  public Set<Class<?>> getClassSet() {
    return tomatoMap.keySet();
  }

  /**
   * Get class which implements {@code interfaceClass}
   */
  public Set<Class<?>> getClassesByInterface(Class<?> interfaceClass) {
    return tomatoMap.keySet()
        .stream()
        .filter(interfaceClass::isAssignableFrom) //is this class implement interfaceClass
        .filter(clz -> !clz.equals(interfaceClass)) // but not interface itself
        .collect(Collectors.toSet());
  }

  public Collection<Object> getTomatoes() {
    return tomatoMap.values();
  }

  /**
   * Load all tomatoes under the {@code packageName}.
   * Put all class's singleton instance with {@code TOMATO_ANNOTATIONS} into the map
   */
  @SuppressWarnings("unchecked")
  public void loadTomatoes(String packageName) {
    Set<Class<?>> clazzs = ClassUtil.getPackageClassSet(packageName);
    clazzs.stream().filter(clz -> {
      for (Class<? extends Annotation> annotation : TOMATO_ANNOTATIONS) {
        if (clz.isAnnotationPresent(annotation)) {
          return true;
        }
      }
      return false;
    }).forEach(clz -> {
      logger().debug("instance: {}", clz.getName());
      tomatoMap.put(clz, ClassUtil.newInstance(clz));
    });
  }

  /**
   * @return Specific type of object of the class
   */
  @SuppressWarnings("unchecked")
  public <T> T getTomato(Class<T> clazz) {
    return ValidUtil.returnNullElse(clazz, (T) tomatoMap.get(clazz));
  }

  /**
   * @return return raw object of the class in the tomatoMap
   */
  public Object getTomatoObject(Class<?> clazz) {
    if (clazz == null) return null;
    return tomatoMap.get(clazz);
  }

  /**
   * Add a tomato to context
   *
   * @return the origin tomato i exits, else return null
   */
  @SuppressWarnings("unchecked")
  public <T> T addTomato(Class<T> clazz, T t) {
    T origin = (T) tomatoMap.put(clazz, t);
    return ValidUtil.returnNullElse(origin, origin);
  }

  /**
   * Remove tomato only if exits
   */
  public void removeTomato(Class<?> clazz) {
    tomatoMap.remove(clazz);
  }

  public int size() {
    return tomatoMap.size();
  }
}
