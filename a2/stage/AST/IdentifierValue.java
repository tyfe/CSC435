package AST;

import AST.ASTNode;
import Semantic.*;

public class IdentifierValue extends Expression {
    
    public Identifier name;

    public IdentifierValue(Identifier i) {
        name = i;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public void accept(TypeChecker t) throws SemanticException {
        t.visit(this);
    }
}
