package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class CharacterLiteral extends Expression {
	Character val;

	public CharacterLiteral (int l, int o, Character v) {
		super(l, o);
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}

	public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
  }
}
