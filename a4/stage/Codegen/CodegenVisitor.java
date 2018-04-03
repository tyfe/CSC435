package Codegen;

import java.io.*;
import java.util.*;

import Instruction.*;
import IR.*;

public class CodegenVisitor {

  private PrintStream out;
  
  public CodegenVisitor(PrintStream p) {
    out = p;
  }

  public void visit(ArrayIndexAssignment a) {

  }

  public void visit(ArrayReferenceAssignment a) {

  }

  public void visit(BinaryOpAssignment b) {

  }

  public void visit(CallInstruction c) {

  }

  public void visit(ConstantAssignment c) {

  }

  public void visit(FunctionNode f) {
    for(int i = 0; i < f.instructions.size(); ++i) {
      Instruction ins = f.instructions.elementAt(i);
      ins.accept(this);
    }
  }

  public void visit(GotoInstruction g) {

  }

  public void visit(IfElseInstruction i) {

  }

  public void visit(IRGenerator ir) {
    for(int i = 0; i < ir.functionList.size(); ++i) {
      FunctionNode f = ir.functionList.get(i);
      this.visit(f);
    }
  }

  public void visit(LabelInstruction l) {

  }

  public void visit(NewArrayAssignment n) {

  }

  public void visit(OperandAssignment o) {

  }

  public void visit(PrintInstruction p) {

  }

  public void visit(PrintLnInstruction p) {

  }

  public void visit(ReturnInstruction r) {

  }

  public void visit(UnaryOpAssignment u) {

  }

}
