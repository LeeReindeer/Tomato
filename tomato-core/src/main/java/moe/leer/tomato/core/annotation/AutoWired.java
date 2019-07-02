package moe.leer.tomato.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author leer
 * Created at 7/1/19 8:14 PM
 * <p>
 * Inject Tomato to field, same as {@code @Tomato}
 * @see moe.leer.tomato.core.annotation.Tomato
 */
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWired {
}
