package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.entities.input.Inputs;
import io.github.askmeagain.lazygen.entities.output.Outputs;
import org.mapstruct.Mapper;

@Mapper
public abstract class NonLazyWrapper implements TestCalculator {

  private Inputs inputs;

  @Override
  public Inputs getInputs() {
    return inputs;
  }

  public Outputs calculate(Inputs inputs) {
    this.inputs = inputs;
    return map(this);
  }
}
