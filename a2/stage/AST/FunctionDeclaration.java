package AST;

import AST.ASTNode;
import Semantic.*;

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

    public void accept(TypeChecker t) throws SemanticException {
        t.visit(this);
    }

}
