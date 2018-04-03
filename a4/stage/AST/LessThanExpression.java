package AST;

import AST.*;
import Semantic.*;
import Type.*;
import IR.*;

public class LessThanExpression extends Expression {

  public Expression expr1;
  public Expression expr2;

  public LessThanExpression(int l, int o, Expression e1, Expression e2) {
    super(l, o);
    expr1 = e1;
    expr2 = e2;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
  }

  public void accept(IRVisitor v) {
    v.visit(this);
  }
}
