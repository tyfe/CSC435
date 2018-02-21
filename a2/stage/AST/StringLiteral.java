package AST;

import AST.*;
import Semantic.*;
import Type.*;


public class StringLiteral extends Expression {
	String val;

	public StringLiteral (int l, int o, String v) {
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
