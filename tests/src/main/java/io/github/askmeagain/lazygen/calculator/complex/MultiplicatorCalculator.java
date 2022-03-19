package io.github.askmeagain.lazygen.calculator.complex;

import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.input.Input;
import io.github.askmeagain.lazygen.output.Multiplications;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface MultiplicatorCalculator {

  @Mapping(target = "number1mul2", source = "input", qualifiedByName = "number1mul2")
  @Mapping(target = "number1mul4", source = "input", qualifiedByName = "number1mul4")
  @Mapping(target = "number1mul8", source = "input", qualifiedByName = "number1mul8")
  Multiplications mapMultiplications(Input input);

  MapStructCalculator getTestCalculator();

  @LazyGen
  @Named("number1mul2")
  default Integer number1mul2(Input input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return input.getNumber1() * 2;
  }

  @LazyGen
  @Named("number1mul4")
  default Integer number1mul4(Input input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return getTestCalculator().number1mul2(input) * 2;
  }

  @LazyGen
  @Named("number1mul8")
  default Integer number1mul8(Input input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return getTestCalculator().number1mul4(input) * 2;
  }
}
