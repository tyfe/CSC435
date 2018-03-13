package AST;

import AST.*;
import Semantic.*;
import Type.*;
import IR.*;

public class PrintLnStatement extends Statement {

  public Expression expr;

  public PrintLnStatement(int l, int o, Expression e) {
    super(l, o);
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
