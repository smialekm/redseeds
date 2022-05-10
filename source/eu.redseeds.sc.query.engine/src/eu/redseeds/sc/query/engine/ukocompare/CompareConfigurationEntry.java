package eu.redseeds.sc.query.engine.ukocompare;

import eu.redseeds.sc.query.comparefunctions.AbstractCompareFunction;


public class CompareConfigurationEntry {
	/* the compare function that should be used */
	AbstractCompareFunction compareFunctionToUse;
	
	/* the weight of this compare function in the similarity of the element this configuration is used for */
	double weight;
	
	CompareConfigurationEntry(AbstractCompareFunction compareFunctionToUse, double weight) {
		this.compareFunctionToUse = compareFunctionToUse;
		this.weight = weight;
	}
	
}