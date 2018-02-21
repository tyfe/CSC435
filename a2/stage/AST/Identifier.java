package AST;

import AST.ASTNode;
import Semantic.*;
import Type.*;

public class Identifier extends ASTNode {
    
    public String name;

    public Identifier(int l, int o, String s) {
        super(l, o);
        name = s;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public Type accept(TypeChecker t) throws SemanticException {
        return t.visit(this);
    }

}
