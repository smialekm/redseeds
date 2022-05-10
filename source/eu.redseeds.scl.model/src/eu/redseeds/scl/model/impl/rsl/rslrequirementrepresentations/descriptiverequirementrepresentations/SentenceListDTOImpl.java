package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceListImpl;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceListDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;

public class SentenceListDTOImpl extends SentenceListImpl implements
		SentenceListDTO {

	public SentenceListDTOImpl(int id, Graph g) {
		super(id, g);
	}

	@Override
	public void addSentence(NaturalLanguageHypertextSentenceDTO sentence) {
		this.addSentence((NaturalLanguageHypertextSentence)sentence);
	}

}
