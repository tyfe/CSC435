package AST;

import AST.*;
import Semantic.*;

public class CharacterLiteral extends Expression {
	Character val;

	public CharacterLiteral (Character v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}

	public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
