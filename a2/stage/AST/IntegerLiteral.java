package AST;

import AST.*;
import Semantic.*;

public class IntegerLiteral extends Expression {
	Integer val;

	public IntegerLiteral (Integer v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}

	public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
