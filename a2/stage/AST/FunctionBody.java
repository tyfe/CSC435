package AST;

import AST.ASTNode;
import AST.VariableDeclarationList;
import Semantic.*;

public class FunctionBody extends ASTNode {

  StatementList statementList;
  VariableDeclarationList varList;

  public FunctionBody(VariableDeclarationList vl, StatementList sl) {
    varList = vl;
    statementList = sl;
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
