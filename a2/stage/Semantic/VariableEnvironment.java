package Semantic;

import java.util.ArrayList;
import AST.*;

public class VariableEnvironment {
  private ArrayList<VariableDeclaration> vlist;

  public VariableEnvironment() {
    vlist = new ArrayList<>();
  }

  public void add(VariableDeclaration v) {
    vlist.add(new FunctionType(f));
  }

  public int size() {
    return vlist.size();
  }

  public FunctionType elementAt(int index) {
    return vlist.get(index);
  }

  public boolean contains() {
    
  }
}
