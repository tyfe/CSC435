package AST;

import java.util.Vector;
import AST.*;
import Type.*;
import IR.*;

public class StatementList extends ASTNode {
  public Vector<Statement> statementList;

  public StatementList() {
    statementList = new Vector<Statement>();
  }

  public void addElement(Statement s) {
    statementList.addElement(s);
  }

  public Statement elementAt(int index) {
    return statementList.elementAt(index);
  }

  public int size() {
    return statementList.size();
  }
}
