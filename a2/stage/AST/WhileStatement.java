package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class WhileStatement extends Statement {

  public Expression expr;
  public Block block;

  public WhileStatement(int l, int o, Expression e, Block b) {
    super(l, o);
    expr = e;
    block = b;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
  }
}
