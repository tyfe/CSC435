package AST;

import AST.ASTNode;
import Semantic.*;
import Type.*;

public class ParenExpression extends Expression {
    
    public Expression expr;

    public ParenExpression(int l, int o, Expression e) {
        super(l, o);
        expr = e;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public Type accept(TypeChecker t) throws SemanticException {
        return t.visit(this);
    }

}
