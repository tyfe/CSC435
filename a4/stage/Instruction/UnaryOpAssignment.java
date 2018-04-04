package Instruction;

import IR.*;
import AST.*;
import Codegen.*;

public class UnaryOpAssignment extends Instruction {

  public Temporary left;
  public Temporary right;
  public UnaryOp operator;

  public UnaryOpAssignment(Temporary left, Temporary right, UnaryOp operator) {
    this.left = left;
    this.right = right;
    this.operator = operator;
  }

  public String toString() {
    switch(operator) {
      case NEGATION:
        return left + " := " + left.type.toShortString() + "- " + right + ";";
      case INVERSION:
        return left + " := " + left.type.toShortString() + "! " + right + ";";
      case CONVERSION:
        return left + " := " + right.type.toShortString() + "2" + left.type.toShortString() + " " + right + ";";
      default:
        return "";
    }
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }

}
