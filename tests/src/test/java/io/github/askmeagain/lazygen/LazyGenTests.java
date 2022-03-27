package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.calculator.LazyGenTestUtils;
import io.github.askmeagain.lazygen.calculator.complex.MapStructCalculatorLazy;
import io.github.askmeagain.lazygen.calculator.simple.*;
import io.github.askmeagain.lazygen.calculator.simple.deepabstract.TestNormalDeepDeepAbstractClass;
import io.github.askmeagain.lazygen.calculator.simple.duplicatemethod.DuplicateClassLazy;
import io.github.askmeagain.lazygen.calculator.simple.duplicatemethod.MultiUseDuplicateClassLazy;
import io.github.askmeagain.lazygen.calculator.simple.primitive.PrimitiveClassLazy;
import io.github.askmeagain.lazygen.calculator.simple.primitive.PrimitiveClassMultiUseLazy;
import io.github.askmeagain.lazygen.calculator.simple.primitive.PrimitiveInterfaceLazy;
import io.github.askmeagain.lazygen.pojos.input.Input;
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
    var calculator = Mappers.getMapper(MapStructCalculatorLazy.class);
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
    var lazyNormalClass = Mappers.getMapper(MapStructInterfaceLazy.class);

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.map("a");
    lazyNormalClass.map("a");
    lazyNormalClass.map("a");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void multiUseMapStructInterface() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = Mappers.getMapper(MultiUseMapStructInterfaceLazy.class);

    //Act ------------------------------------------------------------------------------------
    var result0 = lazyNormalClass.map("a");
    var result1 = lazyNormalClass.map("a");
    var result2 = lazyNormalClass.map("a");
    var result3 = lazyNormalClass.map("b");
    var result4 = lazyNormalClass.map("b");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(2, LazyGenTestUtils.atomicInteger.get());
    Assertions.assertEquals("a", result0.getOutput());
    Assertions.assertEquals("a", result1.getOutput());
    Assertions.assertEquals("a", result2.getOutput());
    Assertions.assertEquals("b", result3.getOutput());
    Assertions.assertEquals("b", result4.getOutput());
  }

  @Test
  void multiUseOverrideMapStructInterface() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = Mappers.getMapper(MultiUseOverrideMapStructInterfaceLazy.class);

    //Act ------------------------------------------------------------------------------------
    var result0 = lazyNormalClass.map("a");
    var result1 = lazyNormalClass.map("a");
    var result2 = lazyNormalClass.map("a");
    var result3 = lazyNormalClass.map("b");
    var result4 = lazyNormalClass.map("b");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(2, LazyGenTestUtils.atomicInteger.get());
    Assertions.assertEquals("a", result0.getOutput());
    Assertions.assertEquals("a", result1.getOutput());
    Assertions.assertEquals("a", result2.getOutput());
    Assertions.assertEquals("b", result3.getOutput());
    Assertions.assertEquals("b", result4.getOutput());
  }

  @Test
  void mapStructAbstractClass() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = Mappers.getMapper(MapstructAbstractClassLazy.class);
    var input = new Input(1);

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.map(input);
    lazyNormalClass.map(input);
    lazyNormalClass.map(input);

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void classResult() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new NormalClassLazy();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc();

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void multiUseClassResult() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new MultiUseNormalClassLazy();

    //Act ------------------------------------------------------------------------------------
    var result0 = lazyNormalClass.abc("a");
    var result1 = lazyNormalClass.abc("a");
    var result2 = lazyNormalClass.abc("a");
    var result3 = lazyNormalClass.abc("b");
    var result4 = lazyNormalClass.abc("b");
    var result5 = lazyNormalClass.abc("b");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(2, LazyGenTestUtils.atomicInteger.get());
    Assertions.assertEquals("a", result0);
    Assertions.assertEquals("a", result1);
    Assertions.assertEquals("a", result2);
    Assertions.assertEquals("b", result3);
    Assertions.assertEquals("b", result4);
    Assertions.assertEquals("b", result5);
  }

  @Test
  void multiUseWithSingleUseParentNormalClassResult() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new MultiUseWithSingleUseParentNormalClassLazy();

    //Act ------------------------------------------------------------------------------------
    var result0 = lazyNormalClass.singleUse("a");
    var result1 = lazyNormalClass.singleUse("a");
    var result2 = lazyNormalClass.singleUse("b");
    var result3 = lazyNormalClass.singleUse("b");
    var result4 = lazyNormalClass.singleUse("b");
    var result5 = lazyNormalClass.singleUse("b");

    var result6 = lazyNormalClass.multiUse("b");
    var result7 = lazyNormalClass.multiUse("b");
    var result8 = lazyNormalClass.multiUse("c");
    var result9 = lazyNormalClass.multiUse("c");
    var result10 = lazyNormalClass.multiUse("d");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(4, LazyGenTestUtils.atomicInteger.get());
    Assertions.assertEquals("a", result0);
    Assertions.assertEquals("a", result1);
    Assertions.assertEquals("a", result2);
    Assertions.assertEquals("a", result3);
    Assertions.assertEquals("a", result4);
    Assertions.assertEquals("a", result5);
    Assertions.assertEquals("b", result6);
    Assertions.assertEquals("b", result7);
    Assertions.assertEquals("c", result8);
    Assertions.assertEquals("c", result9);
    Assertions.assertEquals("d", result10);
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
  void primitiveClass() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new PrimitiveClassLazy();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.testPrimitive();
    lazyNormalClass.testPrimitive();
    lazyNormalClass.testPrimitive();

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(1, LazyGenTestUtils.atomicInteger.get());
  }

  @Test
  void primitiveInterface() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new PrimitiveInterfaceLazy();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.testPrimitive();
    lazyNormalClass.testPrimitive();
    lazyNormalClass.testPrimitive();

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
    var lazyNormalClass = new DuplicateClassLazy();

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

  @Test
  void multiUseDuplicateMethod() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new MultiUseDuplicateClassLazy();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    var result0 = lazyNormalClass.abc("a");
    var result1 = lazyNormalClass.abc("a");
    var result2 = lazyNormalClass.abc("b");
    var result3 = lazyNormalClass.abc("b");
    var result4 = lazyNormalClass.abc("c");

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(4, LazyGenTestUtils.atomicInteger.get());
    Assertions.assertEquals("a", result0);
    Assertions.assertEquals("a", result1);
    Assertions.assertEquals("b", result2);
    Assertions.assertEquals("b", result3);
    Assertions.assertEquals("c", result4);
  }

  @Test
  void multiUseDuplicateMethodPrimitive() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new PrimitiveClassMultiUseLazy();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.testPrimitive();
    lazyNormalClass.testPrimitive();
    lazyNormalClass.testPrimitive();
    var result0 = lazyNormalClass.testPrimitive2(1, 1);
    var result1 = lazyNormalClass.testPrimitive2(1, 1);
    var result2 = lazyNormalClass.testPrimitive2(2, 2);
    var result3 = lazyNormalClass.testPrimitive2(2, 2);
    var result4 = lazyNormalClass.testPrimitive2(3, 3);

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(4, LazyGenTestUtils.atomicInteger.get());
    Assertions.assertEquals(1, result0);
    Assertions.assertEquals(1, result1);
    Assertions.assertEquals(2, result2);
    Assertions.assertEquals(2, result3);
    Assertions.assertEquals(3, result4);
  }
}