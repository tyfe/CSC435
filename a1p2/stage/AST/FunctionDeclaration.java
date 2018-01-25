package AST;

import AST.ASTNode;

public class FunctionDeclaration extends ASTNode {
    
    public Identifier name;
    public TypeNode type;
    public FormalParameterList parameterList;

    public FunctionDeclaration(TypeNode t, Identifier i, FormalParameterList fpl) {
        name = i;
        type = t;
        parameterList = fpl;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

}
