package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.other.ResultType;

@GenerateLazyClass(ResultType.ABSTRACT_CLASS)
public class NormalAbstractClass {

  @LazyGen
  String abc() {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }
}
