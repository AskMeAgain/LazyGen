package io.github.askmeagain.lazygen.calculator.simple.duplicatemethod;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;

@GenerateLazyClass(ResultType.CLASS)
public class DuplicateClass {

  @LazyGen
  String abc() {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }

  @LazyGen
  String abc(String abc) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }
}
