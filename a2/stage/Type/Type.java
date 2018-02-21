package Type;

import AST.*;
import Type.StringType;
import Type.CharType;
import Type.FloatType;
import Type.BooleanType;

public abstract class Type {
    public abstract void accept(Visitor v);

    // public abstract String toShortString();

    public abstract boolean equals(Object o);
}
