package Semantic;

import Semantic.FunctionEnvironment;
import Semantic.SemanticException;
import Type.*;
import AST.*;
import java.util.ArrayList;

public class TypeChecker {

  private FunctionEnvironment fe;
  private VariableEnvironment ve;

  // internal state parameters
  String current_id;
  Type current_type;

  public TypeChecker() {
    fe = new FunctionEnvironment();
  }

  public void visit (AddExpression e) throws SemanticException {
    
  }

	public void visit (ArrayType a) throws SemanticException {
    
  }

	public void visit (ArrayAssignment s) throws SemanticException {
    
  }

	public void visit (ArrayReference a) throws SemanticException {
    
  }

	public void visit (Block b) throws SemanticException {
    
  }

	public void visit (BooleanLiteral b) throws SemanticException {
    
  }

	public void visit (CharacterLiteral c) throws SemanticException {
    
  }

	// public void visit (DoStatement s) throws SemanticException {
  // }

	public void visit (EqualityExpression e) throws SemanticException {
    
  }

	public void visit (ExpressionStatement e) throws SemanticException {
    
  }

	public void visit (FloatLiteral f) throws SemanticException {
    
  }

	public void visit (FormalParameter p) throws SemanticException {
    
  }

	public void visit (Function f) throws SemanticException {
    ve = new VariableEnvironment();
    f.fb.accept(this);
  }

	public void visit (FunctionBody f) throws SemanticException {
    for(int i = 0; i < f.varList.size(); ++i) {
      VariableDeclaration vd = f.varList.elementAt(i);
      VariableType var;
      vd.accept(this);
      var = new VariableType(current_id, current_type);
      System.out.println(current_id);
      if(ve.contains(var)) {
        throw new SemanticException("Duplicate variable identifier: " + var.id, 0, 0);
      }
      ve.add(var);
    }
  }

	public void visit (FunctionCall f) throws SemanticException {
    
  }

	public void visit (FunctionDeclaration f) throws SemanticException {

  }

	public void visit (Identifier i) throws SemanticException {
    this.current_id = i.name;
  }

	public void visit (IdentifierValue v) throws SemanticException {
    
  }

	public void visit (IfStatement i) throws SemanticException {
    
  }

	public void visit (IntegerLiteral i) throws SemanticException {
    
  }

	public void visit (LessThanExpression e) throws SemanticException {
    
  }

	public void visit (MultExpression e) throws SemanticException {
    
  }

	public void visit (ParenExpression p) throws SemanticException {
    
  }

	public void visit (PrintLnStatement s) throws SemanticException {
    
  }

	public void visit (PrintStatement s) throws SemanticException {
    
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

    // visit the bodies of all the functions
    for(int i = 0; i < p.size(); ++i) {
      Function f = p.elementAt(i); 
      f.accept(this);
    }

  }

	public void visit (ReturnStatement s) throws SemanticException {
    
  }

	public void visit (StringLiteral s) throws SemanticException {
    
  }

	public void visit (SubtractExpression e) throws SemanticException {
    
  }

	public void visit (Type t) throws SemanticException {
    
  }

	public void visit (TypeNode t) throws SemanticException {
    current_type = t.type;
  }

	public void visit (VariableAssignment s) throws SemanticException {
    
  }

	public void visit (VariableDeclaration v) throws SemanticException {
    v.name.accept(this);
    v.type.accept(this);
  }

	public void visit (WhileStatement s) throws SemanticException {
    
  }

  
}
