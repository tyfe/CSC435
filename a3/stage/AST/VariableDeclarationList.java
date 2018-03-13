package AST;

import java.util.Vector;
import AST.*;
import Type.*;
import IR.*;

public class VariableDeclarationList extends ASTNode {
  public Vector<VariableDeclaration> variableDeclarationList;

  public VariableDeclarationList() {
    variableDeclarationList = new Vector<VariableDeclaration>();
  }

  public void addElement(VariableDeclaration v) {
    variableDeclarationList.addElement(v);
  }

  public VariableDeclaration elementAt(int index) {
    return variableDeclarationList.elementAt(index);
  }

  public int size() {
    return variableDeclarationList.size();
  }
}
