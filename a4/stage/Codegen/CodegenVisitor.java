package Codegen;

import java.io.*;
import java.util.*;

import Codegen.CodeLabelGen;
import Instruction.*;
import IR.*;
import Type.*;

public class CodegenVisitor {

  private PrintStream out;
  private CodeLabelGen cg;
  private String classname;
  
  public CodegenVisitor(PrintStream p, String c) {
    out = p;
    cg = new CodeLabelGen();
    classname = c;
  }

  private void tab() {
    out.print("  ");
  }

  private void shortType(Type type) {
    if(type instanceof IntegerType) {
      out.print("i");
    } else if (type instanceof FloatType) {
      out.print("f");
    } else if (type instanceof CharType) {
      out.print("i");
    } else if (type instanceof StringType) {
      out.print("a");
    } else if (type instanceof BooleanType) {
      out.print("i");
    } else if (type instanceof ArrayType) {
      ArrayType at = (ArrayType)type;
      shortType(at.subType);
    }
  }

  public void visit(ArrayIndexAssignment a) {
    tab();
    out.println("aload " + a.left.index);
    tab();
    shortType(a.index.type);
    out.println("load " + a.index.index);
    tab();
    shortType(a.right.type);
    out.println("load " + a.right.index);
    tab();
    shortType(a.left.type);
    out.println("astore");
  }

  public void visit(ArrayReferenceAssignment a) {
    tab();
    out.println("aload " + a.right.index);
    tab();
    shortType(a.index.type);
    out.println("load " + a.index.index);
    tab();
    shortType(a.left.type);
    out.println("aload");
    tab();
    shortType(a.left.type);
    out.println("store " + a.left.index);
  }

  public void visit(BinaryOpAssignment b) {
    if(b.left.type instanceof StringType && b.operator == BinaryOp.PLUS) {
      tab();
      out.println("new java/lang/StringBuffer");
      tab();
      out.println("dup");
      tab();
      out.println("invokenonvirtual java/lang/StringBuffer/<init>()V");
      tab();
      out.println("aload " + b.left.index);
      tab();
      out.println("invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;");
      tab();
      out.println("aload " + b.right.index);
      tab();
      out.println("invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;");
      tab();
      out.println("invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;");
      tab();
      out.println("astore " + b.result.index);
    } else {
      tab();
      shortType(b.left.type);
      out.println("load " + b.left.index);

      tab();
      shortType(b.right.type);
      out.println("load " + b.right.index);

      tab();
    
      CodeLabel l1 = cg.addLabel();
      CodeLabel l2 = cg.addLabel();
  
      switch(b.operator) {
        case PLUS:
          shortType(b.left.type);
          out.println("add");
          tab();
          shortType(b.left.type);
          out.println("store " + b.result.index);
          break;
        case MINUS:
          shortType(b.left.type);
          out.println("sub");
          tab();
          shortType(b.left.type);
          out.println("store " + b.result.index);
          break;
        case TIMES:
          shortType(b.left.type);
          out.println("mul");
          tab();
          shortType(b.left.type);
          out.println("store " + b.result.index);
          break;
        case DIVIDE:
          shortType(b.left.type);
          out.println("div");
          tab();
          shortType(b.left.type);
          out.println("store " + b.result.index);
        case LESSTHAN:
          if(b.left.type instanceof StringType) {
            out.println("invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I");
          } else if (b.left.type instanceof FloatType) {
            out.println("fcmpg");
          } else {
            shortType(b.left.type);
            out.println("sub");
          }
          tab();
          out.println("iflt " + l1);
          tab();
          out.println("ldc 0");
          tab();
          out.println("goto " + l2);
          out.println(l1 + ":");
          tab();
          out.println("ldc 1");
          out.println(l2 + ":");
          tab();
          out.println("istore " + b.result.index);
          break;
        case LESSTHANEQUAL:
          if(b.left.type instanceof StringType) {
            out.println("invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I");
          } else if (b.left.type instanceof FloatType) {
            out.println("fcmpg");
          } else {
            shortType(b.left.type);
            out.println("sub");
          }
          tab();
          out.println("ifle " + l1);
          tab();
          out.println("ldc 0");
          tab();
          out.println("goto " + l2);
          out.println(l1 + ":");
          tab();
          out.println("ldc 1");
          out.println(l2 + ":");
          tab();
          out.println("istore " + b.result.index);
          break;
        case EQUALITY:
          if(b.left.type instanceof StringType) {
            out.println("invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I");
          } else if (b.left.type instanceof FloatType) {
            out.println("fcmpg");
          } else {
            shortType(b.left.type);
            out.println("sub");
          }
          tab();
          out.println("ifeq " + l1);
          tab();
          out.println("ldc 0");
          tab();
          out.println("goto " + l2);
          out.println(l1 + ":");
          tab();
          out.println("ldc 1");
          out.println(l2 + ":");
          tab();
          out.println("istore " + b.result.index);
          break;
        case NOTEQUAL:
          if(b.left.type instanceof StringType) {
            out.println("invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I");
          } else if (b.left.type instanceof FloatType) {
            out.println("fcmpg");
          } else {
            shortType(b.left.type);
            out.println("sub");
          }
          tab();
          out.println("ifne " + l1);
          tab();
          out.println("ldc 0");
          tab();
          out.println("goto " + l2);
          out.println(l1 + ":");
          tab();
          out.println("ldc 1");
          out.println(l2 + ":");
          tab();
          out.println("istore " + b.result.index);
          break;
        case GREATERTHANEQUAL:
          if(b.left.type instanceof StringType) {
            out.println("invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I");
          } else if (b.left.type instanceof FloatType) {
            out.println("fcmpg");
          } else {
            shortType(b.left.type);
            out.println("sub");
          }
          tab();
          out.println("ifge " + l1);
          tab();
          out.println("ldc 0");
          tab();
          out.println("goto " + l2);
          out.println(l1 + ":");
          tab();
          out.println("ldc 1");
          out.println(l2 + ":");
          tab();
          out.println("istore " + b.result.index);
          break;
        case GREATERTHAN:
          if(b.left.type instanceof StringType) {
            out.println("invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I");
          } else if (b.left.type instanceof FloatType) {
            out.println("fcmpg");
          } else {
            shortType(b.left.type);
            out.println("sub");
          }
          tab();
          out.println("ifgt " + l1);
          tab();
          out.println("ldc 0");
          tab();
          out.println("goto " + l2);
          out.println(l1 + ":");
          tab();
          out.println("ldc 1");
          out.println(l2 + ":");
          tab();
          out.println("istore " + b.result.index);
          break;
        default:
          break;
      }
    }

  }

