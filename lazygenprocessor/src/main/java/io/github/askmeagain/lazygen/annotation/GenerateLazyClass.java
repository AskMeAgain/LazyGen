package io.github.askmeagain.lazygen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks this class as the origin for all generated methods marked via @LazyGen of superclasses & interfaces
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface GenerateLazyClass {
  ResultType value();
}
