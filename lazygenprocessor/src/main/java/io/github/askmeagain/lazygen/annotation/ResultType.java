package io.github.askmeagain.lazygen.annotation;

public enum ResultType {
  CLASS, ABSTRACT_CLASS, MAPSTRUCT_COMPATIBLE, MAPSTRUCT_COMPATIBLE_WITHOUT_ANNOTATION;

  public boolean isMapStruct() {
    return this == MAPSTRUCT_COMPATIBLE || this == MAPSTRUCT_COMPATIBLE_WITHOUT_ANNOTATION;
  }
}
