package eu.remics.alp.impl;


import de.uni_koblenz.jgralab.Graph;
import eu.remics.alp.ALPDTO;
import eu.redseeds.common.Constants;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTOImpl;
import eu.redseeds.scl.model.impl.sdsl.StereotypeDTOImpl;

public class ALPDTOImpl extends RequirementsPackageDTOImpl implements ALPDTO {

	public ALPDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		
		StereotypeDTOImpl stereotype = new StereotypeDTOImpl(arg0, arg1);
		stereotype.setName(Constants.ALP_STEREOTYPE);
		
		super.addStereotype(stereotype);
		
	}


}
