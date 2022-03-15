package io.github.askmeagain.lazygen.other;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Value
@Builder
public class LazyMethodContainer {

  String methodOriginClass;
  String methodName;
  List<String> parameters;
  String outputType;

  public String computeTemplate() {
    var atomicInt = new AtomicInteger();
    return LazyGenData.LAZY_METHOD_TEMPLATE
        .replace("$LAZY_FIELD_NAME", "_" + methodName)
        .replace("$METHOD_NAME", methodName)
        .replace("$METHOD_ORIGIN_CLASS", methodOriginClass)
        .replace("$PARAMETERS_WITHOUT_TYPE", parameters.stream()
            .map(x -> "_" + x + atomicInt.get())
            .collect(Collectors.joining(",")))
        .replace("$PARAMETERS", parameters.stream()
            .map(x -> x + " _" + x + atomicInt.getAndIncrement())
            .collect(Collectors.joining(",")))
        .replace("$OUTPUT_TYPE", outputType);
  }


}
