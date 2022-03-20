package io.github.askmeagain.lazygen.pojos.output;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ComplexOutputs {
  Multiplications multiplications;
  Summations summations;
}
