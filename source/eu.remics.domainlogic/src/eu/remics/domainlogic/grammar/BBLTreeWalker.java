package eu.remics.domainlogic.grammar;

// $ANTLR 3.4 D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g 2012-09-17 11:45:26

  import eu.remics.domainlogic.generator.*;
  import eu.remics.domainlogic.translation.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class BBLTreeWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AFTER_ALL", "AND", "APOSTR", "CHAR", "COMMA", "DIGIT", "DOT", "FOR_EACH", "IF", "OF_THE", "OR", "OTHERWISE", "QUOTE", "RETURN", "SHOW_ERROR", "SHOW_WARNING", "STRING", "WHEN", "WS", "'are equal to'", "'are greater or equal to'", "'are greater than'", "'are lower or equal to'", "'are lower than'", "'are not equal to'", "'is equal to'", "'is greater or equal to'", "'is greater than'", "'is lower or equal to'", "'is lower than'", "'is not equal to'"
    };

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
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public BBLTreeWalker(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public BBLTreeWalker(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return BBLTreeWalker.tokenNames; }
    public String getGrammarFileName() { return "D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g"; }


      BBL2JavaGenerator generator = new BBL2JavaGenerator();



    // $ANTLR start "rule"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:18:1: rule : ( sentences )+ ;
    public final void rule() throws RecognitionException {
        String sentences1 =null;


        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:18:5: ( ( sentences )+ )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:18:7: ( sentences )+
            {
             generator.getContext().initWithOperationParameters(ModelHelper.getOperationParameters()); 

            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:18:101: ( sentences )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= FOR_EACH && LA1_0 <= IF)||(LA1_0 >= RETURN && LA1_0 <= WHEN)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:18:102: sentences
            	    {
            	    pushFollow(FOLLOW_sentences_in_rule58);
            	    sentences1=sentences();

            	    state._fsp--;


            	    System.out.println(sentences1);

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


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "rule"



    // $ANTLR start "sentences"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:1: sentences returns [String result] : sentence ( (and= COMMA |and= AND ) var= sentences | DOT )? ;
    public final String sentences() throws RecognitionException {
        String result = null;


        CommonTree and=null;
        String var =null;

        String sentence2 =null;


        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:34: ( sentence ( (and= COMMA |and= AND ) var= sentences | DOT )? )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:36: sentence ( (and= COMMA |and= AND ) var= sentences | DOT )?
            {
            pushFollow(FOLLOW_sentence_in_sentences75);
            sentence2=sentence();

            state._fsp--;


            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:45: ( (and= COMMA |and= AND ) var= sentences | DOT )?
            int alt3=3;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==AND||LA3_0==COMMA) ) {
                alt3=1;
            }
            else if ( (LA3_0==DOT) ) {
                alt3=2;
            }
            switch (alt3) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:46: (and= COMMA |and= AND ) var= sentences
                    {
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:46: (and= COMMA |and= AND )
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==COMMA) ) {
                        alt2=1;
                    }
                    else if ( (LA2_0==AND) ) {
                        alt2=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 0, input);

                        throw nvae;

                    }
                    switch (alt2) {
                        case 1 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:47: and= COMMA
                            {
                            and=(CommonTree)match(input,COMMA,FOLLOW_COMMA_in_sentences81); 

                            }
                            break;
                        case 2 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:59: and= AND
                            {
                            and=(CommonTree)match(input,AND,FOLLOW_AND_in_sentences87); 

                            }
                            break;

                    }


                    pushFollow(FOLLOW_sentences_in_sentences92);
                    var=sentences();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:20:84: DOT
                    {
                    match(input,DOT,FOLLOW_DOT_in_sentences96); 

                    }
                    break;

            }


             result = generator.sentences(sentence2,(and!=null?and.getText():null),var); /*System.out.println(result);*/ 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "sentences"



    // $ANTLR start "sentence"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:22:1: sentence returns [String result] : ( conditional ( alternative )? | loop ( after_all )? | except | operation | error | return_stm );
    public final String sentence() throws RecognitionException {
        String result = null;


        String conditional3 =null;

        String alternative4 =null;

        String loop5 =null;

        String operation6 =null;

        String error7 =null;


        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:22:33: ( conditional ( alternative )? | loop ( after_all )? | except | operation | error | return_stm )
            int alt6=6;
            switch ( input.LA(1) ) {
            case IF:
                {
                alt6=1;
                }
                break;
            case FOR_EACH:
                {
                alt6=2;
                }
                break;
            case WHEN:
                {
                alt6=3;
                }
                break;
            case STRING:
                {
                alt6=4;
                }
                break;
            case SHOW_ERROR:
            case SHOW_WARNING:
                {
                alt6=5;
                }
                break;
            case RETURN:
                {
                alt6=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:22:35: conditional ( alternative )?
                    {
                    pushFollow(FOLLOW_conditional_in_sentence112);
                    conditional3=conditional();

                    state._fsp--;


                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:22:47: ( alternative )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==OTHERWISE) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:22:48: alternative
                            {
                            pushFollow(FOLLOW_alternative_in_sentence115);
                            alternative4=alternative();

                            state._fsp--;


                            }
                            break;

                    }


                    result = generator.ifElse(conditional3, alternative4); 

                    }
                    break;
                case 2 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:23:11: loop ( after_all )?
                    {
                    pushFollow(FOLLOW_loop_in_sentence131);
                    loop5=loop();

                    state._fsp--;


                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:23:16: ( after_all )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==AFTER_ALL) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:23:16: after_all
                            {
                            pushFollow(FOLLOW_after_all_in_sentence133);
                            after_all();

                            state._fsp--;


                            }
                            break;

                    }


                    result = loop5;

                    }
                    break;
                case 3 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:24:11: except
                    {
                    pushFollow(FOLLOW_except_in_sentence148);
                    except();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:25:11: operation
                    {
                    pushFollow(FOLLOW_operation_in_sentence160);
                    operation6=operation();

                    state._fsp--;


                    result = operation6;

                    }
                    break;
                case 5 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:26:11: error
                    {
                    pushFollow(FOLLOW_error_in_sentence174);
                    error7=error();

                    state._fsp--;


                    result = error7;

                    }
                    break;
                case 6 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:27:11: return_stm
                    {
                    pushFollow(FOLLOW_return_stm_in_sentence188);
                    return_stm();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "sentence"



    // $ANTLR start "conditional"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:29:1: conditional returns [String result] : ^( IF conditions sentences ) ;
    public final String conditional() throws RecognitionException {
        String result = null;


        String conditions8 =null;

        String sentences9 =null;


        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:29:36: ( ^( IF conditions sentences ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:29:38: ^( IF conditions sentences )
            {
            match(input,IF,FOLLOW_IF_in_conditional200); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_conditions_in_conditional202);
            conditions8=conditions();

            state._fsp--;


            pushFollow(FOLLOW_sentences_in_conditional204);
            sentences9=sentences();

            state._fsp--;


            match(input, Token.UP, null); 


            result = generator.conditional(conditions8, sentences9);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "conditional"



    // $ANTLR start "alternative"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:31:1: alternative returns [String result] : ^( OTHERWISE sentences ) ;
    public final String alternative() throws RecognitionException {
        String result = null;


        String sentences10 =null;


        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:31:36: ( ^( OTHERWISE sentences ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:31:38: ^( OTHERWISE sentences )
            {
            match(input,OTHERWISE,FOLLOW_OTHERWISE_in_alternative220); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_sentences_in_alternative222);
            sentences10=sentences();

            state._fsp--;


            match(input, Token.UP, null); 


            result = generator.alternative(sentences10);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "alternative"



    // $ANTLR start "loop"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:33:1: loop returns [String result] : ^( FOR_EACH item= STRING collection= STRING sentences ) ;
    public final String loop() throws RecognitionException {
        String result = null;


        CommonTree item=null;
        CommonTree collection=null;
        String sentences11 =null;


        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:33:29: ( ^( FOR_EACH item= STRING collection= STRING sentences ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:33:31: ^( FOR_EACH item= STRING collection= STRING sentences )
            {
            match(input,FOR_EACH,FOLLOW_FOR_EACH_in_loop237); 

            match(input, Token.DOWN, null); 
            item=(CommonTree)match(input,STRING,FOLLOW_STRING_in_loop241); 

            collection=(CommonTree)match(input,STRING,FOLLOW_STRING_in_loop245); 

            pushFollow(FOLLOW_sentences_in_loop247);
            sentences11=sentences();

            state._fsp--;


            match(input, Token.UP, null); 


            result = generator.loop((item!=null?item.getText():null), (collection!=null?collection.getText():null), sentences11);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "loop"



    // $ANTLR start "after_all"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:35:1: after_all : ^( AFTER_ALL STRING sentences ) ;
    public final void after_all() throws RecognitionException {
        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:35:10: ( ^( AFTER_ALL STRING sentences ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:35:12: ^( AFTER_ALL STRING sentences )
            {
            match(input,AFTER_ALL,FOLLOW_AFTER_ALL_in_after_all258); 

            match(input, Token.DOWN, null); 
            match(input,STRING,FOLLOW_STRING_in_after_all260); 

            pushFollow(FOLLOW_sentences_in_after_all262);
            sentences();

            state._fsp--;


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "after_all"



    // $ANTLR start "except"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:37:1: except returns [String result] : ^( WHEN STRING sentences ) ;
    public final String except() throws RecognitionException {
        String result = null;


        CommonTree STRING12=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:37:31: ( ^( WHEN STRING sentences ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:37:33: ^( WHEN STRING sentences )
            {
            match(input,WHEN,FOLLOW_WHEN_in_except275); 

            match(input, Token.DOWN, null); 
            STRING12=(CommonTree)match(input,STRING,FOLLOW_STRING_in_except277); 

            pushFollow(FOLLOW_sentences_in_except279);
            sentences();

            state._fsp--;


            match(input, Token.UP, null); 


             result = generator.except((STRING12!=null?STRING12.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "except"



    // $ANTLR start "error"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:39:1: error returns [String result] : ^( (val= SHOW_ERROR |val= SHOW_WARNING ) STRING ) ;
    public final String error() throws RecognitionException {
        String result = null;


        CommonTree val=null;
        CommonTree STRING13=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:39:30: ( ^( (val= SHOW_ERROR |val= SHOW_WARNING ) STRING ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:39:32: ^( (val= SHOW_ERROR |val= SHOW_WARNING ) STRING )
            {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:39:34: (val= SHOW_ERROR |val= SHOW_WARNING )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==SHOW_ERROR) ) {
                alt7=1;
            }
            else if ( (LA7_0==SHOW_WARNING) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:39:35: val= SHOW_ERROR
                    {
                    val=(CommonTree)match(input,SHOW_ERROR,FOLLOW_SHOW_ERROR_in_error297); 

                    }
                    break;
                case 2 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:39:52: val= SHOW_WARNING
                    {
                    val=(CommonTree)match(input,SHOW_WARNING,FOLLOW_SHOW_WARNING_in_error303); 

                    }
                    break;

            }


            match(input, Token.DOWN, null); 
            STRING13=(CommonTree)match(input,STRING,FOLLOW_STRING_in_error306); 

            match(input, Token.UP, null); 


             result = generator.error((val!=null?val.getText():null), (STRING13!=null?STRING13.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "error"



    // $ANTLR start "return_stm"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:41:1: return_stm returns [String result] : ^( RETURN STRING ) ;
    public final String return_stm() throws RecognitionException {
        String result = null;


        CommonTree STRING14=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:41:35: ( ^( RETURN STRING ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:41:37: ^( RETURN STRING )
            {
            match(input,RETURN,FOLLOW_RETURN_in_return_stm321); 

            match(input, Token.DOWN, null); 
            STRING14=(CommonTree)match(input,STRING,FOLLOW_STRING_in_return_stm323); 

            match(input, Token.UP, null); 


             result = generator.returnStatement((STRING14!=null?STRING14.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "return_stm"



    // $ANTLR start "conditions"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:43:1: conditions returns [String result] : conditionsSUM ( OR val= conditions )? ;
    public final String conditions() throws RecognitionException {
        String result = null;


        String val =null;

        String conditionsSUM15 =null;


        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:43:35: ( conditionsSUM ( OR val= conditions )? )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:43:37: conditionsSUM ( OR val= conditions )?
            {
            pushFollow(FOLLOW_conditionsSUM_in_conditions337);
            conditionsSUM15=conditionsSUM();

            state._fsp--;


            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:43:51: ( OR val= conditions )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==OR) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:43:52: OR val= conditions
                    {
                    match(input,OR,FOLLOW_OR_in_conditions340); 

                    pushFollow(FOLLOW_conditions_in_conditions344);
                    val=conditions();

                    state._fsp--;


                    }
                    break;

            }


             result = generator.conditions(conditionsSUM15, val); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "conditions"



    // $ANTLR start "conditionsSUM"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:45:1: conditionsSUM returns [String result] : condition ( AND val= conditionsSUM )? ;
    public final String conditionsSUM() throws RecognitionException {
        String result = null;


        String val =null;

        String condition16 =null;


        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:45:38: ( condition ( AND val= conditionsSUM )? )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:45:40: condition ( AND val= conditionsSUM )?
            {
            pushFollow(FOLLOW_condition_in_conditionsSUM359);
            condition16=condition();

            state._fsp--;


            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:45:50: ( AND val= conditionsSUM )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==AND) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:45:51: AND val= conditionsSUM
                    {
                    match(input,AND,FOLLOW_AND_in_conditionsSUM362); 

                    pushFollow(FOLLOW_conditionsSUM_in_conditionsSUM366);
                    val=conditionsSUM();

                    state._fsp--;


                    }
                    break;

            }


             result = generator.conditionsSUM(condition16, val); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "conditionsSUM"



    // $ANTLR start "condition"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:47:1: condition returns [String result] : ^( (val= 'is equal to' |val= 'are equal to' |val= 'is not equal to' |val= 'are not equal to' |val= 'is greater or equal to' |val= 'are greater or equal to' |val= 'is greater than' |val= 'are greater than' |val= 'is lower or equal to' |val= 'are lower or equal to' |val= 'is lower than' |val= 'are lower than' ) operand1= STRING operand2= STRING ) ;
    public final String condition() throws RecognitionException {
        String result = null;


        CommonTree val=null;
        CommonTree operand1=null;
        CommonTree operand2=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:47:34: ( ^( (val= 'is equal to' |val= 'are equal to' |val= 'is not equal to' |val= 'are not equal to' |val= 'is greater or equal to' |val= 'are greater or equal to' |val= 'is greater than' |val= 'are greater than' |val= 'is lower or equal to' |val= 'are lower or equal to' |val= 'is lower than' |val= 'are lower than' ) operand1= STRING operand2= STRING ) )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:47:36: ^( (val= 'is equal to' |val= 'are equal to' |val= 'is not equal to' |val= 'are not equal to' |val= 'is greater or equal to' |val= 'are greater or equal to' |val= 'is greater than' |val= 'are greater than' |val= 'is lower or equal to' |val= 'are lower or equal to' |val= 'is lower than' |val= 'are lower than' ) operand1= STRING operand2= STRING )
            {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:47:38: (val= 'is equal to' |val= 'are equal to' |val= 'is not equal to' |val= 'are not equal to' |val= 'is greater or equal to' |val= 'are greater or equal to' |val= 'is greater than' |val= 'are greater than' |val= 'is lower or equal to' |val= 'are lower or equal to' |val= 'is lower than' |val= 'are lower than' )
            int alt10=12;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt10=1;
                }
                break;
            case 23:
                {
                alt10=2;
                }
                break;
            case 34:
                {
                alt10=3;
                }
                break;
            case 28:
                {
                alt10=4;
                }
                break;
            case 30:
                {
                alt10=5;
                }
                break;
            case 24:
                {
                alt10=6;
                }
                break;
            case 31:
                {
                alt10=7;
                }
                break;
            case 25:
                {
                alt10=8;
                }
                break;
            case 32:
                {
                alt10=9;
                }
                break;
            case 26:
                {
                alt10=10;
                }
                break;
            case 33:
                {
                alt10=11;
                }
                break;
            case 27:
                {
                alt10=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:47:39: val= 'is equal to'
                    {
                    val=(CommonTree)match(input,29,FOLLOW_29_in_condition385); 

                    }
                    break;
                case 2 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:48:15: val= 'are equal to'
                    {
                    val=(CommonTree)match(input,23,FOLLOW_23_in_condition404); 

                    }
                    break;
                case 3 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:49:15: val= 'is not equal to'
                    {
                    val=(CommonTree)match(input,34,FOLLOW_34_in_condition422); 

                    }
                    break;
                case 4 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:50:15: val= 'are not equal to'
                    {
                    val=(CommonTree)match(input,28,FOLLOW_28_in_condition441); 

                    }
                    break;
                case 5 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:51:15: val= 'is greater or equal to'
                    {
                    val=(CommonTree)match(input,30,FOLLOW_30_in_condition459); 

                    }
                    break;
                case 6 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:52:15: val= 'are greater or equal to'
                    {
                    val=(CommonTree)match(input,24,FOLLOW_24_in_condition478); 

                    }
                    break;
                case 7 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:53:15: val= 'is greater than'
                    {
                    val=(CommonTree)match(input,31,FOLLOW_31_in_condition497); 

                    }
                    break;
                case 8 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:54:15: val= 'are greater than'
                    {
                    val=(CommonTree)match(input,25,FOLLOW_25_in_condition516); 

                    }
                    break;
                case 9 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:55:15: val= 'is lower or equal to'
                    {
                    val=(CommonTree)match(input,32,FOLLOW_32_in_condition534); 

                    }
                    break;
                case 10 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:56:15: val= 'are lower or equal to'
                    {
                    val=(CommonTree)match(input,26,FOLLOW_26_in_condition553); 

                    }
                    break;
                case 11 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:57:15: val= 'is lower than'
                    {
                    val=(CommonTree)match(input,33,FOLLOW_33_in_condition571); 

                    }
                    break;
                case 12 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:58:15: val= 'are lower than'
                    {
                    val=(CommonTree)match(input,27,FOLLOW_27_in_condition590); 

                    }
                    break;

            }


            match(input, Token.DOWN, null); 
            operand1=(CommonTree)match(input,STRING,FOLLOW_STRING_in_condition595); 

            operand2=(CommonTree)match(input,STRING,FOLLOW_STRING_in_condition599); 

            match(input, Token.UP, null); 


             result = generator.condition((operand1!=null?operand1.getText():null),(val!=null?val.getText():null),(operand2!=null?operand2.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "condition"



    // $ANTLR start "operation"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:60:1: operation returns [String result] : STRING ;
    public final String operation() throws RecognitionException {
        String result = null;


        CommonTree STRING17=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:60:34: ( STRING )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBLTreeWalker.g:60:36: STRING
            {
            STRING17=(CommonTree)match(input,STRING,FOLLOW_STRING_in_operation625); 

            result = generator.operation((STRING17!=null?STRING17.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "operation"

    // Delegated rules


 

    public static final BitSet FOLLOW_sentences_in_rule58 = new BitSet(new long[]{0x00000000003E1802L});
    public static final BitSet FOLLOW_sentence_in_sentences75 = new BitSet(new long[]{0x0000000000000522L});
    public static final BitSet FOLLOW_COMMA_in_sentences81 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_AND_in_sentences87 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_sentences92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_sentences96 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_in_sentence112 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_alternative_in_sentence115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_in_sentence131 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_after_all_in_sentence133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_except_in_sentence148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operation_in_sentence160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_error_in_sentence174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stm_in_sentence188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditional200 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_conditions_in_conditional202 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_conditional204 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OTHERWISE_in_alternative220 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_sentences_in_alternative222 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FOR_EACH_in_loop237 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_loop241 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_loop245 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_loop247 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AFTER_ALL_in_after_all258 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_after_all260 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_after_all262 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHEN_in_except275 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_except277 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_except279 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SHOW_ERROR_in_error297 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_SHOW_WARNING_in_error303 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_error306 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RETURN_in_return_stm321 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_return_stm323 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_conditionsSUM_in_conditions337 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_OR_in_conditions340 = new BitSet(new long[]{0x00000007FF800000L});
    public static final BitSet FOLLOW_conditions_in_conditions344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditionsSUM359 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_AND_in_conditionsSUM362 = new BitSet(new long[]{0x00000007FF800000L});
    public static final BitSet FOLLOW_conditionsSUM_in_conditionsSUM366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_condition385 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_23_in_condition404 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_34_in_condition422 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_28_in_condition441 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_30_in_condition459 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_24_in_condition478 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_31_in_condition497 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_25_in_condition516 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_32_in_condition534 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_26_in_condition553 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_33_in_condition571 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_27_in_condition590 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_condition595 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_condition599 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STRING_in_operation625 = new BitSet(new long[]{0x0000000000000002L});

}