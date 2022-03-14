package io.github.askmeagain.lazygen.calculator;

import io.github.askmeagain.lazygen.annotations.LazyAnnotation;
import io.github.askmeagain.lazygen.TestCalculator;
import io.github.askmeagain.lazygen.entities.output.Summations;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface SummationCalculator {

  @Mapping(target = "a", source = "calculator", qualifiedByName = "a")
  @Mapping(target = "aa", source = "calculator", qualifiedByName = "aa")
  @Mapping(target = "aaaa", source = "calculator", qualifiedByName = "aaaa")
  Summations mapSummations(TestCalculator calculator);

  @LazyAnnotation
  @Named("a")
  default String a(TestCalculator calculator) {
    System.out.println("a");
    return "a";
  }

  @LazyAnnotation
  @Named("aa")
  default String aa(TestCalculator calculator) {
    System.out.println("aa");
    return calculator.a(calculator) + calculator.a(calculator);
  }

  @LazyAnnotation
  @Named("aaaa")
  default String aaaa(TestCalculator calculator) {
    System.out.println("aaaa");
    return calculator.a(calculator) + calculator.aa(calculator) + calculator.a(calculator);
  }
}
