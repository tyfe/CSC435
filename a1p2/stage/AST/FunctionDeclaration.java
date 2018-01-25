package AST;

import AST.ASTNode;

public class FunctionDeclaration extends ASTNode {
    
    public Identifier name;

    public FunctionDeclaration(Identifier i) {
        name = i;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

}
