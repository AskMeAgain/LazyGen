package io.github.askmeagain.mapstructcalculator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

//@GenerateProfiler
public interface Calculator extends InnerCalculator, AnotherInnerCalculator {

  @Mapping(target = "a", source = "inputs", qualifiedByName = "calculateStuff")
  @Mapping(target = "b", source = "inputs", qualifiedByName = "calculateStuff")
  @Mapping(target = "c", source = "inputs.a")
  @Mapping(target = "innerOutput", source = "inputs")
  @Mapping(target = "anotherInnerOutput", source = "inputs")
  Outputs map(Inputs inputs);

  default String calculateStuff(Inputs inputs) {
    System.out.println("single time!");
    return "calculated";
  }
}
