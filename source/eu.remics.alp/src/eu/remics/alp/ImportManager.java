package eu.remics.alp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.window.Window;

import de.uni_koblenz.jgralab.Vertex;

import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.navigator.providers.AdapterFactory;
import eu.redseeds.engine.navigator.providers.IProvider;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.merging.BuildMergeStackJob;
import eu.redseeds.sc.merging.MergeJob;
import eu.redseeds.sc.merging.MergeStack;
import eu.redseeds.sc.merging.PreMergeJob;
import eu.redseeds.sc.merging.SCMergeManager;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.sc.merging.dialogs.MergeConflictsDialog;
import eu.redseeds.sc.slicing.Slice;
import eu.redseeds.sc.slicing.SliceType;
import eu.redseeds.sc.slicing.Slicer;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.impl.rsl.rsldomainelements.notions.NotionImpl;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;

/**
 * Class for managing importing ALP
 * @author aambroziewicz
 *
 */
public class ImportManager {
	
	public static boolean validateSCAsLibrary(SoftwareCaseDTO sc) {
		Activator.log("ImportManager.validateSCAsLibrary", Status.INFO);
		return true;
	}
	
	public static boolean importPattern(RequirementsPackageDTO selectedObj, PatternSlice patternSlice) {
		Activator.log("ImportManager.importPattern", Status.INFO);
		return true;
	}
	
	/**
	 * Imports ALP (contained in a slice) into target Requirements specification
	 * @param target a target for import
	 * @param slice container for pattern
	 * @return true if import is successful
	 */
	public static boolean importPattern(RequirementsSpecificationDTO target, PatternSlice slice, IRunnableContext iWizardContainer) {
		Activator.log("ALP Import Manager: importing pattern", Status.INFO);
		
		//check arguments
		if(target == null || slice == null) {//TODO - more checking
			Activator.log("ALP Import Manager: wrong arguments", Status.WARNING);
			return false;
		}
		
		Activator.log("ALP Import Manager: importing slice to clipboard", Status.INFO);
		int numberofClipboards = SCProjectContainer.instance().getSCProject(target).getClipboardList().size();
		SCProjectContainer.instance().getSCProject(target).importSlice(slice.getSlice());
		if(SCProjectContainer.instance().getSCProject(target).getClipboardList().size() <= numberofClipboards) {
			Activator.log("ALP Import Manager: importing slice to clipboard failed", Status.WARNING);
			return false; //import failed
		}
		ClipboardDTO targetClipboard = (ClipboardDTO) SCProjectContainer.instance().getSCProject(target).getClipboardList().toArray()[numberofClipboards];
		
		Activator.log("ALP Import Manager: instantiating domain", Status.INFO);
		renameElements(slice.getDomainInstantiationInfo(), targetClipboard);
		
		Activator.log("ALP Import Manager: instantiating logic", Status.INFO);
//		fixRelationships(slice, targetClipboard);//TODO - check if really needed
		renamePatterns(slice.getDomainInstantiationInfo(), targetClipboard);
		
		//clean-up
		removeUnusedInsertionPoints(targetClipboard);
		removeDuplicateNotions(targetClipboard);
		
		Activator.log("ALP Import Manager: merging", Status.INFO);
		if(!mergeElements(targetClipboard, iWizardContainer)) {
			return false;
		}
		Activator.log("ALP Import Manager: clean-up", Status.INFO);
		targetClipboard.delete();
		SCProjectContainer.instance().getSCProject(target).save();	
		SCProjectHelper.refreshSCNavigator();
		return true;
	}
	
