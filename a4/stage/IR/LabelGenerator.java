package IR;


public class LabelGenerator {

  private int index;

  public LabelGenerator() {
    index = 0;
  }

  public Label newLabel() {
    return new Label(index++);
  }

}
