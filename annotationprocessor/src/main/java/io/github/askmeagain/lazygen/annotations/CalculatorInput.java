package io.github.askmeagain.lazygen.annotations;

public interface CalculatorInput<T> {
  default T getInputs() {
    return null;
  }
}
