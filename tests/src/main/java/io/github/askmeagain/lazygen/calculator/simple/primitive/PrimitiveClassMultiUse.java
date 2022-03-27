package io.github.askmeagain.lazygen.calculator.simple.primitive;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.LazyType;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;

@GenerateLazyClass(value = ResultType.CLASS, usage = LazyType.MULTI_USE)
public class PrimitiveClassMultiUse {

  @LazyGen
  public int testPrimitive() {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return 1;
  }

  @LazyGen
  public int testPrimitive2(int i, int ii) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return i;
  }

}
