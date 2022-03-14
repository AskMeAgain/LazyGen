package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.LazyAnnotation;
import io.github.askmeagain.lazygen.other.LazyProcessorUtils;
import io.github.askmeagain.lazygen.other.LazyMethodContainer;
import lombok.SneakyThrows;

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
import java.util.Collection;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("io.github.askmeagain.lazygen.annotations.GenerateProfiler")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class LazyProcessor extends AbstractProcessor {

  @SneakyThrows
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    if (roundEnv.processingOver() || annotations.size() == 0) {
      return false;
    }

    var elementUtils = processingEnv.getElementUtils();

    annotations.stream()
        .map(roundEnv::getElementsAnnotatedWith)
        .flatMap(Collection::stream)
        .forEach(x -> generateLazyGenerator(roundEnv, elementUtils, x));

    return true;
  }

  @SneakyThrows
  private void generateLazyGenerator(RoundEnvironment roundEnv, Elements elementUtils, Element generatorRoot) {
    var outputFullyQualifiedName = getOutputFullyQualifiedName(generatorRoot);

    var oldFullyQualifiedName = generatorRoot.toString();
    var oldGeneratorName = generatorRoot.getSimpleName();
    var newGeneratorName = "Lazy" + oldGeneratorName;
    var newFullyQualifiedName = oldFullyQualifiedName.replace(oldGeneratorName, newGeneratorName);

    var inputFullyQualifiedName = getInputFullyQualifiedName(generatorRoot);
    var packageName = getPackageName(elementUtils, oldFullyQualifiedName);
    var lazyMethods = getLazyMethods(roundEnv, oldGeneratorName);

    LazyProcessorUtils.writeFile(
        processingEnv,
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

  private List<LazyMethodContainer> getLazyMethods(RoundEnvironment roundEnv, Name oldGeneratorName) {
    return roundEnv.getElementsAnnotatedWith(LazyAnnotation.class).stream()
        .map(x -> LazyMethodContainer.builder()
            .methodName(x.getSimpleName().toString())
            .calculatorName(oldGeneratorName.toString())
            .outputType(((ExecutableType) x.asType()).getReturnType().toString())
            .build())
        .toList();
  }

  private String getPackageName(Elements elementUtils, String oldFullyQualifiedName) {
    return elementUtils.getPackageOf(elementUtils.getTypeElement(oldFullyQualifiedName))
        .getQualifiedName()
        .toString();
  }

  private String getInputFullyQualifiedName(Element generatorRoot) {
    var generatorRootTypeElement = (TypeElement) generatorRoot;

    return generatorRootTypeElement.getInterfaces().stream()
        .map(TypeMirror::toString)
        .filter(x -> x.startsWith("io.github.askmeagain.lazygen.annotations.CalculatorInput"))
        .map(x -> x.substring(x.indexOf('<') + 1, x.length() - 1))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("CalculatorInput interface missing on " + generatorRoot));
  }

  private String getOutputFullyQualifiedName(Element generatorRoot) {
    return generatorRoot.getEnclosedElements().stream()
        .filter(x -> x.getSimpleName().toString().equals("map"))
        .map(x -> (ExecutableType) x.asType())
        .map(x -> x.getReturnType().toString())
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Could not find method with name 'map' on the root interface " + generatorRoot));
  }
}
