package eu.redseeds.sc.merging.resolvers;

import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
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
 * Adapts merge resolver class for specified SCL element
 *
 */
public class MergeResolversAdapter {

	public static IMergeResolver adapt(MergeConflictObject conflict) {
		if (conflict.getCurrentObject() instanceof UseCaseDTO) {
			return new MergeResolverUseCase();
		}
		if (conflict.getCurrentObject() instanceof RequirementDTO) {
			return new MergeResolverRequirement();
		}
		if (conflict.getCurrentObject() instanceof RequirementsPackageDTO) {
			return new MergeResolverReqPackage();
		}
		if (conflict.getCurrentObject() instanceof ActorDTO) {
			return new MergeResolverActor();
		}
		if (conflict.getCurrentObject() instanceof ActorsPackageDTO) {
			return new MergeResolverActorsPackage();
		}
		if (conflict.getCurrentObject() instanceof NotionDTO) {
			return new MergeResolverNotion();
		}
		if (conflict.getCurrentObject() instanceof NotionsPackageDTO) {
			return new MergeResolverNotionsPackage();
		}
		if (conflict.getCurrentObject() instanceof SystemElementDTO) {
			return new MergeResolverSystemElement();
		}
		if (conflict.getCurrentObject() instanceof SystemElementsPackageDTO) {
			return new MergeResolverSystemElementsPackage();
		}
		if (conflict.getCurrentObject() instanceof DomainStatementDTO) {
			return new MergeResolverDomainStatement();
		}
		if (conflict.getCurrentObject() instanceof TermDTO) {
			return new MergeResolverTerm();
		}
		if (conflict.getCurrentObject() instanceof PackageDTO) {
			return new MergeResolverUMLPackage();
		}
		return new MergeResolverGeneric();
	}
	
}
