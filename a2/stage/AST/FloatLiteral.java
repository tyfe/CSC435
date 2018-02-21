package AST;

import AST.*;
import Semantic.*;

public class FloatLiteral extends Expression {
	Float val;

	public FloatLiteral (Float v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}

	public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
