package AST;

import AST.*;
import Type.*;
import java.util.Iterator;

public class PrintVisitor implements Visitor {
  int indentCount;
  final int spaceInc = 2;

  public PrintVisitor() {
    indentCount = 0;
  }

  private void printTab() {
    for(int i = 0; i < indentCount; ++i) {
      System.out.print(" ");
    }
  }

  public void visit(AddExpression e) {
    e.expr1.accept(this);
    System.out.print(" + ");
    e.expr2.accept(this);
  }

  // public void visit(ArrayType a) {

  // }

  public void visit(ArrayAssignment s) {
    s.name.accept(this);
    System.out.print("[");
    s.index.accept(this);
    System.out.print("] = ");
    s.expr.accept(this);
    System.out.println(";");
  }

  public void visit(ArrayReference a) {
    a.name.accept(this);
    System.out.print("[");
    a.expr.accept(this);
    System.out.print("]");
  }

  public void visit(Block b) {
    StatementList sl = b.statementList;

    for(int i = 0; i < sl.size(); ++i) {
      Statement s = sl.elementAt(i);
      this.printTab();
      s.accept(this);
    }

  }

  public void visit(BooleanLiteral b) {
    System.out.print(b.val);
  }

  public void visit(CharacterLiteral c) {
    System.out.print(c.val);
  }

  public void visit(EqualityExpression e) {
    e.expr1.accept(this);
    System.out.print(" == ");
    e.expr2.accept(this);
  }

  public void visit(ExpressionStatement e) {
    e.expr.accept(this);
    System.out.println(";");
  }

  public void visit(FloatLiteral f) {
    System.out.print(f.val);
  }

  public void visit(FormalParameter p) {
    p.type.accept(this);
    System.out.print(" ");
    p.name.accept(this);
  }

  public void visit(Function f) {
    f.fd.accept(this);
    f.fb.accept(this);
  }

  public void visit(FunctionBody f) {
    VariableDeclarationList vl = f.varList;
    StatementList sl = f.statementList;
    Iterator<VariableDeclaration> vlIter = vl.variableDeclarationList.iterator();
    Iterator<Statement> slIter = sl.statementList.iterator();

    indentCount += spaceInc;
    System.out.println("{");

    if(vl.size() != 0 || sl.size() != 0) {
      while(vlIter.hasNext()) {
        VariableDeclaration vd = vlIter.next();
        this.printTab();
        vd.accept(this);
      }

      while(slIter.hasNext()) {
        Statement s = slIter.next();
        this.printTab();
        s.accept(this);
      }
    }

    System.out.println("}");
    indentCount -= spaceInc;
  }

  public void visit(FunctionCall f) {
    ExpressionList el = f.expressionList;

    f.name.accept(this);
    System.out.print("(");

    for(int i = 0; i < el.size() - 1; ++i) {
      Expression e = el.elementAt(i);
      e.accept(this);
      System.out.print(", ");
    }

    Expression e = el.elementAt(el.size() - 1);
    e.accept(this);
    System.out.print(")");

  }

  public void visit(FunctionDeclaration f) {
    Iterator<FormalParameter> fpl = f.parameterList.parameterList.iterator();

    f.type.accept(this);
    System.out.print(" ");
    f.name.accept(this);

    System.out.print("(");
    for(int i = 0; i <  f.parameterList.parameterList.size() - 1; ++i) {
      FormalParameter fp = fpl.next();
      fp.accept(this);
      System.out.print(", ");
    }
    
    if(f.parameterList.parameterList.size() > 0) {
      // don't print out comma on last iteration
      fpl.next().accept(this);
    }
    
    System.out.print(") ");
  }

  public void visit(Identifier i) {
    System.out.print(i.name);
  }

  public void visit(IdentifierValue v) {
    v.name.accept(this);
  }

  public void visit(IfStatement i) {
    System.out.print("if (");
    i.expr.accept(this);

    System.out.println(") {");
    this.indentCount += spaceInc;

    i.block1.accept(this);
    
    this.indentCount -= spaceInc;
    this.printTab();
    System.out.print("}");

    if(i.block2 == null) {
      System.out.println();
    } else {
      System.out.println(" else {");
      
      this.indentCount += spaceInc;
      i.block2.accept(this);

      this.indentCount -= spaceInc;
      this.printTab();
      System.out.println("}");
    }

  }

  public void visit(IntegerLiteral i) {
    System.out.print(i.val);
  }

  public void visit(LessThanExpression e) {
    e.expr1.accept(this);
    System.out.print(" < ");
    e.expr2.accept(this);
  }

  public void visit(MultExpression e) {
    e.expr1.accept(this);
    System.out.print(" * ");
    e.expr2.accept(this);
  }

  public void visit(ParenExpression p) {
    System.out.print("(");
    p.expr.accept(this);
    System.out.print(")");
  }

  public void visit(PrintLnStatement s) {
    System.out.print("println ");
    s.expr.accept(this);
    System.out.println(";");
  }

  public void visit(PrintStatement s) {
    System.out.print("print ");
    s.expr.accept(this);
    System.out.println(";");
  }

  public void visit(Program p) {
    Iterator<Function> fl = p.functionList.iterator();
    while(fl.hasNext()) {
      Function f = fl.next();
      f.accept(this);
    }
  }

  public void visit(ReturnStatement s) {
    System.out.print("return");
    if(s.expr != null) {
      System.out.print(" ");
      s.expr.accept(this);
    }
    System.out.println(";");
  }

  public void visit(StringLiteral s) {
    System.out.print(s.val);
  }

  public void visit(SubtractExpression e) {
    e.expr1.accept(this);
    System.out.print(" - ");
    e.expr2.accept(this);
  }

  public void visit(Type t) {
    System.out.print(t);
  }

  public void visit(TypeNode t) {
    t.type.accept(this);
  }

  public void visit(VariableAssignment s) {
    s.name.accept(this);
    System.out.print(" = ");
    s.expr.accept(this);
    System.out.println(";");
  }

  public void visit(VariableDeclaration v) {
    v.type.accept(this);
    System.out.print(" ");
    v.name.accept(this);
    System.out.println(";");
  }

  public void visit(WhileStatement s) {
    System.out.print("while (");
    s.expr.accept(this);
    System.out.println(") {");

    this.indentCount += spaceInc;
    s.block.accept(this);
    this.indentCount -= spaceInc;

    this.printTab();
    System.out.println("}");
  }

}
