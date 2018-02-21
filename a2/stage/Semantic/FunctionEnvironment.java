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

  public boolean contains() {
    return false;
  }
}
