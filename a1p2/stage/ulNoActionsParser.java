// $ANTLR 3.0.1 ulNoActions.g 2018-01-04 12:49:23

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ulNoActionsParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IF", "NEWLINE"
    };
    public static final int NEWLINE=5;
    public static final int EOF=-1;
    public static final int IF=4;

        public ulNoActionsParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "ulNoActions.g"; }


    protected void mismatch (IntStream input, int ttype, BitSet follow)
            throws RecognitionException
    {
            throw new MismatchedTokenException(ttype, input);
    }
    public void recoverFromMismatchedSet (IntStream input,
                                          RecognitionException e,
                                          BitSet follow)
            throws RecognitionException
    {
            reportError(e);
            throw e;
    }



    // $ANTLR start program
    // ulNoActions.g:27:1: program : IF NEWLINE ;
    public final void program() throws RecognitionException {
        try {
            // ulNoActions.g:27:9: ( IF NEWLINE )
            // ulNoActions.g:27:11: IF NEWLINE
            {
            match(input,IF,FOLLOW_IF_in_program26); 
            match(input,NEWLINE,FOLLOW_NEWLINE_in_program28); 

            }

        }

                catch (RecognitionException ex) {
                        reportError(ex);
                        throw ex;
                }
        finally {
        }
        return ;
    }
    // $ANTLR end program


 

    public static final BitSet FOLLOW_IF_in_program26 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_program28 = new BitSet(new long[]{0x0000000000000002L});

}
