package eu.redseeds.engine.navigator.providers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseArtifact;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.ComponentDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;

public class AdapterFactory {
	
	public static IProvider adapt(Object obj, java.lang.Object type) {
		if(obj instanceof IProject) {
			return new IProjectContentLabelProvider();
		}
		else if(obj instanceof SoftwareCaseDTO) {
			return new SoftwareCaseContentLabelProvider();
		}
		else if(obj instanceof ClipboardDTO) {
			return new SoftwareCaseContentLabelProvider();
		}
		else if(obj instanceof SoftwareCaseArtifact) {
			return new SoftwareCaseArtifactContentLabelProvider();
		}
		else if(obj instanceof RequirementsSpecificationDTO) {
			return new RequirementsSpecificationContentLabelProvider();
		}
		else if(obj instanceof RequirementsPackageDTO) {
			return new RequirementsSpecificationContentLabelProvider();
		}
		else if(obj instanceof RequirementDTO) {
			return new RequirementsSpecificationContentLabelProvider();
		}
		else if(obj instanceof UseCaseDTO) {
			return new RequirementsSpecificationContentLabelProvider();
		}
		else if(obj instanceof ConstrainedLanguageScenarioDTO) {
			return new RequirementsSpecificationContentLabelProvider();
		}
		else if(obj instanceof DomainSpecificationDTO) {
			return new DomainSpecificationContentLabelProvider();
		}
		else if(obj instanceof ActorsPackageDTO) {
			return new DomainSpecificationContentLabelProvider();
		}
		else if(obj instanceof NotionsPackageDTO) {
			return new DomainSpecificationContentLabelProvider();
		}
		else if(obj instanceof SystemElementsPackageDTO) {
			return new DomainSpecificationContentLabelProvider();
		}
		else if(obj instanceof ActorDTO) {
			return new DomainSpecificationContentLabelProvider();
		}
		else if(obj instanceof NotionDTO) {
			return new DomainSpecificationContentLabelProvider();
		}
		else if(obj instanceof SystemElementDTO) {
			return new DomainSpecificationContentLabelProvider();
		}
		else if(obj instanceof DomainStatementDTO) {
			return new DomainSpecificationContentLabelProvider();
		}
		else if(obj instanceof PackageDTO) {
			return new SDSLModelContentLabelProvider();
		}
		else if(obj instanceof eu.redseeds.scl.model.sdsl.ActorDTO) {
			return new SDSLModelContentLabelProvider();
		}
		else if(obj instanceof ClassDTO) {
			return new SDSLModelContentLabelProvider();
		}
		else if(obj instanceof InterfaceDTO) {
			return new SDSLModelContentLabelProvider();
		}
		else if(obj instanceof ComponentDTO) {
			return new SDSLModelContentLabelProvider();
		}
		else if(obj instanceof OperationDTO) {
			return new SDSLModelContentLabelProvider();
		}
		else if(obj instanceof TermDTO) {
			return new TerminologySimpleContentLabelProvider();
		}
//		else if(obj instanceof IFolder) {
//			return new FolderFileContentLabelProvider();
//		}
		else if(obj instanceof IFile && ("model.rsldl".equals(((IFile) obj).getName()) || "rsldl_diagram".equalsIgnoreCase(((IFile) obj).getFileExtension()))) {
			return new ExtendedDomainSpecificationContentLabelProvider();
		}
//		else if(obj instanceof IFile) {
//		return new FolderFileContentLabelProvider();
//		}
		else if(obj instanceof IResource) {
			return new FolderFileContentLabelProvider();
		}
//		else if(obj instanceof IResource[]) {
//			return new FolderFileContentLabelProvider();
//		}
		else {
			return new GenericContentLabelProvider();
		}
	}

}
