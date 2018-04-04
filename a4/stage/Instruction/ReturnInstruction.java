package Instruction;

import IR.*;
import Codegen.*;

public class ReturnInstruction extends Instruction {

  public Temporary op;

  public ReturnInstruction() {
    op = null;
  }

  public ReturnInstruction(Temporary t) {
    op = t;
  }

  public String toString() {
    if(op != null)
      return "RETURN " + op + ";";
    return "RETURN;";
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
