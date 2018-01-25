package AST;

import AST.ASTNode;
import AST.VariableDeclarationList;

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
}
