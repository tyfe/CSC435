package Type;

import AST.*;

public class ArrayType extends Type {

    public int size;
    public Type subType;

    public ArrayType (int size, Type t) {
        this.size = size;
        subType = t;
    }
    
    public String toString() {
            return subType + " [" + this.size + "]";
    }
    public boolean equals (Object o) {
            if (o instanceof ArrayType)
                return true;
            else
                return false;
    }

    public void accept (Visitor v) {
            v.visit(this);
    }
}
