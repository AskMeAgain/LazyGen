package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.entities.input.Inputs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class TestCalculatorTest {

  @Test
  void resultTest() {
    //Arrange --------------------------------------------------------------------------------
    var lazyMapper1 = Mappers.getMapper(LazyTestCalculator2.class);
    var lazyMapper2 = Mappers.getMapper(LazyTestCalculator.class);
    var oldMapper = Mappers.getMapper(NonLazyWrapper.class);
    var input = new Inputs(1, 1);

    //Act ------------------------------------------------------------------------------------
    var lazyResult1 = lazyMapper1.calculate(input);
    var lazyResult2 = lazyMapper2.calculate(input);
    var oldResult = oldMapper.calculate(input);

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(lazyResult1, oldResult);
    Assertions.assertEquals(lazyResult2, oldResult);
    Assertions.assertEquals(lazyResult1, lazyResult2);
  }

  @Test
  void normalLazyTest() {
    //Arrange --------------------------------------------------------------------------------
    var lazyNormalClass = new LazyNormalClass();

    //Act ------------------------------------------------------------------------------------
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc();
    lazyNormalClass.abc();

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(lazyNormalClass.getCounter().get(), 1);
  }


}