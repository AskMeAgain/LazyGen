package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotation.LazyGen;
import io.github.askmeagain.lazygen.annotation.ResultType;
import lombok.RequiredArgsConstructor;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.*;

@RequiredArgsConstructor
class LazyGenDataCollector {

  private final RoundEnvironment roundEnv;
  private final Elements elementUtils;
  private final Types typeUtils;

  public ArrayList<String> getImportList(String oldFullyQualifiedName, boolean oneTimeUse) {
    var importList = new ArrayList<String>();

    importList.add(oldFullyQualifiedName);
    importList.add("org.mapstruct.Mapper");
    importList.add("org.mapstruct.Named");

    if (!oneTimeUse) {
      importList.add("java.util.Map");
      importList.add("java.util.concurrent.ConcurrentHashMap");
    }

    return importList;
  }

  public List<MethodContainer> getLazyMethods(Element generator, ResultType resultType) {
    var interfaces = recursivelyGetChild(generator);

    return roundEnv.getElementsAnnotatedWith(LazyGen.class).stream()
        .filter(x -> interfaces.contains(x.getEnclosingElement().toString()))
        .map(lazyMethod -> fillMethodContainer(generator, lazyMethod, resultType))
        .toList();
  }

  public MethodContainer fillMethodContainer(
      Element generator,
      Element lazyMethod,
      ResultType resultType
  ) {
    var method = ((ExecutableType) lazyMethod.asType());
    var methodOriginClass = generator.getSimpleName().toString() + ".";

    var foundNamed = getNamedAnnotation(lazyMethod, resultType);
    var returnType = method.getReturnType();

    var primitive = returnType.getKind().isPrimitive();
    var outputType = returnType.toString();
    if (primitive) {
      outputType = typeUtils.boxedClass((PrimitiveType) returnType).toString();
    }

    var parametersWithType = new ArrayList<String>();
    var parametersWithoutType = new ArrayList<String>();
    var parameterTypes = method.getParameterTypes();

    for (int i = 0; i < parameterTypes.size(); i++) {
      var parameterType = parameterTypes.get(i);

      if (parameterType.getKind().isPrimitive()) {
        parametersWithType.add(parameterType + " " + parameterType + i);
        parametersWithoutType.add(parameterType.toString() + i);
      } else {
        var typeElement = elementUtils.getTypeElement(parameterType.toString());
        parametersWithType.add(typeElement.getQualifiedName() + " " + typeElement.getSimpleName() + i);
        parametersWithoutType.add(typeElement.getSimpleName().toString() + i);
      }
    }

    return MethodContainer.builder()
        .primitive(primitive)
        .usage(lazyMethod.getAnnotation(LazyGen.class).usage())
        .methodName(lazyMethod.getSimpleName().toString())
        .methodOriginClass(methodOriginClass)
        .namedAnnotation(foundNamed)
        .parameters(parametersWithType)
        .parametersWithoutType(parametersWithoutType)
        .outputType(outputType)
        .outputTypeOriginal(returnType.toString())
        .build();
  }

  private Optional<String> getNamedAnnotation(Element lazyMethod, ResultType resultType) {
    if (!resultType.isMapStruct()) {
      return Optional.empty();
    }

    return lazyMethod.getAnnotationMirrors().stream()
        .map(Object::toString)
        .filter(x -> x.startsWith("@org.mapstruct.Named"))
        .map(x -> x.substring(x.indexOf("(") + 1, x.indexOf(")")))
        .findFirst();
  }

  public Set<String> recursivelyGetChild(Element generator) {
    if (generator == null) {
      return Collections.emptySet();
    }

    var typeElement = elementUtils.getTypeElement(generator.toString());
    var result = new HashSet<String>();

    //superclasses
    var parent = typeElement.getSuperclass().toString();
    if (!parent.equals("none") && !parent.equals("java.lang.Object")) {
      result.addAll(recursivelyGetChild(elementUtils.getTypeElement(parent)));
    }

    //interfaces
    for (var inter : typeElement.getInterfaces()) {
      result.addAll(recursivelyGetChild(elementUtils.getTypeElement(inter.toString())));
    }

    result.add(generator.toString());

    return result;
  }

  public String getPackageName(Elements elementUtils, String oldFullyQualifiedName) {
    return elementUtils.getPackageOf(elementUtils.getTypeElement(oldFullyQualifiedName))
        .getQualifiedName()
        .toString();
  }
}
