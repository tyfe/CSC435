package Semantic;

import Type.*;
import AST.*;
import java.util.ArrayList;

public class TypeChecker {

  private FunctionEnvironment fe;
  private VariableEnvironment ve;

  // internal state parameters
  String current_id;
  Type current_type;
  FunctionType current_function;

  public TypeChecker() {
    fe = new FunctionEnvironment();
  }

  public Type visit (AddExpression e) throws SemanticException {
    return new VoidType();
  }

	public Type visit (ArrayType a) throws SemanticException {
    return new VoidType();
  }

	public Type visit (ArrayAssignment s) throws SemanticException {
    return new VoidType();
  }

	public Type visit (ArrayReference a) throws SemanticException {
    return new VoidType();
  }

	public Type visit (Block b) throws SemanticException {
    return new VoidType();
  }

	public Type visit (BooleanLiteral b) throws SemanticException {
    return new VoidType();
  }

	public Type visit (CharacterLiteral c) throws SemanticException {
    return new VoidType();
  }

	// public Type visit (DoStatement s) throws SemanticException {
  // }

	public Type visit (EqualityExpression e) throws SemanticException {
    return new VoidType();
  }

	public Type visit (ExpressionStatement e) throws SemanticException {
    return new VoidType();
  }

	public Type visit (FloatLiteral f) throws SemanticException {
    return new VoidType();
  }

	public Type visit (FormalParameter p) throws SemanticException {
    return new VoidType();
  }

	public Type visit (Function f) throws SemanticException {
    current_function = new FunctionType(f.fd);
    ve = new VariableEnvironment();
    f.fb.accept(this);
    return new VoidType();
  }

	public Type visit (FunctionBody f) throws SemanticException {
    for(int i = 0; i < f.varList.size(); ++i) {
      VariableDeclaration vd = f.varList.elementAt(i);
      VariableType var;
      vd.accept(this);
      var = new VariableType(current_id, current_type);
      if(ve.contains(var)) {
        throw new SemanticException("Duplicate variable identifier: " + var.id, vd.lineNumber, vd.offset);
      }
      ve.add(var);
    }

    for(int i = 0; i < f.statementList.size(); ++i) {
      Statement s = f.statementList.elementAt(i);
      s.accept(this);
    }

    // on function body, make sure return statement exists with non-void functions
    return new VoidType();
  }

	public Type visit (FunctionCall f) throws SemanticException {
    return new VoidType();
  }

	public Type visit (FunctionDeclaration f) throws SemanticException {
    return new VoidType();
  }

	public Type visit (Identifier i) throws SemanticException {
    this.current_id = i.name;
    return new VoidType();
  }

	public Type visit (IdentifierValue v) throws SemanticException {
    return new VoidType();
  }

	public Type visit (IfStatement i) throws SemanticException {
    return new VoidType();
  }

	public Type visit (IntegerLiteral i) throws SemanticException {
    return new VoidType();
  }

	public Type visit (LessThanExpression e) throws SemanticException {
    return new VoidType();
  }

	public Type visit (MultExpression e) throws SemanticException {
    return new VoidType();
  }

	public Type visit (ParenExpression p) throws SemanticException {
    return new VoidType();
  }

	public Type visit (PrintLnStatement s) throws SemanticException {
    return new VoidType();
  }

	public Type visit (PrintStatement s) throws SemanticException {
    return new VoidType();
  }

	public Type visit (Program p) throws SemanticException {
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
          throw new SemanticException("main() must have void as its return type.", ft.lineNumber, ft.offset);
        }
        if(ft.plist.size() != 0) {
          throw new SemanticException("main() must have no parameters", ft.lineNumber, ft.offset);
        }
      }
        
      if(fnames.contains(ft.id))
        throw new SemanticException("Program contains duplicate functions.", ft.lineNumber, ft.offset);
      fnames.add(ft.id);
    }

    if(fnames.size() == 0) {
      throw new SemanticException("Program contains no functions.", p.lineNumber, p.offset);
    }

    if(!containsMain) {
      throw new SemanticException("Program contains no main function.", p.lineNumber, p.offset);
    }

    // visit the bodies of all the functions
    for(int i = 0; i < p.size(); ++i) {
      Function f = p.elementAt(i); 
      f.accept(this);
    }
    return new VoidType();
  }

	public Type visit (ReturnStatement s) throws SemanticException {
    return new VoidType();
  }

	public Type visit (StringLiteral s) throws SemanticException {
    return new VoidType();
  }

	public Type visit (SubtractExpression e) throws SemanticException {
    return new VoidType();
  }

	public Type visit (Type t) throws SemanticException {
    return new VoidType();
  }

	public Type visit (TypeNode t) throws SemanticException {
    current_type = t.type;
    return t.type;
  }

	public Type visit (VariableAssignment s) throws SemanticException {
    return new VoidType();
  }

	public Type visit (VariableDeclaration v) throws SemanticException {
    v.name.accept(this);
    v.type.accept(this);
    return new VoidType();
  }

	public Type visit (WhileStatement s) throws SemanticException {
    return new VoidType();
  }

  
}
