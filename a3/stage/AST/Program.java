package AST;

import java.util.Vector;
import Semantic.*;
import Type.*;
import IR.*;

public class Program extends ASTNode {
  private Vector<Function> functionList;

  public Program(int l, int o) {
    super(l, o);
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

  public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
  }

  public void accept(IRVisitor v) {
    v.visit(this);
  }
}
