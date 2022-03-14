package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.GenerateLazyMapStructMapper;
import io.github.askmeagain.lazygen.annotations.LazyGenInput;
import io.github.askmeagain.lazygen.calculator.MultiplicatorCalculator;
import io.github.askmeagain.lazygen.calculator.MultiplicatorCalculator2;
import io.github.askmeagain.lazygen.calculator.SummationCalculator;
import io.github.askmeagain.lazygen.calculator.SummationCalculator2;
import io.github.askmeagain.lazygen.entities.input.Inputs;
import io.github.askmeagain.lazygen.entities.output.Outputs;
import org.mapstruct.Mapping;

@GenerateLazyMapStructMapper
public interface TestCalculator2 extends MultiplicatorCalculator2, SummationCalculator2, LazyGenInput<Inputs> {

  @Mapping(target = "multiplications", expression = "java(mapMultiplications(calculator))")
  @Mapping(target = "summations", expression = "java(mapSummations(calculator))")
  Outputs map(TestCalculator2 calculator);
}
