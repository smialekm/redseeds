package eu.redseeds.scl.model.impl.rsl.rsldomainelements.domainelements;

import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.domainelements.DomainElementRelationshipImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.sclkernel.SCLElement;

public class DomainElementRelationshipDTOImpl extends DomainElementRelationshipImpl  implements DomainElementRelationshipDTO {
	
	public DomainElementRelationshipDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getSource() {
		List<? extends SCLElement> sourceList = getSourceList();
		if(sourceList != null) {
			if(!sourceList.isEmpty()) {
				return sourceList.get(0);
			}
		}
		return null;
	}

	@Override
	public Object getTarget() {
		List<? extends SCLElement> targetList = getTargetList();
		if(targetList != null) {
			if(!targetList.isEmpty()) {
				return targetList.get(0);
			}
		}
		return null;
	}

	@Override
	public void setSource(Object elem) {
		if(elem instanceof ActorDTO) {
			addSource((Actor)elem);
		}
		else if(elem instanceof NotionDTO) {
			addSource((Notion)elem);
		}
		if(elem instanceof SystemElementDTO) {
			addSource((SystemElement)elem);
		}
	}

	@Override
	public void setTarget(Object elem) {
		if(elem instanceof ActorDTO) {
			addTarget((Actor)elem);
		}
		else if(elem instanceof NotionDTO) {
			addTarget((Notion)elem);
		}
		if(elem instanceof SystemElementDTO) {
			addTarget((SystemElement)elem);
		}
	}

}
