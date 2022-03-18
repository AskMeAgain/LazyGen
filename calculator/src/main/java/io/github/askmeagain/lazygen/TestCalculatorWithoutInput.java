package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.calculator.MultiplicatorCalculator3;
import io.github.askmeagain.lazygen.calculator.SummationCalculator3;
import io.github.askmeagain.lazygen.entities.output.Outputs;
import io.github.askmeagain.lazygen.other.ResultType;
import org.mapstruct.Mapping;

@GenerateLazyClass(ResultType.ABSTRACT_CLASS)
public interface TestCalculatorWithoutInput extends MultiplicatorCalculator3, SummationCalculator3 {

  @Mapping(target = "multiplications", expression = "java(mapMultiplications(calculator))")
  @Mapping(target = "summations", expression = "java(mapSummations(calculator))")
  Outputs map(TestCalculatorWithoutInput calculator);
}
