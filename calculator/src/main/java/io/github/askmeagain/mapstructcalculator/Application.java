package io.github.askmeagain.mapstructcalculator;

import io.github.askmeagain.mapstructcalculator.entities.Inputs;

public class Application {

  public static void main(String[] args) {
    var mapper = new PseudoGeneratedCalculatorImpl();

    System.out.println("-------------------------");
    var result = mapper.calculate(new Inputs("a", "b"));

    System.out.println("-------------------------");
    System.out.println(result.getInnerOutput().getD());
    System.out.println(result.getAnotherInnerOutput().getG());
  }
}
