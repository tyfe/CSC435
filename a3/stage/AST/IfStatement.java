package AST;

import AST.*;
import Semantic.*;
import Type.*;
import IR.*;

public class IfStatement extends Statement {

  public Expression expr;
  public Block block1;
  public Block block2;

  public IfStatement(int l, int o, Expression e, Block b) {
    super(l, o);
    expr = e;
    block1 = b;
    block2 = null;
  }

  public IfStatement(int l, int o, Expression e, Block b1, Block b2) {
    super(l, o);
    expr = e;
    block1 = b1;
    block2 = b2;
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
