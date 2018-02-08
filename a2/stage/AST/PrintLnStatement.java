package AST;

import AST.*;

public class PrintLnStatement extends Statement {

  Expression expr;

  public PrintLnStatement(Expression e) {
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
