package AST;

import AST.*;
import Semantic.*;
import Type.*;
import IR.*;

public class FormalParameter extends ASTNode {

  public TypeNode type;
  public Identifier name;

  public FormalParameter(int l, int o, TypeNode tn, Identifier i) {
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

  public void accept(IRVisitor v) {
    v.visit(this);
  }
}
