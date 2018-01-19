// $ANTLR 3.0.1 ulNoActions.g 2018-01-04 12:49:23

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ulNoActionsLexer extends Lexer {
    public static final int NEWLINE=5;
    public static final int Tokens=6;
    public static final int EOF=-1;
    public static final int IF=4;
    public ulNoActionsLexer() {;} 
    public ulNoActionsLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "ulNoActions.g"; }

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // ulNoActions.g:33:4: ( 'if' )
            // ulNoActions.g:33:6: 'if'
            {
            match("if"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IF

    // $ANTLR start NEWLINE
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            // ulNoActions.g:39:8: ( '\\n' )
            // ulNoActions.g:39:10: '\\n'
            {
            match('\n'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NEWLINE

    public void mTokens() throws RecognitionException {
        // ulNoActions.g:1:8: ( IF | NEWLINE )
        int alt1=2;
        int LA1_0 = input.LA(1);

        if ( (LA1_0=='i') ) {
            alt1=1;
        }
        else if ( (LA1_0=='\n') ) {
            alt1=2;
        }
        else {
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( IF | NEWLINE );", 1, 0, input);

            throw nvae;
        }
        switch (alt1) {
            case 1 :
                // ulNoActions.g:1:10: IF
                {
                mIF(); 

                }
                break;
            case 2 :
                // ulNoActions.g:1:13: NEWLINE
                {
                mNEWLINE(); 

                }
                break;

        }

    }


 

}
