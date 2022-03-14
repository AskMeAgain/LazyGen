package io.github.askmeagain.mapstructcalculator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("io.github.askmeagain.mapstructcalculator.GenerateProfiler")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class LazyProcessor extends AbstractProcessor {

  @lombok.SneakyThrows
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    if (roundEnv.processingOver() || annotations.size() == 0) {
      return false;
    }

    var elementUtils = processingEnv.getElementUtils();

    for (TypeElement annotation : annotations) {
      var annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

      for (var generatorRoot : annotatedElements) {

        var generatorRootTypeElement = (TypeElement) generatorRoot;

        var outputFullyQualifiedName = generatorRoot.getEnclosedElements().stream()
            .filter(x -> x.getSimpleName().toString().equals("map"))
            .map(x -> (ExecutableType) x.asType())
            .map(x -> x.getReturnType().toString())
            .findFirst()
            .get();

        var oldFullyQualifiedName = generatorRoot.toString();
        var oldGeneratorName = generatorRoot.getSimpleName();
        var newGeneratorName = "Lazy" + oldGeneratorName;
        var newFullyQualifiedName = oldFullyQualifiedName.replace(oldGeneratorName, newGeneratorName);
        var packageName = elementUtils.getPackageOf(elementUtils.getTypeElement(oldFullyQualifiedName))
            .getQualifiedName()
            .toString();
        var inputFullyQualifiedName = generatorRootTypeElement.getInterfaces().stream()
            .map(TypeMirror::toString)
            .filter(x -> x.startsWith("io.github.askmeagain.mapstructcalculator.CalculatorInput"))
            .map(x -> x.substring(x.indexOf('<') + 1, x.length() - 1))
            .findFirst()
            .get();

        var lazyMethods = roundEnv.getElementsAnnotatedWith(LazyAnnotation.class).stream()
            .map(x -> LazyMethodContainer.builder()
                .methodName(x.getSimpleName().toString())
                .calculatorName(oldGeneratorName.toString())
                .outputType(((ExecutableType) x.asType()).getReturnType().toString())
                .build())
            .toList();

        writeFile(
            newFullyQualifiedName,
            "package " + packageName + ";",
            newGeneratorName,
            oldGeneratorName.toString(),
            elementUtils.getTypeElement(inputFullyQualifiedName).getSimpleName().toString(),
            elementUtils.getTypeElement(outputFullyQualifiedName).getSimpleName().toString(),
            lazyMethods,
            List.of(
                outputFullyQualifiedName,
                inputFullyQualifiedName,
                oldFullyQualifiedName,
                "org.mapstruct.Mapper",
                "org.mapstruct.Named"
            )
        );
      }
    }

    return true;
  }

  private void writeFile(
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
      out.println(LazyProcessorUtils.TEMPLATE
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
