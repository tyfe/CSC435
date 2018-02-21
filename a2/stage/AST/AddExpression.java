package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class AddExpression extends Expression {

  Expression expr1;
  Expression expr2;

  public AddExpression(int l, int o, Expression e1, Expression e2) {
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
}
