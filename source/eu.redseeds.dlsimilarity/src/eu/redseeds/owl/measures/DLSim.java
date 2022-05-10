package eu.redseeds.owl.measures;


import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import eu.redseeds.owl.reasoning.OWLReasoner;

/**
 * This class implements the methods for the DL-based similarity
 * measure (see also <code>DLBasedSimilarityMeasure</code>).
 * <br>
 * <br>
 * Basically, this class uses the methods from the class
 * <code>OWLReasoner</code> to compare or retrieve software cases. 
 * 
 * @author Thorsten Krebs
 */
public class DLSim implements DLBasedSimilarityMeasure {

	/**
	 * An instance of the <code>OWLReasoner</code> class.
	 */
	private OWLReasoner reasoner;

	/**
	 * Constructor. Sets the reasoner instance.
	 * 
	 * @param reasoner An instance of the <code>OWLReasoner</code>
	 *                 class.
	 */
	public DLSim(OWLReasoner reasoner) {
		this.reasoner = reasoner;
	}
	
	public double compare(OWLNamedClass class1, OWLNamedClass class2){
		return reasoner.compare(class1, class2);
	}
	
	
	public double compare(OWLNamedClass class1, OWLIndividual individual) {
		// Get inferred types...
		OWLNamedClass class2 = OWLReasoner.getOWLClassOf(individual);
		// ...and compare!
		return reasoner.compare(class1, class2);
	}

	/**
	 * retrieve function for the case that the query is transformed into 
	 * an OWLIndividual
	 */
	public OWLNamedClass retrieve(RDFResource queryResource) {
		return reasoner.retrieve(queryResource);
	}	
}
