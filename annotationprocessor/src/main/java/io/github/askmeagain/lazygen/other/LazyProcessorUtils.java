package io.github.askmeagain.lazygen.other;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LazyProcessorUtils {
  public static void writeFile(
      boolean isMapStructMapper,
      ProcessingEnvironment processingEnv,
      String fullyQualifiedName,
      String packageName,
      String mapperName,
      String mapperInterface,
      Optional<String> inputType,
      Optional<String> outputType,
      List<LazyMethodContainer> lazyMethodContainers,
      List<String> imports
  ) throws IOException {
    JavaFileObject builderFile = processingEnv.getFiler()
        .createSourceFile(fullyQualifiedName);

    try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

      var inputMethod = "";
      if (inputType.isPresent() && outputType.isPresent()) {
        inputMethod = LazyGenData.INPUT_TEMPLATE
            .replace("$INPUT_TYPE", inputType.orElse("NOT FOUND"))
            .replace("$OUTPUT_TYPE", outputType.orElse("NOT FOUND"));
      }

      var mapStructMapperTemplate = "";
      var isAbstractMap = "";
      var extendsImplements = "extends";
      if (isMapStructMapper) {
        extendsImplements = "implements";
        isAbstractMap = "abstract ";
        mapStructMapperTemplate = "@Mapper";
      }

      out.println(LazyGenData.MAPPER_TEMPLATE
          .replace("$EXTENDS_IMPLEMENTS", extendsImplements)
          .replace("$ABSTRACT", isAbstractMap)
          .replace("$MAPSTRUCT", mapStructMapperTemplate)
          .replace("$INPUT_METHOD", inputMethod)
          .replace("$MAPPER_NAME", mapperName)
          .replace("$PACKAGE", packageName)
          .replace("$MAPPER_INTERFACE", mapperInterface)
          .replace("$LAZY_METHODS", lazyMethodContainers.stream()
              .map(LazyMethodContainer::computeTemplate)
              .collect(Collectors.joining("\n")))
          .replace("$IMPORT", imports.stream().map(x -> "import " + x + ";").collect(Collectors.joining("\n")))
      );
    }
  }
}
