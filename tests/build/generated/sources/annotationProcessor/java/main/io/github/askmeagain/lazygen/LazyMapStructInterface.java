package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.MapStructInterface;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import io.github.askmeagain.lazygen.output.Outputs;

@Mapper
public abstract class  LazyMapStructInterface implements MapStructInterface {

  @Named("a")
  @Override
  public java.lang.String a(java.lang.String _String0) {
    if (_a != null) {
      return _a;
    }
    _a = MapStructInterface.super.a(_String0);
    return _a;
  }
  private java.lang.String _a;


}

