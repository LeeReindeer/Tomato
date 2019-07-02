package moe.leer.tomato.core;

import moe.leer.tomato.core.annotation.ALogger;
import moe.leer.tomato.core.annotation.AutoWired;
import moe.leer.tomato.core.annotation.Tomato;
import moe.leer.tomato.utils.ClassUtil;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author leer
 * Created at 7/1/19 11:53 PM
 * <p>
 * Implementation of IOC(Inversion of Controll)
 * @see moe.leer.tomato.utils.ClassUtil
 * @see TomatoContainer
 */
public class IOC implements Loggable {
  private TomatoContainer container;

  private static final List<Class<? extends Annotation>> INJECT_ANNOTATION = Arrays.asList(
      AutoWired.class, Tomato.class);

  public IOC() {
    this.container = TomatoContainer.CONTAINER;
  }

  /**
   * Inject all tomato with annotation {@AutoWired} or {@Tomato}
   */
  public void ioc() {
    Set<Class<?>> classSet = container.getClassSet();
    for (Class<?> clazz : classSet) {
      Object targetTomato = container.getTomato(clazz);
      Field[] fields = clazz.getDeclaredFields(); // get all field contain private fields
      iocWithFields(clazz, targetTomato, fields); // inject fields
      iocLogger(clazz, targetTomato, fields);
    }
  }

  /**
   * Inject {@code org.slf4j.Logger} to {@code ALogger} annotated field
   */
  protected void iocLogger(Class<?> clazz, Object targetTomato, Field[] fields) {
    for (Field field : fields) {
      boolean isLoggerClass = field.getType().isAssignableFrom(org.slf4j.Logger.class);
      boolean isAnnotated = field.isAnnotationPresent(ALogger.class);
      if (isLoggerClass || isAnnotated) {
        if (isLoggerClass && isAnnotated) {
          ClassUtil.setField(field, targetTomato, LoggerFactory.getLogger(clazz));
        } else if (isAnnotated && !isLoggerClass) {
          throw new RuntimeException("Required type: org.slf4j.Logger.class at " + clazz + "." + field.getName());
        }
      }
    }
  }

  protected void iocWithFields(Class<?> clazz, Object targetTomato, Field[] fields) {
    for (Field field : fields) {
      for (Class<? extends Annotation> annoClz : INJECT_ANNOTATION) {
        if (field.isAnnotationPresent(annoClz)) {
          final Class<?> fieldClass = field.getType();
          Object fieldValue = getClassInstance(fieldClass);
          if (null != fieldValue) {
            logger().debug("autowire {} in {} with {}", fieldClass.getName(), clazz.getName(), fieldValue);
            ClassUtil.setField(field, targetTomato, fieldValue);
          } else {
            throw new RuntimeException("can't inject class " + fieldClass.getName() + " to " + clazz.getName() + "." + field.getName());
          }
          break; //injection finished, goto next filed
        }
      }
    }
  }

  /**
   * @return Instance of the class or the implement instance of interface in the {@code TomatoContainer}
   */
  private Object getClassInstance(final Class<?> clz) {
    return Optional
        .ofNullable(container.getTomatoObject(clz))
        .orElseGet(() -> {
          Class<?> implementClass = getImplementClass(clz);
          if (implementClass != null) {
            return container.getTomatoObject(implementClass);
          }
          return null;
        });
  }

  /**
   * @return Implement class of the {@code interfaceClass} in the {@code TomatoContainer}
   */
  private Class<?> getImplementClass(final Class<?> interfaceClass) {
    return container.getClassesByInterface(interfaceClass)
        .stream()
        .findFirst()
        .orElse(null);
  }
}
