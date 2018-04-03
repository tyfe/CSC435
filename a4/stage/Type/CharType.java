package Type;

import AST.*;

public class CharType extends Type {
    public CharType () {
    }
    public String toString() {
        return "char";
    }

    public String toShortString() {
        return "C";
    }

    public boolean equals (Object o) {
        if (o instanceof CharType)
            return true;
        else
            return false;
    }

    public void accept (Visitor v) {
            v.visit(this);
    }
}
