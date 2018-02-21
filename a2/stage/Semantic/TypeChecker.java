package Semantic;

import Semantic.FunctionEnvironment;
import Semantic.SemanticException;
import Type.*;
import AST.*;
import java.util.ArrayList;

public class TypeChecker {

  private FunctionEnvironment fe;

  // internal state parameters
  private boolean inInit = true;

  public TypeChecker() {
    fe = new FunctionEnvironment();
  }

  public Type visit (AddExpression e) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (ArrayType a) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (ArrayAssignment s) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (ArrayReference a) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (Block b) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (BooleanLiteral b) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (CharacterLiteral c) throws SemanticException {
    return new IntegerType();
  }

	// public Type visit (DoStatement s) throws SemanticException {
  // }

	public Type visit (EqualityExpression e) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (ExpressionStatement e) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (FloatLiteral f) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (FormalParameter p) throws SemanticException {
    return new IntegerType();
  }

	public void visit (Function f) throws SemanticException {
  }

	public Type visit (FunctionBody f) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (FunctionCall f) throws SemanticException {
    return new IntegerType();
  }

	public void visit (FunctionDeclaration f) throws SemanticException {

  }

	public void visit (Identifier i) throws SemanticException {
    // return i.name;
  }

	public Type visit (IdentifierValue v) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (IfStatement i) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (IntegerLiteral i) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (LessThanExpression e) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (MultExpression e) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (ParenExpression p) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (PrintLnStatement s) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (PrintStatement s) throws SemanticException {
    return new IntegerType();
  }

	public void visit (Program p) throws SemanticException {
    boolean containsMain = false;
    boolean containsDuplicates = false;
    ArrayList<String> fnames = new ArrayList<>();

    // initially visit all the functions to add to function environment
    for(int i = 0; i < p.size(); ++i) {
      Function f = p.elementAt(i);
      fe.add(f.fd);
    }

    for(int i = 0; i < fe.size(); ++i) {
      FunctionType ft = fe.elementAt(i);

      if(ft.id.equals("main")) {
        containsMain = true;
        if(!(ft.type instanceof VoidType)) {
          throw new SemanticException("main() must have void as its return type.", 0, 0);
        }
        if(ft.plist.size() != 0) {
          throw new SemanticException("main() must have no parameters", 0, 0);
        }
      }
        
      if(fnames.contains(ft.id))
        throw new SemanticException("Program contains duplicate functions.", 0, 0);
      fnames.add(ft.id);
    }

    if(fnames.size() == 0) {
      throw new SemanticException("Program contains no functions.", 0, 0);
    }

    if(!containsMain) {
      throw new SemanticException("Program contains no main function.", 0, 0);
    }

  }

	public Type visit (ReturnStatement s) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (StringLiteral s) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (SubtractExpression e) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (Type t) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (TypeNode t) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (VariableAssignment s) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (VariableDeclaration v) throws SemanticException {
    return new IntegerType();
  }

	public Type visit (WhileStatement s) throws SemanticException {
    return new IntegerType();
  }

  
}
