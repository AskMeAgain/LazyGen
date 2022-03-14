package io.github.askmeagain.mapstructcalculator;

import io.github.askmeagain.mapstructcalculator.calculator.SuperSpecialCalculator;
import io.github.askmeagain.mapstructcalculator.entities.input.Inputs;
import io.github.askmeagain.mapstructcalculator.entities.output.Outputs;
import org.mapstruct.Mapper;

@Mapper
public abstract class NonLazyWrapper implements SuperSpecialCalculator {

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
