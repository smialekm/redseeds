package eu.remics.recovery.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.resource.Resource;

import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.actors.ActorsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.ActorOrSystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainSpecification;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionSpecialisation;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElementsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsSpecification;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MRecoveredNotion;
import eu.remics.recovery.model.preferences.MConfiguration;

public class RecoveryModelHelper {

	SCProject project = null;
	
	private RecoveryModelHelper(SCProject project){
		this.project=project;
	}
	
	public static RecoveryModelHelper instance(){
		return new RecoveryModelHelper(SCProjectContainer.instance().getSCProject(SCProjectHelper.getActiveProject()));
	}
	
	public static RecoveryModelHelper instance(Resource resource){
		if (null==resource) return null;
		IResource file = ResourcesPlugin.getWorkspace().getRoot().findMember(resource.getURI().toPlatformString(true));
		if(null==file) return null;
		return new RecoveryModelHelper(SCProjectContainer.instance().getSCProject(file.getProject()));
	}
	
	public static RecoveryModelHelper instance(SCLElement sclElement){
		return new RecoveryModelHelper(SCProjectContainer.instance().getSCProject(sclElement));
	}
	
	/**
	 * Returns selected or active SCProject
	 * 
	 * @return SCProject
	 */
	public Object getSCProject() {
		return project;
	}
	
	/**
	 * Returns BusinessLayerFacade for selected or active SCProject
	 * 
	 * @return BusinessLayerFacade
	 */
	public BusinessLayerFacade getBussinessLayerFacade(){
		return null!=project?project.getBusinessLayerFacade():null;
	}
	
	/**
	 * Saves active SCProject state
	 */
	public void saveSCProject(){
		if (null!=project) project.save();
	}
	
	/**
	 * Reverts active SCProject to last save state
	 */
	public void revertSCProject(){
		if (null!=project) project.revert();
	}

	/**
	 * Returns Recovered Scenarios Package from active SCProject
	 * 
	 * @return Recovered Scenarios Package
	 */
	public RequirementsPackageDTO getRecoveredScenariosPackage() {
		for(RequirementsPackage reqpackage : getBussinessLayerFacade().getRequirementsPackageVertices())
			if(reqpackage.getName().equals("RecoveredScenarios"))
				return (RequirementsPackageDTO) reqpackage;
		return null;
	}
	
	/**
	 * Returns recovered scenario with given name
	 * 
	 * @param name name
	 * @return recovered scenario (represented by use case)
	 */
	public UseCaseDTO getUseCaseFromRecoveredScenarioPackage(String name){
		RequirementsPackageDTO reqPack = getRecoveredScenariosPackage();
		for(RequirementDTO req : reqPack.getRequirements()){
			if(req.getName().equals("~"+name))
				return (UseCaseDTO)req;
		}
		return null;
	}
	
	/**
	 * Returns Use Case from active SCProject with given name
	 * 
	 * @param name name
	 * @return Use Case
	 */
	public UseCaseDTO getUseCase(String name){
		UseCaseDTO uc = getBussinessLayerFacade().getUseCaseByName(name);
		return uc;
	}
	
	/**
	 * Returns Notions Package from active SCProject with given name
	 * 
	 * @param name name
	 * @return Notions Package
	 */
	public NotionsPackageDTO getNotionsPackage(String name) {
		for(NotionsPackage notpackage : getBussinessLayerFacade().getNotionsPackageVertices())
			if(notpackage.getName().equals(name))
				return (NotionsPackageDTO) notpackage;
		return null;
	}
	
	/**
	 * Returns System Elements Package from active SCProject with given name
	 * 
	 * @param name name
	 * @return System Elements Package
	 */
	public SystemElementsPackageDTO getSystemElementsPackage(String name) {
		for(SystemElementsPackage syspackage : getBussinessLayerFacade().getSystemElementsPackageVertices())
			if(syspackage.getName().equals(name))
				return (SystemElementsPackageDTO) syspackage;
		return null;
	}
	
