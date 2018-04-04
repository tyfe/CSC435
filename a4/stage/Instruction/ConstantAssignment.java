package Instruction;

import IR.*;
import AST.*;
import Type.*;
import Codegen.*;

public class ConstantAssignment extends Instruction {

  public Temporary op;
  public Type type;
  public Integer val_int;
  public  Float val_float;
  public Character val_char;
  public String val_str;
  public Boolean val_bool;

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

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
