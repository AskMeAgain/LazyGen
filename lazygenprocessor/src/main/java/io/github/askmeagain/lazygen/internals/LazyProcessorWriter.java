package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotation.LazyType;
import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.util.stream.Collectors;

import static io.github.askmeagain.lazygen.internals.LazyGenTemplates.*;

class LazyProcessorWriter {

  @SneakyThrows
  public static void createFile(TemplateData lazyTemplateData) {
    var writer = lazyTemplateData.getProcessingEnv()
        .getFiler()
        .createSourceFile(lazyTemplateData.getFullyQualifiedName())
        .openWriter();

    try (var out = new PrintWriter(writer)) {
      out.println(generateTemplateString(lazyTemplateData));
    }
  }

  private static String generateTemplateString(TemplateData lazyTemplateData) {
    return LazyGenTemplates.MAPPER_TEMPLATE
        .replace("$CLASS_INTERFACE", lazyTemplateData.getClassInterface())
        .replace("$MULTI_USE_HELPER", lazyTemplateData.getHasAnyMultiUsage() ? LazyGenTemplates.HELPER_METHOD_MULTI_USE_TEMPLATE : "")
        .replace("$EXTENDS_IMPLEMENTS", lazyTemplateData.getIsInterface() ? "implements" : "extends")
        .replace("$MAPSTRUCT", lazyTemplateData.getMapStructAnnotation() ? "@Mapper" : "")
        .replace("$MAPPER_NAME", lazyTemplateData.getMapperName())
        .replace("$PACKAGE", lazyTemplateData.getPackageName())
        .replace("$MAPPER_INTERFACE", lazyTemplateData.getMapperInterface())
        .replace("$LAZY_METHODS", lazyTemplateData.getLazyMethodContainers().stream()
            .map(methodContainer -> computeMethodTemplate(methodContainer, lazyTemplateData))
            .collect(Collectors.joining("\n")))
        .replace("$IMPORT", lazyTemplateData.getImports().stream()
            .map(imports -> "import " + imports + ";")
            .collect(Collectors.joining("\n")));
  }

  private static String computeMethodTemplate(MethodContainer methodContainer, TemplateData lazyTemplateData) {
    var isMultiUse = methodContainer.getUsage() == LazyType.MULTI_USE ||
        methodContainer.getUsage() == LazyType.PARENT && lazyTemplateData.getParentLazyType() == LazyType.MULTI_USE;

    var oneTimeTemplate = methodContainer.getPrimitive() ? PRIMITIVE_ONE_TIME_USE_METHOD_TEMPLATE : ONE_TIME_USE_METHOD_TEMPLATE;

    var template = isMultiUse ? MULTI_TIME_USE_METHOD_TEMPLATE : oneTimeTemplate;

    return template
        .replace("$LAZY_FIELD_NAME", getLazyFieldName(methodContainer))
        .replace("$METHOD_NAME", methodContainer.getMethodName())
        .replace("$METHOD_ORIGIN_CLASS", lazyTemplateData.getIsInterface() ? methodContainer.getMethodOriginClass() : "")
        .replace("$NAMED", methodContainer.getNamedAnnotation()
            .map(annotation -> "@Named(" + annotation + ")")
            .orElse(""))
        .replace("$PARAMETERS_WITHOUT_TYPE", methodContainer.getParametersWithoutType()
            .stream()
            .collect(Collectors.joining(", ")))
        .replace("$PARAMETERS", methodContainer.getParameters()
            .stream()
            .collect(Collectors.joining(", ")))
        .replace("$OUTPUT_TYPE_ORIGINAL", methodContainer.getOutputTypeOriginal())
        .replace("$OUTPUT_TYPE", methodContainer.getOutputType());
  }

  private static String getLazyFieldName(MethodContainer methodContainer) {
    return "_" + methodContainer.getMethodName() + String.join("_", methodContainer.getParametersWithoutType());
  }
}
