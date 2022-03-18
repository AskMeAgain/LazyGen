package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.other.ResultType;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
public abstract class MapstructAbstractClass {

  @Mapping(target = ".", source = "input", qualifiedByName = "a")
  abstract String map(String input);

  @LazyGen
  @Named("a")
  public String a() {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "a";
  }
}
