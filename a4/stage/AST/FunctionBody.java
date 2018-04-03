package AST;

import AST.ASTNode;
import AST.VariableDeclarationList;
import Semantic.*;
import Type.*;
import IR.*;

public class FunctionBody extends ASTNode {

  public StatementList statementList;
  public VariableDeclarationList varList;

  public FunctionBody(int l, int o, VariableDeclarationList vl, StatementList sl) {
    super(l, o);
    varList = vl;
    statementList = sl;
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
