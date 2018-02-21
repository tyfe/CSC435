package AST;

import AST.ASTNode;
import Semantic.*;

public class Identifier extends ASTNode {
    
    public String name;

    public Identifier(String s) {
        name = s;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public void accept(TypeChecker t) throws SemanticException {
        t.visit(this);
    }

}
