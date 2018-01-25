package AST;

import java.util.Vector;
import AST.*;

public class ExpressionList extends ASTNode {
  public Vector<Expression> expressionList;

  public ExpressionList() {
    expressionList = new Vector<Expression>();
  }

  public void addElement(Expression s) {
    expressionList.addElement(s);
  }

  public Expression elementAt(int index) {
    return expressionList.elementAt(index);
  }

  public int size() {
    return expressionList.size();
  }
}
