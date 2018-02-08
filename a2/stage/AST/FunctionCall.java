package AST;

import AST.*;

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
}
