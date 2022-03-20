package io.github.askmeagain.lazygen.calculator.simple;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.LazyType;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.pojos.output.Outputs;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
public interface MultiUseOverrideMapStructInterface {

  @Mapping(target = "output", source = "input", qualifiedByName = "another_name")
  Outputs map(String input);

  @LazyGen(usage = LazyType.MULTI_USE)
  @Named("another_name")
  default String a(String input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return input;
  }
}
