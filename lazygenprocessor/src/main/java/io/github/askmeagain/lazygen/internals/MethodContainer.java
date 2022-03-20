package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotation.LazyType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Optional;

@Value
@Builder
class MethodContainer {

  @NonNull LazyType usage;
  @NonNull Optional<String> namedAnnotation;
  @NonNull String methodOriginClass;
  @NonNull String methodName;
  @NonNull String outputType;
  @NonNull List<@NonNull TypeElement> parameters;

}
