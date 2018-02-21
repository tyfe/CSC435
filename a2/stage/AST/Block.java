package AST;

import AST.ASTNode;
import Semantic.*;
import Type.*;

public class Block extends ASTNode {

  public StatementList statementList;

  public Block(int l, int o, StatementList sl) {
    super(l, o);
    statementList = sl;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
  }
}
