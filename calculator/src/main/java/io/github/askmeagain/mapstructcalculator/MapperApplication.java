package io.github.askmeagain.mapstructcalculator;

import io.github.askmeagain.mapstructcalculator.calculator.LazySuperSpecialCalculator;
import io.github.askmeagain.mapstructcalculator.entities.input.Inputs;
import lombok.SneakyThrows;
import org.mapstruct.factory.Mappers;

public class MapperApplication {

  @SneakyThrows
  public static void main(String[] args) {


    System.out.println("------------Lazy--------------");
    var lazyMapper = Mappers.getMapper(LazySuperSpecialCalculator.class);

    var lazyResult = lazyMapper.calculate(new Inputs(1, 1));

    System.out.println(lazyResult.getMultiplications().getNumber1mul2());
    System.out.println(lazyResult.getMultiplications().getNumber1mul4());
    System.out.println(lazyResult.getMultiplications().getNumber1mul8());

    System.out.println("------------Old-------------");

    var oldMapper = Mappers.getMapper(NonLazyWrapper.class);
    var oldResult = oldMapper.calculate(new Inputs(1, 1));

    System.out.println(oldResult.getMultiplications().getNumber1mul2());
    System.out.println(oldResult.getMultiplications().getNumber1mul4());
    System.out.println(oldResult.getMultiplications().getNumber1mul8());
  }
}
