package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGenInput;
import io.github.askmeagain.lazygen.calculator.MultiplicatorCalculator;
import io.github.askmeagain.lazygen.calculator.SummationCalculator;
import io.github.askmeagain.lazygen.entities.input.Inputs;
import io.github.askmeagain.lazygen.entities.output.Outputs;
import io.github.askmeagain.lazygen.other.ResultType;
import org.mapstruct.Mapping;

@GenerateLazyClass(ResultType.ABSTRACT_CLASS)
public interface TestCalculator extends MultiplicatorCalculator, SummationCalculator, LazyGenInput<Inputs> {

  @Mapping(target = "multiplications", expression = "java(mapMultiplications(calculator))")
  @Mapping(target = "summations", expression = "java(mapSummations(calculator))")
  Outputs map(TestCalculator calculator);
}
