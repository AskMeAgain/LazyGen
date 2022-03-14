package io.github.askmeagain.mapstructcalculator.calculator;

import io.github.askmeagain.mapstructcalculator.CalculatorInput;
import io.github.askmeagain.mapstructcalculator.GenerateProfiler;
import io.github.askmeagain.mapstructcalculator.entities.input.Inputs;
import io.github.askmeagain.mapstructcalculator.entities.output.Outputs;
import org.mapstruct.Mapping;

@GenerateProfiler
public interface SuperSpecialCalculator extends MultiplicatorCalculator, CalculatorInput<Inputs> {

  @Mapping(target = "multiplications", expression = "java(mapMultiplications(calculator))")
  Outputs map(SuperSpecialCalculator calculator);
}
