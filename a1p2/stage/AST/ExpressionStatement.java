package AST;

import AST.*;

public class ExpressionStatement extends Statement {

  Expression expr;

  public ExpressionStatement(Expression e) {
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
