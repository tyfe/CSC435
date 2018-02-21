package Semantic;

import AST.*;
import Type.*;

public class FunctionType {
  
  public String id;
  public Type type;
  public FormalParameterList plist;

  public FunctionType(FunctionDeclaration f) {
    id = f.name.name;
    type = f.type.type;
    plist = f.parameterList;
  }

}
