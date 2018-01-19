package AST;

public class ASTNode {
  public ASTNode() {

  }

  public void accept() {
    v.visit(this);
  }
}