	/**
	 * Returns Actors Package from active SCProject with given name
	 * 
	 * @param name name
	 * @return Actors Package
	 */
	public ActorsPackageDTO getActorsPackage(String name) {
		for(ActorsPackage actpackage : getBussinessLayerFacade().getActorsPackageVertices())
			if(actpackage.getName().equals(name))
				return (ActorsPackageDTO) actpackage;
		return null;
	}
	
	/**
	 * Returns Requirements Package from active SCProject with given name
	 * 
	 * @param name name
	 * @return Requirements Package
	 */
	public RequirementsPackageDTO getRequirementsPackage(String name) {
		for(RequirementsPackage reqpackage : getBussinessLayerFacade().getRequirementsPackageVertices())
			if(reqpackage.getName().equals(name))
				return (RequirementsPackageDTO) reqpackage;
		return null;
	}
	
	/**
	 * Returns Requirements Specification from active SCProject
	 * 
	 * @return Requirements Specification
	 */
	public RequirementsSpecificationDTO getRequirementsSpecification() {
		for(RequirementsSpecification reqpackage : getBussinessLayerFacade().getRequirementsSpecificationVertices())
			return (RequirementsSpecificationDTO) reqpackage;
		return null;
	}

	public ActorDTO getActorByVertexID(int id) {
		for (ActorOrSystemElement act : getBussinessLayerFacade().getActorOrSystemElementVertices()) {
			if (act.getId() == id && act instanceof Actor) {
				return (ActorDTO) act;
			}
		}
		return null;
	}

	public RequirementsPackageDTO getRequirementsPackageByVertexID(int id) {
		for (RequirementsPackage pck : getBussinessLayerFacade().getRequirementsPackageVertices()) {
			if (pck.getId() == id) {
				return (RequirementsPackageDTO) pck;
			}
		}
		return null;
	}
	
	public Dependency getDependencyByVertexID(int id){
		for (Dependency dep: getBussinessLayerFacade().getDependencyVertices()){
			if(dep.getId() == id){
				return dep;
			}
		}
		return null;
	}
	
	public NotionDTO getNotionByVertexID(int id){
		for (Notion not: getBussinessLayerFacade().getNotionVertices()){
			if(not.getId() == id){
				return (NotionDTO) not;
			}
		}
		return null;
	}

	public NotionsPackageDTO getNotionsPackageByVertexID(int packId) {
		for (NotionsPackage notpack : getBussinessLayerFacade().getNotionsPackageVertices()){
			if(notpack.getId() == packId){
				return (NotionsPackageDTO) notpack;
			}
		}
		return null;
	}

	public DomainStatementDTO getDomainStatementByVertexID(int id) {
		for (DomainStatement phr : getBussinessLayerFacade().getDomainStatementVertices()){
			if(phr.getId() == id){
				return (DomainStatementDTO) phr;
			}
		}
		return null;
	}
	
	public NotionSpecialisationDTO getSpecialisationByVertexID(int id) {
		for (NotionSpecialisation spec : getBussinessLayerFacade().getNotionSpecialisationVertices()){
			if(spec.getId() == id){
				return (NotionSpecialisationDTO) spec;
			}
		}
		return null;
	}

	public DomainElementRelationshipDTO getDomainElementRelationshipByVertexID(int id) {
		for (DomainElementRelationship rel : getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if(rel.getId() == id){
				return (DomainElementRelationshipDTO) rel;
			}
		}
		return null;
	}

	public InvocationRelationshipDTO getInvocationRelationshipByVertexID(int id) {
		for (InvocationRelationship rel : getBussinessLayerFacade().getInvocationRelationshipVertices()){
			if(rel.getId() == id){
				return (InvocationRelationshipDTO) rel;
			}
		}
		return null;
	}
	
