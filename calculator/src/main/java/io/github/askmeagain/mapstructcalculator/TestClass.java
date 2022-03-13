package io.github.askmeagain.mapstructcalculator;

import org.mapstruct.factory.Mappers;

public class TestClass {

  public static void main(String[] args) {
    var mapper = new PseudoGeneratedCalculatorImpl();

    System.out.println("-------------------------");
    var result = mapper.calculate(new Inputs("a", "b"));

    System.out.println("-------------------------");
    System.out.println(result.getInnerOutput().getD());
    System.out.println(result.getAnotherInnerOutput().getG());
  }
}
