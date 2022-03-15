package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.annotations.LazyGen;
import io.github.askmeagain.lazygen.annotations.GenerateLazyClass;
import io.github.askmeagain.lazygen.other.LazyGenData;
import io.github.askmeagain.lazygen.other.LazyMethodContainer;
import io.github.askmeagain.lazygen.other.LazyProcessorWriter;
import lombok.SneakyThrows;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.*;

import static io.github.askmeagain.lazygen.other.LazyGenData.MAPSTRUCT_GENERATOR_ANNOTATION_PATH;

@SupportedAnnotationTypes({MAPSTRUCT_GENERATOR_ANNOTATION_PATH})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class LazyProcessor extends AbstractProcessor {

  @Override
  @SneakyThrows
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    if (roundEnv.processingOver() || annotations.size() == 0) {
      return false;
    }

    var elementUtils = processingEnv.getElementUtils();

    annotations.stream()
        .map(roundEnv::getElementsAnnotatedWith)
        .flatMap(Collection::stream)
        .forEach(generator -> generateLazyGenerator(roundEnv, elementUtils, generator));

    return true;
  }

  @SneakyThrows
  private void generateLazyGenerator(RoundEnvironment roundEnv, Elements elementUtils, Element generatorRoot) {
    var outputFullyQualifiedName = getOutputFullyQualifiedName(generatorRoot);

    var realAnnotation = generatorRoot.getAnnotation(GenerateLazyClass.class);
    var isMapStruct = realAnnotation.mapstruct();
    var oldFullyQualifiedName = generatorRoot.toString();
    var oldGeneratorName = generatorRoot.getSimpleName();
    var newGeneratorName = "Lazy" + oldGeneratorName;
    var newFullyQualifiedName = oldFullyQualifiedName.replace(oldGeneratorName, newGeneratorName);

    var inputFullyQualifiedName = getInputFullyQualifiedName(generatorRoot);
    var packageName = getPackageName(elementUtils, oldFullyQualifiedName);
    var lazyMethods = getLazyMethods(roundEnv, elementUtils, generatorRoot, isMapStruct);

    var importList = new ArrayList<String>();

    importList.add(oldFullyQualifiedName);
    importList.add("org.mapstruct.Mapper");
    importList.add("org.mapstruct.Named");

    outputFullyQualifiedName.ifPresent(importList::add);
    inputFullyQualifiedName.ifPresent(importList::add);

    LazyProcessorWriter.writeFile(
        isMapStruct,
        processingEnv,
        newFullyQualifiedName,
        "package " + packageName + ";",
        newGeneratorName,
        oldGeneratorName.toString(),
        inputFullyQualifiedName.map(x -> elementUtils.getTypeElement(x).getSimpleName().toString()),
        outputFullyQualifiedName.map(x -> elementUtils.getTypeElement(x).getSimpleName().toString()),
        lazyMethods,
        importList
    );
  }

  private List<LazyMethodContainer> getLazyMethods(RoundEnvironment roundEnv, Elements elementUtils, Element generator, boolean isMapStruct) {
    var lazyMethods = roundEnv.getElementsAnnotatedWith(LazyGen.class);

    var interfaces = recursivelyGetInterfaces(elementUtils, generator);

    var resultList = new ArrayList<Element>();

    for (Element method : lazyMethods) {
      var parent = method.getEnclosingElement();
      if (interfaces.contains(parent.toString())) {
        resultList.add(method);
      }
    }

    return resultList.stream()
        .map(lazyMethod -> {
          var method = ((ExecutableType) lazyMethod.asType());
          var methodOriginClass = "";
          if (isMapStruct) {
            methodOriginClass = generator.getSimpleName().toString() + ".";
          }
          return LazyMethodContainer.builder()
              .methodName(lazyMethod.getSimpleName().toString())
              .methodOriginClass(methodOriginClass)
              .parameters(method.getParameterTypes().stream()
                  .map(y -> elementUtils.getTypeElement(y.toString()))
                  .map(y -> y.getSimpleName().toString())
                  .toList())
              .outputType(method.getReturnType().toString())
              .build();
        })
        .toList();
  }

  private Set<String> recursivelyGetInterfaces(Elements elementUtils, Element generator) {
    if (generator == null) {
      return Collections.emptySet();
    }

    List<? extends TypeMirror> interfaces = elementUtils.getTypeElement(generator.toString()).getInterfaces();

    var result = new HashSet<String>();
    result.add(generator.toString());

    for (var inter : interfaces) {
      result.addAll(recursivelyGetInterfaces(elementUtils, elementUtils.getTypeElement(inter.toString())));
    }

    return result;
  }

  private String getPackageName(Elements elementUtils, String oldFullyQualifiedName) {
    return elementUtils.getPackageOf(elementUtils.getTypeElement(oldFullyQualifiedName))
        .getQualifiedName()
        .toString();
  }

  private Optional<String> getInputFullyQualifiedName(Element generatorRoot) {
    var generatorRootTypeElement = (TypeElement) generatorRoot;

    return generatorRootTypeElement.getInterfaces().stream()
        .map(TypeMirror::toString)
        .filter(x -> x.startsWith(LazyGenData.LAZY_INPUT_INTERFACE_PATH))
        .map(x -> x.substring(x.indexOf('<') + 1, x.length() - 1))
        .findFirst();
  }

  private Optional<String> getOutputFullyQualifiedName(Element generatorRoot) {
    return generatorRoot.getEnclosedElements().stream()
        .filter(x -> x.getSimpleName().toString().equals("map"))
        .map(x -> (ExecutableType) x.asType())
        .map(x -> x.getReturnType().toString())
        .findFirst();
  }
}
