package AST;

import AST.*;

public class IntegerLiteral extends Expression {
	Integer val;

	public IntegerLiteral (Integer v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}
}
