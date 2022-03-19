package io.github.askmeagain.lazygen.internals;

class LazyGenTemplates {

  public static final String MAPSTRUCT_GENERATOR_ANNOTATION_PATH = "io.github.askmeagain.lazygen.annotation.GenerateLazyClass";

  public static final String MAPPER_TEMPLATE = """
      package $PACKAGE;

      $IMPORT

      $MAPSTRUCT
      public $CLASS_INTERFACE $MAPPER_NAME $EXTENDS_IMPLEMENTS $MAPPER_INTERFACE {

      $LAZY_METHODS
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
