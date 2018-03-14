package Instruction;

import IR.*;
import AST.*;
import Type.*;

public class NewArrayAssignment extends Instruction {

  Temporary op;
  int length;
  Type type;

  public NewArrayAssignment(Temporary t, Type type, int l) {
    op = t;
    this.type = type;
    length = l;
  }

  public String toString() {
    return op + " := NEWARRAY (" + type.toShortString() + ") " + length;
  }

}
