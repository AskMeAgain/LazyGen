package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.NormalClass;
import org.mapstruct.Mapper;
import org.mapstruct.Named;


public class  LazyNormalClass extends NormalClass {

  
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

