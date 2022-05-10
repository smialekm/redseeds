package eu.redseeds.scl.model.impl.rsl.rsldomainelements.domainelements;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.domainelementrepresentations.DomainElementRepresentationImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRepresentationDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;

public class DomainElementRepresentationDTOImpl extends DomainElementRepresentationImpl implements DomainElementRepresentationDTO {

	public DomainElementRepresentationDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addSentence(NaturalLanguageHypertextSentenceDTO sentence) {
		addSentence((NaturalLanguageHypertextSentence)sentence);
	}

}
