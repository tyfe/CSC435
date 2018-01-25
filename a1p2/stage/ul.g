grammar ul;
@header {
  import AST.*;
  import Type.*;
}

@members {
protected void mismatch (IntStream input, int ttype, BitSet follow)
        throws RecognitionException
{
        throw new MismatchedTokenException(ttype, input);
}
public void recoverFromMismatchedSet (IntStream input,
                                      RecognitionException e,
                                      BitSet follow)
        throws RecognitionException
{
        reportError(e);
        throw e;
}
}

@rulecatch {
        catch (RecognitionException ex) {
                reportError(ex);
                throw ex;
        }
}

/*
 * This is a subset of the ulGrammar to show you how to make new production rules. You will need to:
 * - change type to be compoundType and include appropriate productions - introduce optional
 * formalParameters - change functionBody to include variable declarations and statements
 */

program returns[Program p]
	@init {
	p = new Program();
  }: (f = function {p.addElement(f);})+ EOF;

function returns[Function f]
  : fd=functionDecl fb=functionBody 
  { f = new Function(fd, fb); }
  ;

functionDecl returns[FunctionDeclaration fd]: tn=compoundType i=identifier '(' fp=formalParameters ')' 
	{ 
		fd = new FunctionDeclaration(tn, i, fp); 
	};

formalParameters returns[FormalParameterList fpl] @init { fpl = new FormalParameterList(); }:
	tn=compoundType i=identifier { fpl.addElement(new FormalParameter(tn, i)); } 
	(mf=moreFormals { fpl.addElement(mf); })* 
	|
	;

moreFormals returns[FormalParameter fp]: ',' tn=compoundType i=identifier { fp = new FormalParameter(tn, i); };

varDecl returns[VariableDeclaration v]: tn=compoundType i=identifier ';' { v = new VariableDeclaration(tn, i); };

statement returns[Statement s] options {
	backtrack = true;
}:
	';'
	| e=expr ';' { return new ExpressionStatement(e); }
	| ifStatement
	| WHILE '(' expr ')' block
	| PRINT expr ';'
	| PRINTLN expr ';'
	| RETURN expr? ';'
	| identifier '=' expr ';'
	| identifier '[' expr ']' '=' expr ';';

ifStatement options {
	backtrack = true;
}: IF '(' expr ')' block ELSE block | IF '(' expr ')' block;

block: '{' statement* '}';

expr returns[Expression e]
	@init{ Expression it = null; }
	@after{ e = it; }: 
	e1=lessExpr { it = e1; } ('==' e2=lessExpr { it = new EqualityExpression(it,e2); })*;

lessExpr returns[Expression e]
	@init{ Expression it = null; }
	@after{ e = it; }: e1=pmExpr { it = e1; } ('<' e2=pmExpr { it = new LessThanExpression(it, e2); })*;

pmExpr returns[Expression e]
	@init{ Expression it = null; }
	@after{ e = it; }: e1=mulExpr { it = e1; } (op=('+'|'-') e2=mulExpr {
		if ($op.text.charAt(0) == '+') {
			it = new AddExpression(it, e2);
		} else if ($op.text.charAt(0) == '-') {
			it = new SubtractExpression(it, e2);
		}
	})*;

mulExpr returns[Expression e]
	@init{ Expression it = null; }
	@after{ e = it; }: e1=atom { it = e1; } ('*' e2=atom { it = new MultExpression(it, e2); })*;

exprList: expr exprMore* 
	|
	;

exprMore: ',' expr;

atom returns[Expression e] @init{ e = null; }:
	l=literal { e = l; }
	| id=identifier { e = new IdentifierValue(id); }
	| identifier '(' exprList ')'
	| '(' expression=expr ')' {e = new ParenExpression(expression); }
	| identifier '[' expr ']';

functionBody returns[FunctionBody fb] 
	@init { 
		VariableDeclarationList vl = new VariableDeclarationList();
		StatementList sl = new StatementList();
	 }:
	 '{' (v = varDecl { vl.addElement(v); })*  (s = statement { sl.addElement(s); })* '}' { fb = new FunctionBody(vl, sl); };

identifier returns[Identifier i]: id=ID { i = new Identifier($id.text); };

compoundType returns[TypeNode tn]: t = TYPE
	{
		tn = new TypeNode($t.text);
	}
	|  t = TYPE '[' size = INTEGERCONSTANT ']' 
	{
		tn = new TypeNode(Integer.parseInt($size.text));
	};

literal returns[Expression e]:
	t=STRINGCONSTANT { e = new StringLiteral($t.text); }
	| t=INTEGERCONSTANT { e = new IntegerLiteral(Integer.parseInt($t.text)); }
	| t=FLOATCONSTANT { e = new FloatLiteral(Float.parseFloat($t.text)); }
	| t=CHARCONSTANT { e = new CharacterLiteral($t.text.charAt(0)); }
	| 'true' { e = new BooleanLiteral(true); }
	| 'false' { e = new BooleanLiteral(false); };

/* Lexer */

IF: 'if';

ELSE: 'else';

WHILE: 'while';

PRINT: 'print';

PRINTLN: 'println';

RETURN: 'return';

TYPE: 'int' | 'float' | 'char' | 'string' | 'boolean' | 'void';

ID: ('a' ..'z' | 'A' .. 'Z' | '_') ('a' ..'z'| 'A' .. 'Z'| '0' .. '9'| '_')*;

INTEGERCONSTANT: ('0' .. '9')+;

// for simplicity, only accept alphanumeric characters, simple punctuation, and space
STRINGCONSTANT: '"' ('a' ..'z' | 'A' .. 'Z' | '0' .. '9' | '(' .. '.' | ' ')* '"';

CHARCONSTANT: '\'' ('a' ..'z' | 'A' .. 'Z' | '0' .. '9' | ' ') '\'';

FLOATCONSTANT: ('0' .. '9')+ '.' ('0' .. '9')+;

/* These two lines match whitespace and comments and ignore them. You want to leave these as last in
 * the file. Add new lexical rules above
 */
WS: ( '\t' | ' ' | ('\r' | '\n'))+ { $channel = HIDDEN;};

COMMENT: '//' ~('\r' | '\n')* ('\r' | '\n') { $channel = HIDDEN;};
