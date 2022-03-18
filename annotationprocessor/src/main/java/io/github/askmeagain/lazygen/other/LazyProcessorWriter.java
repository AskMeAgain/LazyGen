package io.github.askmeagain.lazygen.other;

import javax.annotation.processing.ProcessingEnvironment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LazyProcessorWriter {
  public static void writeFile(
      ResultType resultType,
      boolean isInterface,
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
    var builderFile = processingEnv.getFiler().createSourceFile(fullyQualifiedName);

    try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

      var inputMethod = "";
      if (inputType.isPresent() && outputType.isPresent()) {
        inputMethod = LazyGenData.INPUT_TEMPLATE
            .replace("$INPUT_TYPE", inputType.orElse("NOT FOUND"))
            .replace("$OUTPUT_TYPE", outputType.orElse("NOT FOUND"));
      }

      var template = generateTemplateData(resultType, isInterface)
          .imports(imports)
          .lazyMethodContainers(lazyMethodContainers)
          .packageName(packageName)
          .inputMethod(inputMethod)
          .mapperName(mapperName)
          .mapperInterface(mapperInterface)
          .build();

      out.println(generateTemplate(template));
    }
  }

  private static TemplateData.TemplateDataBuilder generateTemplateData(ResultType resultType, boolean isInterface) {

    return switch (resultType) {
      case ABSTRACT_CLASS -> TemplateData.builder()
          .classInterface("class ")
          .isAbstractMap("abstract ")
          .extendsImplements(isInterface ? "implements " : "extends ")
          .mapStructMapperTemplate("");
      case CLASS -> TemplateData.builder()
          .classInterface("class ")
          .isAbstractMap("")
          .extendsImplements(isInterface ? "implements " : "extends ")
          .mapStructMapperTemplate("");
      case MAPSTRUCT_COMPATIBLE -> TemplateData.builder()
          .classInterface("class ")
          .isAbstractMap("abstract ")
          .extendsImplements(isInterface ? "implements " : "extends ")
          .mapStructMapperTemplate("@Mapper ");
    };

  }

  private static String generateTemplate(TemplateData templateData) {

    var result = LazyGenData.MAPPER_TEMPLATE;

    for (int i = 0; i < 2; i++) {
      result = result.replace("$CLASS_INTERFACE", templateData.getClassInterface())
          .replace("$EXTENDS_IMPLEMENTS", templateData.getExtendsImplements())
          .replace("$ABSTRACT", templateData.getIsAbstractMap())
          .replace("$MAPSTRUCT", templateData.getMapStructMapperTemplate())
          .replace("$INPUT_METHOD", templateData.getInputMethod())
          .replace("$MAPPER_NAME", templateData.getMapperName())
          .replace("$PACKAGE", templateData.getPackageName())
          .replace("$MAPPER_INTERFACE", templateData.getMapperInterface())
          .replace("$LAZY_METHODS", templateData.getLazyMethodContainers().stream()
              .map(LazyMethodContainer::computeTemplate)
              .collect(Collectors.joining("\n")))
          .replace("$IMPORT", templateData.getImports().stream()
              .map(x -> "import " + x + ";")
              .collect(Collectors.joining("\n")));
    }

    return result;
  }
}
