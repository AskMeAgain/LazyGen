package io.github.askmeagain.lazygen.calculator;

import io.github.askmeagain.lazygen.TestCalculator;
import io.github.askmeagain.lazygen.TestCalculator2;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.entities.output.Multiplications;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface MultiplicatorCalculator2 {

  @Mapping(target = "number1mul2", source = "calculator", qualifiedByName = "number1mul2")
  @Mapping(target = "number1mul4", source = "calculator", qualifiedByName = "number1mul4")
  @Mapping(target = "number1mul8", source = "calculator", qualifiedByName = "number1mul8")
  Multiplications mapMultiplications(TestCalculator2 calculator);

  @LazyGen
  @Named("number1mul2")
  default Integer number1mul2 (TestCalculator2 calculator) {
    System.out.println("number1mul2");
    return calculator.getInputs().getNumber1() * 2;
  }

  @LazyGen
  @Named("number1mul4")
  default Integer number1mul4 (TestCalculator2 calculator) {
    System.out.println("number1mul4");
    return calculator.number1mul2(calculator) * 2;
  }

  @LazyGen
  @Named("number1mul8")
  default Integer number1mul8 (TestCalculator2 calculator) {
    System.out.println("number1mul8");
    return calculator.number1mul4(calculator) * 2;
  }
}
