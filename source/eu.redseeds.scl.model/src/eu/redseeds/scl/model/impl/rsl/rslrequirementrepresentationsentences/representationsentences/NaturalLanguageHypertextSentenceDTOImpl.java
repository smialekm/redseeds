package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentationsentences.representationsentences;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceImpl;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;

public class NaturalLanguageHypertextSentenceDTOImpl extends
		NaturalLanguageHypertextSentenceImpl implements
		NaturalLanguageHypertextSentenceDTO {

	public NaturalLanguageHypertextSentenceDTOImpl(int id, Graph g) {
		super(id, g);
	}

}
