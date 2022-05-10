package eu.redseeds.sc.query.engine;

import java.util.Map;
import java.util.Set;

import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

/**
 * encapsulates the result of the similarity calculation between two software
 * cases, named as currentCase and pastCase. Beside a double value that
 * represents the overall similarity of the two cases, also other information as
 * for instance the set of matched requirements is accessible by this class.
 *
 */
public interface SimilarityValue {
	
	/**
	 * The threshold that is used to determiner if two requirements are matched or not
	 */
	public static final double MATCHING_THRESHOLD = 0.0;

	/**
	 * retrieves the similarity of the two cases as a double value in the
	 * interval [0,1] where 0 is no similarity and 1 is maximal similarity
	 *
	 * @return a double value in the interval [0,1] representing the similarity
	 *         of the two compared cases
	 */
	public double getSimilarityValue();

	/**
	 * retrieves the current case used in the comparison
	 *
	 * @return the graph representing the current case that was compared
	 */
	public SCLGraph getCurrentCase();

	/**
	 * retrieves the past case used in the comparison
	 *
	 * @return the graph representing the past case that was compared
	 */
	public SCLGraph getPastCase();
	
	
	/**
	 * Returns the comparison type used to compare the cases
	 */
	public ComparisonType getComparisonType(); 

	/**
	 * retrieves a set of pairs of the matched requirements of both cases. The
	 * first element of each pair is a elements of the current case and the
	 * second element is its partner in the past case.
	 */
	public Set<RequirementMatch> getRequirementsPairs();
	
	/**
	 * retrieves a set of pairs of the matched domain elements of both cases. The
	 * first element of each pair is a elements of the current case and the
	 * second element is its partner in the past case.
	 */
	public Set<DomainElementMatch> getDomainElementPairs();


	/**
	 * @return the number of requirements in the current case
	 */
	public int getRequirementCountInCurrentCase();

	/**
	 * @return the number of domain elements in the past case
	 */
	public int getDomainElementCountInPastCase();
	
	/**
	 * @return the number of domain elements in the current case
	 */
	public int getDomainElementCountInCurrentCase();

	/**
	 * @return the number of requirements in the past case
	 */
	public int getRequirementCountInPastCase();

	/**
	 * @return the similarity of the given current requirement and the given
	 *         past one
	 * @throw SimilarityException if currentRequirement is not part of the current
	 *        software case or if pastRequirement is not part of the past
	 *        software case
	 */
	public double getSimilarity(Requirement currentRequirement,
			Requirement pastRequirement) throws SimilarityException;
	
	
	/**
	 * @return the similarity of the given current requirement and the given
	 *         past one
	 * @throw SimilarityException if currentRequirement is not part of the current
	 *        software case or if pastRequirement is not part of the past
	 *        software case
	 */
	public double getSimilarity(DomainElement currentDomainElement,
			DomainElement pastDomainElement) throws SimilarityException;
	
	/**
	 * Retrieves a map of requirements and their similarity to the given
	 * requirement. If the given requirement is part of the current case, the
	 * map of similar requirements of the past case together with the similarity
	 * values will be returned, for a requirement of the past case the map of
	 * similar requirements in the current case will be returned.
	 *
	 * @param requirement
	 *         a requirement of the current or past case
	 * @return a sorted map that contains all requirements of the other case and
	 *         their similarity to the given requirement
	 * @throw SimilarityException if <code>requirement</code> is neither part
	 *        of the current nor of the past case
	 */
	public Map<Requirement, Double> getSimilarRequirements(
			Requirement requirement) throws SimilarityException;
	
	/**
	 * Retrieves a map of domain elements and their similarity to the given
	 * requirement. If the given requirement is part of the current case, the
	 * map of similar requirements of the past case together with the similarity
	 * values will be returned, for a requirement of the past case the map of
	 * similar requirements in the current case will be returned.
	 *
	 * @param requirement
	 *         a requirement of the current or past case
	 * @return a sorted map that contains all requirements of the other case and
	 *         their similarity to the given requirement
	 * @throw SimilarityException if <code>requirement</code> is neither part
	 *        of the current nor of the past case
	 */
	public Map<DomainElement, Double> getSimilarDomainElements(
			DomainElement domainElement) throws SimilarityException;

	/**
	 * @return the set of requirements in the past case that have a
	 * partner in the current case
	 */
	public Set<Requirement> getMatchedRequirementsInPastCase();
	
	/**
	 * @return the set of domain elements in the past case that have a
	 * partner in the current case
	 */
	public Set<DomainElement> getMatchedDomainElementsInPastCase();
	
	

}
