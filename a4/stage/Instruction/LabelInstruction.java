package Instruction;

import IR.*;
import Codegen.*;

public class LabelInstruction extends Instruction {

  Label label;

  public LabelInstruction(Label l) {
    label = l;
  }

  public String toString() {
    return label.toString() + ":;";
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}

