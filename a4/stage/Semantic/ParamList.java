package Semantic;

import java.util.Vector;
import Type.*;

public class ParamList {
  private Vector<Type> parameterList;

  public ParamList() {
    parameterList = new Vector<>();
  }

  public ParamList(Vector<Type> v) {
    parameterList = v;
  }

  public void addElement(Type t) {
    parameterList.addElement(t);
  }

  public Type elementAt(int index) {
    return parameterList.elementAt(index);
  }

  public int size() {
    return parameterList.size();
  }
}
