package AST;

import AST.*;
import Semantic.*;
import Type.*;
import IR.*;

public class BooleanLiteral extends Literal {
	public Boolean val;

	public BooleanLiteral (int l, int o, Boolean v) {
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