	private static void removeDuplicateNotions(ClipboardDTO targetClipboard) {
		BusinessLayerFacade facade 
			= SCProjectContainer.instance().getSCProject(targetClipboard)
				.getBusinessLayerFacade();
		
		List<NotionDTO> notions = new ArrayList<NotionDTO>();
		for(Notion n : facade.getNotionVertices()) {
			notions.add((NotionDTO)n);
		}
		
		//determine if there any duplicated notions, store them and store the names
		Set<String> duplicateNames =  new HashSet<String>();
		List<NotionDTO> duplicateNotions = new ArrayList<NotionDTO>();
		for(NotionDTO n : notions) {
			String nName = n.getName();
			for(NotionDTO nn : notions) {
				if(nn.getName().equalsIgnoreCase(nName) && ((Vertex)nn).getId() != ((Vertex)n).getId()) {
					duplicateNames.add(nName);
					if(!duplicateNotions.contains(n)) {
						duplicateNotions.add(n);
					}
					if(!duplicateNotions.contains(nn)) {
						duplicateNotions.add(nn);
					}
				}
			}
		}
		notions.clear();
		
		if(duplicateNames.size() > 0) {
			Activator.log("ALP Import Manager: found duplicated notions: "
					+ duplicateNames.toString(), Status.INFO);
			for(String dName : duplicateNames) {
				Activator.log("ALP Import Manager: removing notion duplicates for: " 
						+ dName, Status.INFO);
				//get all notions with a given name
				List<NotionDTO> duplicatesForName = new ArrayList<NotionDTO>();
				for(NotionDTO n : duplicateNotions) {
					if(n.getName().equalsIgnoreCase(dName)) {
						duplicatesForName.add(n);
					}
				}
				if(duplicatesForName.size() >= 2) {
					NotionDTO oneAndOnlyNotion = duplicatesForName.get(0);
					for(int i = 1; i <= duplicatesForName.size() - 1; i++) {
//						//copy phrases
						NotionDTO otherNotion = ((NotionDTO)duplicatesForName.get(i));
						for(DomainStatementDTO statement : otherNotion.getDomainStatementDTOList()) {
							//skip same name phrase
							if(!statement.getName().equalsIgnoreCase(dName)) {
								((NotionImpl)otherNotion).removeStatement((DomainStatement)statement);
								boolean shouldBeAdded = false;
//								PhraseDTO phrase = statement.getPhraseDTO();
//								DomainStatementDTO newStmtm =  facade.createDomainStatementDTO(phrase);
								for(DomainStatementDTO dsDTO : oneAndOnlyNotion.getDomainStatementDTOList()) {
									if(!dsDTO.getName().equalsIgnoreCase(statement.getName())) {
										shouldBeAdded = true;
									}
									else {
										statement.delete();
										shouldBeAdded = false;
										break;
									}
								}
								if(shouldBeAdded) {
									oneAndOnlyNotion.addDomainStatementDTO(statement);
								}
								
								//rewire phrases to sentences
								//not needed
//								for (SVOSentence sent : facade
//										.getSVOSentenceVertices()) {
//									if (sent instanceof SVOSentenceDTO) {
//										SVOSentenceDTO svoSent = (SVOSentenceDTO) sent;
//										if (svoSent
//												.getPredicate()
//												.getObject().toString()
//												.equalsIgnoreCase(dName)) {
//											Activator
//													.log("ALP Import Manager: found in sentence: "
//															+ svoSent.getFullSentenceText(),
//															Status.INFO);
////											svoSent.getPredicate().connect();
//										}
//									}
//								}
							}
							//copy relationships
							for(DomainElementRelationshipDTO relation : otherNotion.getDomainElementRelationshipDTOList()) {
								if(relation.getSource().equals(otherNotion)) {
									relation.setSource(oneAndOnlyNotion);
								}
								else if(relation.getTarget().equals(otherNotion)){
									relation.setTarget(oneAndOnlyNotion);
								}
							}
							//TODO: bug with duplicated statements when importing other notion with the same name
						}
						duplicatesForName.remove(otherNotion);
						duplicateNotions.remove(otherNotion);
						otherNotion.delete();
					}
				}
			}
		}
		
	}

