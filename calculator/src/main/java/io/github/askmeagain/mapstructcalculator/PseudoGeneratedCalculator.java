package io.github.askmeagain.mapstructcalculator;

import io.github.askmeagain.mapstructcalculator.calculator.Calculator;
import io.github.askmeagain.mapstructcalculator.entities.Inputs;
import io.github.askmeagain.mapstructcalculator.entities.Outputs;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public abstract class PseudoGeneratedCalculator implements Calculator {

  private String lazyMapD;

  private Inputs inputs;

  @Override
  public Inputs getInputs(){
    return inputs;
  }

  public Outputs calculate(Inputs inputs){
    this.inputs = inputs;
    return map(this);
  }

  @Override
  @Named("mapD")
  public String mapD(Calculator calculator) {
    if (lazyMapD != null) {
      return lazyMapD;
    }
    lazyMapD = Calculator.super.mapD(calculator);
    return lazyMapD;
  }
}

