package eu.redseeds.sc.query.engine;

import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;


public interface DomainElementMatch extends Comparable<DomainElementMatch> {

	
	/**
	 * @return the current domain element of this pair
	 */
	public DomainElement getCurrentDomainElement();
	
	/** 
	 * @return the past domain element of this pair
	 */
	public DomainElement getPastDomainElement();
	
	/**
	 * @return the similarity of both requirements as a double
	 * value in the intervall [0, 1]
	 */
	public double getSimilarityValue();
	
}
