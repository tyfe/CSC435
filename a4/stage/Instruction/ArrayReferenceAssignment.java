package Instruction;

import IR.*;
import AST.*;

public class ArrayReferenceAssignment extends Instruction {

  Temporary left;
  Temporary right;
  Temporary index;

  public ArrayReferenceAssignment(Temporary left, Temporary right, Temporary index) {
    this.left = left;
    this.right = right;
    this.index = index;
  }

  public String toString() {
    return left + " := " + right + "[" + index + "]" + ";";
  }

}
