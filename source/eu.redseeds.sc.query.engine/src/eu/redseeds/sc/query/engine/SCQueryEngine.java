package eu.redseeds.sc.query.engine;

import java.util.List;
import java.util.Set;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

/**
 * Provides an easy to use interface to similarity calculation and slicing of
 * software cases.
 * 
 */
public interface SCQueryEngine {



//	/**
//	 * Compares the two cases with the default similarity calculation method and
//	 * returns the calculated similarity of both cases as a double value in the
//	 * intervall [0,1] where 0 means that the cases aren't similar at all and 1
//	 * means that the two cases are identical. For comparison, the current case
//	 * is restricted to the requirements specified in the parameter 
//	 * <code>requirementsForComparison</code> and the domain elements specified in
//	 * the parameter <code>domainElementsForComparison</code>.
//	 * 
//	 * @param currentCase
//	 *            the first case to compare
//	 * @param pastCase
//	 *            the second case to compare
//	 * @param requirementsForComparison
//	 * 			  a set of requirements from the current case.         
//	 * @param domainElementsForComparison
//	 * 			  a set of domain elements from the current case.     
//	 * @return the similarity between currentCase and pastCase as double value
//	 *         in [0, 1]
//	 */
//	private SimilarityValue compareCases(SCLGraph currentCase, SCLGraph pastCase, Set<Requirement> requirementsForComparison, Set<DomainElement> domainElementsForComparison, ComparisonType comparisonType);


	
	/**
	 * Finds similar Software Cases according to given similarity level.
	 * Similarity level is a double value in the interval [0,1], where 0 means
	 * that the cases aren't similar at all and 1 means that two cases are
	 * identical.
	 * 
	 * @param baseSC
	 *            SC to compare
	 * @param similarityLevel
	 *            criteria for finding Software Cases as double value in [0,1]
	 * @param requirementsToUse a set of requirement that should be used for comparison. 
	 *        Only requirements in this set will be compared
	 * @param domainElementsToUse a set of domain elements that should be used for comparison.
	 *        Only domain elements in this set will be compared
	 * @param comparisonType the type of comparison to use
	 *        DOMAIN_ONLY: the cases will be compared on the basis of their domain elements only       
	 *        REQUIREMENTS_ONLY: the cases will be compared on the basis of their requirements only
	 *        REQUIREMENTS_AND_DOMAIN: the cases will be compared on the basis of their requirement and domain elements                            
	 * @return list of found Software Cases
	 */
	public List<SimilarSCProject> findCases(
			SCProject baseSC,
			double similarityLevel,
			ComparisonType comparisonType,
			Set<Requirement> requirementsToUse,
			Set<DomainElement> domainElementsToUse
		);

}
