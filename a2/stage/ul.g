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

program returns[Program p]
	@init {
		p = new Program(1, 0);
  }: (f = function {p.addElement(f);})+ EOF
	;

function returns[Function f]: 
	fd=functionDecl fb=functionBody { f = new Function(fd.lineNumber, fd.offset, fd, fb); }
  ;

functionDecl returns[FunctionDeclaration fd]: 
	tn=compoundType i=identifier '(' fp=formalParameters ')' { fd = new FunctionDeclaration(tn.lineNumber, tn.offset, tn, i, fp); };

formalParameters returns[FormalParameterList fpl] @init { fpl = new FormalParameterList(); }:
	tn=compoundType i=identifier { fpl.addElement(new FormalParameter(tn.lineNumber, tn.offset, tn, i)); } 
	(mf=moreFormals { fpl.addElement(mf); })* 
	|
	;

moreFormals returns[FormalParameter fp]: ',' tn=compoundType i=identifier { fp = new FormalParameter(tn.lineNumber, tn.offset, tn, i); };

varDecl returns[VariableDeclaration v]: tn=compoundType i=identifier ';' { v = new VariableDeclaration(tn.lineNumber, tn.offset, tn, i); };

statement returns[Statement s] options {
	backtrack = true;
}:
	';'
	| e=expr ';' { s = new ExpressionStatement(e.lineNumber, e.offset, e); }
	| ifs=ifStatement { s = ifs; }
	| r=WHILE '(' e=expr ')' b=block { s = new WhileStatement($r.line, $r.pos, e, b); }
	| r=PRINT e=expr ';' { s = new PrintStatement($r.line, $r.pos, e); }
	| r=PRINTLN e=expr ';' { s = new PrintLnStatement($r.line, $r.pos, e); }
	| r=RETURN e=expr ';' { s = new ReturnStatement($r.line, $r.pos, e); }
	| r=RETURN ';' { s = new ReturnStatement($r.line, $r.pos, null); }
	| i=identifier '=' e=expr ';' { s = new VariableAssignment(i.lineNumber, i.offset, i, e); }
	| i=identifier '[' e1=expr ']' '=' e2=expr ';' { s = new ArrayAssignment(i.lineNumber, i.offset, i, e1, e2); }
	; 

ifStatement returns[Statement s] options {
		backtrack = true;
	}
	: i=IF '(' e1=expr ')' b1=block ELSE b2=block { s = new IfStatement($i.line, $i.pos, e1, b1, b2); } 
	| i=IF '(' e=expr ')' b=block { s = new IfStatement($i.line, $i.pos, e, b); };

block returns[Block b]
	@init{ StatementList sl = new StatementList(); }
	@after{ b = new Block(0, 0, sl); }:
	'{' (s=statement { if (s != null) sl.addElement(s); })* '}';

expr returns[Expression e]
	@init{ Expression it = null; }
	@after{ e = it; }: 
	e1=lessExpr { it = e1; } ('==' e2=lessExpr { it = new EqualityExpression(e2.lineNumber, e2.offset, it,e2); })*;

lessExpr returns[Expression e]
	@init{ Expression it = null; }
	@after{ e = it; }: e1=pmExpr { it = e1; } ('<' e2=pmExpr { it = new LessThanExpression(e2.lineNumber, e2.offset, it, e2); })*;

pmExpr returns[Expression e]
	@init{ Expression it = null; }
	@after{ e = it; }: e1=mulExpr { it = e1; } (op=('+'|'-') e2=mulExpr {
		if ($op.text.charAt(0) == '+') {
			it = new AddExpression(e2.lineNumber, e2.offset, it, e2);
		} else if ($op.text.charAt(0) == '-') {
			it = new SubtractExpression(e2.lineNumber, e2.offset, it, e2);
		}
	})*;

mulExpr returns[Expression e]
	@init{ Expression it = null; }
	@after{ e = it; }: e1=atom { it = e1; } ('*' e2=atom { it = new MultExpression(e2.lineNumber, e2.offset, it, e2); })*;

exprList returns[ExpressionList el]
	@init{ el = new ExpressionList(); }: e1=expr { el.addElement(e1); } (e2=exprMore { el.addElement(e2); })* 
	|
	;

exprMore returns [Expression e]: ',' e1=expr { e = e1; } ;

atom returns[Expression e] @init{ e = null; }:
	l=literal { e = l; }
	| id=identifier { e = new IdentifierValue(id.lineNumber, id.offset, id); }
	| id=identifier '(' el=exprList ')' { e = new FunctionCall(id.lineNumber, id.offset, id, el); }
	| '(' expression=expr ')' {e = new ParenExpression(expression.lineNumber, expression.offset, expression); }
	| id=identifier '[' expression=expr ']' { e = new ArrayReference(id.lineNumber, id.offset, id, expression); };

functionBody returns[FunctionBody fb] 
	@init { 
		VariableDeclarationList vl = new VariableDeclarationList();
		StatementList sl = new StatementList();
	 }:
	 '{' (v = varDecl { vl.addElement(v); })*  (s = statement { sl.addElement(s); })* '}' { fb = new FunctionBody(0, 0, vl, sl); };

identifier returns[Identifier i]: id=ID { i = new Identifier($id.line, $id.pos, $id.text); };

compoundType returns[TypeNode tn]: t = TYPE
	{
		tn = new TypeNode($t.line, $t.pos, $t.text);
	}
	|  t = TYPE '[' size = INTEGERCONSTANT ']' 
	{
		tn = new TypeNode($t.line, $t.pos, $t.text, Integer.parseInt($size.text));
	};

literal returns[Expression e]:
	t=STRINGCONSTANT { e = new StringLiteral($t.line, $t.pos, $t.text.substring(1, $t.text.length() - 1)); }
	| t=INTEGERCONSTANT { e = new IntegerLiteral($t.line, $t.pos, Integer.parseInt($t.text)); }
	| t=FLOATCONSTANT { e = new FloatLiteral($t.line, $t.pos, Float.parseFloat($t.text)); }
	| t=CHARCONSTANT { e = new CharacterLiteral($t.line, $t.pos, $t.text.charAt(1)); }
	| s='true' { e = new BooleanLiteral($s.line, $s.pos, true); }
	| s='false' { e = new BooleanLiteral($s.line, $s.pos, false); };

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
