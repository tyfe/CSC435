
/*
 * Compiler.java
 *
 * A starting place for the unamed language compiler for CSC 435/535
 *
 */

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import AST.*;
import IR.*;
import Semantic.*;
import Codegen.*;
import java.io.*;
import util.NullPrintStream;

public class Compiler {
  public static void main(String[] args) throws Exception {
    ANTLRInputStream input;
    PrintStream out = new NullPrintStream();

    TypeChecker t = new TypeChecker();
    IRGenerator ir = new IRGenerator(out);
    Program p = null;
    FileInputStream f;

    String classname = args[0].substring(0, args[0].length() - 3);
    classname = classname.substring(classname.lastIndexOf('/')+1, classname.length());

    CodegenVisitor cg = new CodegenVisitor(System.out, classname);

    if (args.length == 0) {
      System.out.println("Usage: Test filename.ul");
      return;
    } else {
      f = new FileInputStream(args[0]);
      input = new ANTLRInputStream(f);
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

    try {
      t.visit(p);
    } catch (SemanticException e) {
      System.out.println(e);
      return;
    }

    try {
      out.println("PROG " + classname);
      ir.visit(p);
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      return;
    }
    
    try {
      cg.visit(ir);
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      return;
    }

  }

}
