package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotation.GenerateLazyClass;
import io.github.askmeagain.lazygen.annotation.LazyType;
import io.github.askmeagain.lazygen.annotation.ResultType;
import lombok.SneakyThrows;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Collection;
import java.util.Set;

import static io.github.askmeagain.lazygen.internals.LazyGenTemplates.MAPSTRUCT_GENERATOR_ANNOTATION_PATH;

@SupportedAnnotationTypes({MAPSTRUCT_GENERATOR_ANNOTATION_PATH})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class LazyGenProcessor extends AbstractProcessor {

  @Override
  @SneakyThrows
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    if (roundEnv.processingOver() || annotations.size() == 0) {
      return false;
    }

    annotations.stream()
        .map(roundEnv::getElementsAnnotatedWith)
        .flatMap(Collection::stream)
        .map(generatorClass -> collectData(roundEnv, generatorClass))
        .forEach(LazyProcessorWriter::createFile);

    return true;
  }

  private TemplateData collectData(RoundEnvironment roundEnv, Element root) {
    var elementUtils = processingEnv.getElementUtils();
    var realAnnotation = root.getAnnotation(GenerateLazyClass.class);
    var dataCollector = new LazyGenDataCollector();

    var oldFullyQualifiedName = root.toString();
    var oldGeneratorName = root.getSimpleName();
    var newGeneratorName = oldGeneratorName + "Lazy";

    var lazyMethods = dataCollector.getLazyMethods(roundEnv, elementUtils, root);
    var hasMultiUsage = lazyMethods.stream()
        .filter(x -> x.getUsage() == LazyType.MULTI_USE)
        .findFirst()
        .map(x -> true)
        .orElse(realAnnotation.usage() == LazyType.MULTI_USE);

    return generateTemplateData(realAnnotation.value())
        .hasAnyMultiUsage(hasMultiUsage)
        .parentLazyType(realAnnotation.usage())
        .resultType(realAnnotation.value())
        .isInterface(ElementKind.INTERFACE == root.getKind())
        .processingEnv(processingEnv)
        .fullyQualifiedName(oldFullyQualifiedName.replace(oldGeneratorName, newGeneratorName))
        .packageName(dataCollector.getPackageName(elementUtils, oldFullyQualifiedName))
        .mapperName(newGeneratorName)
        .mapperInterface(oldGeneratorName.toString())
        .lazyMethodContainers(lazyMethods)
        .imports(dataCollector.getImportList(oldFullyQualifiedName, !hasMultiUsage))
        .build();
  }

  private static TemplateData.TemplateDataBuilder generateTemplateData(ResultType resultType) {
    return switch (resultType) {
      case ABSTRACT_CLASS, MAPSTRUCT_COMPATIBLE_WITHOUT_ANNOTATION -> TemplateData.builder()
          .classInterface("abstract class ")
          .mapStructAnnotation(false);
      case CLASS -> TemplateData.builder()
          .classInterface("class ")
          .mapStructAnnotation(false);
      case MAPSTRUCT_COMPATIBLE -> TemplateData.builder()
          .classInterface("abstract class ")
          .mapStructAnnotation(true);
    };
  }
}
