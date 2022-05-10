// $ANTLR 3.4 D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g 2012-09-17 11:45:25

  package eu.remics.domainlogic.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class BBLParser extends Parser {
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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public BBLParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public BBLParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return BBLParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g"; }


    public static class rule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rule"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:34:1: rule : ( sentences )+ ;
    public final BBLParser.rule_return rule() throws RecognitionException {
        BBLParser.rule_return retval = new BBLParser.rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        BBLParser.sentences_return sentences1 =null;



        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:34:5: ( ( sentences )+ )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:34:7: ( sentences )+
            {
            root_0 = (Object)adaptor.nil();


            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:34:7: ( sentences )+
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
            	    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:34:7: sentences
            	    {
            	    pushFollow(FOLLOW_sentences_in_rule187);
            	    sentences1=sentences();

            	    state._fsp--;

            	    adaptor.addChild(root_0, sentences1.getTree());

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

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rule"


    public static class sentences_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sentences"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:36:1: sentences : sentence ( ( COMMA | AND ) sentences | DOT )? ;
    public final BBLParser.sentences_return sentences() throws RecognitionException {
        BBLParser.sentences_return retval = new BBLParser.sentences_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set3=null;
        Token DOT5=null;
        BBLParser.sentence_return sentence2 =null;

        BBLParser.sentences_return sentences4 =null;


        Object set3_tree=null;
        Object DOT5_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:36:10: ( sentence ( ( COMMA | AND ) sentences | DOT )? )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:36:12: sentence ( ( COMMA | AND ) sentences | DOT )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_sentence_in_sentences195);
            sentence2=sentence();

            state._fsp--;

            adaptor.addChild(root_0, sentence2.getTree());

            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:36:21: ( ( COMMA | AND ) sentences | DOT )?
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==AND||LA2_0==COMMA) ) {
                alt2=1;
            }
            else if ( (LA2_0==DOT) ) {
                alt2=2;
            }
            switch (alt2) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:36:22: ( COMMA | AND ) sentences
                    {
                    set3=(Token)input.LT(1);

                    if ( input.LA(1)==AND||input.LA(1)==COMMA ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set3)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_sentences_in_sentences206);
                    sentences4=sentences();

                    state._fsp--;

                    adaptor.addChild(root_0, sentences4.getTree());

                    }
                    break;
                case 2 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:36:48: DOT
                    {
                    DOT5=(Token)match(input,DOT,FOLLOW_DOT_in_sentences210); 
                    DOT5_tree = 
                    (Object)adaptor.create(DOT5)
                    ;
                    adaptor.addChild(root_0, DOT5_tree);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "sentences"


    public static class sentence_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sentence"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:38:1: sentence : ( conditional ( alternative )? | loop ( after_all )? | except | operation | error | return_stm );
    public final BBLParser.sentence_return sentence() throws RecognitionException {
        BBLParser.sentence_return retval = new BBLParser.sentence_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        BBLParser.conditional_return conditional6 =null;

        BBLParser.alternative_return alternative7 =null;

        BBLParser.loop_return loop8 =null;

        BBLParser.after_all_return after_all9 =null;

        BBLParser.except_return except10 =null;

        BBLParser.operation_return operation11 =null;

        BBLParser.error_return error12 =null;

        BBLParser.return_stm_return return_stm13 =null;



        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:38:9: ( conditional ( alternative )? | loop ( after_all )? | except | operation | error | return_stm )
            int alt5=6;
            switch ( input.LA(1) ) {
            case IF:
                {
                alt5=1;
                }
                break;
            case FOR_EACH:
                {
                alt5=2;
                }
                break;
            case WHEN:
                {
                alt5=3;
                }
                break;
            case STRING:
                {
                alt5=4;
                }
                break;
            case SHOW_ERROR:
            case SHOW_WARNING:
                {
                alt5=5;
                }
                break;
            case RETURN:
                {
                alt5=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:38:11: conditional ( alternative )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_conditional_in_sentence219);
                    conditional6=conditional();

                    state._fsp--;

                    adaptor.addChild(root_0, conditional6.getTree());

                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:38:23: ( alternative )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==OTHERWISE) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:38:24: alternative
                            {
                            pushFollow(FOLLOW_alternative_in_sentence222);
                            alternative7=alternative();

                            state._fsp--;

                            adaptor.addChild(root_0, alternative7.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:39:11: loop ( after_all )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_loop_in_sentence236);
                    loop8=loop();

                    state._fsp--;

                    adaptor.addChild(root_0, loop8.getTree());

                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:39:16: ( after_all )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==AFTER_ALL) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:39:16: after_all
                            {
                            pushFollow(FOLLOW_after_all_in_sentence238);
                            after_all9=after_all();

                            state._fsp--;

                            adaptor.addChild(root_0, after_all9.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:40:11: except
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_except_in_sentence251);
                    except10=except();

                    state._fsp--;

                    adaptor.addChild(root_0, except10.getTree());

                    }
                    break;
                case 4 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:41:11: operation
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_operation_in_sentence263);
                    operation11=operation();

                    state._fsp--;

                    adaptor.addChild(root_0, operation11.getTree());

                    }
                    break;
                case 5 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:42:11: error
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_error_in_sentence275);
                    error12=error();

                    state._fsp--;

                    adaptor.addChild(root_0, error12.getTree());

                    }
                    break;
                case 6 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:43:11: return_stm
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_return_stm_in_sentence287);
                    return_stm13=return_stm();

                    state._fsp--;

                    adaptor.addChild(root_0, return_stm13.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "sentence"


    public static class conditional_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conditional"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:45:1: conditional : IF ^ conditions COMMA ! sentences ;
    public final BBLParser.conditional_return conditional() throws RecognitionException {
        BBLParser.conditional_return retval = new BBLParser.conditional_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token IF14=null;
        Token COMMA16=null;
        BBLParser.conditions_return conditions15 =null;

        BBLParser.sentences_return sentences17 =null;


        Object IF14_tree=null;
        Object COMMA16_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:45:12: ( IF ^ conditions COMMA ! sentences )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:45:14: IF ^ conditions COMMA ! sentences
            {
            root_0 = (Object)adaptor.nil();


            IF14=(Token)match(input,IF,FOLLOW_IF_in_conditional294); 
            IF14_tree = 
            (Object)adaptor.create(IF14)
            ;
            root_0 = (Object)adaptor.becomeRoot(IF14_tree, root_0);


            pushFollow(FOLLOW_conditions_in_conditional297);
            conditions15=conditions();

            state._fsp--;

            adaptor.addChild(root_0, conditions15.getTree());

            COMMA16=(Token)match(input,COMMA,FOLLOW_COMMA_in_conditional299); 

            pushFollow(FOLLOW_sentences_in_conditional302);
            sentences17=sentences();

            state._fsp--;

            adaptor.addChild(root_0, sentences17.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "conditional"


    public static class alternative_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "alternative"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:47:1: alternative : OTHERWISE ^ COMMA ! sentences ;
    public final BBLParser.alternative_return alternative() throws RecognitionException {
        BBLParser.alternative_return retval = new BBLParser.alternative_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token OTHERWISE18=null;
        Token COMMA19=null;
        BBLParser.sentences_return sentences20 =null;


        Object OTHERWISE18_tree=null;
        Object COMMA19_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:47:12: ( OTHERWISE ^ COMMA ! sentences )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:47:14: OTHERWISE ^ COMMA ! sentences
            {
            root_0 = (Object)adaptor.nil();


            OTHERWISE18=(Token)match(input,OTHERWISE,FOLLOW_OTHERWISE_in_alternative309); 
            OTHERWISE18_tree = 
            (Object)adaptor.create(OTHERWISE18)
            ;
            root_0 = (Object)adaptor.becomeRoot(OTHERWISE18_tree, root_0);


            COMMA19=(Token)match(input,COMMA,FOLLOW_COMMA_in_alternative312); 

            pushFollow(FOLLOW_sentences_in_alternative315);
            sentences20=sentences();

            state._fsp--;

            adaptor.addChild(root_0, sentences20.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "alternative"


    public static class loop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "loop"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:49:1: loop : FOR_EACH ^ STRING OF_THE ! STRING COMMA ! sentences ;
    public final BBLParser.loop_return loop() throws RecognitionException {
        BBLParser.loop_return retval = new BBLParser.loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token FOR_EACH21=null;
        Token STRING22=null;
        Token OF_THE23=null;
        Token STRING24=null;
        Token COMMA25=null;
        BBLParser.sentences_return sentences26 =null;


        Object FOR_EACH21_tree=null;
        Object STRING22_tree=null;
        Object OF_THE23_tree=null;
        Object STRING24_tree=null;
        Object COMMA25_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:49:5: ( FOR_EACH ^ STRING OF_THE ! STRING COMMA ! sentences )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:49:7: FOR_EACH ^ STRING OF_THE ! STRING COMMA ! sentences
            {
            root_0 = (Object)adaptor.nil();


            FOR_EACH21=(Token)match(input,FOR_EACH,FOLLOW_FOR_EACH_in_loop322); 
            FOR_EACH21_tree = 
            (Object)adaptor.create(FOR_EACH21)
            ;
            root_0 = (Object)adaptor.becomeRoot(FOR_EACH21_tree, root_0);


            STRING22=(Token)match(input,STRING,FOLLOW_STRING_in_loop325); 
            STRING22_tree = 
            (Object)adaptor.create(STRING22)
            ;
            adaptor.addChild(root_0, STRING22_tree);


            OF_THE23=(Token)match(input,OF_THE,FOLLOW_OF_THE_in_loop327); 

            STRING24=(Token)match(input,STRING,FOLLOW_STRING_in_loop330); 
            STRING24_tree = 
            (Object)adaptor.create(STRING24)
            ;
            adaptor.addChild(root_0, STRING24_tree);


            COMMA25=(Token)match(input,COMMA,FOLLOW_COMMA_in_loop332); 

            pushFollow(FOLLOW_sentences_in_loop335);
            sentences26=sentences();

            state._fsp--;

            adaptor.addChild(root_0, sentences26.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "loop"


    public static class after_all_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "after_all"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:51:1: after_all : AFTER_ALL ^ STRING COMMA ! sentences ;
    public final BBLParser.after_all_return after_all() throws RecognitionException {
        BBLParser.after_all_return retval = new BBLParser.after_all_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token AFTER_ALL27=null;
        Token STRING28=null;
        Token COMMA29=null;
        BBLParser.sentences_return sentences30 =null;


        Object AFTER_ALL27_tree=null;
        Object STRING28_tree=null;
        Object COMMA29_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:51:10: ( AFTER_ALL ^ STRING COMMA ! sentences )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:51:12: AFTER_ALL ^ STRING COMMA ! sentences
            {
            root_0 = (Object)adaptor.nil();


            AFTER_ALL27=(Token)match(input,AFTER_ALL,FOLLOW_AFTER_ALL_in_after_all342); 
            AFTER_ALL27_tree = 
            (Object)adaptor.create(AFTER_ALL27)
            ;
            root_0 = (Object)adaptor.becomeRoot(AFTER_ALL27_tree, root_0);


            STRING28=(Token)match(input,STRING,FOLLOW_STRING_in_after_all345); 
            STRING28_tree = 
            (Object)adaptor.create(STRING28)
            ;
            adaptor.addChild(root_0, STRING28_tree);


            COMMA29=(Token)match(input,COMMA,FOLLOW_COMMA_in_after_all347); 

            pushFollow(FOLLOW_sentences_in_after_all350);
            sentences30=sentences();

            state._fsp--;

            adaptor.addChild(root_0, sentences30.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "after_all"


    public static class except_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "except"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:53:1: except : WHEN ^ STRING COMMA ! sentences ;
    public final BBLParser.except_return except() throws RecognitionException {
        BBLParser.except_return retval = new BBLParser.except_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token WHEN31=null;
        Token STRING32=null;
        Token COMMA33=null;
        BBLParser.sentences_return sentences34 =null;


        Object WHEN31_tree=null;
        Object STRING32_tree=null;
        Object COMMA33_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:53:7: ( WHEN ^ STRING COMMA ! sentences )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:53:9: WHEN ^ STRING COMMA ! sentences
            {
            root_0 = (Object)adaptor.nil();


            WHEN31=(Token)match(input,WHEN,FOLLOW_WHEN_in_except357); 
            WHEN31_tree = 
            (Object)adaptor.create(WHEN31)
            ;
            root_0 = (Object)adaptor.becomeRoot(WHEN31_tree, root_0);


            STRING32=(Token)match(input,STRING,FOLLOW_STRING_in_except360); 
            STRING32_tree = 
            (Object)adaptor.create(STRING32)
            ;
            adaptor.addChild(root_0, STRING32_tree);


            COMMA33=(Token)match(input,COMMA,FOLLOW_COMMA_in_except362); 

            pushFollow(FOLLOW_sentences_in_except365);
            sentences34=sentences();

            state._fsp--;

            adaptor.addChild(root_0, sentences34.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "except"


    public static class error_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "error"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:55:1: error : ( SHOW_ERROR ^| SHOW_WARNING ^) STRING ;
    public final BBLParser.error_return error() throws RecognitionException {
        BBLParser.error_return retval = new BBLParser.error_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SHOW_ERROR35=null;
        Token SHOW_WARNING36=null;
        Token STRING37=null;

        Object SHOW_ERROR35_tree=null;
        Object SHOW_WARNING36_tree=null;
        Object STRING37_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:55:6: ( ( SHOW_ERROR ^| SHOW_WARNING ^) STRING )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:55:8: ( SHOW_ERROR ^| SHOW_WARNING ^) STRING
            {
            root_0 = (Object)adaptor.nil();


            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:55:8: ( SHOW_ERROR ^| SHOW_WARNING ^)
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SHOW_ERROR) ) {
                alt6=1;
            }
            else if ( (LA6_0==SHOW_WARNING) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:55:9: SHOW_ERROR ^
                    {
                    SHOW_ERROR35=(Token)match(input,SHOW_ERROR,FOLLOW_SHOW_ERROR_in_error373); 
                    SHOW_ERROR35_tree = 
                    (Object)adaptor.create(SHOW_ERROR35)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(SHOW_ERROR35_tree, root_0);


                    }
                    break;
                case 2 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:55:23: SHOW_WARNING ^
                    {
                    SHOW_WARNING36=(Token)match(input,SHOW_WARNING,FOLLOW_SHOW_WARNING_in_error378); 
                    SHOW_WARNING36_tree = 
                    (Object)adaptor.create(SHOW_WARNING36)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(SHOW_WARNING36_tree, root_0);


                    }
                    break;

            }


            STRING37=(Token)match(input,STRING,FOLLOW_STRING_in_error382); 
            STRING37_tree = 
            (Object)adaptor.create(STRING37)
            ;
            adaptor.addChild(root_0, STRING37_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "error"


    public static class return_stm_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "return_stm"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:57:1: return_stm : RETURN ^ STRING ;
    public final BBLParser.return_stm_return return_stm() throws RecognitionException {
        BBLParser.return_stm_return retval = new BBLParser.return_stm_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token RETURN38=null;
        Token STRING39=null;

        Object RETURN38_tree=null;
        Object STRING39_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:57:11: ( RETURN ^ STRING )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:57:13: RETURN ^ STRING
            {
            root_0 = (Object)adaptor.nil();


            RETURN38=(Token)match(input,RETURN,FOLLOW_RETURN_in_return_stm389); 
            RETURN38_tree = 
            (Object)adaptor.create(RETURN38)
            ;
            root_0 = (Object)adaptor.becomeRoot(RETURN38_tree, root_0);


            STRING39=(Token)match(input,STRING,FOLLOW_STRING_in_return_stm392); 
            STRING39_tree = 
            (Object)adaptor.create(STRING39)
            ;
            adaptor.addChild(root_0, STRING39_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "return_stm"


    public static class conditions_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conditions"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:59:1: conditions : conditionsSUM ( OR conditions )? ;
    public final BBLParser.conditions_return conditions() throws RecognitionException {
        BBLParser.conditions_return retval = new BBLParser.conditions_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token OR41=null;
        BBLParser.conditionsSUM_return conditionsSUM40 =null;

        BBLParser.conditions_return conditions42 =null;


        Object OR41_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:59:11: ( conditionsSUM ( OR conditions )? )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:59:13: conditionsSUM ( OR conditions )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_conditionsSUM_in_conditions399);
            conditionsSUM40=conditionsSUM();

            state._fsp--;

            adaptor.addChild(root_0, conditionsSUM40.getTree());

            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:59:27: ( OR conditions )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==OR) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:59:28: OR conditions
                    {
                    OR41=(Token)match(input,OR,FOLLOW_OR_in_conditions402); 
                    OR41_tree = 
                    (Object)adaptor.create(OR41)
                    ;
                    adaptor.addChild(root_0, OR41_tree);


                    pushFollow(FOLLOW_conditions_in_conditions404);
                    conditions42=conditions();

                    state._fsp--;

                    adaptor.addChild(root_0, conditions42.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "conditions"


    public static class conditionsSUM_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conditionsSUM"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:61:1: conditionsSUM : condition ( AND conditionsSUM )? ;
    public final BBLParser.conditionsSUM_return conditionsSUM() throws RecognitionException {
        BBLParser.conditionsSUM_return retval = new BBLParser.conditionsSUM_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token AND44=null;
        BBLParser.condition_return condition43 =null;

        BBLParser.conditionsSUM_return conditionsSUM45 =null;


        Object AND44_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:61:14: ( condition ( AND conditionsSUM )? )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:61:16: condition ( AND conditionsSUM )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_condition_in_conditionsSUM413);
            condition43=condition();

            state._fsp--;

            adaptor.addChild(root_0, condition43.getTree());

            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:61:26: ( AND conditionsSUM )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==AND) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:61:27: AND conditionsSUM
                    {
                    AND44=(Token)match(input,AND,FOLLOW_AND_in_conditionsSUM416); 
                    AND44_tree = 
                    (Object)adaptor.create(AND44)
                    ;
                    adaptor.addChild(root_0, AND44_tree);


                    pushFollow(FOLLOW_conditionsSUM_in_conditionsSUM418);
                    conditionsSUM45=conditionsSUM();

                    state._fsp--;

                    adaptor.addChild(root_0, conditionsSUM45.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "conditionsSUM"


    public static class condition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "condition"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:63:1: condition : STRING ( ( 'is equal to' ^| 'are equal to' ^| 'is not equal to' ^| 'are not equal to' ^| 'is greater or equal to' ^| 'are greater or equal to' ^| 'is greater than' ^| 'are greater than' ^| 'is lower or equal to' ^| 'are lower or equal to' ^| 'is lower than' ^| 'are lower than' ^) STRING )? ;
    public final BBLParser.condition_return condition() throws RecognitionException {
        BBLParser.condition_return retval = new BBLParser.condition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token STRING46=null;
        Token string_literal47=null;
        Token string_literal48=null;
        Token string_literal49=null;
        Token string_literal50=null;
        Token string_literal51=null;
        Token string_literal52=null;
        Token string_literal53=null;
        Token string_literal54=null;
        Token string_literal55=null;
        Token string_literal56=null;
        Token string_literal57=null;
        Token string_literal58=null;
        Token STRING59=null;

        Object STRING46_tree=null;
        Object string_literal47_tree=null;
        Object string_literal48_tree=null;
        Object string_literal49_tree=null;
        Object string_literal50_tree=null;
        Object string_literal51_tree=null;
        Object string_literal52_tree=null;
        Object string_literal53_tree=null;
        Object string_literal54_tree=null;
        Object string_literal55_tree=null;
        Object string_literal56_tree=null;
        Object string_literal57_tree=null;
        Object string_literal58_tree=null;
        Object STRING59_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:63:10: ( STRING ( ( 'is equal to' ^| 'are equal to' ^| 'is not equal to' ^| 'are not equal to' ^| 'is greater or equal to' ^| 'are greater or equal to' ^| 'is greater than' ^| 'are greater than' ^| 'is lower or equal to' ^| 'are lower or equal to' ^| 'is lower than' ^| 'are lower than' ^) STRING )? )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:63:12: STRING ( ( 'is equal to' ^| 'are equal to' ^| 'is not equal to' ^| 'are not equal to' ^| 'is greater or equal to' ^| 'are greater or equal to' ^| 'is greater than' ^| 'are greater than' ^| 'is lower or equal to' ^| 'are lower or equal to' ^| 'is lower than' ^| 'are lower than' ^) STRING )?
            {
            root_0 = (Object)adaptor.nil();


            STRING46=(Token)match(input,STRING,FOLLOW_STRING_in_condition427); 
            STRING46_tree = 
            (Object)adaptor.create(STRING46)
            ;
            adaptor.addChild(root_0, STRING46_tree);


            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:63:19: ( ( 'is equal to' ^| 'are equal to' ^| 'is not equal to' ^| 'are not equal to' ^| 'is greater or equal to' ^| 'are greater or equal to' ^| 'is greater than' ^| 'are greater than' ^| 'is lower or equal to' ^| 'are lower or equal to' ^| 'is lower than' ^| 'are lower than' ^) STRING )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0 >= 23 && LA10_0 <= 34)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:63:20: ( 'is equal to' ^| 'are equal to' ^| 'is not equal to' ^| 'are not equal to' ^| 'is greater or equal to' ^| 'are greater or equal to' ^| 'is greater than' ^| 'are greater than' ^| 'is lower or equal to' ^| 'are lower or equal to' ^| 'is lower than' ^| 'are lower than' ^) STRING
                    {
                    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:63:20: ( 'is equal to' ^| 'are equal to' ^| 'is not equal to' ^| 'are not equal to' ^| 'is greater or equal to' ^| 'are greater or equal to' ^| 'is greater than' ^| 'are greater than' ^| 'is lower or equal to' ^| 'are lower or equal to' ^| 'is lower than' ^| 'are lower than' ^)
                    int alt9=12;
                    switch ( input.LA(1) ) {
                    case 29:
                        {
                        alt9=1;
                        }
                        break;
                    case 23:
                        {
                        alt9=2;
                        }
                        break;
                    case 34:
                        {
                        alt9=3;
                        }
                        break;
                    case 28:
                        {
                        alt9=4;
                        }
                        break;
                    case 30:
                        {
                        alt9=5;
                        }
                        break;
                    case 24:
                        {
                        alt9=6;
                        }
                        break;
                    case 31:
                        {
                        alt9=7;
                        }
                        break;
                    case 25:
                        {
                        alt9=8;
                        }
                        break;
                    case 32:
                        {
                        alt9=9;
                        }
                        break;
                    case 26:
                        {
                        alt9=10;
                        }
                        break;
                    case 33:
                        {
                        alt9=11;
                        }
                        break;
                    case 27:
                        {
                        alt9=12;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 0, input);

                        throw nvae;

                    }

                    switch (alt9) {
                        case 1 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:63:21: 'is equal to' ^
                            {
                            string_literal47=(Token)match(input,29,FOLLOW_29_in_condition431); 
                            string_literal47_tree = 
                            (Object)adaptor.create(string_literal47)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal47_tree, root_0);


                            }
                            break;
                        case 2 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:64:22: 'are equal to' ^
                            {
                            string_literal48=(Token)match(input,23,FOLLOW_23_in_condition456); 
                            string_literal48_tree = 
                            (Object)adaptor.create(string_literal48)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal48_tree, root_0);


                            }
                            break;
                        case 3 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:65:22: 'is not equal to' ^
                            {
                            string_literal49=(Token)match(input,34,FOLLOW_34_in_condition481); 
                            string_literal49_tree = 
                            (Object)adaptor.create(string_literal49)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal49_tree, root_0);


                            }
                            break;
                        case 4 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:66:22: 'are not equal to' ^
                            {
                            string_literal50=(Token)match(input,28,FOLLOW_28_in_condition506); 
                            string_literal50_tree = 
                            (Object)adaptor.create(string_literal50)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal50_tree, root_0);


                            }
                            break;
                        case 5 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:67:22: 'is greater or equal to' ^
                            {
                            string_literal51=(Token)match(input,30,FOLLOW_30_in_condition531); 
                            string_literal51_tree = 
                            (Object)adaptor.create(string_literal51)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal51_tree, root_0);


                            }
                            break;
                        case 6 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:68:22: 'are greater or equal to' ^
                            {
                            string_literal52=(Token)match(input,24,FOLLOW_24_in_condition556); 
                            string_literal52_tree = 
                            (Object)adaptor.create(string_literal52)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal52_tree, root_0);


                            }
                            break;
                        case 7 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:69:22: 'is greater than' ^
                            {
                            string_literal53=(Token)match(input,31,FOLLOW_31_in_condition581); 
                            string_literal53_tree = 
                            (Object)adaptor.create(string_literal53)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal53_tree, root_0);


                            }
                            break;
                        case 8 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:70:22: 'are greater than' ^
                            {
                            string_literal54=(Token)match(input,25,FOLLOW_25_in_condition606); 
                            string_literal54_tree = 
                            (Object)adaptor.create(string_literal54)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal54_tree, root_0);


                            }
                            break;
                        case 9 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:71:22: 'is lower or equal to' ^
                            {
                            string_literal55=(Token)match(input,32,FOLLOW_32_in_condition630); 
                            string_literal55_tree = 
                            (Object)adaptor.create(string_literal55)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal55_tree, root_0);


                            }
                            break;
                        case 10 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:72:22: 'are lower or equal to' ^
                            {
                            string_literal56=(Token)match(input,26,FOLLOW_26_in_condition655); 
                            string_literal56_tree = 
                            (Object)adaptor.create(string_literal56)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal56_tree, root_0);


                            }
                            break;
                        case 11 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:73:22: 'is lower than' ^
                            {
                            string_literal57=(Token)match(input,33,FOLLOW_33_in_condition679); 
                            string_literal57_tree = 
                            (Object)adaptor.create(string_literal57)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal57_tree, root_0);


                            }
                            break;
                        case 12 :
                            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:74:22: 'are lower than' ^
                            {
                            string_literal58=(Token)match(input,27,FOLLOW_27_in_condition704); 
                            string_literal58_tree = 
                            (Object)adaptor.create(string_literal58)
                            ;
                            root_0 = (Object)adaptor.becomeRoot(string_literal58_tree, root_0);


                            }
                            break;

                    }


                    STRING59=(Token)match(input,STRING,FOLLOW_STRING_in_condition708); 
                    STRING59_tree = 
                    (Object)adaptor.create(STRING59)
                    ;
                    adaptor.addChild(root_0, STRING59_tree);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "condition"


    public static class operation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operation"
    // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:86:1: operation : STRING ;
    public final BBLParser.operation_return operation() throws RecognitionException {
        BBLParser.operation_return retval = new BBLParser.operation_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token STRING60=null;

        Object STRING60_tree=null;

        try {
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:86:10: ( STRING )
            // D:\\eclipse-rcp-helios-win32\\workspace\\eu.remics.domainlogic\\src\\eu\\remics\\domainlogic\\grammar\\BBL.g:86:12: STRING
            {
            root_0 = (Object)adaptor.nil();


            STRING60=(Token)match(input,STRING,FOLLOW_STRING_in_operation727); 
            STRING60_tree = 
            (Object)adaptor.create(STRING60)
            ;
            adaptor.addChild(root_0, STRING60_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "operation"

    // Delegated rules


 

    public static final BitSet FOLLOW_sentences_in_rule187 = new BitSet(new long[]{0x00000000003E1802L});
    public static final BitSet FOLLOW_sentence_in_sentences195 = new BitSet(new long[]{0x0000000000000522L});
    public static final BitSet FOLLOW_set_in_sentences198 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_sentences206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_sentences210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_in_sentence219 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_alternative_in_sentence222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_in_sentence236 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_after_all_in_sentence238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_except_in_sentence251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operation_in_sentence263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_error_in_sentence275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stm_in_sentence287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditional294 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_conditions_in_conditional297 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_conditional299 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_conditional302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OTHERWISE_in_alternative309 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_alternative312 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_alternative315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_EACH_in_loop322 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_loop325 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_OF_THE_in_loop327 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_loop330 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_loop332 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_loop335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_ALL_in_after_all342 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_after_all345 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_after_all347 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_after_all350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHEN_in_except357 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_except360 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_except362 = new BitSet(new long[]{0x00000000003E1800L});
    public static final BitSet FOLLOW_sentences_in_except365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHOW_ERROR_in_error373 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_SHOW_WARNING_in_error378 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_error382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_stm389 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_return_stm392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionsSUM_in_conditions399 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_OR_in_conditions402 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_conditions_in_conditions404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditionsSUM413 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_AND_in_conditionsSUM416 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_conditionsSUM_in_conditionsSUM418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_condition427 = new BitSet(new long[]{0x00000007FF800002L});
    public static final BitSet FOLLOW_29_in_condition431 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_23_in_condition456 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_34_in_condition481 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_28_in_condition506 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_30_in_condition531 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_24_in_condition556 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_31_in_condition581 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_25_in_condition606 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_32_in_condition630 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_26_in_condition655 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_33_in_condition679 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_27_in_condition704 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_condition708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_operation727 = new BitSet(new long[]{0x0000000000000002L});

}