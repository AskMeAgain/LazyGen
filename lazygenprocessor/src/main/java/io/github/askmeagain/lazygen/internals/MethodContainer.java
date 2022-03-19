package io.github.askmeagain.lazygen.internals;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Optional;

@Value
@Builder
class MethodContainer {

  @NonNull Optional<String> namedAnnotation;
  @NonNull String methodOriginClass;
  @NonNull String methodName;
  @NonNull String outputType;
  @NonNull List<@NonNull TypeElement> parameters;

  public static class MethodContainerBuilder {
    //for javadoc
  }
}
