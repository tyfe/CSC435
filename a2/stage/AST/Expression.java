package AST;

import AST.*;

public abstract class Expression extends ASTNode {
  public Expression(int l, int o) {
    super(l, o);
  }
  public abstract void accept(PrintVisitor v);
}
