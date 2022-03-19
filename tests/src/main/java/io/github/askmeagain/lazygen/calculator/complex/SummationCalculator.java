package io.github.askmeagain.lazygen.calculator.complex;

import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.output.Summations;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


public interface SummationCalculator {

  @Mapping(target = "a", source = "calculator", qualifiedByName = "a")
  @Mapping(target = "aa", source = "calculator", qualifiedByName = "aa")
  @Mapping(target = "aaaa", source = "calculator", qualifiedByName = "aaaa")
  Summations mapSummations(TestCalculator calculator);

  @LazyGen
  @Named("a")
  default String a(TestCalculator calculator) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "a";
  }

  @LazyGen
  @Named("aa")
  default String aa(TestCalculator calculator) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return calculator.a(calculator) + calculator.a(calculator);
  }

  @LazyGen
  @Named("aaaa")
  default String aaaa(TestCalculator calculator) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return calculator.a(calculator) + calculator.aa(calculator) + calculator.a(calculator);
  }
}
