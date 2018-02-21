package AST;

import AST.*;
import Semantic.*;


public class StringLiteral extends Expression {
	String val;

	public StringLiteral (String v) {
		val = v;
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}

	public void accept(TypeChecker t) throws SemanticException {
    t.visit(this);
  }
}
