package Type;

import AST.*;

public class VoidType extends Type {
    public VoidType () {
    }
    public String toString() {
            return "void";
    }

    public String toShortString() {
        return "V";
    }

    public boolean equals (Object o) {
            if (o instanceof VoidType)
                return true;
            else
                return false;
    }

    public void accept (Visitor v) {
            v.visit(this);
    }
}
