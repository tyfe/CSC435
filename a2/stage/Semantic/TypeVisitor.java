package Semantic;

import AST.*;
import Type.*;

public interface TypeVisitor
{
	public void visit (AddExpression e) throws SemanticException;
	public void visit (ArrayType a) throws SemanticException;
	public void visit (ArrayAssignment s) throws SemanticException;
	public void visit (ArrayReference a) throws SemanticException;
	public void visit (Block b) throws SemanticException;
	public void visit (BooleanLiteral b) throws SemanticException;
	public void visit (CharacterLiteral c) throws SemanticException;
	// public void visit (DoStatement s) throws SemanticException;
	public void visit (EqualityExpression e) throws SemanticException;
	public void visit (ExpressionStatement e) throws SemanticException;
	public void visit (FloatLiteral f) throws SemanticException;	
	public void visit (FormalParameter p) throws SemanticException;
	public void visit (Function f) throws SemanticException;
	public void visit (FunctionBody f) throws SemanticException;
	public void visit (FunctionCall f) throws SemanticException;
	public void visit (FunctionDeclaration f) throws SemanticException;
	public void visit (Identifier i) throws SemanticException;
	public void visit (IdentifierValue v) throws SemanticException;
	public void visit (IfStatement i) throws SemanticException;
	public void visit (IntegerLiteral i) throws SemanticException;
	public void visit (LessThanExpression e) throws SemanticException;
	public void visit (MultExpression e) throws SemanticException;
	public void visit (ParenExpression p) throws SemanticException;
	public void visit (PrintLnStatement s) throws SemanticException;
	public void visit (PrintStatement s) throws SemanticException;	
	public void visit (Program p) throws SemanticException;
	public void visit (ReturnStatement s) throws SemanticException;
	public void visit (StringLiteral s) throws SemanticException;
	public void visit (SubtractExpression e) throws SemanticException;
	public void visit (Type t) throws SemanticException;
	public void visit (TypeNode t) throws SemanticException;
	public void visit (VariableAssignment s) throws SemanticException;
	public void visit (VariableDeclaration v) throws SemanticException;
	public void visit (WhileStatement s) throws SemanticException;
}
