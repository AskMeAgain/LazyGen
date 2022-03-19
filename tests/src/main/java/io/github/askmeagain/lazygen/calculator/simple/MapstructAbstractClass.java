package io.github.askmeagain.lazygen.calculator.simple;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
public abstract class MapstructAbstractClass {

  @Mapping(target = ".", source = "input", qualifiedByName = "a")
  public abstract String map(String input);

  @LazyGen
  @Named("a")
  public String a() {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "a";
  }
}
