package AST;

import AST.*;
import Semantic.*;

public class PrintLnStatement extends Statement {

  Expression expr;

  public PrintLnStatement(Expression e) {
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
