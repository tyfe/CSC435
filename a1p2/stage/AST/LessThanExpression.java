package AST;

import AST.*;

public class LessThanExpression extends Expression {

  Expression expr1;
  Expression expr2;

  public LessThanExpression(Expression e1, Expression e2) {
    expr1 = e1;
    expr2 = e2;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
