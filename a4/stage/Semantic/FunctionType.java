package Semantic;

import AST.*;
import Type.*;

public class FunctionType {
  
  public String id;
  public Type returnType;
  public ParamList plist;
  public int lineNumber;
  public int offset;

  private final IntegerType INTEGER = new IntegerType();
  private final FloatType FLOAT = new FloatType();

  private Boolean checkFloat(Type left, Type right) {
    return (left.equals(FLOAT) && right.equals(INTEGER));
  }

  private Boolean paramsMatch(ParamList p1, ParamList p2) {
    if(p1.size() != p2.size()) 
      return false;
    for(int i = 0; i < p1.size(); ++i) {
      Type t1 = p1.elementAt(i);
      Type t2 = p2.elementAt(i);
      if(!t1.equals(t2)) {
        if(!checkFloat(t1, t2))
          return false;
      }
    }
    return true;
  }

  private ParamList createParamList(FormalParameterList f) {
    ParamList p = new ParamList();
    for(int i = 0; i < f.size(); ++i) {
      TypeNode t = f.elementAt(i).type;
      p.addElement(t.type);
    }
    return p;
  }

  public FunctionType(FunctionDeclaration f) {
    id = f.name.name;
    returnType = f.type.type;
    plist = createParamList(f.parameterList);
    lineNumber = f.lineNumber;
    offset = f.offset;
  }

  public Boolean equals(ParamList p) {
    return paramsMatch(this.plist, p);
  }

}
