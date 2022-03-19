package io.github.askmeagain.lazygen.calculator.simple;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.annotations.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;

@GenerateLazyClass(ResultType.CLASS)
public class NormalClass {

  @LazyGen
  String abc(){
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }
}
