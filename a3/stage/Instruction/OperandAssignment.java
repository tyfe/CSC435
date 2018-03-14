package Instruction;

import IR.*;
import AST.*;

public class OperandAssignment extends Instruction {

  Temporary left;
  Temporary right;

  public OperandAssignment(Temporary left, Temporary right) {
    this.left = left;
    this.right = right;
  }

  public String toString() {
    return left + " := " + right + ";";
  }

}
