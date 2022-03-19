package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotations.ResultType;
import lombok.SneakyThrows;

import javax.lang.model.element.TypeElement;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LazyProcessorWriter {

  @SneakyThrows
  public static void createFile(LazyTemplateData lazyTemplateData) {
    try (var out = new PrintWriter(lazyTemplateData.getProcessingEnv().getFiler().createSourceFile(lazyTemplateData.getFullyQualifiedName()).openWriter())) {

      var inputMethod = getInputMethod(lazyTemplateData.getInputType(), lazyTemplateData.getOutputType());

      var template = generateTemplateData(lazyTemplateData.getResultType())
          .imports(lazyTemplateData.getImports())
          .extendsImplements(lazyTemplateData.isInterface())
          .lazyMethodContainers(lazyTemplateData.getLazyMethodContainers())
          .packageName(lazyTemplateData.getPackageName())
          .inputMethod(inputMethod)
          .mapperName(lazyTemplateData.getMapperName())
          .mapperInterface(lazyTemplateData.getMapperInterface())
          .build();

      out.println(generateTemplateString(template));
    }
  }

  private static String getInputMethod(Optional<String> inputType, Optional<String> outputType) {
    if (inputType.isPresent() && outputType.isPresent()) {
      return LazyGenTemplates.INPUT_TEMPLATE
          .replace("$INPUT_TYPE", inputType.get())
          .replace("$OUTPUT_TYPE", outputType.get());
    }
    return "";
  }

  private static TemplateData.TemplateDataBuilder generateTemplateData(ResultType resultType) {
    return switch (resultType) {
      case ABSTRACT_CLASS -> TemplateData.builder()
          .classInterface("abstract class ")
          .mapStructMapperTemplate(false);
      case CLASS -> TemplateData.builder()
          .classInterface("class ")
          .mapStructMapperTemplate(false);
      case MAPSTRUCT_COMPATIBLE -> TemplateData.builder()
          .classInterface("abstract class ")
          .mapStructMapperTemplate(true);
    };
  }

  private static String generateTemplateString(TemplateData templateData) {
    return LazyGenTemplates.MAPPER_TEMPLATE
        .replace("$CLASS_INTERFACE", templateData.getClassInterface())
        .replace("$EXTENDS_IMPLEMENTS", templateData.getExtendsImplements() ? "implements" : "extends")
        .replace("$MAPSTRUCT", templateData.getMapStructMapperTemplate() ? "@Mapper" : "")
        .replace("$INPUT_METHOD", templateData.getInputMethod())
        .replace("$MAPPER_NAME", templateData.getMapperName())
        .replace("$PACKAGE", templateData.getPackageName())
        .replace("$MAPPER_INTERFACE", templateData.getMapperInterface())
        .replace("$LAZY_METHODS", templateData.getLazyMethodContainers().stream()
            .map(LazyProcessorWriter::computeMethodTemplate)
            .collect(Collectors.joining("\n")))
        .replace("$IMPORT", templateData.getImports().stream()
            .map(imports -> "import " + imports + ";")
            .collect(Collectors.joining("\n")));
  }

  private static String computeMethodTemplate(LazyMethodContainer methodContainer) {
    var atomicInt = new AtomicInteger();
    return LazyGenTemplates.LAZY_METHOD_TEMPLATE
        .replace("$NAMED", methodContainer.getIsMapstruct() ? "@Named(\"$METHOD_NAME\")" : "")
        .replace("$LAZY_FIELD_NAME", getLazyFieldName(methodContainer))
        .replace("$METHOD_NAME", methodContainer.getMethodName())
        .replace("$METHOD_ORIGIN_CLASS", methodContainer.getMethodOriginClass())
        .replace("$PARAMETERS_WITHOUT_TYPE", methodContainer.getParameters().stream()
            .map(x -> "_" + x.getSimpleName() + atomicInt.get())
            .collect(Collectors.joining(",")))
        .replace("$PARAMETERS", methodContainer.getParameters().stream()
            .map(x -> x.getQualifiedName() + " _" + x.getSimpleName() + atomicInt.getAndIncrement())
            .collect(Collectors.joining(",")))
        .replace("$OUTPUT_TYPE", methodContainer.getOutputType());
  }

  private static String getLazyFieldName(LazyMethodContainer methodContainer) {
    var collectedTypes = methodContainer.getParameters().stream()
        .map(TypeElement::getSimpleName)
        .collect(Collectors.joining("_"));

    return "_" + methodContainer.getMethodName() + collectedTypes;
  }
}
