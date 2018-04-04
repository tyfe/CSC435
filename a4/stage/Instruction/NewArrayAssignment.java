package Instruction;

import IR.*;
import AST.*;
import Type.*;
import Codegen.*;

public class NewArrayAssignment extends Instruction {

  public Temporary op;
  public int length;
  public Type type;

  public NewArrayAssignment(Temporary t, Type type, int l) {
    op = t;
    this.type = type;
    length = l;
  }

  public String toString() {
    return op + " := NEWARRAY" + type.toShortString() + " " + length + ";";
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
