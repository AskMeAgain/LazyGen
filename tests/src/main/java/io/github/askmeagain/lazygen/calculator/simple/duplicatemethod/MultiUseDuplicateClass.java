package io.github.askmeagain.lazygen.calculator.simple.duplicatemethod;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.LazyType;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;

@GenerateLazyClass(ResultType.CLASS)
public class MultiUseDuplicateClass {

  @LazyGen
  String abc() {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }

  @LazyGen(usage = LazyType.MULTI_USE)
  String abc(String abc) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return abc;
  }
}
