package Codegen;

public class CodeLabelGen {

  int index;

  public CodeLabelGen() {
    index = 0;
  }

  public CodeLabel addLabel() {
    return new CodeLabel(index++);
  }
}
