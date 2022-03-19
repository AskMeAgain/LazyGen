package io.github.askmeagain.lazygen.calculator.complex;

import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.output.Multiplications;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface MultiplicatorCalculator {

  @Mapping(target = "number1mul2", source = "calculator", qualifiedByName = "number1mul2")
  @Mapping(target = "number1mul4", source = "calculator", qualifiedByName = "number1mul4")
  @Mapping(target = "number1mul8", source = "calculator", qualifiedByName = "number1mul8")
  Multiplications mapMultiplications(TestCalculator calculator);

  @LazyGen
  @Named("number1mul2")
  default Integer number1mul2 (TestCalculator calculator) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return calculator.getInputs().getNumber1() * 2;
  }

  @LazyGen
  @Named("number1mul4")
  default Integer number1mul4 (TestCalculator calculator) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return calculator.number1mul2(calculator) * 2;
  }

  @LazyGen
  @Named("number1mul8")
  default Integer number1mul8 (TestCalculator calculator) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return calculator.number1mul4(calculator) * 2;
  }
}
