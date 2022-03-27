package io.github.askmeagain.lazygen.internals;

import io.github.askmeagain.lazygen.annotation.LazyType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;
import java.util.Optional;

@Value
@Builder
class MethodContainer {

  @NonNull Boolean primitive;
  @NonNull LazyType usage;
  @NonNull Optional<String> namedAnnotation;
  @NonNull String methodOriginClass;
  @NonNull String methodName;
  @NonNull String outputType;
  @NonNull String outputTypeOriginal;
  @NonNull List<String> parameters;
  @NonNull List<String> parametersWithoutType;

}
