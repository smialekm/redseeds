package eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.impl.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationImpl;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;


public class RequirementsSpecificationDTOImpl extends RequirementsSpecificationImpl implements RequirementsSpecificationDTO {

	public static String REQUIREMENTS_SPECIFICATION_NAME = "Requirements";
	
	public RequirementsSpecificationDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addRequirementsPackageDTO(RequirementsPackageDTO reqPackage) {
		RequirementsPackage r = (RequirementsPackage)reqPackage;
		this.addRequirementsPackage(r);
	}
	@Override
	public List<RequirementsPackageDTO> getRequirementsPackagesDTOList() {
		List<? extends RequirementsPackage> l = super.getRequirementsPackageList();
		List<RequirementsPackageDTO> result = new ArrayList<RequirementsPackageDTO>();
		for(RequirementsPackage rp : l) {
			result.add((RequirementsPackageDTO)rp);
		}
		return result;
	}

	@Override
	public DomainSpecificationDTO getDomainSpecificationDTO() {
		return (DomainSpecificationDTO)super.getDomainSpecificationList().get(0);
	}

	@Override
	public String toString(){
		return REQUIREMENTS_SPECIFICATION_NAME;
	}

	@Override
	public boolean testAttribute(Object target, String name, String value) {
		if (!(target instanceof RequirementsSpecificationDTO))
			return false;
		if ("canInitializeRSLDL".equals(name)){
			IFile edm = DiagramFileHelper.getExtendedDomainModel(SCProjectHelper.getActiveProject());
			if ("true".equals(value) && null==edm && DiagramFileHelper.isEnableRSLDL())
				return true;
			if ("false".equals(value) && (null!=edm || !DiagramFileHelper.isEnableRSLDL()))
				return true;
		}
		if ("RSLDLInitialized".equals(name)){
			IFile edm = DiagramFileHelper.getExtendedDomainModel(SCProjectHelper.getActiveProject());
			if ("true".equals(value) && null!=edm && DiagramFileHelper.isEnableRSLDL())
				return true;
			if ("false".equals(value) && (null==edm || !DiagramFileHelper.isEnableRSLDL()))
				return true;
		}
		if ("RSLDLEnabled".equals(name)){
			if ("true".equals(value) && DiagramFileHelper.isEnableRSLDL())
				return true;
			if ("false".equals(value) && !DiagramFileHelper.isEnableRSLDL())
				return true;
		}
		return false;
	}

}