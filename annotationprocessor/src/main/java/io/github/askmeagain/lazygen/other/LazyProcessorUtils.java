package io.github.askmeagain.lazygen.other;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class LazyProcessorUtils {
  public static void writeFile(
      ProcessingEnvironment processingEnv,
      String fullyQualifiedName,
      String packageName,
      String mapperName,
      String mapperInterface,
      String inputType,
      String outputType,
      List<LazyMethodContainer> lazyMethodContainers,
      List<String> imports
  ) throws IOException {
    JavaFileObject builderFile = processingEnv.getFiler()
        .createSourceFile(fullyQualifiedName);

    try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
      out.println(LazyGenData.MAPPER_TEMPLATE
          .replace("$MAPPER_NAME", mapperName)
          .replace("$PACKAGE", packageName)
          .replace("$MAPPER_INTERFACE", mapperInterface)
          .replace("$INPUT_TYPE", inputType)
          .replace("$OUTPUT_TYPE", outputType)
          .replace("$LAZY_METHODS", lazyMethodContainers.stream().map(LazyMethodContainer::computeTemplate).collect(Collectors.joining("\n")))
          .replace("$IMPORT", imports.stream().map(x -> "import " + x + ";").collect(Collectors.joining("\n")))
      );
    }
  }
}
