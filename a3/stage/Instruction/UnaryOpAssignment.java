package Instruction;

import IR.*;
import AST.*;

public class UnaryOpAssignment extends Instruction {

  Temporary left;
  Temporary right;
  UnaryOp operator;

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

}
