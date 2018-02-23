package Semantic;

import Type.*;
import AST.*;
import java.util.ArrayList;
import java.util.Vector;

public class TypeChecker {

  private FunctionEnvironment fe;
  private VariableEnvironment ve;

  // internal state parameters
  private String current_id;
  private Type current_type;
  private FunctionType current_function;

  // constants
  private final FloatType FLOAT = new FloatType();
  private final CharType CHAR = new CharType();
  private final IntegerType INTEGER = new IntegerType();
  private final StringType STRING = new StringType();
  private final VoidType VOID = new VoidType();
  private final BooleanType BOOLEAN = new BooleanType();

  public TypeChecker() {
    fe = new FunctionEnvironment();
  }

  private Boolean checkFloat(Type left, Type right) {
    return (left.equals(FLOAT) && right.equals(INTEGER)) || (left.equals(INTEGER) && right.equals(FLOAT));
  }

  public Type visit(AddExpression e) throws SemanticException {
    Type left = e.expr1.accept(this);
    Type right = e.expr2.accept(this);
    if(!left.equals(right)) {
      if(checkFloat(left, right)) {
        return FLOAT;
      }
      throw new SemanticException("Cannot add expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    if(left.equals(BOOLEAN) || left.equals(VOID)) {
      throw new SemanticException("Cannot add expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    return left;
  }

  public Type visit(ArrayType a) throws SemanticException {
    return a;
  }

  public Type visit(ArrayAssignment s) throws SemanticException {
    Type index = s.index.accept(this);
    Type assignExpr = s.expr.accept(this);
    ArrayType varType = (ArrayType)s.name.accept(this);

    if(!varType.subType.equals(assignExpr)) {
      if(!varType.subType.equals(FLOAT) || !assignExpr.equals(INTEGER)) {
        throw new SemanticException("Cannot assign expression of type " + assignExpr + " to variable of type " + 
                                  varType.subType + ".", s.lineNumber, s.offset);
      }
    }

    if(!index.equals(INTEGER)) {
      throw new SemanticException("Array index must be integer.", s.lineNumber, s.offset);
    }

    return VOID;
  }

  public Type visit(ArrayReference a) throws SemanticException {
    Type index = a.expr.accept(this);
    Type t = a.name.accept(this);

    if(!(t instanceof ArrayType))
      throw new SemanticException("Variable referenced as array but not declared as array.", a.lineNumber, a.offset);


    if(!index.equals(INTEGER)) {
      throw new SemanticException("Array index must be integer.", a.lineNumber, a.offset);
    }

    return ((ArrayType)t).subType;
  }

  public Type visit(Block b) throws SemanticException {
    for(int i = 0; i < b.statementList.size(); ++i) {
      Statement s = b.statementList.elementAt(i);
      s.accept(this);
    }
    return VOID;
  }

  public Type visit(BooleanLiteral b) throws SemanticException {
    return BOOLEAN;
  }

  public Type visit(CharacterLiteral c) throws SemanticException {
    return CHAR;
  }

  public Type visit(EqualityExpression e) throws SemanticException {
    Type left = e.expr1.accept(this);
    Type right = e.expr2.accept(this);
    if(!left.equals(right)) {
      if(checkFloat(left, right)) {
        return BOOLEAN;
      }
      throw new SemanticException("Cannot equate expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    if(left.equals(VOID)) {
      throw new SemanticException("Cannot equate expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    return BOOLEAN;
  }

  public Type visit(ExpressionStatement e) throws SemanticException {
    e.expr.accept(this);
    return VOID;
  }

  public Type visit(FloatLiteral f) throws SemanticException {
    return FLOAT;
  }

  public Type visit(FormalParameter p) throws SemanticException {
    VariableType v = new VariableType(p.name.name, p.type.type);
    if(ve.contains(v))
      throw new SemanticException("Variable " + v.id + " already declared in scope.", p.lineNumber, p.offset);
    if(v.type.equals(VOID))
      throw new SemanticException("Variable " + v.id + " has invalid type, " + v.type + ".", p.lineNumber, p.offset);
    ve.add(v);
    return VOID;
  }

  public Type visit(Function f) throws SemanticException {
    current_function = new FunctionType(f.fd);
    ve = new VariableEnvironment();
    f.fd.accept(this);
    f.fb.accept(this);
    return VOID;
  }

  public Type visit(FunctionBody f) throws SemanticException {
    for (int i = 0; i < f.varList.size(); ++i) {
      VariableDeclaration vd = f.varList.elementAt(i);
      VariableType var;
      vd.accept(this);
      if(current_type.equals(VOID))
        throw new SemanticException("Local variable " + current_id + 
                                    " cannot have type " + current_type + ".", vd.lineNumber, vd.offset);
      var = new VariableType(current_id, current_type);
      if (ve.contains(var)) {
        throw new SemanticException("Duplicate variable identifier: " + var.id + ".", vd.lineNumber, vd.offset);
      }
      ve.add(var);
    }

    for (int i = 0; i < f.statementList.size(); ++i) {
      Statement s = f.statementList.elementAt(i);
      s.accept(this);
    }

    return VOID;
  }

  public Type visit(FunctionCall f) throws SemanticException {
    Vector<Type> v = new Vector<>();
    String id = f.name.name;
    ExpressionList epl = f.expressionList;
    FunctionType funcFound = fe.lookup(id);

    if(funcFound == null) {
      throw new SemanticException("Function " + id + " not found.", f.lineNumber, f.offset);
    }

    for(int i = 0; i < epl.size(); ++i) {
      Expression e = epl.elementAt(i);
      v.add(e.accept(this));
    }

    ParamList p = new ParamList(v);
    
    if(!funcFound.equals(p)) {
      throw new SemanticException("Formal argument mismatch for function " + id + ".", f.lineNumber, f.offset);
    }

    return funcFound.returnType;
  }

  public Type visit(FunctionDeclaration f) throws SemanticException {
    for(int i = 0; i < f.parameterList.size(); ++i) {
      FormalParameter fp = f.parameterList.elementAt(i);
      fp.accept(this);
    }
    return VOID;
  }

  public Type visit(Identifier i) throws SemanticException {
    VariableType v = ve.lookup(i.name);

    if(v == null) {
      throw new SemanticException("Variable " + i.name + " not declared in scope.", i.lineNumber, i.offset);
    }

    return v.type;
  }

  public Type visit(IdentifierValue v) throws SemanticException {
    return v.name.accept(this);
  }

  public Type visit(IfStatement i) throws SemanticException {
    if(!i.expr.accept(this).equals(BOOLEAN)) {
      throw new SemanticException("If statement must contain boolean expression.", i.lineNumber, i.offset);
    }
    i.block1.accept(this);
    if(i.block2 != null) {
      i.block2.accept(this);
    }
    return VOID;
  }

  public Type visit(IntegerLiteral i) throws SemanticException {
    return INTEGER;
  }

  public Type visit(LessThanExpression e) throws SemanticException {
    Type left = e.expr1.accept(this);
    Type right = e.expr2.accept(this);
    if(!left.equals(right)) {
      if(checkFloat(left, right)) {
        return BOOLEAN;
      }
      throw new SemanticException("Cannot compare expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    if(left.equals(VOID)) {
      throw new SemanticException("Cannot compare expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    return BOOLEAN;
  }

  public Type visit(MultExpression e) throws SemanticException {
    Type left = e.expr1.accept(this);
    Type right = e.expr2.accept(this);
    if(!left.equals(right)) {
      if(checkFloat(left, right)) {
        return FLOAT;
      }
      throw new SemanticException("Cannot multiply expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    if(left.equals(BOOLEAN) || left.equals(VOID) || left.equals(STRING) || left.equals(CHAR)) {
      throw new SemanticException("Cannot multiply expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    return left;
  }

  public Type visit(ParenExpression p) throws SemanticException {
    return p.expr.accept(this);
  }

  public Type visit(PrintLnStatement s) throws SemanticException {
    Type t = s.expr.accept(this);
    if(t instanceof ArrayType || t.equals(VOID)) {
      throw new SemanticException("Type " + t + " cannot be used in println statements", s.lineNumber, s.offset);
    }
    return VOID;
  }

  public Type visit(PrintStatement s) throws SemanticException {
    Type t = s.expr.accept(this);
    if(t instanceof ArrayType || t.equals(VOID)) {
      throw new SemanticException("Type " + t + " cannot be used in print statements", s.lineNumber, s.offset);
    }
    return VOID;
  }

  public Type visit(Program p) throws SemanticException {
    boolean containsMain = false;
    boolean containsDuplicates = false;
    ArrayList<String> fnames = new ArrayList<>();

    // initially visit all the functions to add to function environment
    for (int i = 0; i < p.size(); ++i) {
      Function f = p.elementAt(i);
      fe.add(f.fd);
    }

    for (int i = 0; i < fe.size(); ++i) {
      FunctionType ft = fe.elementAt(i);

      if (ft.id.equals("main")) {
        containsMain = true;
        if (!(ft.returnType.equals(VOID))) {
          throw new SemanticException("main() must have void as its return type.", ft.lineNumber, ft.offset);
        }
        if (ft.plist.size() != 0) {
          throw new SemanticException("main() must have no parameters.", ft.lineNumber, ft.offset);
        }
      }

      if (fnames.contains(ft.id))
        throw new SemanticException("Program contains duplicate functions.", ft.lineNumber, ft.offset);
      fnames.add(ft.id);
    }

    if (fnames.size() == 0) {
      throw new SemanticException("Program contains no functions.", p.lineNumber, p.offset);
    }

    if (!containsMain) {
      throw new SemanticException("Program contains no main function.", p.lineNumber, p.offset);
    }

    // visit the bodies of all the functions
    for (int i = 0; i < p.size(); ++i) {
      Function f = p.elementAt(i);
      f.accept(this);
    }
    return VOID;
  }

  public Type visit(ReturnStatement s) throws SemanticException {
    Type t;
    if(s.expr != null) {
      t = s.expr.accept(this);
    } else {
      t = VOID;
    }
    
    Type rtype = current_function.returnType;
    if(!rtype.equals(t)) {
      if(!rtype.equals(FLOAT) || !t.equals(INTEGER)) {
        throw new SemanticException("Expected return type, " + rtype + ", does not match found return type, "
                                    + t + ".", s.lineNumber, s.offset);
      }
    }
    return VOID;
  }

  public Type visit(StringLiteral s) throws SemanticException {
    return STRING;
  }

  public Type visit(SubtractExpression e) throws SemanticException {
    Type left = e.expr1.accept(this);
    Type right = e.expr2.accept(this);
    if(!left.equals(right)) {
      if(checkFloat(left, right)) {
        return FLOAT;
      }
      throw new SemanticException("Cannot subtract expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    if(left.equals(BOOLEAN) || left.equals(VOID) || left.equals(STRING)) {
      throw new SemanticException("Cannot subtract expressions of type " + left + " and " + right + ".", e.lineNumber, e.offset);
    }
    return left;
  }

  public Type visit(Type t) throws SemanticException {
    return VOID;
  }

  public Type visit(TypeNode t) throws SemanticException {
    current_type = t.type;
    return t.type;
  }

  public Type visit(VariableAssignment s) throws SemanticException {
    Type varType = s.name.accept(this);
    Type assignType = s.expr.accept(this);

    if(!varType.equals(assignType)) {
      if(!varType.equals(FLOAT) || !assignType.equals(INTEGER)) {
        throw new SemanticException("Cannot assign expression of type " + assignType + " to variable of type " + 
                                    varType + ".", s.lineNumber, s.offset);
      }
    }

    return VOID;
  }

  public Type visit(VariableDeclaration v) throws SemanticException {
    this.current_id = v.name.name;
    v.type.accept(this);
    return VOID;
  }

  public Type visit(WhileStatement s) throws SemanticException {
    if(!s.expr.accept(this).equals(BOOLEAN)) {
      throw new SemanticException("While statement must contain boolean expression.", s.lineNumber, s.offset);
    }
    s.block.accept(this);
    return VOID;
  }

}
