package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGenInput;
import io.github.askmeagain.lazygen.calculator.MultiplicatorCalculator2;
import io.github.askmeagain.lazygen.calculator.SummationCalculator2;
import io.github.askmeagain.lazygen.entities.input.Inputs;
import io.github.askmeagain.lazygen.entities.output.Outputs;
import io.github.askmeagain.lazygen.other.ResultType;
import org.mapstruct.Mapping;

@GenerateLazyClass(ResultType.ABSTRACT_CLASS)
public interface TestCalculator2 extends MultiplicatorCalculator2, SummationCalculator2, LazyGenInput<Inputs> {

  @Mapping(target = "multiplications", expression = "java(mapMultiplications(calculator))")
  @Mapping(target = "summations", expression = "java(mapSummations(calculator))")
  Outputs map(TestCalculator2 calculator);
}
