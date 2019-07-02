package moe.leer.tomato.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author leer
 * Created at 7/1/19 8:12 PM
 * <p>
 * A normal Tomato is like a Bean in Spring.
 * This annatation likes Spring's @Component
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tomato {

  /**
   * Alias for name
   */
//  String value();

  /**
   * The name of Tomato, default is the class name
   */
//  String name();
}
