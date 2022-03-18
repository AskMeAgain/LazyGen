package io.github.askmeagain.lazygen.calculator;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public class TestMapper {

  @LazyGen
  @Named("a")
  public String a(TestMapper calculator) {
    System.out.println("a");
    return "a";
  }

  @Mapper
  public abstract static class TestMapper2 extends TestMapper {

    @LazyGen
    @Named("a")
    public String a(io.github.askmeagain.lazygen.calculator.TestMapper calculator) {
      System.out.println("a");
      return "a";
    }
  }
}
