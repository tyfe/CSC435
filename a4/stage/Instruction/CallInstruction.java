package Instruction;

import IR.*;
import java.lang.StringBuilder;
import java.util.*;
import Codegen.*;

public class CallInstruction extends Instruction {

  public Temporary result;
  public FunctionNode member;
  public List<Temporary> operands;

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
    for(int i = 0; i < operands.size() - 1; ++i) {
      Temporary t = operands.get(i);
      s.append(t.toString());
      s.append(" ");
    }
    if (operands.size() >= 1) {
      Temporary t = operands.get(operands.size() - 1);
      s.append(t.toString());
    }
    s.append(");");
    return s.toString();
  }

  public void accept(CodegenVisitor v) {
    v.visit(this);
  }
}
