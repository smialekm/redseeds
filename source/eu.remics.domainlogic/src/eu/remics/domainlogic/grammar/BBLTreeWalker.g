tree grammar BBLTreeWalker;

options {
  language = Java;
  tokenVocab = BBL;
  ASTLabelType = CommonTree;
}

@header {
  import eu.remics.domainlogic.generator.*;
  import eu.remics.domainlogic.translation.*;
}

@members {
  BBL2JavaGenerator generator = new BBL2JavaGenerator();
}

rule: { generator.getContext().initWithOperationParameters(ModelHelper.getOperationParameters()); } (sentences {System.out.println($sentences.result);})+ /*{System.out.println($sentences.result);}*/;

sentences returns [String result]: sentence ((and=COMMA | and=AND) var=sentences | DOT)? { $result = generator.sentences($sentence.result,$and.text,$var.result); /*System.out.println($result);*/ } ;

sentence returns [String result]: conditional (alternative)? {$result = generator.ifElse($conditional.result, $alternative.result); }
        | loop after_all? {$result = $loop.result;}
        | except
        | operation {$result = $operation.result;}
        | error {$result = $error.result;}
        | return_stm;

conditional returns [String result]: ^(IF conditions sentences) {$result = generator.conditional($conditions.result, $sentences.result);} ;

alternative returns [String result]: ^(OTHERWISE sentences) {$result = generator.alternative($sentences.result);};

loop returns [String result]: ^(FOR_EACH item=STRING collection=STRING sentences) {$result = generator.loop($item.text, $collection.text, $sentences.result);};

after_all: ^(AFTER_ALL STRING sentences);

except returns [String result]: ^(WHEN STRING sentences) { $result = generator.except($STRING.text); };

error returns [String result]: ^((val=SHOW_ERROR | val=SHOW_WARNING) STRING) { $result = generator.error($val.text, $STRING.text); };

return_stm returns [String result]: ^(RETURN STRING) { $result = generator.returnStatement($STRING.text); };

conditions returns [String result]: conditionsSUM (OR val=conditions)? { $result = generator.conditions($conditionsSUM.result, $val.result); };//^(OR conditionsSUM conditions?);

conditionsSUM returns [String result]: condition (AND val=conditionsSUM)? { $result = generator.conditionsSUM($condition.result, $val.result); };//^(AND condition conditionsSUM);

condition returns [String result]: ^((val='is equal to' 
            | val='are equal to'
            | val='is not equal to' 
            | val='are not equal to'
            | val='is greater or equal to' 
            | val='are greater or equal to' 
            | val='is greater than' 
            | val='are greater than'
            | val='is lower or equal to' 
            | val='are lower or equal to'
            | val='is lower than' 
            | val='are lower than') operand1=STRING operand2=STRING) { $result = generator.condition($operand1.text,$val.text,$operand2.text);};
            
operation returns [String result]: STRING {$result = generator.operation($STRING.text);};