  public void visit(CallInstruction c) {
    for(int i = 0; i < c.operands.size(); ++i) {
      Temporary t = c.operands.get(i);
      tab();
      shortType(t.type);
      out.println("load " + t.index);
    }
    tab();
    out.print("invokestatic " + classname + 
      "/" + c.member.id + "(");
    
    for(int i = 0; i < c.member.params.size(); ++i) {
      Type t = c.member.params.get(i);
      if(t instanceof StringType) {
        out.print("Ljava/lang/String;");
      } else {
        out.print(t.toShortString());
      }
    }
    out.println(")" + c.member.returnType.toShortString());
    
    if(c.result != null) {
      tab();
      shortType(c.result.type);
      out.println("store " + c.result.index);
    }
  }

  public void visit(ConstantAssignment c) {
    tab();
    if(c.type instanceof IntegerType) {
      out.println("ldc " + c.val_int);
    } else if (c.type instanceof FloatType) {
      out.println("ldc " + c.val_float);
    } else if (c.type instanceof CharType) {
      out.println("ldc " + (int)c.val_char);
    } else if (c.type instanceof StringType) {
      out.println("ldc " + "\"" + c.val_str + "\"");
    } else if (c.type instanceof BooleanType) {
      int val = c.val_bool ? 1 : 0;
      out.println("ldc " + val);
    }
    
    tab();
    shortType(c.type);
    out.println("store " + c.op.index);
  }

  public void visit(FunctionNode f) {
    CodeLabel startLabel = cg.addLabel();
    CodeLabel endLabel = cg.addLabel();

    out.print(".method public static ");
    if(f.id.equals("main")) {
      out.print("__main");
    } else {
      out.print(f.id);
    }
    out.print("(");
    for(int i = 0; i < f.params.size(); ++i) {
      Type t = f.params.get(i);
      if(t instanceof StringType) {
        out.print("Ljava/lang/String;");
      } else if (t instanceof ArrayType) {
        ArrayType arrayType = (ArrayType)t;
        out.print("[" + arrayType.subType.toShortString());
      } else {
        out.print(t.toShortString());
      }
    }
    out.println(")" + f.returnType.toShortString());

    tab();
    out.println(".limit locals " + f.vars.size());

    for(int i = 0; i < f.vars.size(); ++i) {
      VariableNode v = f.vars.elementAt(i);
      tab();
      out.print(".var " + i + " is ");
      if(!v.isParam && !v.isLocal) {
        out.print(v.t);
      } else {
        out.print(v.identifier);
      }

      out.print(" ");

      if(v.t.type instanceof ArrayType) {
        ArrayType arrayType = (ArrayType)v.t.type;
        Type type = arrayType.subType;
        out.print("[");
        if(type instanceof StringType) {
          out.print("Ljava/lang/String;");
        } else {
          out.print(type.toShortString());
        }
      } else if (v.t.type instanceof StringType) {
        out.print("Ljava/lang/String;");
      } else {
        out.print(v.t.type.toShortString());
      }

      out.println(" from " + startLabel + " to " + endLabel);
    }
    
    tab();
    out.println(".limit stack 16");
    out.println(startLabel + ":");

    for(int i = 0; i < f.vars.size(); ++i) {
      VariableNode v = f.vars.elementAt(i);
      if(!v.isParam) {
        Temporary t = v.t;
        if(t.type instanceof IntegerType) {
          tab();
          out.println("ldc 0");
          tab();
          out.println("istore " + t.index);
        } else if (t.type instanceof FloatType) {
          tab();
          out.println("ldc 0.0");
          tab();
          out.println("fstore " + t.index);
        } else if (t.type instanceof CharType) {
          tab();
          out.println("ldc 0");
          tab();
          out.println("istore " + t.index);
        } else if (t.type instanceof StringType) {
          tab();
          out.println("aconst_null");
          tab();
          out.println("astore " + t.index);
        } else if (t.type instanceof BooleanType) {
          tab();
          out.println("ldc 0");
          tab();
          out.println("istore " + t.index);
        } else if (t.type instanceof ArrayType) {
          tab();
          out.println("aconst_null");
          tab();
          out.println("astore " + t.index);
        }
      }
    }

    for(int i = 0; i < f.instructions.size(); ++i) {
      Instruction ins = f.instructions.elementAt(i);

      out.println(";\t" + ins);

      ins.accept(this);
    }
    out.println(endLabel + ":");
    out.println(".end method"); 
  }

