package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.complexcalculator.LazyTestCalculatorImpl;
import io.github.askmeagain.lazygen.input.Input;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class LazyGenTests {

  @BeforeEach
  public void reset() {
    LazyGenTestUtils.atomicInteger.set(0);
  }

  @Test
  void mapStructInput() {
    //Arrange --------------------------------------------------------------------------------
    var calculator = new LazyTestCalculatorImpl();
    var input = new Input(1);

    //Act ------------------------------------------------------------------------------------
    var result = calculator.calculate(input);

    //Assert ---------------------------------------------------------------------------------
    var multiplications = result.getMultiplications();
    Assertions.assertEquals(2, multiplications.getNumber1mul2());
    Assertions.assertEquals(4, multiplications.getNumber1mul4());
    Assertions.assertEquals(8, multiplications.getNumber1mul8());

    var summations = result.getSummations();
    Assertions.assertEquals("a", summations.getA());
    Assertions.assertEquals("aa", summations.getAa());
    Assertions.assertEquals("aaaa", summations.getAaaa());

    Assertions.assertEquals(6, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void mapStructInterface() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new LazyMapStructInterfaceImpl();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.map("a");
    lazyNormalClass.map("a");
    lazyNormalClass.map("a");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void mapStructAbstractClass() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = Mappers.getMapper(LazyMapstructAbstractClass.class);

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.map("a");
    lazyNormalClass.map("a");
    lazyNormalClass.map("a");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void classResult() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new LazyNormalClass();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc();

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void abstractClassResult() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new TestNormalAbstractClass();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc();

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

}