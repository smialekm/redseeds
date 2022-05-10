package eu.redseeds.scl.model.impl.sclkernel;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTOImpl;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.sclkernel.DetailedDesignModel;
import eu.redseeds.scl.sclkernel.SoftwareArtifact;
import eu.redseeds.scl.impl.sclkernel.ArchitecturalModelImpl;
import eu.redseeds.scl.impl.sclkernel.SoftwareCaseImpl;


public class SoftwareCaseDTOImpl extends SoftwareCaseImpl implements SoftwareCaseDTO {

	
	public static String SC_PROP_KEY_NAME = "Name";
	public static String SC_PROP_KEY_AUTHOR = "Author";
	public static String SC_PROP_KEY_DESCRIPTION = "Description";
	protected String name;
	
	public SoftwareCaseDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public RequirementsSpecificationDTO getRequirementsSpecificationDTO() {
		for(SoftwareArtifact artifact : super.getArtifactList()) {
			if(artifact instanceof RequirementsSpecificationDTOImpl){
				return (RequirementsSpecificationDTO)artifact;
			}
		}
		return null;
	}
	
	@Override
	public ArchitecturalModelDTO getArchitecturalModelDTO() {
		for(SoftwareArtifact artifact : super.getArtifactList()) {
			if(artifact instanceof ArchitecturalModelImpl){
				return (ArchitecturalModelDTO)artifact;
			}
		}
		return null;
	}	
	
	@Override
	public DetailedDesignModelDTO getDetailedDesignModelDTO() {
		for(SoftwareArtifact artifact : super.getArtifactList()) {
			if(artifact instanceof DetailedDesignModel){
				return (DetailedDesignModelDTO)artifact;
			}
		}
		return null;
	}


	
	
	public String toString(){
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	

}