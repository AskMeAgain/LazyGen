package io.github.askmeagain.mapstructcalculator;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Outputs {

  String a;
  String b;
  String c;

  InnerOutput innerOutput;
  AnotherInnerOutput anotherInnerOutput;

  @Value
  @Builder
  public static class InnerOutput {
    String d;
    String e;
    String f;
  }

  @Value
  @Builder
  public static class AnotherInnerOutput {
    String g;
    String h;
    String i;
  }
}
