package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.NormalClassWithInput;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import java.lang.String;
import io.github.askmeagain.lazygen.input.Input;


public class  LazyNormalClassWithInput extends NormalClassWithInput {
  private Input inputs;

  @Override
  public Input getInputs(){
    return inputs;
  }

  public String calculate(Input inputs){
    this.inputs = inputs;
    return map(this);
  }

  
  @Override
  public java.lang.String map(io.github.askmeagain.lazygen.NormalClassWithInput _NormalClassWithInput0) {
    if (_map != null) {
      return _map;
    }
    _map = super.map(_NormalClassWithInput0);
    return _map;
  }
  private java.lang.String _map;


}

