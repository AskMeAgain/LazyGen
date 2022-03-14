package io.github.askmeagain.mapstructcalculator;

public class LazyProcessorUtils {

  public static final String TEMPLATE = """
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

}
