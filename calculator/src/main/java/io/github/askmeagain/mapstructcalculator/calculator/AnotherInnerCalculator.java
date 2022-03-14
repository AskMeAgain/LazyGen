package io.github.askmeagain.mapstructcalculator.calculator;

import io.github.askmeagain.mapstructcalculator.entities.Outputs;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface AnotherInnerCalculator {

  @Mapping(target = "g", source = "calculator", qualifiedByName = "mapG")
  Outputs.AnotherInnerOutput mapAnotherInnerOutput(Calculator calculator);

  @Named("mapG")
  default String mapG (Calculator calculator) {
    System.out.println("mapG");
    return calculator.mapD(calculator);
  }
}
