package Type;

import AST.*;

public class IntegerType extends Type {
    public IntegerType() {
    }

    public String toString() {
        return "int";
    }

    public String toShortString() {
        return "I";
    }

    public boolean equals(Object o) {
        if (o instanceof IntegerType)
            return true;
        else
            return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
