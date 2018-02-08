package AST;

import AST.*;

public class ReturnStatement extends Statement {

  public Expression expr;

  public ReturnStatement(Expression e) {
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
