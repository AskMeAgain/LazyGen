package io.github.askmeagain.mapstructcalculator;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public abstract class PseudoGeneratedCalculator implements Calculator {

  private String lazyCalculateStuff;
  private String lazyMapD;

  @Named("calculateStuff")
  public String lazyCalculateStuff(Inputs inputs) {
    if (lazyCalculateStuff != null) {
      return lazyCalculateStuff;
    }
    lazyCalculateStuff = calculateStuff(inputs);
    return lazyCalculateStuff;
  }

  @Named("mapD")
  public String lazyMapD(Inputs inputs) {
    if (lazyMapD != null) {
      return lazyMapD;
    }
    lazyMapD = mapD(inputs);
    return lazyMapD;
  }
}

