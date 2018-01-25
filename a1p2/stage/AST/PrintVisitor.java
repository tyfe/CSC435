package AST;

import AST.*;
import Type.*;
import java.util.Iterator;

public class PrintVisitor implements Visitor {
  int indentCount;

  public PrintVisitor() {
    indentCount = 0;
  }

  private void printTab() {
    for(int i = 0; i < indentCount; ++i) {
      System.out.print(" ");
    }
  }

  // public void visit(AddExpression e) {

  // }

  // public void visit(ArrayType a) {

  // }

  // public void visit(ArrayAssignment s) {

  // }

  // public void visit(ArrayReference a) {

  // }

  // public void visit(Block b) {

  // }

  // public void visit(BooleanLiteral b) {

  // }

  // public void visit(CharacterLiteral c) {

  // }

  // public void visit(DoStatement s) {

  // }

  // public void visit(EqualityExpression e) {

  // }

  // public void visit(ExpressionStatement e) {

  // }

  // public void visit(FloatLiteral f) {

  // }

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
    // Iterator<Statement> slIter = sl.statementList.iterator();

    indentCount += 4;
    System.out.println("{");

    if(vl.size() != 0 && sl.size() != 0) {
      while(vlIter.hasNext()) {
        VariableDeclaration vd = vlIter.next();
        this.printTab();
        vd.accept(this);
      }

      // while(slIter.hasNext()) {
      //   Statement s = slIter.next();
      //   this.printTab();
      //   s.accept(this);
      // }
    }

    System.out.println("}");
    indentCount -= 4;
  }

  // public void visit(FunctionCall f) {

  // }

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

  // public void visit(IdentifierValue v) {

  // }

  // public void visit(IfStatement i) {

  // }

  // public void visit(IntegerLiteral i) {

  // }

  // public void visit(LessThanExpression e) {

  // }

  // public void visit(MultExpression e) {

  // }

  // public void visit(ParenExpression p) {

  // }

  // public void visit(PrintLnStatement s) {

  // }

  // public void visit(PrintStatement s) {

  // }

  public void visit(Program p) {
    Iterator<Function> fl = p.functionList.iterator();
    while(fl.hasNext()) {
      Function f = fl.next();
      f.accept(this);
    }
  }

  // public void visit(ReturnStatement s) {

  // }

  // public void visit(StringLiteral s) {

  // }

  // public void visit(SubtractExpression e) {

  // }

  public void visit(Type t) {
    System.out.print(t);
  }

  public void visit(TypeNode t) {
    t.type.accept(this);
  }

  // public void visit(VariableAssignment s) {

  // }

  public void visit(VariableDeclaration v) {
    v.type.accept(this);
    System.out.print(" ");
    v.name.accept(this);
    System.out.println(";");
  }

  // public void visit(WhileStatement s) {

  // }

}
