package Type;

import AST.*;

public class StringType extends Type {
  public StringType () {
  }
  public String toString() {
    return "string";
  }

  public String toShortString() {
    return "U";
  }

  public boolean equals (Object o) {
    if (o instanceof StringType)
      return true;
    else
      return false;
  }

  public void accept (Visitor v) {
    v.visit(this);
  }
}
