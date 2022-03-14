package io.github.askmeagain.mapstructcalculator.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Inputs {

  String a;
  String b;
}
