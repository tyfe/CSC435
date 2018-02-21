package Semantic;

import AST.*;
import Type.*;

public class FunctionType {
  
  public String id;
  public Type type;
  public FormalParameterList plist;
  public int lineNumber;
  public int offset;

  public FunctionType(FunctionDeclaration f) {
    id = f.name.name;
    type = f.type.type;
    plist = f.parameterList;
    lineNumber = f.lineNumber;
    offset = f.offset;
  }

}
