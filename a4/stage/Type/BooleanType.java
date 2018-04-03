package Type;

import AST.*;

public class BooleanType extends Type {
    public BooleanType () {
    }
    public String toString() {
            return "boolean";
    }

    public String toShortString() {
        return "Z";
    }

    public boolean equals (Object o) {
            if (o instanceof BooleanType)
                return true;
            else
                return false;
    }

    public void accept (Visitor v) {
            v.visit(this);
    }
}
