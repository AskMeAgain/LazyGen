package io.github.askmeagain.lazygen.other;

public class CodeTemplates {

  public static final String MAPPER_TEMPLATE = """
      $PACKAGE

      $IMPORT

      @Mapper
      public abstract class $MAPPER_NAME implements $MAPPER_INTERFACE {

        private $INPUT_TYPE inputs;

        @Override
        public $INPUT_TYPE getInputs(){
          return inputs;
        }

        public $OUTPUT_TYPE calculate($INPUT_TYPE inputs){
          this.inputs = inputs;
          return map(this);
        }

        $LAZY_METHODS
      }
       """;

  public static String LAZY_METHOD_TEMPLATE = """
      
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

}
