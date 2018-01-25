package AST;

import AST.*;

public class VariableAssignment extends Statement {

  public Identifier name;
  public Expression expr;

  public VariableAssignment(Identifier i, Expression e) {
    name = i;
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
