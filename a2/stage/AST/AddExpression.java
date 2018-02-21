package AST;

import AST.*;
import Semantic.*;

public class AddExpression extends Expression {

  Expression expr1;
  Expression expr2;

  public AddExpression(Expression e1, Expression e2) {
    expr1 = e1;
    expr2 = e2;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
