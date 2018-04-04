package Instruction;

import IR.*;
import AST.*;
import Codegen.*;

public class ArrayIndexAssignment extends Instruction {

  public Temporary left;
  public Temporary right;
  public Temporary index;

  public ArrayIndexAssignment(Temporary left, Temporary right, Temporary index) {
    this.left = left;
    this.right = right;
    this.index = index;
  }

  public String toString() {
    return left + "[" + index + "] := " + right + ";";
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
