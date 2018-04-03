package IR;

import java.util.ArrayList;
import AST.*;

public class FunctionTable {
  private ArrayList<FunctionNode> flist;

  public FunctionTable() {
    flist = new ArrayList<>();
  }

  public void add(FunctionDeclaration f) {
    flist.add(new FunctionNode(f));
  }

  public int size() {
    return flist.size();
  }

  public FunctionNode elementAt(int index) {
    return flist.get(index);
  }

  public FunctionNode retrieve(String id) {
    for(int i = 0; i < this.size(); ++i) {
      FunctionNode ft = this.elementAt(i);
      if (ft.id.equals(id))
        return ft;
    }
    return null;
  }

  public Boolean contains(FunctionNode f) {
    return false;
  }
}
