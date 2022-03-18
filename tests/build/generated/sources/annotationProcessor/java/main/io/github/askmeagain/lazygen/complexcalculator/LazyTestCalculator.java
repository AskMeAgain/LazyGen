package io.github.askmeagain.lazygen.complexcalculator;

import io.github.askmeagain.lazygen.complexcalculator.TestCalculator;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import io.github.askmeagain.lazygen.output.ComplexOutputs;
import io.github.askmeagain.lazygen.input.Input;

@Mapper 
public abstract class  LazyTestCalculator implements  TestCalculator {
  private Input inputs;

  @Override
  public Input getInputs(){
    return inputs;
  }

  public ComplexOutputs calculate(Input inputs){
    this.inputs = inputs;
    return map(this);
  }

  @Named("number1mul2")
  @Override
  public java.lang.Integer number1mul2(io.github.askmeagain.lazygen.complexcalculator.TestCalculator _TestCalculator0) {
    if (_number1mul2 != null) {
      return _number1mul2;
    }
    _number1mul2 = TestCalculator.super.number1mul2(_TestCalculator0);
    return _number1mul2;
  }
  private java.lang.Integer _number1mul2;


  @Named("number1mul4")
  @Override
  public java.lang.Integer number1mul4(io.github.askmeagain.lazygen.complexcalculator.TestCalculator _TestCalculator0) {
    if (_number1mul4 != null) {
      return _number1mul4;
    }
    _number1mul4 = TestCalculator.super.number1mul4(_TestCalculator0);
    return _number1mul4;
  }
  private java.lang.Integer _number1mul4;


  @Named("number1mul8")
  @Override
  public java.lang.Integer number1mul8(io.github.askmeagain.lazygen.complexcalculator.TestCalculator _TestCalculator0) {
    if (_number1mul8 != null) {
      return _number1mul8;
    }
    _number1mul8 = TestCalculator.super.number1mul8(_TestCalculator0);
    return _number1mul8;
  }
  private java.lang.Integer _number1mul8;


  @Named("a")
  @Override
  public java.lang.String a(io.github.askmeagain.lazygen.complexcalculator.TestCalculator _TestCalculator0) {
    if (_a != null) {
      return _a;
    }
    _a = TestCalculator.super.a(_TestCalculator0);
    return _a;
  }
  private java.lang.String _a;


  @Named("aa")
  @Override
  public java.lang.String aa(io.github.askmeagain.lazygen.complexcalculator.TestCalculator _TestCalculator0) {
    if (_aa != null) {
      return _aa;
    }
    _aa = TestCalculator.super.aa(_TestCalculator0);
    return _aa;
  }
  private java.lang.String _aa;


  @Named("aaaa")
  @Override
  public java.lang.String aaaa(io.github.askmeagain.lazygen.complexcalculator.TestCalculator _TestCalculator0) {
    if (_aaaa != null) {
      return _aaaa;
    }
    _aaaa = TestCalculator.super.aaaa(_TestCalculator0);
    return _aaaa;
  }
  private java.lang.String _aaaa;


}

