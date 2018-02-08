package AST;

import java.util.Vector;
import AST.*;

public class FormalParameterList extends ASTNode {
  public Vector<FormalParameter> parameterList;

  public FormalParameterList() {
    parameterList = new Vector<FormalParameter>();
  }

  public void addElement(FormalParameter p) {
    parameterList.addElement(p);
  }

  public FormalParameter elementAt(int index) {
    return parameterList.elementAt(index);
  }

  public int size() {
    return parameterList.size();
  }
}
