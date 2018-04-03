package Semantic;

import Type.*;

public class VariableType {
  public String id;
  public Type type;
  

  public VariableType(String name, Type t) {
    id = name;
    type = t;
  }

  public boolean equals(VariableType v) {
    return id.equals(v.id);
  }
}
