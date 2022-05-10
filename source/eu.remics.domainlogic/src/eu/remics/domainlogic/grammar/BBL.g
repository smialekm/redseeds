grammar BBL;

options {
  language = Java;
  output = AST;
}

tokens {
  DOT = '.';
  COMMA = ',';
  AND = 'and';
  OR = 'or';
  SHOW_ERROR = 'show error';
  SHOW_WARNING = 'show warning';
  QUOTE = '"';
  APOSTR = '\'';
  RETURN = 'return';
  OTHERWISE = 'otherwise';
  IF = 'if';
  FOR_EACH = 'for each';
  OF_THE = 'of the';
  WHEN = 'when';
  AFTER_ALL = 'after all';
}

@header {
  package eu.remics.domainlogic.grammar;
}

@lexer::header {
  package eu.remics.domainlogic.grammar;
}

rule: sentences+;

sentences: sentence ((COMMA | AND) sentences | DOT)?;

sentence: conditional (alternative)?
        | loop after_all?
        | except
        | operation
        | error
        | return_stm;

conditional: IF^ conditions COMMA! sentences;

alternative: OTHERWISE^ COMMA! sentences;

loop: FOR_EACH^ STRING OF_THE! STRING COMMA! sentences;

after_all: AFTER_ALL^ STRING COMMA! sentences;

except: WHEN^ STRING COMMA! sentences;

error: (SHOW_ERROR^ | SHOW_WARNING^) STRING;

return_stm: RETURN^ STRING;

conditions: conditionsSUM (OR conditions)?;

conditionsSUM: condition (AND conditionsSUM)?;

condition: STRING (('is equal to'^ 
                   | 'are equal to'^ 
                   | 'is not equal to'^ 
                   | 'are not equal to'^ 
                   | 'is greater or equal to'^ 
                   | 'are greater or equal to'^ 
                   | 'is greater than'^ 
                   | 'are greater than'^
                   | 'is lower or equal to'^ 
                   | 'are lower or equal to'^
                   | 'is lower than'^ 
                   | 'are lower than'^) STRING)?;

//message: STRING;

//elem: STRING;

//collection: STRING;

//event: STRING;

//operand: STRING;

operation: STRING;

//alpha_numeric: (CHAR | DIGIT)(DIGIT | CHAR)*;

STRING: (QUOTE | APOSTR) ('0'..'9' | 'a'..'z' | 'A'..'Z' | '_' | ' ')+ (QUOTE | APOSTR);
CHAR: ('a'..'z' | 'A'..'Z' | '_')+;
DIGIT: '0'..'9'+;
WS: (' ' | '\t' | '\r' | '\n')+{$channel = HIDDEN;};
