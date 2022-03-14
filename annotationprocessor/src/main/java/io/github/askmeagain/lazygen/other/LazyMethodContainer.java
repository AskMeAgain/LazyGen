package io.github.askmeagain.lazygen.other;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LazyMethodContainer {

  String methodName;
  String calculatorName;
  String outputType;

  public String computeTemplate() {
    return LazyGenData.LAZY_METHOD_TEMPLATE
        .replace("$LAZY_FIELD_NAME", "_" + methodName)
        .replace("$METHOD_NAME", methodName)
        .replace("$OUTPUT_TYPE", outputType)
        .replace("$CALCULATOR_NAME", calculatorName);
  }


}
