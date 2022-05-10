package eu.redseeds.owl.tests;

public class OWLSimilarityExperimentResultTuple {
	
	private String name;
	private double similarity;
	
//	public OWLSimilarityExperimentResultTuple(String n, double sim){
//		name = n;
//		similarity = sim;
//	}
	
	public OWLSimilarityExperimentResultTuple(OWLSimilarityExperimentResult r, boolean c){
		name = r.getCaseName();
		if(c) similarity = r.getClassifiedSimilarity();
		else  similarity = r.getUnclassifiedSimilarity();
	}
	
	public String getName(){
		return name;
	}
	public double getSimilarity(){
		return similarity;
	}

}
