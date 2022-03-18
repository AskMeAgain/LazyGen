package io.github.askmeagain.lazygen.input;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Input {
  Integer number1;
}
