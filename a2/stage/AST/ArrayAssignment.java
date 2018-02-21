package AST;

import AST.*;
import Semantic.*;

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

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
