package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class ReturnStatement extends Statement {

  public Expression expr;

  public ReturnStatement(int l, int o, Expression e) {
    super(l, o);
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
  }
}
