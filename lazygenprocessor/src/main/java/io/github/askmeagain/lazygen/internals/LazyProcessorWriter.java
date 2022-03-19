package io.github.askmeagain.lazygen.internals;

import lombok.SneakyThrows;

import javax.lang.model.element.TypeElement;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        .replace("$EXTENDS_IMPLEMENTS", lazyTemplateData.getIsInterface() ? "implements" : "extends")
        .replace("$MAPSTRUCT", lazyTemplateData.getMapStructMapperTemplate() ? "@Mapper" : "")
        .replace("$MAPPER_NAME", lazyTemplateData.getMapperName())
        .replace("$PACKAGE", lazyTemplateData.getPackageName())
        .replace("$MAPPER_INTERFACE", lazyTemplateData.getMapperInterface())
        .replace("$LAZY_METHODS", lazyTemplateData.getLazyMethodContainers().stream()
            .map(LazyProcessorWriter::computeMethodTemplate)
            .collect(Collectors.joining("\n")))
        .replace("$IMPORT", lazyTemplateData.getImports().stream()
            .map(imports -> "import " + imports + ";")
            .collect(Collectors.joining("\n")));
  }

  private static String computeMethodTemplate(MethodContainer methodContainer) {
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

  private static String getLazyFieldName(MethodContainer methodContainer) {
    var collectedTypes = methodContainer.getParameters().stream()
        .map(TypeElement::getSimpleName)
        .collect(Collectors.joining("_"));

    return "_" + methodContainer.getMethodName() + collectedTypes;
  }
}
