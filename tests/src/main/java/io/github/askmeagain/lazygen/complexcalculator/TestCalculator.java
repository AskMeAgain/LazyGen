package io.github.askmeagain.lazygen.complexcalculator;

import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotations.LazyGenInput;
import io.github.askmeagain.lazygen.input.Input;
import io.github.askmeagain.lazygen.output.ComplexOutputs;
import io.github.askmeagain.lazygen.other.ResultType;
import org.mapstruct.Mapping;

@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
public interface TestCalculator extends MultiplicatorCalculator, SummationCalculator, LazyGenInput<Input> {

  @Mapping(target = "multiplications", expression = "java(mapMultiplications(calculator))")
  @Mapping(target = "summations", expression = "java(mapSummations(calculator))")
  ComplexOutputs map(TestCalculator calculator);

}
