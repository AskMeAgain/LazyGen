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

  public List<MethodContainer> getLazyMethods(RoundEnvironment roundEnv, Elements elementUtils, Element generator, boolean isMapStruct) {
    var lazyMethods = roundEnv.getElementsAnnotatedWith(LazyGen.class);

    var interfaces = recursivelyGetChild(elementUtils, generator);

    var resultList = new ArrayList<Element>();

    for (var method : lazyMethods) {
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
          return MethodContainer.builder()
              .methodName(lazyMethod.getSimpleName().toString())
              .methodOriginClass(methodOriginClass)
              .isMapstruct(isMapStruct)
              .parameters(method.getParameterTypes().stream()
                  .map(y -> elementUtils.getTypeElement(y.toString()))
                  .toList())
              .outputType(method.getReturnType().toString())
              .build();
        })
        .toList();
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
