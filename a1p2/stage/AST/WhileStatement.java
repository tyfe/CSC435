package AST;

import AST.*;

public class WhileStatement extends Statement {

  public Expression expr;
  public Block block;

  public WhileStatement(Expression e, Block b) {
    expr = e;
    block = b;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
