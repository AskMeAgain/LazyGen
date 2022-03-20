package io.github.askmeagain.lazygen.calculator.complex;

import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.pojos.input.Input;
import io.github.askmeagain.lazygen.pojos.output.Multiplications;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface MultiplicationCalculator {

  @Mapping(target = "number1mul2", source = ".", qualifiedByName = "number1mul2")
  @Mapping(target = "number1mul4", source = ".", qualifiedByName = "number1mul4")
  @Mapping(target = "number1mul8", source = ".", qualifiedByName = "number1mul8")
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
