package eu.redseeds.sc.query.comparefunctions;

import java.rmi.RemoteException;

import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgwnl.exceptions.JGWNLException;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;

/**
 * This function compares two RSL terms on the basis of their linked JGWNL word
 * 
 * @author dbildh
 * 
 */
public class TermSimilarityFunction extends AbstractCompareFunction  {

	private static String FUNCTION_NAME = "TermSimilarityFunction";

	
	public double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2) {
		long startTime = System.nanoTime();
		double result = compareNodes((Term) vertex1, (Term) vertex2);
		if (DEBUG) {
			recordComputationTime(FUNCTION_NAME, startTime, System.nanoTime());
		}
		return result;
	}


	public static int successfullComparisons = 0;

	public static int failedComparisons = 0;

	private final double compareNodes(Term a, Term b) {
		if (a.getClass() != b.getClass()) {
			throw new RuntimeException(
					"Two terms with different classes may not be compared");
		} else {
			if (a.getSynonymUid() == b.getSynonymUid()) {
				return 1.0f;
			}
			if ((a.getSynonymUid() == 0) || (b.getSynonymUid() == 0)) {
				return 0;
			}
			try {
				double result = RemoteJGWNL.getInstance().similarity(
						a.getSynonymUid(), b.getSynonymUid());
				successfullComparisons++;
				return result;
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (JGWNLException e) {
				failedComparisons++;
				// e.printStackTrace();
			}
			return -1;
		}
	}

}
