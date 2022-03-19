package io.github.askmeagain.lazygen.calculator.complex;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.input.Input;
import io.github.askmeagain.lazygen.output.ComplexOutputs;
import org.mapstruct.Mapping;

@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
public interface MapStructCalculator extends MultiplicatorCalculator, SummationCalculator {

  @Mapping(target = "multiplications", expression = "java(mapMultiplications(input))")
  @Mapping(target = "summations", expression = "java(mapSummations(input))")
  ComplexOutputs map(Input input);

  default MapStructCalculator getTestCalculator() {
    return this;
  }
}
