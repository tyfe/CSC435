package AST;

import AST.ASTNode;

public class IdentifierValue extends Expression {
    
    public Identifier name;

    public IdentifierValue(Identifier i) {
        name = i;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

}
