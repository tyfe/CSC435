package AST;

import AST.ASTNode;

public class Identifier extends ASTNode {
    
    public String name;

    public Identifier(String s) {
        name = s;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

}
