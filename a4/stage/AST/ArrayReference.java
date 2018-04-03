package AST;

import AST.ASTNode;
import Type.*;
import Semantic.*;
import IR.*;

public class ArrayReference extends Expression {

  public Identifier name;
  public Expression expr;

  public ArrayReference(int l, int o, Identifier i, Expression e) {
    super(l, o);
    name = i;
    expr = e;
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
