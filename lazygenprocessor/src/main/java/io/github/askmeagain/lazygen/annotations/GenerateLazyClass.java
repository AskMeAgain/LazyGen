package io.github.askmeagain.lazygen.annotations;

import io.github.askmeagain.lazygen.other.ResultType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface GenerateLazyClass {
  ResultType value();
}
