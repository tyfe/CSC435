package AST;

import AST.*;
import Semantic.*;

public class VariableDeclaration {

  public Identifier name;
  public TypeNode type;

  public VariableDeclaration(TypeNode tn, Identifier i) {
    type = tn;
    name = i;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }

}
