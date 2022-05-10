package eu.redseeds.scl.measures;

import eu.redseeds.scl.sclkernel.SCLElement;

public interface SCLBasedSimilarityMeasure {

	public abstract double compare(SCLElement elem1, SCLElement elem2);
}