  public void visit(GotoInstruction g) {
    tab();
    out.println("goto " + g.label);
  }

  public void visit(IfElseInstruction i) {
    tab();
    shortType(i.op.type);
    out.println("load " + i.op.index);
    tab();
    out.println("ifne " + i.label);
  }

  public void visit(IRGenerator ir) {
    out.println(".source " + classname + ".ul");
    out.println(".class public " + classname);
    out.println(".super java/lang/Object");
    for(int i = 0; i < ir.functionList.size(); ++i) {
      FunctionNode f = ir.functionList.get(i);
      this.visit(f);
    }

    out.println();
    out.println(
      ";--------------------------------------------;\n" +
      ";                                            ;\n" +
      "; Boilerplate                                ;\n" +
      ";                                            ;\n" +
      ";--------------------------------------------;\n" +
      "\n" +
      ".method public static main([Ljava/lang/String;)V\n" +
      "  ; set limits used by this method\n" +
      "  .limit locals 1\n" +
      "  .limit stack 4\n" +
      "  invokestatic " + classname + "/__main()V\n" +
      "  return\n" +
      ".end method\n" +
      "\n" +
      "; standard initializer\n" +
      ".method public <init>()V\n" +
      "  aload_0\n" +
      "  invokenonvirtual java/lang/Object/<init>()V\n" +
      "  return\n" +
      ".end method"
    );
  }

  public void visit(LabelInstruction l) {
    out.println(l.label + ":");
  }

  public void visit(NewArrayAssignment n) {
    tab();
    out.println("ldc " + n.length);
    tab();
    if(n.type instanceof StringType) {
      out.println("anewarray java/lang/String");
    } else {
      out.println("newarray " + n.type);
    }
    tab();
    out.println("astore " + n.op.index);
  }

  public void visit(OperandAssignment o) {
    tab();
    shortType(o.right.type);
    out.println("load " + o.right.index);
    tab();
    shortType(o.left.type);
    out.println("store " + o.left.index);
  }

  public void visit(PrintInstruction p) {
    tab();
    out.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
    tab();
    shortType(p.op.type);
    out.println("load " + p.op.index);
    tab();
    out.print("invokevirtual java/io/PrintStream/print(");
    if(p.op.type instanceof StringType) {
      out.print("Ljava/lang/String;");
    } else {
      out.print(p.op.type.toShortString());
    }
    out.println(")V");
  }

  public void visit(PrintLnInstruction p) {
    tab();
    out.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
    tab();
    shortType(p.op.type);
    out.println("load " + p.op.index);
    tab();
    out.print("invokevirtual java/io/PrintStream/println(");
    if(p.op.type instanceof StringType) {
      out.print("Ljava/lang/String;");
    } else {
      out.print(p.op.type.toShortString());
    }
    out.println(")V");

  }

  public void visit(ReturnInstruction r) {
    tab();
    if(r.op != null) {
      shortType(r.op.type);
      out.println("load " + r.op.index);
      tab();
      shortType(r.op.type);
      out.println("return");
    } else {
      out.println("return");
    }
  }

  public void visit(UnaryOpAssignment u) {
    tab();
    shortType(u.right.type);
    out.println("load " + u.right.index);
    
    switch(u.operator) {
      case NEGATION:
        tab(); 
        shortType(u.right.type);
        out.println("neg");
        tab();
        shortType(u.left.type);
        out.println("store " + u.left.index);
        break;
      case INVERSION:
        tab();
        out.println("ldc 1");
        tab();
        shortType(u.right.type);
        out.println("xor");
        tab();
        shortType(u.left.type);
        out.println("store " + u.left.index);
        break;
      case CONVERSION:
        tab();
        shortType(u.right.type);
        out.print("2");
        shortType(u.left.type);
        out.println();
        tab();
        shortType(u.left.type);
        out.println("store " + u.left.index);
        break;
      default:
        break;
    }
  }

}
