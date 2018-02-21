package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class ArrayAssignment extends Statement {

  public Identifier name;
  public Expression index;
  public Expression expr;

  public ArrayAssignment(int l, int o, Identifier i, Expression idx, Expression e) {
    super(l, o);
    name = i;
    index = idx;
    expr = e;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
  }
}
