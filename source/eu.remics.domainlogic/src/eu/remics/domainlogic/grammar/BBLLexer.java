// $ANTLR 3.4 D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g 2012-09-17 11:45:25

  package eu.remics.domainlogic.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class BBLLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int AFTER_ALL=4;
    public static final int AND=5;
    public static final int APOSTR=6;
    public static final int CHAR=7;
    public static final int COMMA=8;
    public static final int DIGIT=9;
    public static final int DOT=10;
    public static final int FOR_EACH=11;
    public static final int IF=12;
    public static final int OF_THE=13;
    public static final int OR=14;
    public static final int OTHERWISE=15;
    public static final int QUOTE=16;
    public static final int RETURN=17;
    public static final int SHOW_ERROR=18;
    public static final int SHOW_WARNING=19;
    public static final int STRING=20;
    public static final int WHEN=21;
    public static final int WS=22;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public BBLLexer() {} 
    public BBLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public BBLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g"; }

    // $ANTLR start "AFTER_ALL"
    public final void mAFTER_ALL() throws RecognitionException {
        try {
            int _type = AFTER_ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:11:11: ( 'after all' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:11:13: 'after all'
            {
            match("after all"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AFTER_ALL"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:12:5: ( 'and' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:12:7: 'and'
            {
            match("and"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "APOSTR"
    public final void mAPOSTR() throws RecognitionException {
        try {
            int _type = APOSTR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:13:8: ( '\\'' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:13:10: '\\''
            {
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "APOSTR"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:14:7: ( ',' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:14:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:15:5: ( '.' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:15:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "FOR_EACH"
    public final void mFOR_EACH() throws RecognitionException {
        try {
            int _type = FOR_EACH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:16:10: ( 'for each' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:16:12: 'for each'
            {
            match("for each"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FOR_EACH"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:17:4: ( 'if' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:17:6: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "OF_THE"
    public final void mOF_THE() throws RecognitionException {
        try {
            int _type = OF_THE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:18:8: ( 'of the' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:18:10: 'of the'
            {
            match("of the"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OF_THE"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:19:4: ( 'or' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:19:6: 'or'
            {
            match("or"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "OTHERWISE"
    public final void mOTHERWISE() throws RecognitionException {
        try {
            int _type = OTHERWISE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:20:11: ( 'otherwise' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:20:13: 'otherwise'
            {
            match("otherwise"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OTHERWISE"

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:21:7: ( '\"' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:21:9: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUOTE"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:22:8: ( 'return' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:22:10: 'return'
            {
            match("return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "SHOW_ERROR"
    public final void mSHOW_ERROR() throws RecognitionException {
        try {
            int _type = SHOW_ERROR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:23:12: ( 'show error' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:23:14: 'show error'
            {
            match("show error"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHOW_ERROR"

    // $ANTLR start "SHOW_WARNING"
    public final void mSHOW_WARNING() throws RecognitionException {
        try {
            int _type = SHOW_WARNING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:24:14: ( 'show warning' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:24:16: 'show warning'
            {
            match("show warning"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHOW_WARNING"

    // $ANTLR start "WHEN"
    public final void mWHEN() throws RecognitionException {
        try {
            int _type = WHEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:25:6: ( 'when' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:25:8: 'when'
            {
            match("when"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHEN"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:26:7: ( 'are equal to' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:26:9: 'are equal to'
            {
            match("are equal to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:27:7: ( 'are greater or equal to' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:27:9: 'are greater or equal to'
            {
            match("are greater or equal to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:28:7: ( 'are greater than' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:28:9: 'are greater than'
            {
            match("are greater than"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:29:7: ( 'are lower or equal to' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:29:9: 'are lower or equal to'
            {
            match("are lower or equal to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:30:7: ( 'are lower than' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:30:9: 'are lower than'
            {
            match("are lower than"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:31:7: ( 'are not equal to' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:31:9: 'are not equal to'
            {
            match("are not equal to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:32:7: ( 'is equal to' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:32:9: 'is equal to'
            {
            match("is equal to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:33:7: ( 'is greater or equal to' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:33:9: 'is greater or equal to'
            {
            match("is greater or equal to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:34:7: ( 'is greater than' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:34:9: 'is greater than'
            {
            match("is greater than"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:35:7: ( 'is lower or equal to' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:35:9: 'is lower or equal to'
            {
            match("is lower or equal to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:36:7: ( 'is lower than' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:36:9: 'is lower than'
            {
            match("is lower than"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:37:7: ( 'is not equal to' )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:37:9: 'is not equal to'
            {
            match("is not equal to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:90:7: ( ( QUOTE | APOSTR ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' | ' ' )+ ( QUOTE | APOSTR ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:90:9: ( QUOTE | APOSTR ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' | ' ' )+ ( QUOTE | APOSTR )
            {
            if ( input.LA(1)=='\"'||input.LA(1)=='\'' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:90:26: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' | ' ' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==' '||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:
            	    {
            	    if ( input.LA(1)==' '||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            if ( input.LA(1)=='\"'||input.LA(1)=='\'' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:91:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+ )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:91:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:91:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:
            	    {
            	    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:92:6: ( ( '0' .. '9' )+ )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:92:8: ( '0' .. '9' )+
            {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:92:8: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:93:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:93:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:93:5: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||LA4_0=='\r'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:8: ( AFTER_ALL | AND | APOSTR | COMMA | DOT | FOR_EACH | IF | OF_THE | OR | OTHERWISE | QUOTE | RETURN | SHOW_ERROR | SHOW_WARNING | WHEN | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | STRING | CHAR | DIGIT | WS )
        int alt5=31;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:10: AFTER_ALL
                {
                mAFTER_ALL(); 


                }
                break;
            case 2 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:20: AND
                {
                mAND(); 


                }
                break;
            case 3 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:24: APOSTR
                {
                mAPOSTR(); 


                }
                break;
            case 4 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:31: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 5 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:37: DOT
                {
                mDOT(); 


                }
                break;
            case 6 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:41: FOR_EACH
                {
                mFOR_EACH(); 


                }
                break;
            case 7 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:50: IF
                {
                mIF(); 


                }
                break;
            case 8 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:53: OF_THE
                {
                mOF_THE(); 


                }
                break;
            case 9 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:60: OR
                {
                mOR(); 


                }
                break;
            case 10 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:63: OTHERWISE
                {
                mOTHERWISE(); 


                }
                break;
            case 11 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:73: QUOTE
                {
                mQUOTE(); 


                }
                break;
            case 12 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:79: RETURN
                {
                mRETURN(); 


                }
                break;
            case 13 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:86: SHOW_ERROR
                {
                mSHOW_ERROR(); 


                }
                break;
            case 14 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:97: SHOW_WARNING
                {
                mSHOW_WARNING(); 


                }
                break;
            case 15 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:110: WHEN
                {
                mWHEN(); 


                }
                break;
            case 16 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:115: T__23
                {
                mT__23(); 


                }
                break;
            case 17 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:121: T__24
                {
                mT__24(); 


                }
                break;
            case 18 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:127: T__25
                {
                mT__25(); 


                }
                break;
            case 19 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:133: T__26
                {
                mT__26(); 


                }
                break;
            case 20 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:139: T__27
                {
                mT__27(); 


                }
                break;
            case 21 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:145: T__28
                {
                mT__28(); 


                }
                break;
            case 22 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:151: T__29
                {
                mT__29(); 


                }
                break;
            case 23 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:157: T__30
                {
                mT__30(); 


                }
                break;
            case 24 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:163: T__31
                {
                mT__31(); 


                }
                break;
            case 25 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:169: T__32
                {
                mT__32(); 


                }
                break;
            case 26 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:175: T__33
                {
                mT__33(); 


                }
                break;
            case 27 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:181: T__34
                {
                mT__34(); 


                }
                break;
            case 28 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:187: STRING
                {
                mSTRING(); 


                }
                break;
            case 29 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:194: CHAR
                {
                mCHAR(); 


                }
                break;
            case 30 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:199: DIGIT
                {
                mDIGIT(); 


                }
                break;
            case 31 :
                // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:1:205: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\1\uffff\1\14\1\22\2\uffff\3\14\1\32\3\14\3\uffff\3\14\2\uffff\1"+
        "\14\1\42\2\14\1\45\1\14\1\uffff\4\14\1\53\2\14\4\uffff\5\14\7\uffff"+
        "\3\14\1\100\1\14\6\uffff\2\14\7\uffff\1\14\1\117\6\uffff\1\14\5"+
        "\uffff\1\14\4\uffff\1\137\17\uffff";
    static final String DFA5_eofS =
        "\151\uffff";
    static final String DFA5_minS =
        "\1\11\1\146\1\40\2\uffff\1\157\2\146\1\40\1\145\2\150\3\uffff\1"+
        "\164\1\144\1\145\2\uffff\1\162\1\101\2\40\1\101\1\150\1\uffff\1"+
        "\164\1\157\2\145\1\101\2\40\1\uffff\1\145\2\uffff\1\145\1\165\1"+
        "\167\1\156\1\162\1\uffff\1\145\2\uffff\1\162\1\157\1\uffff\2\162"+
        "\1\40\1\101\1\40\1\uffff\1\162\1\157\1\uffff\1\145\2\167\1\156\1"+
        "\145\2\uffff\1\145\1\167\1\141\1\145\1\151\1\101\2\uffff\1\141\1"+
        "\145\1\164\1\162\1\163\1\uffff\1\164\1\162\1\145\1\40\2\145\1\40"+
        "\1\162\1\157\1\101\1\162\1\157\1\40\3\uffff\1\40\2\uffff\2\157\4"+
        "\uffff";
    static final String DFA5_maxS =
        "\1\172\1\162\1\172\2\uffff\1\157\1\163\1\164\1\172\1\145\2\150\3"+
        "\uffff\1\164\1\144\1\145\2\uffff\1\162\1\172\2\40\1\172\1\150\1"+
        "\uffff\1\164\1\157\2\145\1\172\2\40\1\uffff\1\156\2\uffff\1\145"+
        "\1\165\1\167\1\156\1\162\1\uffff\1\156\2\uffff\1\162\1\157\1\uffff"+
        "\2\162\1\40\1\172\1\40\1\uffff\1\162\1\157\1\uffff\1\145\2\167\1"+
        "\156\1\167\2\uffff\1\145\1\167\1\141\1\145\1\151\1\172\2\uffff\1"+
        "\141\1\145\1\164\1\162\1\163\1\uffff\1\164\1\162\1\145\1\40\2\145"+
        "\1\40\1\162\1\164\1\172\1\162\1\164\1\40\3\uffff\1\40\2\uffff\2"+
        "\164\4\uffff";
    static final String DFA5_acceptS =
        "\3\uffff\1\4\1\5\7\uffff\1\35\1\36\1\37\3\uffff\1\3\1\34\6\uffff"+
        "\1\13\7\uffff\1\7\1\uffff\1\10\1\11\5\uffff\1\2\1\uffff\1\6\1\26"+
        "\2\uffff\1\33\5\uffff\1\20\2\uffff\1\25\5\uffff\1\17\1\1\6\uffff"+
        "\1\15\1\16\5\uffff\1\14\15\uffff\1\31\1\32\1\12\1\uffff\1\23\1\24"+
        "\2\uffff\1\27\1\30\1\21\1\22";
    static final String DFA5_specialS =
        "\151\uffff}>";
    static final String[] DFA5_transitionS = {
            "\2\16\2\uffff\1\16\22\uffff\1\16\1\uffff\1\10\4\uffff\1\2\4"+
            "\uffff\1\3\1\uffff\1\4\1\uffff\12\15\7\uffff\32\14\4\uffff\1"+
            "\14\1\uffff\1\1\4\14\1\5\2\14\1\6\5\14\1\7\2\14\1\11\1\12\3"+
            "\14\1\13\3\14",
            "\1\17\7\uffff\1\20\3\uffff\1\21",
            "\1\23\17\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32"+
            "\23",
            "",
            "",
            "\1\24",
            "\1\25\14\uffff\1\26",
            "\1\27\13\uffff\1\30\1\uffff\1\31",
            "\1\23\17\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32"+
            "\23",
            "\1\33",
            "\1\34",
            "\1\35",
            "",
            "",
            "",
            "\1\36",
            "\1\37",
            "\1\40",
            "",
            "",
            "\1\41",
            "\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\43",
            "\1\44",
            "\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\46",
            "",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\54",
            "\1\55",
            "",
            "\1\56\1\uffff\1\57\4\uffff\1\60\1\uffff\1\61",
            "",
            "",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "",
            "\1\67\1\uffff\1\70\4\uffff\1\71\1\uffff\1\72",
            "",
            "",
            "\1\73",
            "\1\74",
            "",
            "\1\75",
            "\1\76",
            "\1\77",
            "\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\101",
            "",
            "\1\102",
            "\1\103",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110\21\uffff\1\111",
            "",
            "",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\32\14\4\uffff\1\14\1\uffff\32\14",
            "",
            "",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135\4\uffff\1\136",
            "\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\140",
            "\1\141\4\uffff\1\142",
            "\1\143",
            "",
            "",
            "",
            "\1\144",
            "",
            "",
            "\1\145\4\uffff\1\146",
            "\1\147\4\uffff\1\150",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( AFTER_ALL | AND | APOSTR | COMMA | DOT | FOR_EACH | IF | OF_THE | OR | OTHERWISE | QUOTE | RETURN | SHOW_ERROR | SHOW_WARNING | WHEN | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | STRING | CHAR | DIGIT | WS );";
        }
    }
 

}