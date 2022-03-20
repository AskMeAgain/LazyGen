package io.github.askmeagain.lazygen.calculator.complex;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.ResultType;
import io.github.askmeagain.lazygen.pojos.input.Input;
import io.github.askmeagain.lazygen.pojos.output.ComplexOutputs;
import org.mapstruct.Mapping;

@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
public interface MapStructCalculator extends MultiplicationCalculator, SummationCalculator {

  @Mapping(target = "multiplications", source = ".")
  @Mapping(target = "summations", source = ".")
  ComplexOutputs map(Input input);

  default MapStructCalculator getTestCalculator() {
    return this;
  }
}
