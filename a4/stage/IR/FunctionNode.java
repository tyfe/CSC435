package IR;

import AST.*;
import Type.*;
import java.util.*;

public class FunctionNode {
  
  public String id;
  public Type returnType;
  public InstructionList instructions;
  private List<Type> params;

  public FunctionNode(FunctionDeclaration f) {
    id = f.name.name;
    returnType = f.type.type;
    params = new ArrayList<>();
    for(int i = 0; i < f.parameterList.size(); ++i) {
      FormalParameter fp = f.parameterList.elementAt(i);
      params.add(fp.type.type);
    }
  }

  public Type getParam(int index) {
    return params.get(index);
  }

}
