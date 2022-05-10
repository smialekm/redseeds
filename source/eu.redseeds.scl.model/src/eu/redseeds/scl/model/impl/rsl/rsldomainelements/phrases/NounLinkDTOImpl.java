package eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.phrases.NounLinkImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;

public class NounLinkDTOImpl extends NounLinkImpl implements NounLinkDTO {
	
	private String newValue;

	public NounLinkDTOImpl(int id, Graph g) {
		super(id, g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getNewValue() {
		return newValue;
	}

	@Override
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	
}
