package IR;

import Type.*;

public class TempGenerator {
  private int index;

  public TempGenerator() {
    index = 0;
  }

  public void reset() {
    index = 0;
  }

  public Temporary newVar(Type t) {
    return new Temporary(index++, t);
  }
}
