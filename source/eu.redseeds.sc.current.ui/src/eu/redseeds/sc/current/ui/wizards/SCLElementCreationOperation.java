package eu.redseeds.sc.current.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainSpecification;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsSpecification;
import eu.redseeds.scl.sclkernel.ArchitecturalModel;
import eu.redseeds.scl.sclkernel.Clipboard;
import eu.redseeds.scl.sclkernel.DetailedDesignModel;
import eu.remics.recovery.model.NotionKindHelper;



public class SCLElementCreationOperation extends WorkspaceModifyOperation{

	private java.lang.Object parent, type;
	private String name;
	private IProject eclipseProject;
	private SCLElementWizardPage page;
	
	public SCLElementCreationOperation(IProject eclipseProject, java.lang.Object parent, java.lang.Object type, SCLElementWizardPage page) {
		this.parent = parent;
		this.type = type;
		this.name = page.getSCLElementName();
		this.page = page;
		this.eclipseProject = eclipseProject;
	}
	
	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		
		// start task
		monitor.beginTask(
				ResourceMessages.NewClipboardCreationOperation_creating, 2);
		monitor.subTask(ResourceMessages.NewClipboardCreationOperation_project);
		createSCLElement();
		monitor.worked(1);		
	}
	
	private void createSCLElement() {
		
		SCProject scProject = SCProjectContainer.instance().getSCProject(eclipseProject);
		
		if(type == ClipboardDTO.class) {
//			System.out.println("ClipboardDTO parent: "+parent.toString());
//			System.out.println("ClipboardDTO type: "+type.toString());
//			System.out.println("ClipboardDTO name: "+name);
			
			//TODO?
			ClipboardDTO clip = scProject.getBusinessLayerFacade().createClipboardDTO(name, scProject.getMainCase());
			RequirementsSpecification reqs = ((SCLGraph)scProject.getBusinessLayerFacade()).createRequirementsSpecification();
			DomainSpecification domain = ((SCLGraph)scProject.getBusinessLayerFacade()).createDomainSpecification();
			reqs.addDomainSpecification(domain);
			ArchitecturalModel arch = ((SCLGraph)scProject.getBusinessLayerFacade()).createArchitecturalModel();
			DetailedDesignModel dd = ((SCLGraph)scProject.getBusinessLayerFacade()).createDetailedDesignModel();
			Clipboard cp = (Clipboard)clip;
			cp.addArtifact(arch);
			cp.addArtifact(dd);
			cp.addArtifact(reqs);
		}
		else if(type == RequirementsPackageDTO.class) {
			
			if (parent instanceof RequirementsSpecificationDTO){
				RequirementsPackageDTO rp = scProject.getBusinessLayerFacade().createRequirementsPackageDTO();				
				rp.setName(name);				
				((RequirementsSpecificationDTO)parent).addRequirementsPackageDTO(rp);
			} else if (parent instanceof RequirementsPackageDTO){
				RequirementsPackageDTO rp = scProject.getBusinessLayerFacade().createRequirementsPackageDTO();				
				rp.setName(name);				
				((RequirementsPackageDTO)parent).addRequirementsPackage(rp);				
			}
			
		}
		
		else if(type == RequirementDTO.class) {
			if (parent instanceof RequirementsPackageDTO){
				RequirementDTO r = scProject.getBusinessLayerFacade().createRequirementDTO();
				r.setName(name);				
				((RequirementsPackageDTO)parent).addRequirement(r);
			} 
		}
		else if(type == UseCaseDTO.class) {
			if (parent instanceof RequirementsPackageDTO){
				UseCaseDTO uc = scProject.getBusinessLayerFacade().createUseCaseDTO();
				ConstrainedLanguageScenarioDTO scenario 
					= scProject.getBusinessLayerFacade().createConstrainedLanguageScenarioDTO();
				scenario.setName(name);
				PreconditionSentenceDTO preconditionSent 
					= scProject.getBusinessLayerFacade().createPreconditionSentenceDTO();
				preconditionSent.setSentenceText("");
				scenario.addScenarioStep(preconditionSent);
				SVOSentenceDTO fSent = scProject.getBusinessLayerFacade().createSVOSentenceDTO();
				fSent.setSentenceText("");
				scenario.addScenarioStep(fSent);
				uc.setName(name);					
				uc.addConstrainedLanguageScenario(scenario);
				((RequirementsPackageDTO)parent).addUseCase(uc);				
				
			} 
		}
		

		else if(type == NotionsPackageDTO.class) {
			if (parent instanceof NotionsPackageDTO){
				NotionsPackageDTO notionsPackage = scProject.getBusinessLayerFacade().createNotionsPackageDTO();				
				notionsPackage.setName(name);				
				((NotionsPackageDTO)parent).addNotionsPackageDTO(notionsPackage);
			} 
			else if (parent instanceof DomainSpecificationDTO){
				NotionsPackageDTO notionsPackage = scProject.getBusinessLayerFacade().createNotionsPackageDTO();				
				notionsPackage.setName(name);				
				((DomainSpecificationDTO)parent).addNotionsPackageDTO(notionsPackage);
			} 
		}
		
		else if(type == NotionDTO.class) {
			if (parent instanceof NotionsPackageDTO){
				NotionDTO notion = scProject.getBusinessLayerFacade().createNotionDTO();
				NounPhraseDTO phrase = scProject.getBusinessLayerFacade().createNounPhraseDTO();
				NounDTO noun = page.getAssignedNoun();
				phrase.setNoun(noun);
				if (0==noun.getSynonymUid() && SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
					noun.autoAddAndAssignSense();
				} catch (NullPointerException e){
					e.printStackTrace();
				}
				((BusinessLayerFacade)((NounImpl)noun).getGraph()).cleanNouns(noun);
				phrase.setNounText(noun.getName());
				notion.setNamePhrase(phrase);		
				NotionKindHelper.setDefaultNotionKind(notion);
				((NotionsPackageDTO)parent).addNotionDTO(notion);
			} 
		}
		
		else if(type == ActorDTO.class) {
			if (parent instanceof ActorsPackageDTO){
				ActorDTO actor = scProject.getBusinessLayerFacade().createActorDTO();
				NounPhraseDTO phrase = scProject.getBusinessLayerFacade().createNounPhraseDTO();
				NounDTO noun = page.getAssignedNoun();
				phrase.setNoun(noun);
				if (0==noun.getSynonymUid() && SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
					noun.autoAddAndAssignSense();
				} catch (NullPointerException e){
					e.printStackTrace();
				}
				((BusinessLayerFacade)((NounImpl)noun).getGraph()).cleanNouns(noun);
				phrase.setNounText(noun.getName());
				actor.setNamePhrase(phrase);			
				((ActorsPackageDTO)parent).addActorDTO(actor);
			} 
		}
		
		else if(type == ActorsPackageDTO.class) {
			if (parent instanceof ActorsPackageDTO){
				ActorsPackageDTO actorsPackage = scProject.getBusinessLayerFacade().createActorsPackageDTO();				
				actorsPackage.setName(name);				
				((ActorsPackageDTO)parent).addActorsPackageDTO(actorsPackage);
			} 
			else if (parent instanceof DomainSpecificationDTO){
				ActorsPackageDTO actorsPackage = scProject.getBusinessLayerFacade().createActorsPackageDTO();				
				actorsPackage.setName(name);				
				((DomainSpecificationDTO)parent).addActorsPackageDTO(actorsPackage);
			} 
		}
		
		else if(type == SystemElementDTO.class) {
			if (parent instanceof SystemElementsPackageDTO){
				SystemElementDTO sysElement = scProject.getBusinessLayerFacade().createSystemElementDTO();
				NounPhraseDTO phrase = scProject.getBusinessLayerFacade().createNounPhraseDTO();
				NounDTO noun = page.getAssignedNoun();
				phrase.setNoun(noun);
				if (0==noun.getSynonymUid() && SCProjectHelper.getSenseAutoAddAndAssigmentFlag())try {
					noun.autoAddAndAssignSense();
				} catch (NullPointerException e){
					e.printStackTrace();
				}
				((BusinessLayerFacade)((NounImpl)noun).getGraph()).cleanNouns(noun);
				phrase.setNounText(noun.getName());
				sysElement.setNamePhrase(phrase);			
				((SystemElementsPackageDTO)parent).addSystemElementDTO(sysElement);
			} 
		}
		
		else if(type == SystemElementsPackageDTO.class) {
			if (parent instanceof SystemElementsPackageDTO){
				SystemElementsPackageDTO sysElementsPackage = scProject.getBusinessLayerFacade().createSystemElementsPackageDTO();				
				sysElementsPackage.setName(name);				
				((SystemElementsPackageDTO)parent).addSystemElementDTO(sysElementsPackage);
			} 
			else if (parent instanceof DomainSpecificationDTO){
				SystemElementsPackageDTO sePackage = scProject.getBusinessLayerFacade().createSystemElementsPackageDTO();				
				sePackage.setName(name);				
				((DomainSpecificationDTO)parent).addSystemElementsPackageDTO(sePackage);
			} 
		}
	
		scProject.save();
	}

}
