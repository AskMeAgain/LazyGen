package io.github.askmeagain.lazygen.internals;

class LazyGenTemplates {

  public static final String MAPSTRUCT_GENERATOR_ANNOTATION_PATH = "io.github.askmeagain.lazygen.annotation.GenerateLazyClass";

  public static final String MAPPER_TEMPLATE = """
      package $PACKAGE;

      $IMPORT

      $MAPSTRUCT
      public $CLASS_INTERFACE $MAPPER_NAME $EXTENDS_IMPLEMENTS $MAPPER_INTERFACE {

      $LAZY_METHODS
      $MULTI_USE_HELPER
      }
       """;

  public static String ONE_TIME_USE_METHOD_TEMPLATE = """
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

  public static String MULTI_TIME_USE_METHOD_TEMPLATE = """
        $NAMED
        @Override
        public $OUTPUT_TYPE $METHOD_NAME($PARAMETERS) {
          var combined = lazyHelperCombine($PARAMETERS_WITHOUT_TYPE);
          if ($LAZY_FIELD_NAME.containsKey(combined)) {
            return $LAZY_FIELD_NAME.get(combined);
          }
          var result = $METHOD_ORIGIN_CLASSsuper.$METHOD_NAME($PARAMETERS_WITHOUT_TYPE);
          $LAZY_FIELD_NAME.put(combined,result);
          return result;
        }
        private Map<java.lang.String,$OUTPUT_TYPE> $LAZY_FIELD_NAME = new ConcurrentHashMap<>();
        
      """;

  public static String HELPER_METHOD_MULTI_USE_TEMPLATE = """
        private String lazyHelperCombine(Object... params){
          var stringBuilder = new StringBuilder();
          for(int i = 0; i < params.length; i++){
            stringBuilder.append(params[i].hashCode());
          }
          return stringBuilder.toString();
        }
      """;

}
