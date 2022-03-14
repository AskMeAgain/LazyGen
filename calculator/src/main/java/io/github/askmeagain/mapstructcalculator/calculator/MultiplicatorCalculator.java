package io.github.askmeagain.mapstructcalculator.calculator;

import io.github.askmeagain.mapstructcalculator.LazyAnnotation;
import io.github.askmeagain.mapstructcalculator.entities.output.Multiplications;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface MultiplicatorCalculator {

  @Mapping(target = "number1mul2", source = "calculator", qualifiedByName = "number1mul2")
  @Mapping(target = "number1mul4", source = "calculator", qualifiedByName = "number1mul4")
  @Mapping(target = "number1mul8", source = "calculator", qualifiedByName = "number1mul8")
  Multiplications mapMultiplications(SuperSpecialCalculator calculator);

  @LazyAnnotation
  @Named("number1mul2")
  default Integer number1mul2 (SuperSpecialCalculator calculator) {
    System.out.println("number1mul2");
    return calculator.getInputs().getNumber1() * 2;
  }

  @LazyAnnotation
  @Named("number1mul4")
  default Integer number1mul4 (SuperSpecialCalculator calculator) {
    System.out.println("number1mul4");
    return calculator.number1mul2(calculator) * 2;
  }

  @LazyAnnotation
  @Named("number1mul8")
  default Integer number1mul8 (SuperSpecialCalculator calculator) {
    System.out.println("number1mul8");
    return calculator.number1mul4(calculator) * 2;
  }
}
