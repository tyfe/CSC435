package Instruction;

import IR.*;
import Codegen.*;

public class PrintInstruction extends Instruction {

  Temporary op;

  public PrintInstruction(Temporary t) {
    op = t;
  }

  public String toString() {
    return "PRINT" + op.type.toShortString() + " " + op + ";";
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
