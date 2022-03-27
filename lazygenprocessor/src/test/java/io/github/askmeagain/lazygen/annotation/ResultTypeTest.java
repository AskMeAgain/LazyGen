package io.github.askmeagain.lazygen.annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTypeTest {

  @ParameterizedTest
  @CsvSource({
      "ABSTRACT_CLASS,false",
      "CLASS,false",
      "MAPSTRUCT_COMPATIBLE,true",
      "MAPSTRUCT_COMPATIBLE_WITHOUT_ANNOTATION,true"
  })
  void isMapStruct(ResultType resultType, boolean expected) {
    //Arrange --------------------------------------------------------------------------------
    //Act ------------------------------------------------------------------------------------
    var result = resultType.isMapStruct();

    //Assert ---------------------------------------------------------------------------------
    Assertions.assertEquals(expected, result);
  }
}