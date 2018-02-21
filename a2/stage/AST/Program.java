package AST;

import java.util.Vector;
import Semantic.*;

public class Program {
  private Vector<Function> functionList;

  public Program() {
    functionList = new Vector<Function>();
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

  public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
