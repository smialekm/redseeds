package eu.redseeds.scl.measures;

import de.uni_koblenz.jgwnl.info.SynonymInfo;


import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.VerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Verb;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.wrapper.SoftwareCaseLoader;
import eu.redseeds.wrapper.WordNetQuery;

public class WNSim2 implements SCLBasedSimilarityMeasure {

	private WordNetQuery wn;
	
	public WNSim2 (WordNetQuery wn) {
		this.wn = wn;
	}
	
	public double compare(SCLElement elem1, SCLElement elem2) {
		if (elem1 instanceof Verb)
			return compare((Verb)elem1, elem2);
		return 0;
	}
	
	public double compare(Verb verb, SCLElement scl) {
		// According to RSL Meta Model there is exactly one "name" / "object"
		SCLElement elem = null;
		if (scl instanceof DomainStatement) {
			DomainStatement statement = (DomainStatement)scl;
			elem = statement.getNameList().get(0);
			// special case: the DomainStatement does not contain a Verb
			if (elem instanceof NounPhrase)
				return 0;
		} else if (scl instanceof SimpleVerbPhrase) {
			elem = (SimpleVerbPhrase)scl;
		} else if (scl instanceof ComplexVerbPhrase) {
			ComplexVerbPhrase cvp = (ComplexVerbPhrase)scl;
			// TODO how many can there be!?
			SimpleVerbPhrase svp = cvp.getSimpleVerbPhraseList().get(0);
			elem = svp.getObjectList().get(0);
		} else System.err.println("SCLElement is none of DomainStatement / SimpleVerbPhrase / ComplexVerbPhrase -- " + scl);
		if (elem instanceof VerbPhrase) {
			VerbPhrase vp = (VerbPhrase)elem;
			Verb verb2 = SoftwareCaseLoader.getVerbFromVerbPhrase(vp);
			SynonymInfo verb1SynonymInfo = wn.retrieveSynonym(verb.getSynonymUid());
			SynonymInfo verb2SynonymInfo = wn.retrieveSynonym(verb2.getSynonymUid());
			return wn.wordnetSimilarity(verb1SynonymInfo, verb2SynonymInfo);
			// TODO DL-based similarity? ;-)
		} else System.err.println("SCLElement is not of type VerbPhrase!? -- " + elem);
		return 0;
	}
}