	private static void renamePatterns(List<Object[]> domainInstantiationInfo,
			ClipboardDTO targetClipboard) {
		
		HashSet<RequirementsPackageDTO> patterns = new HashSet<RequirementsPackageDTO>();

		BusinessLayerFacade facade 
			= SCProjectContainer.instance().getSCProject(targetClipboard)
				.getBusinessLayerFacade();
		
		for(RSLUseCase uc : facade.getRSLUseCaseVertices()) {
			ClipboardDTO clipboard = facade.whichClipboardMember(uc);
			if(clipboard == null) { //is part of main SC
				continue;
			}
			if(!clipboard.equals(targetClipboard)) {//other clipboard
				continue;
			}
			UseCaseDTO ucDTO = facade.getUseCaseByVertexID(uc.getId());
			patterns.add(ucDTO.getParent()); //get pattern - non-formal limitation: only one level hierarchy in patterns
			//check conditional sentences and replace abstract with concrete notions
			adaptConditionalSentences(domainInstantiationInfo, ucDTO);
			String name = ucDTO.getName();
			if(name.contains("(") && name.contains(")")) {//needs renaming
				if(name.indexOf("(") + 1 < name.indexOf(")")) {//at least one char between
					String notionName = name.substring(name.indexOf("(") + 1, name.indexOf(")"));
					notionName.trim();
					for(Object[] triple : domainInstantiationInfo) {
						//check args.
						if(!checkTripleOfDomainInstantiationInfo(triple)) {
							continue;
						}
						//extract elements
						Object domainElement = triple[0];
						if(domainElement instanceof NotionDTO) {//found notion
							if(((NotionDTO) domainElement).getName().equalsIgnoreCase(notionName)) {//exactly the one
								String newName = (String)triple[1];//check what's new name
								String notionNameBrackets = "\\(" + notionName + "\\)";
								String newUCName = name.replaceFirst(notionNameBrackets, newName);
								if(ucDTO.isNameUnique(newUCName) || name != "") {//if valid
									Activator.log("ALP Import Manager: renaming " 
											+ ucDTO.getName() + " -> " 
											+ newUCName, Status.INFO);
									ucDTO.setName(newUCName);//set new name
									for(ConstrainedLanguageScenarioDTO scenario : ucDTO.getConstrainedLanguageScenarioDTOList()) {
										scenario.setName(scenario.getName().replaceFirst(notionNameBrackets, newName));
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		adaptPackagesNames(domainInstantiationInfo, patterns);
		SCProjectContainer.instance().getSCProject(targetClipboard).save();
	}

//	private static void fixRelationships(PatternSlice slice,
//			ClipboardDTO targetClipboard) {
//		for(Object element : slice.getAllElements()) {
//			if(element instanceof InvocationSentenceDTO) {
////				Activator.log("ALP Import Manager: invoked UC: " + ((InvocationSentenceDTO) element).getInvokedUseCase(), Status.INFO);
//				if(((InvocationSentenceDTO) element).getStereotypes()
//						.contains(Constants
//							.ALP_INSERTION_POINT_RELATIONSHIP_STEREOTYPE)) {//is ALP invoke
//					//remove the stereotype
////					Activator.log("ALP Import Manager: fixed invoke", Status.INFO);
//					((InvocationSentenceDTO) element).removeStereotype(Constants
//							.ALP_INSERTION_POINT_RELATIONSHIP_STEREOTYPE);
//					// TODO re-set invoked usecase
//				}
//			}
//		}
//	}

	private static void adaptPackagesNames(List<Object[]> domainInstantiationInfo,
			Collection<RequirementsPackageDTO> patterns) {
		for(RequirementsPackageDTO pattern : patterns) {
			String name = pattern.getName();
			if(name.contains("(") && name.contains(")")) {//needs renaming
				if(name.indexOf("(") + 1 < name.indexOf(")")) {//at least one char between
					String notionName = name.substring(name.indexOf("(") + 1, name.indexOf(")"));
					notionName.trim();
					for(Object[] triple : domainInstantiationInfo) {
						//check args.
						if(!checkTripleOfDomainInstantiationInfo(triple)) {
							continue;
						}
						//extract elements
						Object domainElement = triple[0];
						if(domainElement instanceof NotionDTO) {//found notion
							if(((NotionDTO) domainElement).getName().equalsIgnoreCase(notionName)) {//exactly the one
								String newName = (String)triple[1];//check what's new name
								String notionNameBrackets = "\\(" + notionName + "\\)";
								String newALPName = name.replaceFirst(notionNameBrackets, newName);
//								if(pattern.isNameUnique(newUCName) || name != "") {//if valid
									Activator.log("ALP Import Manager: renaming " 
											+ pattern.getName() + " -> " 
											+ newALPName, Status.INFO);
									pattern.setName(newALPName);//set new name
//								}
								break;
							}
						}
					}
				}
			}
		}
	}


	private static void renameElements(List<Object[]> domainInstantiationInfo,
			ClipboardDTO targetClipboard) {
		for(Object[] triple : domainInstantiationInfo) {
			//check args.
			if(!checkTripleOfDomainInstantiationInfo(triple)) {
				continue;
			}
			//extract elements
			Object domainElement = triple[0];
			String newName = (String)triple[1];
			//rename
			BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(targetClipboard).getBusinessLayerFacade();
			NounPhraseDTO nounPhrase = facade.createNounPhraseDTO();
			NounDTO noun = facade.createNounDTO();
			if(SCProjectHelper.getSenseAutoAddAndAssigmentFlag() || SCProjectHelper.getSenseAutoAssigmentFlag()) {
				Activator.log("ALP Import Manager: renaming " + domainElement + " -> " + newName, Status.INFO);
				noun.setName(newName);
				if(SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) {
					try {
						noun.autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
				}
				if(SCProjectHelper.getSenseAutoAssigmentFlag()) {
					noun.autoAssignSense();
				}
			}
			else {
				//extract termsense 
				TermSenseDTO termSense = (TermSenseDTO)triple[2];
				Activator.log("ALP Import Manager: renaming " + domainElement + " -> " + newName + ": " + termSense, Status.INFO);
				noun.setName(newName);
				noun.setSynonymUid(termSense.getUid());
			}
			nounPhrase.setNoun(noun);
			((BusinessLayerFacade)((NounImpl) noun).getGraph()).cleanNouns(noun);

			if (domainElement instanceof NotionDTO){
				NotionDTO notion = (NotionDTO)domainElement;
				NotionDTO clipNotion = facade.getNotionDTO(notion.getNamePhrase().getNoun().getSynonymUid());
				List<NounLinkDTO> links = new ArrayList<NounLinkDTO>();
				for(NounLinkDTO link : clipNotion.getNonBasicNounLinksValues()) {
					link.setNewValue(newName);
					links.add(link);
				}
				clipNotion.rename(nounPhrase, links);								
			}
			else if (domainElement instanceof ActorDTO){
				ActorDTO actor = (ActorDTO)domainElement;	
				ActorDTO clipActor = facade.getActorDTOIncludeClipboards(actor.getName());
				clipActor.rename(nounPhrase);								
			}
			else if (domainElement instanceof SystemElementDTO){
				SystemElementDTO sysel = (SystemElementDTO)domainElement;	
				SystemElementDTO clipSysel = facade.getSystemElementDTOIncludeClipboards(sysel.getName());
				clipSysel.rename(nounPhrase);								
			}
		}
		SCProjectContainer.instance().getSCProject(targetClipboard).save();
	}
	
	private static boolean mergeElements(ClipboardDTO targetClipboard,
			IRunnableContext iWizardContainer) {
		
		final SoftwareCaseDTO sc 
			= SCProjectContainer.instance().getSCProject(targetClipboard).getMainCase();
		
		//check args.
		if(!SCMergeManager.validateClipboardForMerging(targetClipboard)) {
			return false;
		}
		if(!SCMergeManager.validateTargetSCForMerging(sc)) {
			return false;
		}
		
		//prepare merge
		final BuildMergeStackJob bmsj = new BuildMergeStackJob(targetClipboard);
		
		try {
			iWizardContainer.run(true, true, bmsj);
		} catch (InvocationTargetException e) {
            Activator.log("ALP Import Manager: Error during gathering elements for merge: "+e.getMessage(), Status.ERROR);
        } catch (InterruptedException e) {
            Activator.log("ALP Import Manager: Error during gathering elements for merge: "+e.getMessage(), Status.ERROR);
        } 
			
        //check for conflicts
		final MergeStack ms = bmsj.getMergeStack();
		final List<MergeConflictObject> conflicts = new ArrayList<MergeConflictObject>();
		PreMergeJob preMergeJob = new PreMergeJob(ms, sc);
		try {
			iWizardContainer.run(false, false, preMergeJob);
		} catch (InvocationTargetException e) {
            Activator.log("ALP Import Manager: Error during gathering elements for merge: "+e.getMessage(), Status.ERROR);
        } catch (InterruptedException e) {
            Activator.log("ALP Import Manager: Error during gathering elements for merge: "+e.getMessage(), Status.ERROR);
        } 
		conflicts.addAll(preMergeJob.getFoundConflicts());

		//resolve conflicts
		if(conflicts.size() > 0) {//if there are any conflicts
			//refine the list
			SCMergeManager.refineMergeConflictsList(conflicts);
			
			//show dialog
			MergeConflictsDialog confdial = new MergeConflictsDialog(SCProjectHelper.getShell());
			confdial.setInput(conflicts.toArray());
			int dialogResult = confdial.open();
			if(dialogResult != Window.OK) {
				return true;
			}
		}
		
		MergeJob mergeJob = new MergeJob(conflicts, targetClipboard, sc);
		try {
			iWizardContainer.run(false, false, mergeJob);
		} catch (InvocationTargetException e) {
            Activator.log("ALP Import Manager: Error during gathering elements for merge: "+e.getMessage(), Status.ERROR);
        } catch (InterruptedException e) {
            Activator.log("ALP Import Manager: Error during gathering elements for merge: "+e.getMessage(), Status.ERROR);
        } 
		
		SCProjectContainer.instance().getSCProject(targetClipboard).save();	
		return true;
	}

	/**
	 * Calculates slice for a given pattern
	 * @param pattern
	 * @return null if error during Slice.computeSlice occurs 
	 */
	public static PatternSlice getPatternSlice(RequirementsPackageDTO pattern) {
		Set<Vertex> sliceCriterion = new HashSet<Vertex>();
		sliceCriterion.add((Vertex)pattern);
		sliceCriterion.addAll(
				castPatternsListToVerticesList(
						calculateRelatedPatterns(pattern)));
		Slice slice = null;
		try {
			slice = Slicer.computeSlice(sliceCriterion, SliceType.DOMAIN_INCLUDING_SLICE); 
		} catch (RuntimeException e) {
			Activator.log("Error occurred during calculating slice", IStatus.ERROR);
			return null;
		} 
		PatternSlice pSlice = new PatternSlice(slice);
		return pSlice;
	}
	
	/**
	 * Calculates slice for a given set of patterns
	 * @param pattern
	 * @return null if error during Slice.computeSlice occurs 
	 */
	public static PatternSlice getPatternSlice(List<RequirementsPackageDTO> patterns) {
		Set<Vertex> sliceCriterion = new HashSet<Vertex>();
		sliceCriterion.addAll(
				castPatternsListToVerticesList(patterns));
		Slice slice = null;
		try {
			slice = Slicer.computeSlice(sliceCriterion, SliceType.DOMAIN_INCLUDING_SLICE); 
		} catch (RuntimeException e) {
			Activator.log("Error occurred during calculating slice", IStatus.ERROR);
			return null;
		} 
		PatternSlice pSlice = new PatternSlice(slice);
		return pSlice;
	}
	
	/**
	 * calculates all related (invoked) patterns for a given pattern
	 * @param pattern
	 * @return
	 */
	public static List<RequirementsPackageDTO> calculateRelatedPatterns(RequirementsPackageDTO pattern) {
		
		List<RequirementsPackageDTO> result = new ArrayList<RequirementsPackageDTO>();
		
		//check arg
		if(!pattern.getStereotypes().contains(Constants.ALP_STEREOTYPE)) {
			return result;
		}
		
		//find all use cases in the pattern
		//find all use cases from other patterns alp-invoked by this pattern's use cases
		
		//get all children - scenarios will be among them
		Stack<Object> objects = new Stack<Object>();
		objects.add(pattern);
		List<ConstrainedLanguageScenarioDTO> scenarios 
			= new ArrayList<ConstrainedLanguageScenarioDTO>();
		
		IProvider provider = null;
		
		while(!objects.isEmpty()) {
			Object obj = objects.pop();
			provider = AdapterFactory.adapt(obj, null);
			Object[] children = provider.getChildren(obj);
			for(Object child : children) {
				if(child instanceof ConstrainedLanguageScenarioDTO) {
					scenarios.add((ConstrainedLanguageScenarioDTO)child);
				}
			}
			objects.addAll(Arrays.asList(children));
		}
		
		//find all use cases from other patterns alp-invoked by this pattern's use cases' scenarios
		for(ConstrainedLanguageScenarioDTO scenario : scenarios) {
			for(ConstrainedLanguageSentenceDTO sentence : scenario.getScenarioSentenceList()) {
				if(sentence instanceof InvocationSentenceDTO) {
					if(((InvocationSentenceDTO) sentence)
							.getStereotypes().contains(
									Constants.ALP_INSERTION_POINT_RELATIONSHIP_STEREOTYPE)) {
						
						UseCaseDTO invokedUC = ((InvocationSentenceDTO) sentence)
							.getInvokedUseCase();
						//check whether it in a pattern 
						RequirementsPackageDTO parent = invokedUC.getParent();
						while(parent != null) {
							if(parent.equals(pattern)) {//UC must be in other pattern
								continue;
							}
							if(parent.getStereotypes()
									.contains(Constants.ALP_STEREOTYPE)) {//parent is a pattern
								result.add(parent);
							}
							parent = parent.getParent();
						}
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * casts list of RequirementsPackageDTO to list of Vertices
	 * @param patternList
	 * @return
	 */
	private static List<Vertex> castPatternsListToVerticesList(List<RequirementsPackageDTO> patternList) {
		if(patternList == null) {
			return new ArrayList<Vertex>(0);
		}
 		List<Vertex> result = new ArrayList<Vertex>(patternList.size());
		
		for(RequirementsPackageDTO pack : patternList) {
			result.add((Vertex)pack);
		}
		
		return result;
	}
	
	/**
	 * Gets list of domain elements (notions, actors and system elements) for a given slice
	 * @param slice
	 * @return
	 */
	public static List<Object> getDomainElementsForSlice(PatternSlice pSlice) {
		List<Object> result = new ArrayList<Object>();
		for(Object element : pSlice.getAllElements()) {
			if(element instanceof Notion || element instanceof Actor || element instanceof SystemElement) {
				result.add(element);
			}
		}
		return result;
	}
	
	/**
	 * checks given use case for conditional sentences and replaces abstract notion with concrete notion (only one, sentences are not structured)
	 * @param domainInstantiationInfo
	 * @param ucDTO
	 */
	private static void adaptConditionalSentences(List<Object[]> domainInstantiationInfo,
			UseCaseDTO ucDTO) {
		for(ConstrainedLanguageScenarioDTO scenario : ucDTO.getConstrainedLanguageScenarioDTOList()) {
			for(ConstrainedLanguageSentenceDTO sentence : scenario.getScenarioSentenceList()) {
				if(sentence instanceof ConditionSentenceDTO || sentence instanceof PreconditionSentenceDTO || sentence instanceof PostconditionSentenceDTO) {//free text sentence which may contain (notion)
//					Activator.log("scenario: " + scenario.getName() + "sentence: " + sentence.getClass(), IStatus.INFO);
					String text = "";
					if(sentence instanceof ConditionSentenceDTO) {
						text = ((ConditionSentenceDTO) sentence).getSentenceText();
					}
					else if(sentence instanceof PreconditionSentenceDTO) {
						text = ((PreconditionSentenceDTO) sentence).getSentenceText();
					}
					else if(sentence instanceof PostconditionSentenceDTO) {
						text = ((PostconditionSentenceDTO) sentence).getSentenceText();
					}
					if(text == null) {
						continue;
					}
					String unprocessedText = text;
					if(unprocessedText.contains("(") && unprocessedText.contains(")")) {//needs renaming
						if(unprocessedText.indexOf("(") + 1 < unprocessedText.indexOf(")")) {//at least one char between
							String notionName = unprocessedText.substring(unprocessedText.indexOf("(") + 1, unprocessedText.indexOf(")"));
							notionName.trim();
							for(Object[] triple : domainInstantiationInfo) {
										//check args.
										if(!checkTripleOfDomainInstantiationInfo(triple)) {
											continue;
										}
										//extract elements
										Object domainElement = triple[0];
										if(domainElement instanceof NotionDTO) {//found notion
											if(((NotionDTO) domainElement).getName().equalsIgnoreCase(notionName)) {//exactly the one
												String newName = (String)triple[1];//check what's new name
												String notionNameBrackets = "\\(" + notionName + "\\)";
												String newText = unprocessedText.replaceFirst(notionNameBrackets, newName);
												//replace only one that was found
												if(sentence instanceof ConditionSentenceDTO) {
													((ConditionSentenceDTO) sentence).setSentenceText(newText);
													unprocessedText = ((ConditionSentenceDTO) sentence).getSentenceText();
												}
												else if(sentence instanceof PreconditionSentenceDTO) {
													((PreconditionSentenceDTO) sentence).setSentenceText(newText);
													unprocessedText = ((PreconditionSentenceDTO) sentence).getSentenceText();
												}
												else if(sentence instanceof PostconditionSentenceDTO) {
													((PostconditionSentenceDTO) sentence).setSentenceText(newText);
													unprocessedText = ((PostconditionSentenceDTO) sentence).getSentenceText();
												}
											}
										}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * checks use cases in a given clipboard for unused insertion points (SVO sentences with "(n)" type text) and removes them
	 * @param ucDTO
	 */
	private static void removeUnusedInsertionPoints(ClipboardDTO targetClipboard) {
		
		HashSet<SVOSentenceDTO> svoSentencesToDelete = new HashSet<SVOSentenceDTO>();
		
		BusinessLayerFacade facade = SCProjectContainer.instance()
				.getSCProject(targetClipboard).getBusinessLayerFacade();

		for (RSLUseCase uc : facade.getRSLUseCaseVertices()) {
			ClipboardDTO clipboard = facade.whichClipboardMember(uc);
			if (clipboard == null) { // is part of main SC
				continue;
			}
			if (!clipboard.equals(targetClipboard)) {// other clipboard
				continue;
			}
			UseCaseDTO ucDTO = facade.getUseCaseByVertexID(uc.getId());
			for (ConstrainedLanguageScenarioDTO scenario : ucDTO
					.getConstrainedLanguageScenarioDTOList()) {
				for (ConstrainedLanguageSentenceDTO sentence : scenario
						.getScenarioSentenceList()) {
					if (sentence instanceof SVOSentenceDTO) {// SVO sentence which may contain unused insertion point
						String text = ((SVOSentenceDTO) sentence)
								.getFullSentenceText();
//						Activator.log("scenario: " + scenario.getName()
//								+ "sentence: " + sentence.getClass() + "text: "
//								+ text, IStatus.INFO);
						if (text == null) {
							continue;
						}
						if (Pattern.matches(Constants.ALP_INSERTION_POINT_PATTERN, text)) {// needs removing
							svoSentencesToDelete.add((SVOSentenceDTO)sentence);
						}
					}
				}
			}
		}
		for (SVOSentenceDTO s : svoSentencesToDelete) {
			for (ConstrainedLanguageScenarioDTO scen : s.getOwningScenarios()) {
				scen.removeScenarioStep(s);
			}	
			s.delete();
		}

	}
	
	/**
	 * check if old notion - new notion - sense is valid
	 * @param triple
	 */
	private static boolean checkTripleOfDomainInstantiationInfo(Object[] triple) {
		if(triple == null) {
			return false;
		}
		if(triple.length != 3) {
			return false;
		}
		if(triple[0] == null || triple[1] == null || triple[2] == null) {
			return false;
		}
		return true;
	}

}
