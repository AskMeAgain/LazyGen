package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.other.ResultType;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@GenerateLazyClass(ResultType.ABSTRACT_CLASS)
public class NormalClass {

  @Getter
  private final AtomicInteger counter = new AtomicInteger();

  @LazyGen
  String abc(){
    counter.getAndIncrement();
    return "Test";
  }
}
