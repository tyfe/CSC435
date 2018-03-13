package IR;

import AST.*;
import IR.TempGenerator;
import Type.*;
import java.io.PrintStream;


public class IRGenerator implements IRVisitor {

  private PrintStream out;
  private VariableTable vars;
  private TempGenerator gen;

  public IRGenerator(PrintStream p) {
    out = p;
  }

  private void printTab() {
    out.print("  ");
  }

	public void visit (AddExpression e) {

  }

	public void visit (ArrayType a) {

  }

	public void visit (ArrayAssignment s) {

  }

	public void visit (ArrayReference a) {

  }

	public void visit (Block b) {

  }

	public void visit (BooleanLiteral b) {

  }

	public void visit (CharacterLiteral c) {

  }

	public void visit (EqualityExpression e) {

  }

	public void visit (ExpressionStatement e) {

  }

	public void visit (FloatLiteral f) {

  }	

	public void visit (FormalParameter p) {
    p.type.accept(this);
  }

	public void visit (Function f) {
    gen = new TempGenerator();
    vars = new VariableTable();
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
  }

	public void visit (FunctionCall f) {

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

  }

	public void visit (IfStatement i) {

  }

	public void visit (IntegerLiteral i) {

  }

	public void visit (LessThanExpression e) {

  }

	public void visit (MultExpression e) {

  }

	public void visit (ParenExpression p) {

  }

	public void visit (PrintLnStatement s) {

  }

	public void visit (PrintStatement s) {

  }	

	public void visit (Program p) {
    for(int i = 0; i < p.size(); ++i) {
      Function f = p.elementAt(i);
      f.accept(this);
    }
  }

	public void visit (ReturnStatement s) {

  }

	public void visit (StringLiteral s) {

  }

	public void visit (SubtractExpression e) {

  }

	public void visit (Type t) {

  }

	public void visit (TypeNode t) {
    out.print(t.type.toShortString());
  }

	public void visit (VariableAssignment s) {

  }

	public void visit (VariableDeclaration v) {
    Type t = v.type.type;
    Temporary temp = gen.newVar(t);
    vars.addLocal(v.name.name, temp);
  }

	public void visit (WhileStatement s) {

  }
}
