package AST;

import AST.ASTNode;
import AST.FunctionBody;
import AST.FunctionDeclaration;
import AST.PrintVisitor;

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

}
