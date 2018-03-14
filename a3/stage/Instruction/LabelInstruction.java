package Instruction;

import IR.*;

public class LabelInstruction extends Instruction {

  Label label;

  public LabelInstruction(Label l) {
    label = l;
  }

  public String toString() {
    return label.toString() + ":;";
  }

}

