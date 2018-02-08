package AST;

import AST.ASTNode;

public class ParenExpression extends Expression {
    
    public Expression expr;

    public ParenExpression(Expression e) {
        expr = e;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

}
