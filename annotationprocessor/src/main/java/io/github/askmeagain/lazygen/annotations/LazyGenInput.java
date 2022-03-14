package io.github.askmeagain.lazygen.annotations;

public interface LazyGenInput<T> {
  default T getInputs() {
    return null;
  }
}
