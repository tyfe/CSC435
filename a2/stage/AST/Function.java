package AST;

import Semantic.*;
import Type.*;

public class Function extends ASTNode {

    public FunctionDeclaration fd;
    public FunctionBody fb;

    
    public Function(int l, int o, FunctionDeclaration fd, FunctionBody fb) {
        super(l, o);
        this.fd = fd;
        this.fb = fb;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public Type accept(TypeChecker t) throws SemanticException {
        return t.visit(this);
    }
}
