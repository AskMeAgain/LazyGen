package io.github.askmeagain.lazygen.other;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class TemplateData {

  @NonNull String classInterface;
  @NonNull String extendsImplements;
  @NonNull String isAbstractMap;
  @NonNull String mapStructMapperTemplate;
  @NonNull String inputMethod;
  @NonNull String mapperName;
  @NonNull String packageName;
  @NonNull String mapperInterface;
  List<LazyMethodContainer> lazyMethodContainers;
  List<@NonNull String> imports;

}