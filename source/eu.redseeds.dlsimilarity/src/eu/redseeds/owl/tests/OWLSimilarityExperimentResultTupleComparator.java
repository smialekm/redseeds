package eu.redseeds.owl.tests;

import java.util.Comparator;

public class OWLSimilarityExperimentResultTupleComparator implements Comparator<OWLSimilarityExperimentResultTuple> {
	
	@Override
	public int compare(OWLSimilarityExperimentResultTuple r1, OWLSimilarityExperimentResultTuple r2){
		double sim1 = r1.getSimilarity();
		double sim2 = r2.getSimilarity();
		
		if(sim1 > sim2) return +1;
		else if(sim1 < sim2) return -1;
		else return 0;
	}

}
