package io.github.askmeagain.lazygen.calculator.simple;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.annotations.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.output.Outputs;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
public interface MapStructInterface {

  @Mapping(target = "output", source = "input", qualifiedByName = "a")
  Outputs map(String input);

  @LazyGen
  @Named("a")
  default String a(String input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "a";
  }
}
