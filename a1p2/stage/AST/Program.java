package AST;

import java.util.Vector;

public class Program {
  public Vector functionList;

  public Program() {
    // functionList = new Vector();
  }

  public void addElement(Function f) {
    // functionList.addElement(f);
  }

  public Function elementAt(int index) {
    // return (Function) functionList.elementAt(index);
    return new Function();
  }

  public int size() {
    // return functionList.size();
    return 0;
  }

  public void accept(Visitor v) {
    // v.visit(this);
  }
}
