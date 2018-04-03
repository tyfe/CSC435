package IR;

import Instruction.*;
import java.util.*;

public class InstructionList {
  List<Instruction> instructions;

  public InstructionList() {
    instructions = new ArrayList<>();
  }

  public void add(Instruction i) {
    instructions.add(i);
  }

  public int size() {
    return instructions.size();
  }

  public Instruction elementAt(int i) {
    return instructions.get(i);
  }
}
