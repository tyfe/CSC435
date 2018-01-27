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

    public TypeNode(String token, int size) {
        Type t;
        if (token.equals("int") ) {
			t = new IntegerType();
		} else if (token.equals("float")) {
			t = new FloatType();
		} else if (token.equals("char")) {
			t = new CharType();
		} else if (token.equals("string")) {
			t = new StringType();
		} else if (token.equals("boolean")) {
			t = new BooleanType();
		} else {
			t = new VoidType();
        }
        type = new ArrayType(size, t);
    }

    public void accept(PrintVisitor v) {
        v.visit(this);
    }

}
