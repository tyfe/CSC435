package AST;

import AST.*;

public class FormalParameter extends ASTNode {

  TypeNode type;
  Identifier name;

  public FormalParameter(TypeNode tn, Identifier i) {
    type = tn;
    name = i;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
