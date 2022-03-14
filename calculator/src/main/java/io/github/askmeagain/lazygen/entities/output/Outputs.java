package io.github.askmeagain.lazygen.entities.output;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Outputs {

  Multiplications multiplications;
  Summations summations;

}
