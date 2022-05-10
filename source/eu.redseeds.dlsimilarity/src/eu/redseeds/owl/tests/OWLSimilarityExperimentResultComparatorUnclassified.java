package eu.redseeds.owl.tests;

import java.util.Comparator;

public class OWLSimilarityExperimentResultComparatorUnclassified implements Comparator<OWLSimilarityExperimentResult>{
	
	@Override
	public int compare(OWLSimilarityExperimentResult r1, OWLSimilarityExperimentResult r2){
		double sim1 = r1.getUnclassifiedSimilarity();
		double sim2 = r2.getUnclassifiedSimilarity();
		
		if(sim1 > sim2) return +1;
		else if(sim1 < sim2) return -1;
		else return 0;
	}

}
