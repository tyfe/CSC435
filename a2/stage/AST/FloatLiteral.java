package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class FloatLiteral extends Expression {
	Float val;

	public FloatLiteral (int l, int o, Float v) {
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
