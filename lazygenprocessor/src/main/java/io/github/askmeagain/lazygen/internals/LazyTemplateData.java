package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotations.ResultType;
import lombok.Builder;
import lombok.Value;

import javax.annotation.processing.ProcessingEnvironment;
import java.util.List;
import java.util.Optional;

@Value
@Builder
public class LazyTemplateData {
  ResultType resultType;
  boolean isInterface;
  ProcessingEnvironment processingEnv;
  String fullyQualifiedName;
  String packageName;
  String mapperName;
  String mapperInterface;
  Optional<String> inputType;
  Optional<String> outputType;
  List<LazyMethodContainer> lazyMethodContainers;
  List<String> imports;
}