	public List<UseCaseDTO> getTemporaryUseCases(){
		List<UseCaseDTO> result = new ArrayList<UseCaseDTO>();
		for(RSLUseCase uc:getBussinessLayerFacade().getRSLUseCaseVertices()){
			if (((UseCaseDTO) uc).getName().charAt(0)=='~' && (null==((UseCaseDTO) uc).getParent()) || ((UseCaseDTO) uc).getParent().getName().equals("RecoveredScenarios")) result.add((UseCaseDTO) uc);
		}
		return result;
	}

	public ActorOrSystemElementDTO getActorOrSystemElementByVertexID(int id) {
		for (ActorOrSystemElement aose : getBussinessLayerFacade().getActorOrSystemElementVertices()) {
			if (aose.getId() == id) {
				return (ActorOrSystemElementDTO) aose;
			}
		}
		return null;
	}

	public NounDTO getNounByVertexId(int id) {
		for (Noun n : getBussinessLayerFacade().getNounVertices()) {
			if (n.getId() == id) {
				return (NounDTO) n;
			}
		}
		return null;
	}

	public DomainSpecificationDTO getDomainSpecification() {
		for(DomainSpecification reqpackage : getBussinessLayerFacade().getDomainSpecificationVertices())
			return (DomainSpecificationDTO) reqpackage;
		return null;
	}
	
	public NotionDTO getNotion(String name) {
		for(NotionDTO not : getBussinessLayerFacade().getAllNotions())
			if(not.getName().equals(name))
				return not;
		return null;
	}
    
	public NotionDTO getNotion(NounDTO noun) {
		for (Notion not: getBussinessLayerFacade().getNotionVertices()) if (((Noun)((NotionDTO)not).getNamePhrase().getNoun()).getId()==((Noun)noun).getId()) return (NotionDTO) not;
		return null;
	}
	
	public String checkNotionName(String elname, Set<NotionDTO> fields, boolean data, boolean window, String type){
    	String name=new String(elname+((!data || !window)?(data?(null!=MConfiguration.getInputsufix()?" "+MConfiguration.getInputsufix():""):(window?(null!=MConfiguration.getWindowsufix()?" "+MConfiguration.getWindowsufix():""):(null!=type&&null!=MRecoveredNotion.getRoleMaping(type)?" "+MRecoveredNotion.getRoleMaping(type):""))):""));
    	NotionDTO not;
    	int i=1;
    	while(null!=(not=getNotion(RemoteJGWNL.getInstance().getNounBaseForm(name)))){
    		if (data && !window){
    			if (NotionTypesEnum.Simple_View.tag().equals(not.getType()) && MRecoveredNotion.hasTheSameFields(not,fields)) return name;
    			name=elname+(null!=MConfiguration.getInputsufix()?" "+MConfiguration.getInputsufix():"")+(MConfiguration.isCanUseBrackets()?" ("+i+")":" "+i);
    		} else if (window && !data){
    			if (Arrays.asList(NotionTypesEnum.Screen.tag(),NotionTypesEnum.Message.tag(),NotionTypesEnum.Confirmation_Dialog.tag()).contains(not.getType())) return name;
    			name=elname+(null!=MConfiguration.getWindowsufix()?" "+MConfiguration.getWindowsufix():"")+(MConfiguration.isCanUseBrackets()?" ("+i+")":" "+i);
    		} else if (data && window){
    			if (not.getType().isEmpty() && (null==fields || MRecoveredNotion.hasTheSameFields(not,fields))) return name;
    			name=elname+(MConfiguration.isCanUseBrackets()?" ("+i+")":" "+i);
    		} else if (!MConfiguration.getListroles().contains(type)) {
    			if (NotionTypesEnum.Trigger.tag().equals(not.getType())) return name;
    			name=elname+(null!=type&&null!=MRecoveredNotion.getRoleMaping(type)?" "+MRecoveredNotion.getRoleMaping(type):"")+(MConfiguration.isCanUseBrackets()?" ("+i+")":" "+i);
    		} else {
    			if (NotionTypesEnum.List_View.tag().equals(not.getType()) && MRecoveredNotion.hasTheSameFields(not,fields)) return name;
    			name=elname+(null!=type&&null!=MRecoveredNotion.getRoleMaping(type)?" "+MRecoveredNotion.getRoleMaping(type):"")+(MConfiguration.isCanUseBrackets()?" ("+i+")":" "+i);
    		}
    		i++;
    	}
    	return name;
    }
	
