package AST;

import AST.*;
import Semantic.*;
import Type.*;
import IR.*;

public class FloatLiteral extends Literal {
	public Float val;

	public FloatLiteral (int l, int o, Float v) {
		super(l, o);
		val = v;
	}

	public String toString() {
		return val.toString();
	}

	public void accept (PrintVisitor v) {
		v.visit(this);
	}

	public Type accept(TypeChecker t) throws SemanticException {
    return t.visit(this);
	}
	public void accept(IRVisitor v) {
    v.visit(this);
  }
}
