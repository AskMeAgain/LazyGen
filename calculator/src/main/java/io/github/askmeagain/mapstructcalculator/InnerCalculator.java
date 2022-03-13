package io.github.askmeagain.mapstructcalculator;

import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface InnerCalculator {

  @Mapping(target = "d", source = "inputs", qualifiedByName = "mapD")
  @Mapping(target = "e", source = "inputs", qualifiedByName = "mapD")
  Outputs.InnerOutput mapInnerOutput(Inputs inputs);

  default String mapD(Inputs inputs) {
    System.out.println("inner call!");
    return inputs.getA();
  }
}
