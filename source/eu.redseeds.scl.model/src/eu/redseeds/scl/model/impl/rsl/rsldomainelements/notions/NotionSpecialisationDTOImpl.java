package eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.notions.NotionSpecialisationImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;

public class NotionSpecialisationDTOImpl extends NotionSpecialisationImpl
		implements NotionSpecialisationDTO {

	public NotionSpecialisationDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public NotionDTO getGeneralNotion() {
		return getTargetList().isEmpty() ? null : (NotionDTO)getTargetList().get(0);
	}

	@Override
	public NotionDTO getSpecialisedNotion() {
		return getSourceList().isEmpty() ? null : (NotionDTO)getSourceList().get(0);
	}

	@Override
	public void setGeneralNotion(NotionDTO notion) {
		addTarget((Notion)notion);
	}

	@Override
	public void setSpecialisedNotion(NotionDTO notion) {
		addSource((Notion)notion);
	}

	@Override
	public void reverse() {
		NotionDTO oldGeneral = getGeneralNotion();
		NotionDTO oldSpecialised = getSpecialisedNotion();
		removeTarget((Notion)oldGeneral);
		setGeneralNotion(getSpecialisedNotion());
		removeSource((Notion)oldSpecialised);
		setSpecialisedNotion(oldGeneral);
	}

}
