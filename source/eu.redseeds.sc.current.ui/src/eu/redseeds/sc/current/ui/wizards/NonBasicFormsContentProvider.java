package eu.redseeds.sc.current.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;

public class NonBasicFormsContentProvider implements IStructuredContentProvider {
	
	protected Object parentObject;
	protected String defaultNewValue;
	List<NounLinkDTO> links = new ArrayList<NounLinkDTO>();

	public NonBasicFormsContentProvider(Object parentObject, String defaultNewValue) {
		super();
		this.parentObject = parentObject;
		this.defaultNewValue = defaultNewValue;
		//TODO - type checking
		if(parentObject instanceof NotionDTO) {
			for(NounLinkDTO link : ((NotionDTO)parentObject).getNonBasicNounLinksValues()) {
				link.setNewValue(defaultNewValue);
				links.add(link);
			}
		}
		if(parentObject instanceof ActorDTO) {
			for(NounLinkDTO link : ((ActorDTO)parentObject).getNonBasicNounLinksValues()) {
				link.setNewValue(defaultNewValue);
				links.add(link);
			}
		}
		if(parentObject instanceof SystemElementDTO) {
			for(NounLinkDTO link : ((SystemElementDTO)parentObject).getNonBasicNounLinksValues()) {
				link.setNewValue(defaultNewValue);
				links.add(link);
			}
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return links.toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	public String getDefaultNewValue() {
		return defaultNewValue;
	}

	public void setDefaultNewValue(String defaultNewValue) {
		this.defaultNewValue = defaultNewValue;
		for(NounLinkDTO link : links) {
			link.setNewValue(defaultNewValue);
		}
	}

	public List<NounLinkDTO> getLinks() {
		return links;
	}

}
