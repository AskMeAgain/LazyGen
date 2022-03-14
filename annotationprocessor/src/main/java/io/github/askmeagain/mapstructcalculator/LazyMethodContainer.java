package io.github.askmeagain.mapstructcalculator;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LazyMethodContainer {

  private static String template = """
      
        private $OUTPUT_TYPE $LAZY_FIELD_NAME;

        @Override
        @Named("$METHOD_NAME")
        public $OUTPUT_TYPE $METHOD_NAME($CALCULATOR_NAME calculator) {
          if ($LAZY_FIELD_NAME != null) {
            return $LAZY_FIELD_NAME;
          }
          $LAZY_FIELD_NAME = $CALCULATOR_NAME.super.$METHOD_NAME(calculator);
          return $LAZY_FIELD_NAME;
        }
      """;

  String methodName;
  String calculatorName;
  String outputType;

  public String computeTemplate() {
    return template
        .replace("$LAZY_FIELD_NAME", "_" + methodName)
        .replace("$METHOD_NAME", methodName)
        .replace("$OUTPUT_TYPE", outputType)
        .replace("$CALCULATOR_NAME", calculatorName);
  }


}
