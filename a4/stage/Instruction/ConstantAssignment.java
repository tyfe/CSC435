package Instruction;

import IR.*;
import AST.*;
import Type.*;

public class ConstantAssignment extends Instruction {

  Temporary op;
  Type type;
  Integer val_int;
  Float val_float;
  Character val_char;
  String val_str;
  Boolean val_bool;

  public ConstantAssignment(Temporary t, Integer val) {
    op = t;
    type = t.type;
    val_int = val;
  }

  public ConstantAssignment(Temporary t, Float val) {
    op = t;
    type = t.type;
    val_float = val;
  }

  public ConstantAssignment(Temporary t, Character val) {
    op = t;
    type = t.type;
    val_char = val;
  }

  public ConstantAssignment(Temporary t, String val) {
    op = t;
    type = t.type;
    val_str = val;
  }

  public ConstantAssignment(Temporary t, Boolean val) {
    op = t;
    type = t.type;
    val_bool = val;
  }

  public String toString() {
    if(type instanceof IntegerType) {
      return op + " := " + val_int + ";";
    } else if (type instanceof FloatType) {
      return op + " := " + val_float + ";";
    } else if (type instanceof CharType) {
      return op + " := '" + val_char + "'" + ";";
    } else if (type instanceof StringType) {
      return op + " := \"" + val_str + "\"" + ";";
    } else if (type instanceof BooleanType) {
      return op + " := " + val_bool.toString().toUpperCase() + ";";
    }
    return "";
  }

}
