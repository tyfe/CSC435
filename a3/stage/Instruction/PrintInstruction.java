package Instruction;

import IR.*;

public class PrintInstruction extends Instruction {

  Temporary op;

  public PrintInstruction(Temporary t) {
    op = t;
  }

  public String toString() {
    return "PRINT" + op.type.toShortString() + " " + op;
  }

}
