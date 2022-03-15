package io.github.askmeagain.lazygen.calculator;

import io.github.askmeagain.lazygen.TestCalculator2;
import io.github.askmeagain.lazygen.TestCalculatorWithoutInput;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.entities.output.Summations;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface SummationCalculator3 {

  @Mapping(target = "a", source = "calculator", qualifiedByName = "a")
  @Mapping(target = "aa", source = "calculator", qualifiedByName = "aa")
  @Mapping(target = "aaaa", source = "calculator", qualifiedByName = "aaaa")
  Summations mapSummations(TestCalculatorWithoutInput calculator);

  @LazyGen
  @Named("a")
  default String a(TestCalculatorWithoutInput calculator) {
    System.out.println("a");
    return "a";
  }

  @LazyGen
  @Named("aa")
  default String aa(TestCalculatorWithoutInput calculator) {
    System.out.println("aa");
    return calculator.a(calculator) + calculator.a(calculator);
  }

  @LazyGen
  @Named("aaaa")
  default String aaaa(TestCalculatorWithoutInput calculator) {
    System.out.println("aaaa");
    return calculator.a(calculator) + calculator.aa(calculator) + calculator.a(calculator);
  }
}
