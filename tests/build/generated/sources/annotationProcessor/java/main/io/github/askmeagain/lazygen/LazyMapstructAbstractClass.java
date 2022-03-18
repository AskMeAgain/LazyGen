package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.MapstructAbstractClass;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import java.lang.String;

@Mapper 
public abstract class  LazyMapstructAbstractClass extends  MapstructAbstractClass {

  
  @Override
  public java.lang.String a() {
    if (_a != null) {
      return _a;
    }
    _a = super.a();
    return _a;
  }
  private java.lang.String _a;


}

