package eu.redseeds.owl.tests;

import java.util.Comparator;

public class OWLSimilarityExperimentResultComparatorClassified implements Comparator<OWLSimilarityExperimentResult>{
	
	@Override
	public int compare(OWLSimilarityExperimentResult r1, OWLSimilarityExperimentResult r2){
		double sim1 = r1.getClassifiedSimilarity();
		double sim2 = r2.getClassifiedSimilarity();
		
		if(sim1 > sim2) return +1;
		else if(sim1 < sim2) return -1;
		else return 0;
	}
}
