package AST;

import AST.*;
import Semantic.*;

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

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
