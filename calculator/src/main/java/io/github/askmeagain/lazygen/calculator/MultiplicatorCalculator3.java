package io.github.askmeagain.lazygen.calculator;

import io.github.askmeagain.lazygen.TestCalculator2;
import io.github.askmeagain.lazygen.TestCalculatorWithoutInput;
import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.entities.output.Multiplications;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface MultiplicatorCalculator3 {

  @Mapping(target = "number1mul2", source = "calculator", qualifiedByName = "number1mul2")
  @Mapping(target = "number1mul4", source = "calculator", qualifiedByName = "number1mul4")
  @Mapping(target = "number1mul8", source = "calculator", qualifiedByName = "number1mul8")
  Multiplications mapMultiplications(TestCalculatorWithoutInput calculator);

  @LazyGen
  @Named("number1mul2")
  default Integer number1mul2 (TestCalculatorWithoutInput calculator) {
    System.out.println("number1mul2");
    return 2;
  }

  @LazyGen
  @Named("number1mul4")
  default Integer number1mul4 (TestCalculatorWithoutInput calculator) {
    System.out.println("number1mul4");
    return calculator.number1mul2(calculator) * 2;
  }

  @LazyGen
  @Named("number1mul8")
  default Integer number1mul8 (TestCalculatorWithoutInput calculator) {
    System.out.println("number1mul8");
    return calculator.number1mul4(calculator) * 2;
  }
}
