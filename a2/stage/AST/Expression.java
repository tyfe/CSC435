package AST;

import AST.*;

public abstract class Expression extends ASTNode {
  public abstract void accept(PrintVisitor v);
}
