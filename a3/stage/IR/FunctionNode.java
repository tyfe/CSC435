package IR;

import AST.*;
import Type.*;

public class FunctionNode {
  
  public String id;
  public Type returnType;

  public FunctionNode(FunctionDeclaration f) {
    id = f.name.name;
    returnType = f.type.type;
  }

}
