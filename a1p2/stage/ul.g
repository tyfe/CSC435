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

functionDecl returns[FunctionDeclaration fd]: ct=compoundType i=identifier '(' formalParameters ')' 
	{ 
		fd = new FunctionDeclaration(i); 
	};

formalParameters: compoundType identifier moreFormals* |;

moreFormals: ',' compoundType identifier;

varDecl: compoundType identifier ';';

statement options {
	backtrack = true;
}:
	';'
	| expr ';'
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

expr: lessExpr exprPrime;

exprPrime: | '==' lessExpr exprPrime;

lessExpr: pmExpr lessExprPrime;

lessExprPrime: | '<' pmExpr lessExprPrime;

pmExpr: mulExpr pmExprPrime;

pmExprPrime:
	| '+' mulExpr pmExprPrime
	| '-' mulExpr pmExprPrime;

mulExpr: atom mulExprPrime;

mulExprPrime: | '*' atom mulExprPrime;

exprList: expr exprMore* |;

exprMore: ',' expr;

atom:
	literal
	| identifier
	| identifier '(' exprList ')'
	| '(' expr ')'
	| identifier '[' expr ']';

functionBody returns[FunctionBody fb]: '{' varDecl* statement* '}' { fb = null; };

identifier returns[Identifier i]: id=ID { i = new Identifier($id.text); };

//TYPE: 'int' | 'float' | 'char' | 'string' | 'boolean' | 'void';
compoundType returns[Type type]: t = TYPE
	{
		String token = $t.text;
		if (token == "int") {
			type = new IntegerType();
		} else if (token == "float") {
			type = new FloatType();
		} else if (token == "char") {
			type = new CharType();
		} else if (token == "string") {
			type = new StringType();
		} else if (token == "boolean") {
			type = new BooleanType();
		} else if (token == "void") {
			type = new VoidType();
		}
	}
	|  t = TYPE '[' size = INTEGERCONSTANT ']' 
	{
		type = new ArrayType(Integer.parseInt($size.text));
	};

literal:
	STRINGCONSTANT
	| INTEGERCONSTANT
	| FLOATCONSTANT
	| CHARCONSTANT
	| 'true'
	| 'false';

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
