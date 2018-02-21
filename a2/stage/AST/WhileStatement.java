package AST;

import AST.*;
import Semantic.*;

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

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
