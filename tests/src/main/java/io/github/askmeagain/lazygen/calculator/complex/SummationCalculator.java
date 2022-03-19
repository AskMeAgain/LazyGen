package io.github.askmeagain.lazygen.calculator.complex;

import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.input.Input;
import io.github.askmeagain.lazygen.output.Summations;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


public interface SummationCalculator {

  @Mapping(target = "a", source = "input", qualifiedByName = "a")
  @Mapping(target = "aa", source = "input", qualifiedByName = "aa")
  @Mapping(target = "aaaa", source = "input", qualifiedByName = "aaaa")
  Summations mapSummations(Input input);

  MapStructCalculator getTestCalculator();

  @LazyGen
  @Named("a")
  default String a(Input input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return "a";
  }

  @LazyGen
  @Named("aa")
  default String aa(Input input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return getTestCalculator().a(input) + getTestCalculator().a(input);
  }

  @LazyGen
  @Named("aaaa")
  default String aaaa(Input input) {
    LazyGenTestUtils.atomicInteger.getAndIncrement();
    return getTestCalculator().a(input) + getTestCalculator().aa(input) + getTestCalculator().a(input);
  }
}
