package AST;

import AST.Visitor;
import Semantic.*;
import Type.*;

public abstract class ASTNode {
  public int lineNumber;
  public int offset;

  public ASTNode() {
    lineNumber = 0;
    offset = 0;
  }

  public ASTNode(int line, int off) {
    lineNumber = line;
    offset = off;
  }

  // public abstract void accept(TypeChecker t) throws SemanticException;
}
