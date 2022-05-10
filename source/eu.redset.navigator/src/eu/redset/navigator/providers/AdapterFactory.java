  package eu.redset.navigator.providers;

import org.eclipse.core.resources.IProject;

import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;



public class AdapterFactory {
	
	public static IProvider adapt(Object obj, java.lang.Object type) {
		if(obj instanceof IProject) {
			return new IProjectContentLabelProvider();
		}
		
		else if(obj instanceof TestSpecification) {
			return new TestSpecificationContentLabelProvider();
		}
		else if(obj instanceof TestPackage) {
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof UseCaseTest) {
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof UseCaseTestScenario){
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof TestScenario){
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof TestCase){
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof Notion){
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof DomainStatement){
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof NFTest){
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof GUITest){
			return new TestPackageContentLabelProvider();
		}
		
		else if(obj instanceof BLTest){
			return new TestPackageContentLabelProvider();
		}
		/*
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
//		else if(obj instanceof IFile) {
//			return new FolderFileContentLabelProvider();
//		}
		else if(obj instanceof IResource) {
			return new FolderFileContentLabelProvider();
		}
//		else if(obj instanceof IResource[]) {
//			return new FolderFileContentLabelProvider();
//		}
		else {*/
			return new GenericContentLabelProvider();
		//}
	}

}
