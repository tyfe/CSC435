package Instruction;

import IR.*;
import java.lang.StringBuilder;
import java.util.*;

public class CallInstruction extends Instruction {

  Temporary result;
  FunctionNode member;
  List<Temporary> operands;

  public CallInstruction(Temporary result, FunctionNode member, List<Temporary> operands) {
    this.result = result;
    this.member = member;
    this.operands = operands;
  }

  public CallInstruction(FunctionNode member, List<Temporary> operands) {
    this.result = null;
    this.member = member;
    this.operands = operands;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    if(result != null) {
      s.append(result + " := ");
    }
    s.append("CALL " + member.id + "(");
    for(int i = 0; i < operands.size(); ++i) {
      Temporary t = operands.get(i);
      s.append(t.toString());
    }
    s.append(")");
    return s.toString();
  }
}
