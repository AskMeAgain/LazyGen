package io.github.askmeagain.mapstructcalculator;

import io.github.askmeagain.mapstructcalculator.calculator.Calculator;
import lombok.Getter;
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

  @Named("mapD")
  public String lazyMapD(Calculator calculator) {
    if (lazyMapD != null) {
      return lazyMapD;
    }
    lazyMapD = mapD(calculator);
    return lazyMapD;
  }
}

