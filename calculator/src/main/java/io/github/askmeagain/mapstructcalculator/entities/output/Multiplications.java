package io.github.askmeagain.mapstructcalculator.entities.output;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Multiplications {
  Integer number1mul2;
  Integer number1mul4;
  Integer number1mul8;
}
