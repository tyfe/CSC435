package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class ExpressionStatement extends Statement {

  Expression expr;

  public ExpressionStatement(int l, int o, Expression e) {
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
