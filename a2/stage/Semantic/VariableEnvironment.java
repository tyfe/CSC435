package Semantic;

import java.util.ArrayList;
import AST.*;

public class VariableEnvironment {
  private ArrayList<VariableType> vlist;

  public VariableEnvironment() {
    vlist = new ArrayList<>();
  }

  public void add(VariableType v) {
    vlist.add(v);
  }

  public int size() {
    return vlist.size();
  }

  public VariableType elementAt(int index) {
    return vlist.get(index);
  }

  public boolean contains(VariableType v) {
    for(int i = 0; i < this.size(); ++i) {
      VariableType current_var = this.elementAt(i);
      if(current_var.equals(v)) {
        return true;
      }
    }
    return false;
  }
}