	public List<NotionDTO> getNotionsOfType(List<String> tags){
		List<NotionDTO> l = new ArrayList<NotionDTO>();
		for (Notion not : getBussinessLayerFacade().getNotionVertices())
			if (tags.isEmpty() &&((NotionDTO) not).getType().isEmpty() || tags.contains(((NotionDTO) not).getType())) l.add((NotionDTO) not);
		return l;
	}
	
	public void createPackagesSystemElementsAndActors(){
		if (null==getNotionsPackage("Notions")){
			NotionsPackageDTO np = getBussinessLayerFacade().createNotionsPackageDTO();
			np.setName("Notions");
			getDomainSpecification().addNotionsPackageDTO(np);
		}
		if (null==getSystemElementsPackage("SystemElements")){
			SystemElementsPackageDTO sep = getBussinessLayerFacade().createSystemElementsPackageDTO();
			sep.setName("SystemElements");
			getDomainSpecification().addSystemElementsPackageDTO(sep);
		}
		if (null==getActorsPackage("Actors")){
			ActorsPackageDTO ap = getBussinessLayerFacade().createActorsPackageDTO();
			ap.setName("Actors");
			getDomainSpecification().addActorsPackageDTO(ap);
		}
		if (MConfiguration.isPackageorg()){
			NotionsPackageDTO np,pnp;
			if (!MConfiguration.getDataPack().isEmpty()){
				pnp=getNotionsPackage(MConfiguration.getDataPack());
				if(null==pnp) {
					pnp = getBussinessLayerFacade().createNotionsPackageDTO();
					pnp.setName(MConfiguration.getDataPack());
					getNotionsPackage("Notions").addNotionsPackageDTO(pnp);
				}
			}
			if (!MConfiguration.getUiPack().isEmpty()){
				pnp=getNotionsPackage(MConfiguration.getUiPack());
				if(null==pnp) {
					pnp = getBussinessLayerFacade().createNotionsPackageDTO();
					pnp.setName(MConfiguration.getUiPack());
					getNotionsPackage("Notions").addNotionsPackageDTO(pnp);
				}
			} else pnp=getNotionsPackage("Notions");
			if(!MConfiguration.getTriggerPack().isEmpty()){
				if(null==getNotionsPackage(MConfiguration.getTriggerPack())){
					np = getBussinessLayerFacade().createNotionsPackageDTO();
					np.setName(MConfiguration.getTriggerPack());
					np.setParent(pnp);
				}
			}
			if(!MConfiguration.getListPack().isEmpty()){
				if(null==getNotionsPackage(MConfiguration.getListPack())){
					np = getBussinessLayerFacade().createNotionsPackageDTO();
					np.setName(MConfiguration.getListPack());
					np.setParent(pnp);
				}
			}
			if (!MConfiguration.getConceptPack().isEmpty()){
				pnp=getNotionsPackage(MConfiguration.getConceptPack());
				if(null==pnp){
					pnp = getBussinessLayerFacade().createNotionsPackageDTO();
					pnp.setName(MConfiguration.getConceptPack());
					getNotionsPackage("Notions").addNotionsPackageDTO(pnp);
				}
			}
			if (!MConfiguration.getAttributePack().isEmpty()){
				pnp=getNotionsPackage(MConfiguration.getAttributePack());
				if(null==pnp){
					pnp = getBussinessLayerFacade().createNotionsPackageDTO();
					pnp.setName(MConfiguration.getAttributePack());
					getNotionsPackage("Notions").addNotionsPackageDTO(pnp);
				}
			} else pnp=getNotionsPackage("Notions");
			if (!MConfiguration.getOptionPack().isEmpty()){
				if(null==getNotionsPackage(MConfiguration.getOptionPack())){
					np = getBussinessLayerFacade().createNotionsPackageDTO();
					np.setName(MConfiguration.getOptionPack());
					np.setParent(pnp);
				}
			}
		}
		if (null==getBussinessLayerFacade().getSystemElementDTO("system")){
			SystemElementDTO sys = getBussinessLayerFacade().createSystemElementDTO();
			NounPhraseDTO noun = getBussinessLayerFacade().createNounPhraseDTO();
			noun.setNounText("system");
    		NounDTO n;
    		if (null==(n=getBussinessLayerFacade().getNoun("system"))) n=getBussinessLayerFacade().createNounDTO();
			n.setName("system");
			noun.setNoun(n);
			sys.setNamePhrase(noun);
			if (0==n.getSynonymUid()) sys.getNounPhrase().getNoun().autoAssignSense();
			getSystemElementsPackage("SystemElements").addSystemElementDTO(sys);
		}
		if (null==getBussinessLayerFacade().getActorDTO("user")){
			ActorDTO act = getBussinessLayerFacade().createActorDTO();
			NounPhraseDTO noun = getBussinessLayerFacade().createNounPhraseDTO();
			noun.setNounText("user");
    		NounDTO n;
    		if (null==(n=getBussinessLayerFacade().getNoun("user"))) n=getBussinessLayerFacade().createNounDTO();
			n.setName("user");
			noun.setNoun(n);
			act.setNamePhrase(noun);
			if (0==n.getSynonymUid()) act.getNounPhrase().getNoun().autoAssignSense();
			getActorsPackage("Actors").addActorDTO(act);
		}
	}
	
