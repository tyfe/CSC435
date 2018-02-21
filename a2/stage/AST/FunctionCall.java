package AST;

import AST.*;
import Semantic.*;

public class FunctionCall extends Expression {

  public Identifier name;
  public ExpressionList expressionList;

  public FunctionCall(Identifier id, ExpressionList el) {
    name = id;
    expressionList = el;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
