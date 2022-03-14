package io.github.askmeagain.lazygen.entities.input;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Inputs {
  Integer number1;
  Integer number2;
}
