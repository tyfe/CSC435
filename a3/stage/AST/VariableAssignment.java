package AST;

import AST.*;
import Semantic.*;
import Type.*;
import IR.*;

public class VariableAssignment extends Statement {

  public Identifier name;
  public Expression expr;

  public VariableAssignment(int l, int o, Identifier i, Expression e) {
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
