package moe.leer.tomato.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author leer
 * Created at 7/2/19 3:20 PM
 * <p>
 * To inject {@code org.slf4j.Logger} to he annotated field
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ALogger {
}
