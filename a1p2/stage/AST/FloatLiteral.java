package AST;

import AST.*;

public class FloatLiteral extends Expression {
	Float val;

	public FloatLiteral (Float v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}
}
