package io.github.askmeagain.lazygen.calculator.simple;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.pojos.input.Input;
import io.github.askmeagain.lazygen.pojos.output.Outputs;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
public abstract class MapstructAbstractClass {

  @Mapping(target = "output", source = "input", qualifiedByName = "different_name")
  public abstract Outputs map(Input input);

  @LazyGen
  @Named("different_name")
  String a(Input input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "a";
  }
}
