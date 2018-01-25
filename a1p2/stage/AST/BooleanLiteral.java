package AST;

import AST.*;

public class BooleanLiteral extends Expression {
	Boolean val;

	public BooleanLiteral (Boolean v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}
}
