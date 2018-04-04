package Codegen;

public class CodeLabel {

  private int index;

  public CodeLabel(int i) {
    index = i;
  }

  public String toString() {
    return "L_" + index;
  }
}
