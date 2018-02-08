package AST;

import AST.*;

public class ArrayAssignment extends Statement {

  public Identifier name;
  public Expression index;
  public Expression expr;

  public ArrayAssignment(Identifier i, Expression idx, Expression e) {
    name = i;
    index = idx;
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
