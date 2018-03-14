package Instruction;

import IR.*;

public class GotoInstruction extends Instruction {

  Label label;

  public GotoInstruction(Label l) {
    label = l;
  }

  public String toString() {
    return "GOTO " + label;
  }

}
