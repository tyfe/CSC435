package IR;

public class Label {

  public int index;

  Label(int i) {
    index = i;
  }

  public String toString() {
    return "L" + index;
  }
}
