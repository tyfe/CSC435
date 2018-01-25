package Type;

import AST.*;

public class ArrayType extends Type {

    public int size;

    public ArrayType (int size) {
    }
    public String toString() {
            return "int [" + this.size + "]";
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
