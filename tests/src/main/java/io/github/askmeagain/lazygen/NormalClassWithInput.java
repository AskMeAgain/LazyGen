package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.annotations.LazyGenInput;
import io.github.askmeagain.lazygen.input.Input;
import io.github.askmeagain.lazygen.other.ResultType;

@GenerateLazyClass(ResultType.CLASS)
public class NormalClassWithInput implements LazyGenInput<Input> {

  @LazyGen
  String map(NormalClassWithInput instance){
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "Test";
  }
}
