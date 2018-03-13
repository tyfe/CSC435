package IR;

import java.util.*;

public class VariableTable {
  private List<VariableNode> vars;

  public VariableTable() {
    vars = new ArrayList<>();
  }

  public void add(Temporary t) {
    VariableNode v = new VariableNode(t);
    vars.add(v);
  }

  public void addParameter(String id, Temporary t) {
    VariableNode v = new VariableNode(id, t, true, false);
    vars.add(v);
  }

  public void addLocal(String id, Temporary t) {
    VariableNode v = new VariableNode(id, t, false, true);
    vars.add(v);
  }

  public Temporary retrieve(String id) {
    for(int i = 0; i < vars.size(); ++i) {
      VariableNode v = vars.get(i);
      if(v.is(id)) {
        return v.t;
      }
    }
    return null;
  }

  public int size() {
    return vars.size();
  }

  public VariableNode elementAt(int i) {
    return vars.get(i);
  }
}
