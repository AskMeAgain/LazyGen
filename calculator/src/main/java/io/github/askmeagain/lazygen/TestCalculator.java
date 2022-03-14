package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.CalculatorInput;
import io.github.askmeagain.lazygen.annotations.GenerateProfiler;
import io.github.askmeagain.lazygen.calculator.MultiplicatorCalculator;
import io.github.askmeagain.lazygen.calculator.SummationCalculator;
import io.github.askmeagain.lazygen.entities.input.Inputs;
import io.github.askmeagain.lazygen.entities.output.Outputs;
import org.mapstruct.Mapping;

@GenerateProfiler
public interface TestCalculator extends MultiplicatorCalculator, SummationCalculator, CalculatorInput<Inputs> {

  @Mapping(target = "multiplications", expression = "java(mapMultiplications(calculator))")
  @Mapping(target = "summations", expression = "java(mapSummations(calculator))")
  Outputs map(TestCalculator calculator);
}
