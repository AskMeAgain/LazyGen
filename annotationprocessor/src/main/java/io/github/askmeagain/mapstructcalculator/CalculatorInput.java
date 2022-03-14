package io.github.askmeagain.mapstructcalculator;

public interface CalculatorInput<T> {
  default T getInputs() {
    return null;
  }
}
