package Instruction;

import IR.*;
import Codegen.*;

public class GotoInstruction extends Instruction {

  public Label label;

  public GotoInstruction(Label l) {
    label = l;
  }

  public String toString() {
    return "GOTO " + label + ";";
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
