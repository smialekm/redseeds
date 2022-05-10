package eu.redseeds.scl.measures;

import de.uni_koblenz.jgwnl.info.SynonymInfo;


import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Modifier;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.wrapper.SoftwareCaseLoader;
import eu.redseeds.wrapper.WordNetQuery;

public class WNSim3 implements SCLBasedSimilarityMeasure {

	private WordNetQuery wn;
	
	public WNSim3 (WordNetQuery wn) {
		this.wn = wn;
	}
	
	public double compare(SCLElement elem1, SCLElement elem2) {
		if (elem1 instanceof Modifier &&
				elem2 instanceof NounPhrase)
			return compare((Modifier)elem1, (NounPhrase)elem2);
		return 0;
	}
	
	public double compare(Modifier mod, NounPhrase phrase) {
		Modifier mod2 = SoftwareCaseLoader.getModifierFromNounPhrase(phrase);
		SynonymInfo mod1SynonymInfo = wn.retrieveSynonym(mod.getSynonymUid());
		SynonymInfo mod2SynonymInfo = wn.retrieveSynonym(mod2.getSynonymUid());
		return wn.wordnetSimilarity(mod1SynonymInfo, mod2SynonymInfo);
		// TODO DL-based similarity? ;-)
	}
}
