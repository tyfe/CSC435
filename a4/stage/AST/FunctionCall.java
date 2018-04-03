package AST;

import AST.*;
import Type.*;
import Semantic.*;
import IR.*;

public class FunctionCall extends Expression {

  public Identifier name;
  public ExpressionList expressionList;

  public FunctionCall(int l, int o, Identifier id, ExpressionList el) {
    super(l, o);
    name = id;
    expressionList = el;
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
