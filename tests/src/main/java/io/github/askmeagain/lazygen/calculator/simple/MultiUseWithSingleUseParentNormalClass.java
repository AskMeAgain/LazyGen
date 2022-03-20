package io.github.askmeagain.lazygen.calculator.simple;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.LazyType;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;

@GenerateLazyClass(ResultType.CLASS)
public class MultiUseWithSingleUseParentNormalClass {

  @LazyGen
  String singleUse(String a) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return a;
  }

  @LazyGen(usage = LazyType.MULTI_USE)
  String multiUse(String a) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return a;
  }
}
