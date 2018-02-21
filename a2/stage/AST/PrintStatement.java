package AST;

import AST.*;
import Semantic.*;

public class PrintStatement extends Statement {

  Expression expr;

  public PrintStatement(Expression e) {
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
