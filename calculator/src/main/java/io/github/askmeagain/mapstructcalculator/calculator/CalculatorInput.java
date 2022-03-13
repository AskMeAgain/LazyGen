package io.github.askmeagain.mapstructcalculator.calculator;

import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

public interface CalculatorInput<T> {
  @DoIgnore
  default T getInputs() {
    return null;
  }
}
