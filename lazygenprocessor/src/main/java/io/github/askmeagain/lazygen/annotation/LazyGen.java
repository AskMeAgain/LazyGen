package io.github.askmeagain.lazygen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Any method marked with this annotation will be made lazy by the LazyGen annotation processor.
 * <p>
 * If using with MapStruct, make sure that this is added to a method annotated with @Named or else the lazy method is
 * not used by the MapStruct mapper
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface LazyGen {
  LazyType usage() default LazyType.PARENT;
}
