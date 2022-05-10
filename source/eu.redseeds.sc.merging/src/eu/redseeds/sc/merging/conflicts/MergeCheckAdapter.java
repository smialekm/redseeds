package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;

/**
 * Adapts merge check class for specified SCL element
 *
 */
public class MergeCheckAdapter {
	
	/**
	 * Adapts merge check class for specified SCL element
	 * @param element
	 * SCL elements from clipboard
	 * @return
	 */
	public static IMergeCheck adapt(Object element) {
		if(element instanceof UseCaseDTO) {
			return new MergeCheckUseCase();
		}
		if(element instanceof RequirementDTO) {
			return new MergeCheckRequirement();
		}
		if(element instanceof RequirementsPackageDTO) {
			return new MergeCheckReqPackage();
		}
		if(element instanceof ActorDTO) {
			return new MergeCheckActor();
		}
		if(element instanceof ActorsPackageDTO) {
			return new MergeCheckActorsPackage();
		}
		if(element instanceof NotionDTO) {
			return new MergeCheckNotion();
		}
		if(element instanceof NotionsPackageDTO) {
			return new MergeCheckNotionsPackage();
		}
		if(element instanceof SystemElementDTO) {
			return new MergeCheckSystemElement();
		}
		if(element instanceof SystemElementsPackageDTO) {
			return new MergeCheckSystemElementsPackage();
		}
		if(element instanceof DomainStatementDTO) {
			return new MergeCheckDomainStatement();
		}
		if(element instanceof PackageDTO) {
			return new MergeCheckUMLPackage();
		}
		if(element instanceof TermDTO) {
			return new MergeCheckTerm();
		}
		return new MergeCheckGenericObject();
	}

}
