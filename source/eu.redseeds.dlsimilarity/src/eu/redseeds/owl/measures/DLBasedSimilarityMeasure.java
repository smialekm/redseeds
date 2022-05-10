package eu.redseeds.owl.measures;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFResource;

/**
 * This interface defines the methods for the DL-based similarity
 * measure, i.e. comparing and retrieving software cases. 
 * 
 * @author Thorsten Krebs
 */
public interface DLBasedSimilarityMeasure {

	/**
	 * Compares a past software case (or any other element) to a query
	 * software case (or any other element).
	 * 
	 * @param class1 The former software case (or any other element) that
	 *               is represented by an OWL class.
	 * @param individual The query software case (or any other element)
	 *                   that is represented by an OWL instance
	 * @return A value between 0 and 1. The smaller the value, the less
	 *         similar and the larger the value, the more similar the two
	 *         software cases (or other elements) are. The value 1 denotes
	 *         equality (with respect to the similarity measure).
	 */
	public abstract double compare(OWLNamedClass class1, OWLIndividual individual);

	/**
	 * Retrieves the one past software case out of all known software cases
	 * (i.e. from the TBox) that is the most similar to given individual
	 * software case.  
	 * 
	 * @param individual The query software case that is represented by an
	 *                   OWL instance.
	 * @return The most similar past software case that is represented by
	 *         an OWL class.
	 */
	public abstract OWLNamedClass retrieve(RDFResource queryResource);
}
