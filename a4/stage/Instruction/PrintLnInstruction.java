package Instruction;

import IR.*;
import Codegen.*;

public class PrintLnInstruction extends Instruction {

  Temporary op;

  public PrintLnInstruction(Temporary t) {
    op = t;
  }

  public String toString() {
    return "PRINTLN" + op.type.toShortString() + " " + op + ";";
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
