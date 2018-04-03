package IR;

import Type.*;

public class Temporary {
  public int index;
  public Type type;

  public Temporary(int index, Type t) {
    this.index = index;
    type = t;
  }

  public String toString() {
    return "T" + index;
  }
}
