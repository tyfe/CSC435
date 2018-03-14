package Instruction;

import IR.*;

public class PrintLnInstruction extends Instruction {

  Temporary op;

  public PrintLnInstruction(Temporary t) {
    op = t;
  }

  public String toString() {
    return "PRINTLN" + op.type.toShortString() + " " + op;
  }

}
