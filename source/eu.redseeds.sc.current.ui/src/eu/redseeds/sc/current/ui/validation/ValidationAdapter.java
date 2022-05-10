package eu.redseeds.sc.current.ui.validation;

import org.eclipse.core.resources.IProject;

import eu.redseeds.sc.current.ui.validation.validators.ActorValidator;
import eu.redseeds.sc.current.ui.validation.validators.ActorsPackageValidator;
import eu.redseeds.sc.current.ui.validation.validators.ArchitectureValidator;
import eu.redseeds.sc.current.ui.validation.validators.ClipboardValidator;
import eu.redseeds.sc.current.ui.validation.validators.ConditionSentenceValidator;
import eu.redseeds.sc.current.ui.validation.validators.ConstrainedLanguageScenarioValidator;
import eu.redseeds.sc.current.ui.validation.validators.DetailedDesignValidator;
import eu.redseeds.sc.current.ui.validation.validators.DomainElementRelationshipValidator;
import eu.redseeds.sc.current.ui.validation.validators.DomainSpecificationValidator;
import eu.redseeds.sc.current.ui.validation.validators.DomainStatementValidator;
import eu.redseeds.sc.current.ui.validation.validators.GenericValidator;
import eu.redseeds.sc.current.ui.validation.validators.InvocationSentenceValidator;
import eu.redseeds.sc.current.ui.validation.validators.NotionSpecialisationValidator;
import eu.redseeds.sc.current.ui.validation.validators.NotionValidator;
import eu.redseeds.sc.current.ui.validation.validators.NotionsPackageValidator;
import eu.redseeds.sc.current.ui.validation.validators.PostconditionSentenceValidator;
import eu.redseeds.sc.current.ui.validation.validators.PreconditionSentenceValidator;
import eu.redseeds.sc.current.ui.validation.validators.RejoinSentenceValidator;
import eu.redseeds.sc.current.ui.validation.validators.RequirementValidator;
import eu.redseeds.sc.current.ui.validation.validators.RequirementsPackageValidator;
import eu.redseeds.sc.current.ui.validation.validators.RequirementsSpecificationValidator;
import eu.redseeds.sc.current.ui.validation.validators.SCProjectValidator;
import eu.redseeds.sc.current.ui.validation.validators.SVOSentenceValidator;
import eu.redseeds.sc.current.ui.validation.validators.SoftwareCaseValidator;
import eu.redseeds.sc.current.ui.validation.validators.SystemElementValidator;
import eu.redseeds.sc.current.ui.validation.validators.SystemElementsPackageValdiator;
import eu.redseeds.sc.current.ui.validation.validators.TermValidator;
import eu.redseeds.sc.current.ui.validation.validators.UseCaseValidator;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.ComponentDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;

/**
 * Prepares a SCL validator accordingly to the type of element supplied
 *
 */
public class ValidationAdapter {
	
	public static IValidate getValidator(Object sclElement) {
		if(sclElement instanceof IProject) {
			return new SCProjectValidator();
		}
		else if(sclElement instanceof SoftwareCaseDTO) {
			return new SoftwareCaseValidator();
		}
		else if(sclElement instanceof ClipboardDTO) {
			return new ClipboardValidator();
		}
		else if(sclElement instanceof RequirementsSpecificationDTO) {
			return new RequirementsSpecificationValidator();
		}
		else if(sclElement instanceof ArchitecturalModelDTO) {
			return new ArchitectureValidator();
		}
		else if(sclElement instanceof DetailedDesignModelDTO) {
			return new DetailedDesignValidator();
		}
		else if(sclElement instanceof RequirementsPackageDTO) {
			return new RequirementsPackageValidator();
		}
		else if(sclElement instanceof RequirementDTO && !(sclElement instanceof UseCaseDTO)) {
			return new RequirementValidator();
		}
		else if(sclElement instanceof UseCaseDTO) {
			return new UseCaseValidator();
		}
		else if(sclElement instanceof ConstrainedLanguageScenarioDTO) {
			return new ConstrainedLanguageScenarioValidator();
		}
		else if(sclElement instanceof InvocationSentenceDTO) {
			return new InvocationSentenceValidator();
		}
		else if(sclElement instanceof ConditionSentenceDTO) {
			return new ConditionSentenceValidator();
		}
		else if(sclElement instanceof PreconditionSentenceDTO) {
			return new PreconditionSentenceValidator();
		}
		else if(sclElement instanceof PostconditionSentenceDTO) {
			return new PostconditionSentenceValidator();
		}
		else if(sclElement instanceof SVOSentenceDTO) {
			return new SVOSentenceValidator();
		}
		else if(sclElement instanceof RejoinSentenceDTO) {
			return new RejoinSentenceValidator();
		}
		else if(sclElement instanceof DomainSpecificationDTO) {
			return new DomainSpecificationValidator();
		}
		else if(sclElement instanceof ActorsPackageDTO) {
			return new ActorsPackageValidator();
		}
		else if(sclElement instanceof NotionsPackageDTO) {
			return new NotionsPackageValidator();
		}
		else if(sclElement instanceof SystemElementsPackageDTO) {
			return new SystemElementsPackageValdiator();
		}
		else if(sclElement instanceof ActorDTO) {
			return new ActorValidator();
		}
		else if(sclElement instanceof NotionDTO) {
			return new NotionValidator();
		}
		else if(sclElement instanceof SystemElementDTO) {
			return new  SystemElementValidator();
		}
		else if(sclElement instanceof DomainStatementDTO) {
			return new DomainStatementValidator();
		}
		else if(sclElement instanceof PackageDTO) {
			return new GenericValidator();
		}
		else if(sclElement instanceof ClassDTO) {
			return new GenericValidator();
		}
		else if(sclElement instanceof InterfaceDTO) {
			return new GenericValidator();
		}
		else if(sclElement instanceof ComponentDTO) {
			return new GenericValidator();
		}
		else if(sclElement instanceof TermDTO) {
			return new TermValidator();
		}
		else if(sclElement instanceof NotionSpecialisationDTO) {
			return new NotionSpecialisationValidator();
		}
		else if(sclElement instanceof DomainElementRelationshipDTO) {
			return new DomainElementRelationshipValidator();
		}
		else {
			return new GenericValidator();
		}
	}

}
