package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.NormalAbstractClass;
import org.mapstruct.Mapper;
import org.mapstruct.Named;


public abstract class  LazyNormalAbstractClass extends NormalAbstractClass {

  
  @Override
  public java.lang.String abc() {
    if (_abc != null) {
      return _abc;
    }
    _abc = super.abc();
    return _abc;
  }
  private java.lang.String _abc;


}

