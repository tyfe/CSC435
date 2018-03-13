package IR;

public class VariableNode {
  public String identifier;
  public Temporary t;
  public boolean isParam;
  public boolean isLocal;

  public VariableNode(Temporary t) {
    this.t = t;
    isParam = false;
    isLocal = false;
  }

  public VariableNode(String id, Temporary t, boolean isParam, boolean isLocal) {
    identifier = id;
    this.t = t;
    this.isParam = isParam;
    this.isLocal = isLocal;
  }

  public boolean is(String s) {
    return identifier.equals(s);
  }
}
