package AST;

import AST.ASTNode;
import Semantic.*;

public class ParenExpression extends Expression {
    
    public Expression expr;

    public ParenExpression(Expression e) {
        expr = e;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public void accept(TypeChecker t) throws SemanticException {
        t.visit(this);
      }

}
