package io.github.askmeagain.mapstructcalculator.calculator;

import io.github.askmeagain.mapstructcalculator.Inputs;
import io.github.askmeagain.mapstructcalculator.Outputs;
import org.mapstruct.Mapping;

//@GenerateProfiler
public interface Calculator extends InnerCalculator, AnotherInnerCalculator, CalculatorInput<Inputs> {

  @Mapping(target = "innerOutput", expression = "java(mapInnerOutput(calculator))")
  @Mapping(target = "anotherInnerOutput", expression = "java(mapAnotherInnerOutput(calculator))")
  Outputs map(Calculator calculator);
}
