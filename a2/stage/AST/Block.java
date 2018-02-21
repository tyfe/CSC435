package AST;

import AST.ASTNode;
import Semantic.*;

public class Block extends ASTNode {

  public StatementList statementList;

  public Block(StatementList sl) {
    statementList = sl;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