	public boolean compareSenses(TermDTO term, String name){
		String type;
		if (term instanceof NounDTO) type= Constants.TERM_NOUN;
		else if (term.getTerm() instanceof VerbDTO) type=Constants.TERM_VERB;
		else if (term.getTerm() instanceof PrepositionDTO) type=Constants.TERM_PREPOSITION;
		else if (term.getTerm() instanceof ModifierDTO) type=Constants.TERM_MODIFIER;
		else if (term.getTerm() instanceof DeterminerDTO) type=Constants.TERM_DETERMINER;
		else type=Constants.TERM_ADJECTIVE;
		List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
		for (TermSenseDTO sens : RemoteJGWNL.getInstance().getTermSenses(name.toLowerCase().trim().replaceAll("[ ]+"," "),type)) thisSenses.add(sens);
		List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();
		TermSenseDTO thatSense =  RemoteJGWNL.getInstance().getTermSenseDTO(term.getSynonymUid());
		if (thatSense == null){
			TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(term.getName().toLowerCase().replaceAll("[ ]+"," "), type);
			if (senses.length > 0) for (TermSenseDTO s : senses){
				thatSenses.add(s);
			} else {
				if (name.trim().replaceAll("[ ]+"," ").equalsIgnoreCase(term.getName().replaceAll("[ ]+"," "))){
					return true;
				}
			}
		} else {
			thatSenses.add(thatSense);
		}
		for (TermSenseDTO thisS : thisSenses){
			for (TermSenseDTO thatS : thatSenses){	
				if (thisS.getUid() == thatS.getUid()){
					return true;
				}
			}
		}
		return false;
	}
	
	public static Point dispersLocations(Point location, int index, int size) {
		if (1!=size) switch(MConfiguration.getDiagramDispersType()){
			case 0:
				if (index>0){
					return new Point(location.x+index*30, location.y+index*30);
				}
				break;
			//point for extensions
		}
		return location;
	}
	
}
