package Instruction;

import IR.*;
import AST.*;

public class BinaryOpAssignment extends Instruction {

  Temporary result;
  Temporary left;
  Temporary right;
  BinaryOp operator;

  public BinaryOpAssignment(Temporary result, Temporary left, Temporary right, BinaryOp operator) {
    this.result = result;
    this.left = left;
    this.right = right;
    this.operator = operator;
  }

  public String toString() {
    switch(operator) {
      case PLUS:
        return result + " := " + left + " " + left.type.toShortString() + "+ " + right + ";";
      case MINUS:
        return result + " := " + left + " " + left.type.toShortString() + "- " + right + ";";
      case TIMES:
        return result + " := " + left + " " + left.type.toShortString() + "* " + right + ";";
      case DIVIDE:
        return result + " := " + left + " " + left.type.toShortString() + "/ " + right + ";";
      case LESSTHAN:
        return result + " := " + left + " " + left.type.toShortString() + "< " + right + ";";
      case LESSTHANEQUAL:
        return result + " := " + left + " " + left.type.toShortString() + "<= " + right + ";";
      case EQUALITY:
        return result + " := " + left + " " + left.type.toShortString() + "== " + right + ";";
      case NOTEQUAL:
        return result + " := " + left + " " + left.type.toShortString() + "!= " + right + ";";
      case GREATERTHANEQUAL:
        return result + " := " + left + " " + left.type.toShortString() + ">= " + right + ";";
      case GREATERTHAN:
        return result + " := " + left + " " + left.type.toShortString() + "> " + right + ";";
      default:
        return "";
    }
  }

}
