package AST;

import AST.ASTNode;

public class ArrayReference extends Expression {

  public Identifier name;
  public Expression expr;

  public ArrayReference(Identifier i, Expression e) {
    name = i;
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

}
