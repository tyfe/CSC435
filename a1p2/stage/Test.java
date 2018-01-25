import org.antlr.runtime.*;

public class Test
{
	public static void main (String[] args) throws Exception
	{
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		ulLexer lexer = new ulLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ulParser parser = new ulParser(tokens);
		try {
			parser.program();
		} catch (RecognitionException e) {
			// A lexical or parsing error occured.
			// ANTLR will have already printed information on the
			// console due to code added to the grammar.  So there is
			// nothing to do here.
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}

