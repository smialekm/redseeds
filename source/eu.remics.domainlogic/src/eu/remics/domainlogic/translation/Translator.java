package eu.remics.domainlogic.translation;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;

import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.remics.domainlogic.grammar.BBLLexer;
import eu.remics.domainlogic.grammar.BBLParser;
import eu.remics.domainlogic.grammar.BBLTreeWalker;

public class Translator {
	
	private ANTLRStringStream stream;
	private BBLLexer lexer;
	private CommonTokenStream tokens;
	private BBLParser parser;
	private CommonTree tree;
	private CommonTreeNodeStream nodes;
	private BBLTreeWalker walker;
	
	public void translate(Pair<Class, Operation> pair, String code){
		stream = new ANTLRStringStream(code);
        lexer = new BBLLexer(stream);
        tokens = new CommonTokenStream(lexer);
        parser = new BBLParser(tokens);
        try {
			tree  = (CommonTree) parser.rule().getTree();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
		
		/*DOTTreeGenerator gen = new DOTTreeGenerator();
        StringTemplate st = gen.toDOT(tree);
        System.out.println(st);*/
		
        nodes = new CommonTreeNodeStream(tree);
        walker = new BBLTreeWalker(nodes);
        
        ModelHelper.setOperationParameters((List<Parameter>) pair.getOperation().getOwnedParameterList());
        ModelHelper.setClassOperationPair(pair);
        
        try {
			walker.rule();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
	}
}
