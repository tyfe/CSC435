package AST;

import AST.*;

public abstract class Statement extends ASTNode {
  public abstract void accept(PrintVisitor v);
}
