package eu.redseeds.sc.query.engine;

import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;


public interface RequirementMatch extends Comparable<RequirementMatch> {

	
	/**
	 * @return the current requirement of this pair
	 */
	public Requirement getCurrentRequirement();
	
	/** 
	 * @return the past requirement of this pair
	 */
	public Requirement getPastRequirement();
	
	/**
	 * @return the similarity of both requirements as a double
	 * value in the intervall [0, 1]
	 */
	public double getSimilarityValue();
	
}
