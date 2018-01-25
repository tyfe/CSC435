package AST;

import AST.ASTNode;
import Type.*;

public class TypeNode extends ASTNode {
    
    public Type type;

    public TypeNode(String token) {
        if (token.equals("int") ) {
			type = new IntegerType();
		} else if (token.equals("float")) {
			type = new FloatType();
		} else if (token.equals("char")) {
			type = new CharType();
		} else if (token.equals("string")) {
			type = new StringType();
		} else if (token.equals("boolean")) {
			type = new BooleanType();
		} else if (token.equals("void")) {
			type = new VoidType();
        }
    }

    public TypeNode(int size) {
        type = new ArrayType(size);
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

}
