package Instruction;

import IR.*;
import Codegen.*;

public class IfElseInstruction extends Instruction {

  Temporary op;
  Label label;

  public IfElseInstruction(Temporary t, Label l) {
    op = t;
    label = l;
  }

  public String toString() {
    return "IF " + op + " GOTO " + label + ";";
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
