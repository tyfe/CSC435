package Instruction;

import IR.*;

public class ReturnInstruction extends Instruction {

  Temporary op;

  public ReturnInstruction() {
    op = null;
  }

  public ReturnInstruction(Temporary t) {
    op = t;
  }

  public String toString() {
    if(op != null)
      return "RETURN " + op;
    return "RETURN";
  }

}
