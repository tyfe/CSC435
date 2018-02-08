package AST;

import AST.*;

public class StringLiteral extends Expression {
	String val;

	public StringLiteral (String v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}
}
