package AST;

import AST.*;
import Semantic.*;

public class ReturnStatement extends Statement {

  public Expression expr;

  public ReturnStatement(Expression e) {
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
