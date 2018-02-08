package AST;

import AST.*;

public class PrintStatement extends Statement {

  Expression expr;

  public PrintStatement(Expression e) {
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
