package io.github.askmeagain.lazygen.calculator.simple;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.LazyType;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;

@GenerateLazyClass(value = ResultType.CLASS, usage = LazyType.MULTI_USE)
public class MultiUseNormalClass {

  @LazyGen
  String abc(String a) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return a;
  }
}
