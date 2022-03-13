package io.github.askmeagain.mapstructcalculator;

import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface AnotherInnerCalculator extends InnerCalculator{

  @Mapping(target = "g", source = "inputs", qualifiedByName = "whatever")
  @Mapping(target = "h", source = "inputs", qualifiedByName = "whatever")
  Outputs.AnotherInnerOutput mapAnotherInnerOutput(Inputs inputs);

  @Named("whatever")
  default String whatever(Inputs inputs) {
    System.out.println("another nested inner call!");
    return "whatever";
    //return this.mapInnerOutput(inputs).getE();
  }
}
