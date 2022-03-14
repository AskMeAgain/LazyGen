package io.github.askmeagain.mapstructcalculator.calculator;

import io.github.askmeagain.mapstructcalculator.entities.Outputs;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface InnerCalculator {

  @Mapping(target = "d", source = "calculator", qualifiedByName = "mapD")
  Outputs.InnerOutput mapInnerOutput(Calculator calculator);

  @Named("mapD")
  default String mapD (Calculator calculator) {
    System.out.println("mapD");
    return calculator.getInputs().getA();
  }
}
