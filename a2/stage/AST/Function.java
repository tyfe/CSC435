package AST;

import AST.ASTNode;
import AST.FormalParameterList;
import AST.FunctionBody;
import AST.FunctionDeclaration;
import AST.PrintVisitor;
import Semantic.*;

public class Function extends ASTNode {

    public FunctionDeclaration fd;
    public FunctionBody fb;

    
    public Function(FunctionDeclaration fd, FunctionBody fb) {
        this.fd = fd;
        this.fb = fb;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public void accept(TypeChecker t) throws SemanticException {
        t.visit(this);
    }
}
