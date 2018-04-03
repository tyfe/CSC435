package Semantic;

import java.util.ArrayList;
import AST.*;

public class FunctionEnvironment {
  private ArrayList<FunctionType> flist;

  public FunctionEnvironment() {
    flist = new ArrayList<>();
  }

  public void add(FunctionDeclaration f) {
    flist.add(new FunctionType(f));
  }

  public int size() {
    return flist.size();
  }

  public FunctionType elementAt(int index) {
    return flist.get(index);
  }

  public FunctionType lookup(String id) {
    for(int i = 0; i < this.size(); ++i) {
      FunctionType ft = this.elementAt(i);
      if (ft.id.equals(id))
        return ft;
    }
    return null;
  }

  public Boolean contains(FunctionType f) {
    return false;
  }
}
