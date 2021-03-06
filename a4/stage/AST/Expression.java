package AST;

import AST.*;
import Semantic.*;
import Type.*;
import IR.*;

public abstract class Expression extends ASTNode {
  public Expression(int l, int o) {
    super(l, o);
  }
  public abstract void accept(PrintVisitor v);
  public abstract Type accept(TypeChecker t) throws SemanticException;
  public abstract void accept(IRVisitor v);
}
