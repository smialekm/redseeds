package eu.redseeds.java.transformation.engine.impl;

import eu.redseeds.java.parser.elements.NotionsParser;
import eu.redseeds.java.parser.initial.InitialParser;
import eu.redseeds.java.transformation.engine.interfaces.ITransformationExecution;

public class TransformationExecutionImpl implements ITransformationExecution {
	
	private InitialParser appParser;
	private NotionsParser notionsParser;
	
	public TransformationExecutionImpl() {
		appParser = new InitialParser();
		notionsParser = new NotionsParser();
	}
	
	@Override
	public void execute() {
		appParser.parse();
		notionsParser.parse();
		
	}

}
