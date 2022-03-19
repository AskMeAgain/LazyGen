package io.github.askmeagain.lazygen.calculator.simple.deepabstract;

import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;

public class NormalDeepAbstractClass {

  @LazyGen
  public String abc() {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }
}
