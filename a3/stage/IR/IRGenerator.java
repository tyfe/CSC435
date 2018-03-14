package IR;

import AST.*;
import Instruction.*;
import Type.*;
import java.io.PrintStream;
import java.util.*;


public class IRGenerator implements IRVisitor {

  private PrintStream out;
  private VariableTable vars;
  private FunctionTable funcs;
  private TempGenerator gen;
  private InstructionList ins;
  private LabelGenerator lgen;
  private Temporary place;

  // constants
  private final FloatType FLOAT = new FloatType();
  private final CharType CHAR = new CharType();
  private final IntegerType INTEGER = new IntegerType();
  private final StringType STRING = new StringType();
  private final VoidType VOID = new VoidType();
  private final BooleanType BOOLEAN = new BooleanType();

  public IRGenerator(PrintStream p) {
    funcs = new FunctionTable();
    out = p;
  }

  private void printTab() {
    out.print("  ");
  }

  private Temporary newTemp(Type t) {
    Temporary temp = gen.newVar(t);
    vars.add(temp);
    return temp;
  }

	public void visit (AddExpression e) {
    Temporary left = newTemp(null);
    Temporary right = newTemp(null);
    Temporary parent = place;
    
    place = left;
    e.expr1.accept(this);

    place = right;
    e.expr2.accept(this);

    // convert types
    if(!left.type.equals(right.type)) {
      if(left.type.equals(INTEGER) && right.type.equals(FLOAT)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, left, UnaryOp.CONVERSION));
        left = t;
      } else if(left.type.equals(FLOAT) && right.type.equals(INTEGER)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, right, UnaryOp.CONVERSION));
        right = t;
      }
    }

    // temp hack
    if(parent == null) {
      parent = newTemp(left.type);
    }

    parent.type = left.type;
    place = parent;

    ins.add(new BinaryOpAssignment(parent, left, right, BinaryOp.PLUS));
  }

	public void visit (ArrayType a) {

  }

	public void visit (ArrayAssignment s) {

  }

	public void visit (ArrayReference a) {
    Temporary t = newTemp(null);
    Temporary parent = place;
    Temporary var = vars.retrieve(a.name.name);
    
    place = t;
    a.expr.accept(this);
    place = parent;

    place.type = var.type;

    ins.add(new ArrayReferenceAssignment(place, var, t));

  }

	public void visit (Block b) {
    for(int i = 0; i < b.statementList.size(); ++i) {
      Statement s = b.statementList.elementAt(i);
      s.accept(this);
    }

  }

	public void visit (BooleanLiteral b) {
    place.type = FLOAT;
    ins.add(new ConstantAssignment(place, b.val));

  }

	public void visit (CharacterLiteral c) {
    place.type = CHAR;
    ins.add(new ConstantAssignment(place, c.val));

  }

	public void visit (EqualityExpression e) {
    Temporary left = newTemp(null);
    Temporary right = newTemp(null);
    Temporary parent = place;
    
    place = left;
    e.expr1.accept(this);

    place = right;
    e.expr2.accept(this);

    // convert types
    if(!left.type.equals(right.type)) {
      if(left.type.equals(INTEGER) && right.type.equals(FLOAT)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, left, UnaryOp.CONVERSION));
        left = t;
      } else if(left.type.equals(FLOAT) && right.type.equals(INTEGER)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, right, UnaryOp.CONVERSION));
        right = t;
      }
    }

    // temp hack
    if(parent == null) {
      parent = newTemp(left.type);
    }

    parent.type = left.type;
    place = parent;

    ins.add(new BinaryOpAssignment(parent, left, right, BinaryOp.EQUALITY));

  }

	public void visit (ExpressionStatement e) {
    e.expr.accept(this);

  }

	public void visit (FloatLiteral f) {
    place.type = FLOAT;
    ins.add(new ConstantAssignment(place, f.val));
  }	

	public void visit (FormalParameter p) {
    p.type.accept(this);
  }

	public void visit (Function f) {
    gen = new TempGenerator();
    vars = new VariableTable();
    lgen = new LabelGenerator();
    ins = new InstructionList();

    f.fd.accept(this);
    f.fb.accept(this);
    out.println("}");
  }

	public void visit (FunctionBody f) {
    for(int i = 0; i < f.varList.size(); ++i) {
      VariableDeclaration v = f.varList.elementAt(i);
      v.accept(this);
    }

    for(int i = 0; i < f.statementList.size(); ++i) {
      Statement s = f.statementList.elementAt(i);
      place = null; 
      s.accept(this);
    }

    for(int i = 0; i < vars.size(); ++i) {
      VariableNode v = vars.elementAt(i);
      printTab();
      out.print("TEMP " + v.t.index + ":" + v.t.type.toShortString());
      if(v.isParam) {
        out.print(" [P(\"" + v.identifier + "\")]" );
      } else if (v.isLocal) {
        out.print(" [L(\"" + v.identifier + "\")]" );
      }
      out.println(";");
    }

    for(int i = 0; i < ins.size(); ++i) {
      Instruction s = ins.elementAt(i);
      if(!(s instanceof LabelInstruction)) {
        printTab();
      }
      out.println(s);
    }
  }

	public void visit (FunctionCall f) {
    Temporary parent = place;
    List<Temporary> params = new ArrayList<>();
    FunctionNode func = funcs.retrieve(f.name.name);
    for(int i = 0; i < f.expressionList.size(); ++i) {
      Expression e = f.expressionList.elementAt(i);
      place = newTemp(null);
      e.accept(this);
      params.add(place);
    }
    place = parent;
    if(place != null) place.type = func.returnType;
    if(func.returnType.equals(VOID)) {
      ins.add(new CallInstruction(func, params));
    } else {
      ins.add(new CallInstruction(parent, func, params));
    }
    
  }

	public void visit (FunctionDeclaration f) {
    out.print("FUNC " + f.name.name + "(");

    for(int i = 0; i < f.parameterList.size(); ++i) {
      FormalParameter fp = f.parameterList.elementAt(i);
      fp.accept(this);
    }

    out.print(")" + f.type.type.toShortString() + "\n{\n");

    //create temporaries for the parameters
    for(int i = 0; i < f.parameterList.size(); ++i) {
      FormalParameter fp = f.parameterList.elementAt(i);
      Type t = fp.type.type;
      Temporary temp = gen.newVar(t);
      vars.addParameter(fp.name.name, temp);
    }
  }

	public void visit (Identifier i) {
  }

	public void visit (IdentifierValue v) {
    Temporary x = vars.retrieve(v.name.name);
    place.type = x.type;
    ins.add(new OperandAssignment(place, x));
  }

	public void visit (IfStatement i) {
    Temporary t = newTemp(null);
    Label l1 = lgen.newLabel();
    Label l2;
    place = t;

    i.expr.accept(this);

    t = newTemp(place.type);
    ins.add(new UnaryOpAssignment(t, place, UnaryOp.INVERSION));

    if(i.block2 != null) {
      l2 = lgen.newLabel();
      ins.add(new IfElseInstruction(t, l1));
      i.block1.accept(this);
      ins.add(new GotoInstruction(l2));
      ins.add(new LabelInstruction(l1));
      i.block2.accept(this);
      ins.add(new LabelInstruction(l2));
    } else {
      ins.add(new IfElseInstruction(t, l1));
      i.block1.accept(this);
      ins.add(new LabelInstruction(l1));
    }
    

  }

	public void visit (IntegerLiteral i) {
    place.type = INTEGER;
    ins.add(new ConstantAssignment(place, i.val));
  }

	public void visit (LessThanExpression e) {
    Temporary left = newTemp(null);
    Temporary right = newTemp(null);
    Temporary parent = place;
    
    place = left;
    e.expr1.accept(this);

    place = right;
    e.expr2.accept(this);

    // convert types
    if(!left.type.equals(right.type)) {
      if(left.type.equals(INTEGER) && right.type.equals(FLOAT)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, left, UnaryOp.CONVERSION));
        left = t;
      } else if(left.type.equals(FLOAT) && right.type.equals(INTEGER)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, right, UnaryOp.CONVERSION));
        right = t;
      }
    }

    // temp hack
    if(parent == null) {
      parent = newTemp(left.type);
    }

    parent.type = left.type;
    place = parent;

    ins.add(new BinaryOpAssignment(parent, left, right, BinaryOp.LESSTHAN));

  }

	public void visit (MultExpression e) {
    Temporary left = newTemp(null);
    Temporary right = newTemp(null);
    Temporary parent = place;
    
    place = left;
    e.expr1.accept(this);

    place = right;
    e.expr2.accept(this);

    // convert types
    if(!left.type.equals(right.type)) {
      if(left.type.equals(INTEGER) && right.type.equals(FLOAT)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, left, UnaryOp.CONVERSION));
        left = t;
      } else if(left.type.equals(FLOAT) && right.type.equals(INTEGER)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, right, UnaryOp.CONVERSION));
        right = t;
      }
    }

    // temp hack
    if(parent == null) {
      parent = newTemp(left.type);
    }

    parent.type = left.type;
    place = parent;

    ins.add(new BinaryOpAssignment(parent, left, right, BinaryOp.TIMES));

  }

	public void visit (ParenExpression p) {
    p.expr.accept(this);
  }

	public void visit (PrintLnStatement s) {
    place = newTemp(null);
    s.expr.accept(this);

    ins.add(new PrintLnInstruction(place));

  }

	public void visit (PrintStatement s) {
    place = newTemp(null);
    s.expr.accept(this);

    ins.add(new PrintInstruction(place));
  }	

	public void visit (Program p) {
    for(int i = 0; i < p.size(); ++i) {
      Function f = p.elementAt(i);
      funcs.add(f.fd);
    }

    for(int i = 0; i < p.size(); ++i) {
      Function f = p.elementAt(i);
      f.accept(this);
    }
  }

	public void visit (ReturnStatement s) {
    if(s.expr == null) {
      ins.add(new ReturnInstruction());
      return;
    }
    Temporary t = newTemp(null);
    place = t;
    s.expr.accept(this);
    ins.add(new ReturnInstruction(place));
  }

	public void visit (StringLiteral s) {
    place.type = STRING;
    ins.add(new ConstantAssignment(place, s.val));

  }

	public void visit (SubtractExpression e) {
    Temporary left = newTemp(null);
    Temporary right = newTemp(null);
    Temporary parent = place;
    
    place = left;
    e.expr1.accept(this);

    place = right;
    e.expr2.accept(this);

    // convert types
    if(!left.type.equals(right.type)) {
      if(left.type.equals(INTEGER) && right.type.equals(FLOAT)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, left, UnaryOp.CONVERSION));
        left = t;
      } else if(left.type.equals(FLOAT) && right.type.equals(INTEGER)) {
        Temporary t = newTemp(FLOAT);
        ins.add(new UnaryOpAssignment(t, right, UnaryOp.CONVERSION));
        right = t;
      }
    }

    // temp hack
    if(parent == null) {
      parent = newTemp(left.type);
    }

    parent.type = left.type;
    place = parent;

    ins.add(new BinaryOpAssignment(parent, left, right, BinaryOp.MINUS));

  }

	public void visit (Type t) {

  }

	public void visit (TypeNode t) {
    out.print(t.type.toShortString());
  }

	public void visit (VariableAssignment s) {
    place = vars.retrieve(s.name.name);
    s.expr.accept(this);
  }

	public void visit (VariableDeclaration v) {
    Type t = v.type.type;
    Temporary temp = gen.newVar(t);
    vars.addLocal(v.name.name, temp);
    if(temp.type instanceof ArrayType) {
      ins.add(new NewArrayAssignment(temp, ((ArrayType)t).subType, ((ArrayType)t).size));
    }
  }

	public void visit (WhileStatement s) {
    Temporary t = newTemp(null);
    Label l1 = lgen.newLabel();
    Label l2 = lgen.newLabel();
    place = t;

    ins.add(new LabelInstruction(l1));
    s.expr.accept(this);

    t = newTemp(place.type);
    ins.add(new UnaryOpAssignment(t, place, UnaryOp.INVERSION));

    ins.add(new IfElseInstruction(t, l2));
    s.block.accept(this);
    ins.add(new GotoInstruction(l1));
    ins.add(new LabelInstruction(l2));
  }
}
