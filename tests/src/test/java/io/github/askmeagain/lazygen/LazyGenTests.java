package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.calculator.complex.LazyMapStructCalculatorImpl;
import io.github.askmeagain.lazygen.calculator.simple.LazyMapStructInterfaceImpl;
import io.github.askmeagain.lazygen.calculator.simple.LazyMapstructAbstractClass;
import io.github.askmeagain.lazygen.calculator.simple.LazyNormalClass;
import io.github.askmeagain.lazygen.calculator.simple.TestNormalAbstractClass;
import io.github.askmeagain.lazygen.calculator.simple.deepabstract.TestNormalDeepDeepAbstractClass;
import io.github.askmeagain.lazygen.calculator.simple.duplicatemethod.LazyDuplicateClass;
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
    var calculator = new LazyMapStructCalculatorImpl();
    var input = new Input(1);

    //Act ------------------------------------------------------------------------------------
    var result = calculator.map(input);

    //Assert ---------------------------------------------------------------------------------
    var multiplications = result.getMultiplications();
    var summations = result.getSummations();

    Assertions.assertEquals(2, multiplications.getNumber1mul2());
    Assertions.assertEquals(4, multiplications.getNumber1mul4());
    Assertions.assertEquals(8, multiplications.getNumber1mul8());

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


  @Test
  void abstractDeepClassResult() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new TestNormalDeepDeepAbstractClass();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc();

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void abstractDuplicateMethod() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new LazyDuplicateClass();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc("a");
    lazyNormalClass.abc("a");
    lazyNormalClass.abc("a");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(2, LazyGenTestUtils.atomicInteger.get());
  }
}