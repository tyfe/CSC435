package AST;

import AST.Visitor;
import Semantic.*;

public abstract class ASTNode {
  public abstract void accept(TypeChecker t) throws SemanticException;
}
