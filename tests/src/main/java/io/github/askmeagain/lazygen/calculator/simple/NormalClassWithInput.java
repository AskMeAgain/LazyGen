package io.github.askmeagain.lazygen.calculator.simple;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.annotations.LazyGenInput;
import io.github.askmeagain.lazygen.annotations.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.input.Input;

@GenerateLazyClass(ResultType.CLASS)
public class NormalClassWithInput implements LazyGenInput<Input> {

  @LazyGen
  String map(NormalClassWithInput instance){
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }
}
