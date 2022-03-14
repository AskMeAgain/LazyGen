package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.entities.input.Inputs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class TestCalculatorTest {

  @Test
  void resultTest() {
    //Arrange --------------------------------------------------------------------------------
    var lazyMapper = Mappers.getMapper(LazyTestCalculator.class);
    var oldMapper = Mappers.getMapper(NonLazyWrapper.class);
    var input = new Inputs(1, 1);

    //Act ------------------------------------------------------------------------------------
    var lazyResult = lazyMapper.calculate(input);
    var oldResult = oldMapper.calculate(input);

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(lazyResult, oldResult);
  }

}