package Instruction;

import IR.*;
import AST.*;
import Codegen.*;

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

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
