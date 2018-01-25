package AST;

import AST.ASTNode;

public class Block extends ASTNode {

  public StatementList statementList;

  public Block(StatementList sl) {
    statementList = sl;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
