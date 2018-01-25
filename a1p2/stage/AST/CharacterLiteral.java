package AST;

import AST.*;

public class CharacterLiteral extends Expression {
	Character val;

	public CharacterLiteral (Character v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}
}
