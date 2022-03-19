package io.github.askmeagain.lazygen.internals;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.lang.model.element.TypeElement;
import java.util.List;

@Value
@Builder
public class LazyMethodContainer {

  @NonNull Boolean isMapstruct;
  @NonNull String methodOriginClass;
  @NonNull String methodName;
  @NonNull String outputType;
  @NonNull List<@NonNull TypeElement> parameters;

}
