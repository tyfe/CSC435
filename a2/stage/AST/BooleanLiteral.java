package AST;

import AST.*;
import Semantic.*;
import Type.*;

public class BooleanLiteral extends Expression {
	Boolean val;

	public BooleanLiteral (int l, int o, Boolean v) {
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
