package io.github.askmeagain.mapstructcalculator.calculator;

import io.github.askmeagain.mapstructcalculator.entities.Inputs;
import io.github.askmeagain.mapstructcalculator.entities.Outputs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@GenerateProfiler
@Mapper
public interface Calculator extends InnerCalculator, AnotherInnerCalculator, CalculatorInput<Inputs> {

  @Mapping(target = "innerOutput", expression = "java(mapInnerOutput(calculator))")
  @Mapping(target = "anotherInnerOutput", expression = "java(mapAnotherInnerOutput(calculator))")
  Outputs map(Calculator calculator);
}
