package moe.leer.tomato.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author leer
 * Created at 7/1/19 8:01 PM
 * <p>
 * Annotation for handler method
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {

  /**
   * HTTP method of the annotated method
   */
  String method();

  /**
   * URL path of the the method
   */
  String path();
}
