package io.github.askmeagain.lazygen.calculator.simple.primitive;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;

@GenerateLazyClass(ResultType.CLASS)
public class PrimitiveClass {

  @LazyGen
  public int testPrimitive() {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return 1;
  }

}
