package io.github.askmeagain.mapstructcalculator.calculator;

public interface CalculatorInput<T> {
  default T getInputs() {
    return null;
  }
}
