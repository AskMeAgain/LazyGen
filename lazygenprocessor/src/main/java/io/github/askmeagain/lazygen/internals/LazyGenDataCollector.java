package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotation.LazyGen;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.util.Elements;
import java.util.*;

class LazyGenDataCollector {

  public ArrayList<String> getImportList(String oldFullyQualifiedName) {
    var importList = new ArrayList<String>();

    importList.add(oldFullyQualifiedName);
    importList.add("org.mapstruct.Mapper");
    importList.add("org.mapstruct.Named");

    return importList;
  }

  public List<MethodContainer> getLazyMethods(RoundEnvironment roundEnv, Elements elementUtils, Element generator) {
    var interfaces = recursivelyGetChild(elementUtils, generator);

    return roundEnv.getElementsAnnotatedWith(LazyGen.class).stream()
        .filter(x -> interfaces.contains(x.getEnclosingElement().toString()))
        .map(lazyMethod -> fillMethodContainer(elementUtils, generator, lazyMethod))
        .toList();
  }

  public MethodContainer fillMethodContainer(Elements elementUtils, Element generator, Element lazyMethod) {
    var method = ((ExecutableType) lazyMethod.asType());
    var methodOriginClass = generator.getSimpleName().toString() + ".";

    var foundNamed = lazyMethod.getAnnotationMirrors().stream()
        .map(Object::toString)
        .filter(x -> x.startsWith("@org.mapstruct.Named"))
        .map(x -> x.substring(x.indexOf("(") + 1, x.indexOf(")")))
        .findFirst();

    return MethodContainer.builder()
        .methodName(lazyMethod.getSimpleName().toString())
        .methodOriginClass(methodOriginClass)
        .foundNamed(foundNamed)
        .parameters(method.getParameterTypes().stream()
            .map(parameterType -> elementUtils.getTypeElement(parameterType.toString()))
            .toList())
        .outputType(method.getReturnType().toString())
        .build();
  }

  public Set<String> recursivelyGetChild(Elements elementUtils, Element generator) {
    if (generator == null) {
      return Collections.emptySet();
    }

    var typeElement = elementUtils.getTypeElement(generator.toString());
    var result = new HashSet<String>();

    //superclasses
    var parent = typeElement.getSuperclass().toString();
    if (!parent.equals("none") && !parent.equals("java.lang.Object")) {
      result.addAll(recursivelyGetChild(elementUtils, elementUtils.getTypeElement(parent)));
    }

    //interfaces
    for (var inter : typeElement.getInterfaces()) {
      result.addAll(recursivelyGetChild(elementUtils, elementUtils.getTypeElement(inter.toString())));
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
