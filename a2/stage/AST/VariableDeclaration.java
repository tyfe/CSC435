package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class VariableDeclaration extends ASTNode {

  public Identifier name;
  public TypeNode type;

  public VariableDeclaration(int l, int o, TypeNode tn, Identifier i) {
    super(l, o);
    type = tn;
    name = i;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
  }

}
