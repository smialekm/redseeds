package eu.redseeds.scl.model.impl.rsl.rsldomainelements.domainelements;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.actors.ActorsPackageDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionsPackageDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementsPackageDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.ActorsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElementsPackage;
import eu.redseeds.scl.impl.rsl.rsldomainelements.domainelements.DomainSpecificationImpl;

public class DomainSpecificationDTOImpl extends DomainSpecificationImpl implements DomainSpecificationDTO {

	public static String DOMAIN_SPECIFICATION_NAME = "Domain Specification";

	public DomainSpecificationDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ActorsPackageDTO> getActorsPackageDTOList() {
		List<? extends DomainElementsPackage> l = super.getDomainElementsPackageList();
		List<ActorsPackageDTO> result = new ArrayList<ActorsPackageDTO>();
		for(DomainElementsPackage dep : l) {
			if(dep instanceof ActorsPackage) {
				result.add((ActorsPackageDTO)dep);
			}
		}
		return result;
	}

	@Override
	public List<NotionsPackageDTO> getNotionsPackageDTOList() {
		List<? extends DomainElementsPackage> l = super.getDomainElementsPackageList();
		List<NotionsPackageDTO> result = new ArrayList<NotionsPackageDTO>();
		for(DomainElementsPackage dep : l) {
			if(dep instanceof NotionsPackage) {
				result.add((NotionsPackageDTO)dep);
			}
		}
		return result;
	}

	@Override
	public List<SystemElementsPackageDTO> getSystemElementsPackageDTOList() {
		List<? extends DomainElementsPackage> l = super.getDomainElementsPackageList();
		List<SystemElementsPackageDTO> result = new ArrayList<SystemElementsPackageDTO>();
		for(DomainElementsPackage dep : l) {
			if(dep instanceof SystemElementsPackage) {
				result.add((SystemElementsPackageDTO)dep);
			}
		}
		return result;
	}


	@Override
	public String toString(){
		return DOMAIN_SPECIFICATION_NAME;
	}

	@Override
	public void addActorsPackageDTO(ActorsPackageDTO actorsPackage) {
		super.addDomainElementsPackage((ActorsPackageDTOImpl)actorsPackage);

	}

	@Override
	public void addNotionsPackageDTO(NotionsPackageDTO notionsPackage) {
		super.addDomainElementsPackage((NotionsPackageDTOImpl)notionsPackage);
	}

	@Override
	public void addSystemElementsPackageDTO(
			SystemElementsPackageDTO systemElementsPackage) {
		super.addDomainElementsPackage((SystemElementsPackageDTOImpl)systemElementsPackage);

	}

}