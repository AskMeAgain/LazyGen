package io.github.askmeagain.lazygen.other;

public class LazyGenData {

  public static final String LAZY_INPUT_INTERFACE_PATH = "io.github.askmeagain.lazygen.annotations.LazyGenInput";
  public static final String MAPSTRUCT_GENERATOR_ANNOTATION_PATH = "io.github.askmeagain.lazygen.annotations.GenerateLazyClass";

  public static final String MAPPER_TEMPLATE = """
      package $PACKAGE;

      $IMPORT

      $MAPSTRUCT
      public $CLASS_INTERFACE $MAPPER_NAME $EXTENDS_IMPLEMENTS $MAPPER_INTERFACE {
      $INPUT_METHOD
      $LAZY_METHODS
      }
       """;

  public static String INPUT_TEMPLATE = """
        private $INPUT_TYPE inputs;
  
        @Override
        public $INPUT_TYPE getInputs(){
          return inputs;
        }
  
        public $OUTPUT_TYPE calculate($INPUT_TYPE inputs){
          this.inputs = inputs;
          return map(this);
        }
      """;

  public static String LAZY_METHOD_TEMPLATE = """
        $NAMED
        @Override
        public $OUTPUT_TYPE $METHOD_NAME($PARAMETERS) {
          if ($LAZY_FIELD_NAME != null) {
            return $LAZY_FIELD_NAME;
          }
          $LAZY_FIELD_NAME = $METHOD_ORIGIN_CLASSsuper.$METHOD_NAME($PARAMETERS_WITHOUT_TYPE);
          return $LAZY_FIELD_NAME;
        }
        private $OUTPUT_TYPE $LAZY_FIELD_NAME;
        
      """;

}
