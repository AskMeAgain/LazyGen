package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.other.ResultType;

@GenerateLazyClass(ResultType.CLASS)
public class NormalClass {

  @LazyGen
  String abc(){
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }
}
