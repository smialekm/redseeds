package eu.redseeds.rsldl.engine.structure;

public interface IDLSymbolicEngine {
	
	public String[] solve(String formula, String variable);
    
    public boolean canSolve(String formula, String variable);
	
}
