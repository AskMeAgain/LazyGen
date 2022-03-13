package io.github.askmeagain.mapstructcalculator;

import org.mapstruct.factory.Mappers;

public class TestClass {

  public static void main(String[] args) {
    var mapper = Mappers.getMapper(PseudoGeneratedCalculator.class);
    var map = mapper.map(new Inputs("a", "b"));
    System.out.println("Result:" + map.getA());
    System.out.println("Result:" + map.getC());
    System.out.println("Result:" + map.getInnerOutput().getD());
    System.out.println("Result:" + map.getInnerOutput().getE());
    System.out.println("Result:" + map.getInnerOutput().getF());
  }
}
