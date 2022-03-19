package io.github.askmeagain.lazygen.calculator.simple.duplicatemethod;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.annotations.ResultType;
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
