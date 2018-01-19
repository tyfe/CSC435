package Type;

public class ArrayType extends Type {
    public ArrayType () {
    }
    public String toString() {
            return "??? fix"; // whatever might be printed out in pretty print
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
