package AST;

import AST.ASTNode;
import Semantic.*;
import Type.*;

public class IdentifierValue extends Expression {
    
    public Identifier name;

    public IdentifierValue(int l, int o, Identifier i) {
        super(l, o);
        name = i;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public Type accept(TypeChecker t) throws SemanticException {
        return t.visit(this);
    }
}
