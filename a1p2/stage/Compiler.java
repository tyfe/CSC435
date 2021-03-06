
/*
 * Compiler.java
 *
 * A starting place for the unamed language compiler for CSC 435/535
 *
 */

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import AST.*;
import java.io.*;

public class Compiler {
  public static void main(String[] args) throws Exception {
    ANTLRInputStream input;
    PrintVisitor v = new PrintVisitor();
    Program p = null;

    if (args.length == 0) {
      System.out.println("Usage: Test filename.ul");
      return;
    } else {
      input = new ANTLRInputStream(new FileInputStream(args[0]));
    }

    // The name of the grammar here is "ul",
    // so ANTLR generates ulLexer and ulParser
    ulLexer lexer = new ulLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    ulParser parser = new ulParser(tokens);

    try {
      p = parser.program();
    } catch (RecognitionException e) {
      // A lexical or parsing error occured.
      // ANTLR will have already printed information on the
      // console due to code added to the grammar.  So there is
      // nothing to do here.
      return;
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      return;
    }

    v.visit(p);
  
  }

}
