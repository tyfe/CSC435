package AST;

import AST.Program;
import java.util.Iterator;

public class PrintVisitor implements Visitor {
  int indentCount;

  public PrintVisitor() {
    indentCount = 0;
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

  // public void visit(FormalParameter p) {

  // }

  public void visit(Function f) {
    f.fd.accept(this);
  }

  // public void visit(FunctionBody f) {

  // }

  // public void visit(FunctionCall f) {

  // }

  public void visit(FunctionDeclaration f) {
    f.name.accept(this);
  }

  public void visit(Identifier i) {
    System.out.println(i.name);
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

  // public void visit(Type t) {

  // }

  // public void visit(TypeNode t) {

  // }

  // public void visit(VariableAssignment s) {

  // }

  // public void visit(VariableDeclaration v) {

  // }

  // public void visit(WhileStatement s) {

  // }

}
