package io.github.askmeagain.mapstructcalculator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@GenerateProfiler
public abstract class Calculator {

  @Mapping(target = "a", source = "inputs", qualifiedByName = "calculateStuff")
  @Mapping(target = "b", source = "inputs", qualifiedByName = "calculateStuff")
  @Mapping(target = "c", source = "inputs.a")
  public abstract Outputs map(Inputs inputs);

  String calculateStuff(Inputs inputs){
    System.out.println("single time!");
    return "calculated";
  }
}
