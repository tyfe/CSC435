package AST;

import AST.ASTNode;
import Semantic.*;
import Type.*;
import IR.*;

public class FunctionDeclaration extends ASTNode {
    
    public Identifier name;
    public TypeNode type;
    public FormalParameterList parameterList;

    public FunctionDeclaration(int l, int o, TypeNode t, Identifier i, FormalParameterList fpl) {
        super(l, o);
        name = i;
        type = t;
        parameterList = fpl;
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

    public Type accept(TypeChecker t) throws SemanticException {
        return t.visit(this);
    }

    public void accept(IRVisitor v) {
        v.visit(this);
      }

}
