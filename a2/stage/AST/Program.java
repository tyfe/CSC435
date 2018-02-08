package AST;

import java.util.Vector;

public class Program {
  public Vector<Function> functionList;

  public Program() {
    functionList = new Vector();
  }

  public void addElement(Function f) {
    functionList.addElement(f);
  }

  public Function elementAt(int index) {
    return (Function) functionList.elementAt(index);
  }

  public int size() {
    return functionList.size();
  }

  public void accept(PrintVisitor v) {
    v.visit(this);
  }
}
