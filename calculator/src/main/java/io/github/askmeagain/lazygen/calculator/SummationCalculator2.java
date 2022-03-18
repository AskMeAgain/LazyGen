package io.github.askmeagain.lazygen.calculator;

import io.github.askmeagain.lazygen.TestCalculator;
import io.github.askmeagain.lazygen.TestCalculator2;
import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.entities.output.Summations;
import io.github.askmeagain.lazygen.other.ResultType;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@GenerateLazyClass(ResultType.ABSTRACT_CLASS)
public interface SummationCalculator2 {

  @Mapping(target = "a", source = "calculator", qualifiedByName = "a")
  @Mapping(target = "aa", source = "calculator", qualifiedByName = "aa")
  @Mapping(target = "aaaa", source = "calculator", qualifiedByName = "aaaa")
  Summations mapSummations(TestCalculator2 calculator);

  @LazyGen
  @Named("a")
  default String a(TestCalculator2 calculator) {
    System.out.println("a");
    return "a";
  }

  @LazyGen
  @Named("aa")
  default String aa(TestCalculator2 calculator) {
    System.out.println("aa");
    return calculator.a(calculator) + calculator.a(calculator);
  }

  @LazyGen
  @Named("aaaa")
  default String aaaa(TestCalculator2 calculator) {
    System.out.println("aaaa");
    return calculator.a(calculator) + calculator.aa(calculator) + calculator.a(calculator);
  }
}
