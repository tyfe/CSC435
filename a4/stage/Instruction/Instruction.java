package Instruction;

import Codegen.*;

public abstract class Instruction {
  public abstract void accept(CodegenVisitor v);
}
