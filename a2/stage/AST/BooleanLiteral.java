package AST;

import AST.*;
import Semantic.*;

public class BooleanLiteral extends Expression {
	Boolean val;

	public BooleanLiteral (Boolean v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}

	public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
