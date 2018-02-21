package AST;

import AST.*;
import Semantic.*;

public class IfStatement extends Statement {

  public Expression expr;
  public Block block1;
  public Block block2;

  public IfStatement(Expression e, Block b) {
    expr = e;
    block1 = b;
    block2 = null;
  }

  public IfStatement(Expression e, Block b1, Block b2) {
    expr = e;
    block1 = b1;
    block2 = b2;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
