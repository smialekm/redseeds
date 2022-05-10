package eu.redseeds.scl.measures;

import de.uni_koblenz.jgwnl.info.SynonymInfo;


import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.wrapper.SoftwareCaseLoader;
import eu.redseeds.wrapper.WordNetQuery;

public class WNSim1 implements SCLBasedSimilarityMeasure {

	private WordNetQuery wn;
	
	public WNSim1 (WordNetQuery wn) {
		this.wn = wn;
	}
	
	public double compare(SCLElement elem1, SCLElement elem2) {
		if (elem1 instanceof Noun)
			return compare((Noun)elem1, elem2);
		return 0;
	}
	
	public double compare(Noun noun, SCLElement scl) {
		// According to RSL Meta Model there is exactly one "name"
		SCLElement elem = null;
		if (scl instanceof Actor) {
			Actor actor = (Actor)scl;
			elem = actor.getNameList().get(0);
		} else if (scl instanceof SystemElement) {
			SystemElement sysElem = (SystemElement)scl;
			elem = sysElem.getNameList().get(0);
		} else if (scl instanceof Notion) {
			Notion notion = (Notion)scl;
			elem = notion.getNameList().get(0);
		} else if (scl instanceof DomainStatement) {
			DomainStatement statement = (DomainStatement) scl;
			elem = statement.getNameList().get(0);
		} else if (scl instanceof NounPhrase) {
			elem = scl;
		} else if (scl instanceof SimpleVerbPhrase) {
			SimpleVerbPhrase svp = (SimpleVerbPhrase)scl;
			elem = svp.getObjectList().get(0);
		} else if (scl instanceof ComplexVerbPhrase) {
			ComplexVerbPhrase cvp = (ComplexVerbPhrase)scl;
			// TODO how many can there be!?
			SimpleVerbPhrase svp = cvp.getSimpleVerbPhraseList().get(0);
			elem = svp.getObjectList().get(0);
		} else System.err.println("SCLElement is none of Actor / SystemElement / Notion / DomainStatement / NounPhrase / SimpleVerbPhrase / ComplexVerbPhrase -- " + scl);
		if (elem instanceof NounPhrase) {
			NounPhrase np = (NounPhrase)elem;
			Noun noun2 = SoftwareCaseLoader.getNounFromNounPhrase(np);
			SynonymInfo noun1SynonymInfo = wn.retrieveSynonym(noun.getSynonymUid());
			SynonymInfo noun2SynonymInfo = wn.retrieveSynonym(noun2.getSynonymUid());
			return wn.wordnetSimilarity(noun1SynonymInfo, noun2SynonymInfo);
			// TODO DL-based similarity? ;-)
		} else System.err.println("SCLElement is not of type NounPhrase!? -- " + elem);
		return 0;
	}
}
