package AST;

import AST.*;
import Semantic.*;

public class ExpressionStatement extends Statement {

  Expression expr;

  public ExpressionStatement(Expression e) {
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
