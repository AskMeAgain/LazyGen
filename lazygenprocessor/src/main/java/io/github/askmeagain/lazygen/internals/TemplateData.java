package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotation.ResultType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.processing.ProcessingEnvironment;
import java.util.List;

@Value
@Builder
class TemplateData {

  @NonNull ProcessingEnvironment processingEnv;

  @NonNull ResultType resultType;
  @NonNull Boolean isInterface;
  @NonNull String fullyQualifiedName;
  @NonNull String packageName;
  @NonNull String mapperName;
  @NonNull String mapperInterface;
  @NonNull String classInterface;
  @NonNull Boolean mapStructMapperTemplate;

  @NonNull List<MethodContainer> lazyMethodContainers;
  @NonNull List<String> imports;

  public static class TemplateDataBuilder {
    //for javadocs
  }
}
