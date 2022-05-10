package eu.redset.transformation.engine.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import org.eclipse.core.resources.IFolder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import eu.redseeds.common.Constants;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.TestSpecificationContainer;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ControlSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;
import eu.redset.emf.model.tsl.Condition;
import eu.redset.emf.model.tsl.ConditionSentence;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.NotionAttribute;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;
import eu.redset.emf.model.tsl.impl.TSLBusinessLayerFacadeImpl;
import eu.redset.transformation.engine.interfaces.ITransformationExecution;


public class TransformtaionExecutionImpl implements ITransformationExecution{

	private TSLBusinessLayerFacade tsFacade;
	private BusinessLayerFacade rslFacade;
	private Resource testResource;
	private List includeRelationships = new ArrayList();
	private String tsName = "";
	private TestSpecification testSpecification;
	
	@Override
	public void execute(SCProject proj) {
		
		//--------------
		
		tsFacade = new TSLBusinessLayerFacadeImpl();
		// Create a resource set.
		ResourceSet resourceSet = new ResourceSetImpl();

		// Register the default resource factory -- only needed for stand-alone!
		// this tells EMF to use XML to save the model
		resourceSet
			.getResourceFactoryRegistry()
			.getExtensionToFactoryMap()
			.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
					new XMIResourceFactoryImpl());

		// Get the URI of the model file.
		
		IFolder scf = proj.getEclipseProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
		IFolder testFolder = scf.getFolder(Constants.TESTS_FOLDER_NAME);
		File testFileFolder = testFolder.getLocation().toFile();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.HH-mm");
		Date date = new Date();
		tsName = proj.getName()+" "+dateFormat.format(date);
		File testFile = new File(testFileFolder, tsName+".xmi");
		URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
		
		// Create a resource for this file.
		testResource = resourceSet.createResource(fileURI);
		tsFacade.createTestSpecification(tsName);
		tsFacade.createInitialTestSpecificationStructure();
		testResource.getContents().add(tsFacade.getTestSpecification());
		tsFacade.setModelResources(testResource);
		
		testSpecification = tsFacade.getTestSpecification();
		
		proj.getTestSpecificationContainer().addTestSpecification(testResource, tsFacade);
		//-----------------
		
		//if (tsFacade == null)
		//	tsFacade = proj.getTSLBusinessLayerFacade();
		if (rslFacade == null)
			rslFacade = proj.getBusinessLayerFacade();
		//ReDSeT TODO
		RequirementsSpecificationDTO reqSpec = proj.getMainCase().getRequirementsSpecificationDTO();
		
		
		//Notions processing
		TestPackage notionsPackage = tsFacade.getNotionsPackage();
		if(notionsPackage == null){
			notionsPackage = tsFacade.createTestPackage(null, "Notions");
			testSpecification.getEReference0().add(notionsPackage);	
		}
		List notionsPackages = reqSpec.getDomainSpecificationDTO().getNotionsPackageDTOList();
		for (int i=0; i<notionsPackages.size(); i++){
			executeNotionsPackage(notionsPackage, (NotionsPackageDTO)notionsPackages.get(i));
		}
	
		for (int i=0; i<notionsPackages.size(); i++){
			executeNotionsPackageWithDomainStatements((NotionsPackageDTO)notionsPackages.get(i));
		}
		
		//Use cases processing
		TestPackage uctPackage = tsFacade.getUseCaseTestPackage();
		if(uctPackage == null){
			uctPackage = tsFacade.createTestPackage(null, "Use Case Tests");
			testSpecification.getEReference0().add(uctPackage);	
		}
		List reqPackages = reqSpec.getRequirementsPackagesDTOList();
		for (int i=0; i<reqPackages.size(); i++){
			executeRequirementsPackage(uctPackage, (RequirementsPackageDTO)reqPackages.get(i));
		}
		
		for (int i=0; i<includeRelationships.size(); i++){
			TestInvocationRelationship testInv = (TestInvocationRelationship)includeRelationships.get(i);
			InvocationRelationshipDTO irDTO = rslFacade.getInvocationRelationshipByUid(testInv.getUid());			
			if (irDTO.getUid().equals(testInv.getUid())){				
				testInv.setInvocationTarget(tsFacade.getUseCaseTestByUid(irDTO.getTarget().getUid()));
			}
		}
		
		//NFRs processing
		TestPackage nfPackage = tsFacade.getNFTestsPackage();
		if(nfPackage == null){
			nfPackage = tsFacade.createTestPackage(null, "Non Functional Tests");
			testSpecification.getEReference0().add(nfPackage);	
		}
		List nfrPackages = reqSpec.getRequirementsPackagesDTOList();
		for (int i=0; i<reqPackages.size(); i++){
			executeNFRPackage(nfPackage, (RequirementsPackageDTO)reqPackages.get(i));
		}
		
		//MessageDialog.openInformation(null, "Info", "Generation of Test Specification completed");
		proj.saveTS(testSpecification);
	}

	private void executeNotionsPackage(TestPackage parent, NotionsPackageDTO pack){
		TestPackage testPack;
		testPack = tsFacade.createTestPackage(parent, pack.toString());
		
		List notionsPacks = pack.getNotionsPackageDTOList();
		for (int i=0; i<notionsPacks.size(); i++){
			if (notionsPacks.get(i) instanceof NotionsPackageDTO)
				executeNotionsPackage(testPack, (NotionsPackageDTO)notionsPacks.get(i));
		}
		
		List notions = pack.getNotionDTOList();
		for (int i=0; i<notions.size(); i++){
			if (notions.get(i) instanceof NotionDTO){
				executeNotion(testPack, (NotionDTO)notions.get(i));
			}
		}	
	}
	
	private void executeNotionsPackageWithDomainStatements(NotionsPackageDTO pack){
	
		
		List notionsPacks = pack.getNotionsPackageDTOList();
		for (int i=0; i<notionsPacks.size(); i++){
			if (notionsPacks.get(i) instanceof NotionsPackageDTO)
				executeNotionsPackageWithDomainStatements((NotionsPackageDTO)notionsPacks.get(i));
		}
		
		List notions = pack.getNotionDTOList();
		for (int i=0; i<notions.size(); i++){
			if (notions.get(i) instanceof NotionDTO){
				executeNotionWithDomainStatemetns((NotionDTO)notions.get(i));
			}
		}	
	}
	
	private void executeRequirementsPackage(TestPackage parent, RequirementsPackageDTO pack){
		TestPackage testPack;
		testPack = tsFacade.createTestPackage(parent, pack.toString());
		
		List reqPacks = pack.getRequirementsPackages();
		for (int i=0; i<reqPacks.size(); i++){
			if (reqPacks.get(i) instanceof RequirementsPackageDTO)
				executeRequirementsPackage(testPack, (RequirementsPackageDTO)reqPacks.get(i));
		}
		
		List reqs = pack.getRequirements();
		for (int i=0; i<reqs.size(); i++){
			if (reqs.get(i) instanceof UseCaseDTO){
				executeUseCase(testPack, (UseCaseDTO)reqs.get(i));
			}
		}	
	}
	
	private void executeNFRPackage(TestPackage parent, RequirementsPackageDTO pack){
		TestPackage testPack;
		//testPack = tsFacade.createTestPackage(parent, pack.toString());
		
		List reqPacks = pack.getRequirementsPackages();
		for (int i=0; i<reqPacks.size(); i++){
			if (reqPacks.get(i) instanceof RequirementsPackageDTO)
				executeRequirementsPackage(parent, (RequirementsPackageDTO)reqPacks.get(i));
		}
		
		List reqs = pack.getRequirements();
		for (int i=0; i<reqs.size(); i++){
			if (reqs.get(i) instanceof RequirementDTO && !(reqs.get(i) instanceof UseCaseDTO)){
				executeNFR(parent, (RequirementDTO)reqs.get(i));
			}
		}	
	}
	
	private void executeNFR(TestPackage parent, RequirementDTO nfr){
		NFTest nft = tsFacade.createNFTest(parent, nfr.getName());
		nft.setDescription(nfr.getDescription());
		nft.setNfrTrail(nfr.getSpecificationPath());
		nft.setUid(nfr.getUid());
		
		/*for (RequirementDTO reqDTO : nfr.getRelatedRequirements()){
			if (reqDTO instanceof UseCaseDTO){
				
			}
		}
		*/
	}
	
	private void executeNotion(TestPackage parent, NotionDTO notionDTO){
		Notion notion = tsFacade.createNotion(parent, notionDTO.getName());
		notion.setNotionDescription(notionDTO.getDescription());
		notion.setNotionTrail(notionDTO.getSpecificationPath());
		notion.setUid(notionDTO.getUid());
		
		List notionDTOAttrList = notionDTO.getNotionDTOAttributeList();
		for (int i=0; i<notionDTOAttrList.size(); i++){
			NotionDTO notionDTOAttr = (NotionDTO)notionDTOAttrList.get(i);
			NotionAttribute attr = tsFacade.createNotionAttribute(notion, notionDTOAttr.getName());
			attr.setDescription(notionDTOAttr.getDescription());
			
		}
	}
	
	private void executeNotionWithDomainStatemetns(NotionDTO notionDTO){
			
		Notion parentNotion = tsFacade.getNotionByUid(notionDTO.getUid());
		if (parentNotion != null){
			List notDTODSList = notionDTO.getDomainStatementDTOList();
			for (int i=0; i<notDTODSList.size(); i++){
				DomainStatementDTO dsDTO = (DomainStatementDTO)notDTODSList.get(i);
				PhraseDTO phrase = dsDTO.getPhraseDTO(); 
				if (phrase instanceof SimpleVerbPhraseDTO){
					DomainStatement ds = tsFacade.createDomainStatement(parentNotion, dsDTO.getName());
					SimpleVerbPhraseDTO svp = (SimpleVerbPhraseDTO)phrase;
					String[] names = {svp.getObject().getNoun().getName()};
					NotionDTO nDTO = rslFacade.getNotionDTO(names);
					Notion diNotion = tsFacade.getNotionByUid(nDTO.getUid());
					ds.setUid(phrase.getUid());
					ds.setDirectNotion(diNotion);
					
				} else if (phrase instanceof ComplexVerbPhraseDTO){
					DomainStatement ds = tsFacade.createDomainStatement(parentNotion, dsDTO.getName());
					ComplexVerbPhraseDTO cvp = (ComplexVerbPhraseDTO)phrase;
					String[] namesIn = {cvp.getObject().getNoun().getName()};
					NotionDTO nDTOin = rslFacade.getNotionDTO(namesIn);
					Notion inNotion = tsFacade.getNotionByUid(nDTOin.getUid());
					ds.setIndirectNotion(inNotion);				
					SimpleVerbPhraseDTO svp = cvp.getSimpleVerbPhrase();
					String[] namesDi = {svp.getObject().getNoun().getName()};
					NotionDTO nDTO = rslFacade.getNotionDTO(namesDi);
					Notion diNotion = tsFacade.getNotionByUid(nDTO.getUid());
					ds.setUid(phrase.getUid());
					ds.setDirectNotion(diNotion);
				}				
			}
		} else {
			System.out.println("Nie mozna znalexc pojecia "+notionDTO.getName());
		}		
	}
	
	private void executeUseCase(TestPackage parent, UseCaseDTO uc){
		List invokeDTOs;
		
		UseCaseTest uct = tsFacade.createUseCaseTest(parent, uc.getName());
		uct.setDescription(uc.getDescription());
		uct.setUcName(uc.getName());
		uct.setUcTrail(uc.getSpecificationPath());
		uct.setUid(uc.getUid());
		
		invokeDTOs = uc.getInvocationRelationshipFromList();
		
		List scenarios = uc.getConstrainedLanguageScenarioDTOList();
		for (int i=0; i<scenarios.size(); i++){
			ConstrainedLanguageScenarioDTO scenario = null;
			UseCaseTestScenario ucts = null;
			
			scenario = (ConstrainedLanguageScenarioDTO)scenarios.get(i);
			ucts = tsFacade.createUseCaseTestScenario(uct, scenario.toString());
			uct.getEReference0().add(ucts);
			
			List sentences = scenario.getScenarioSentenceList();
			int num = 0;
			for (int j=0; j<sentences.size(); j++){
				Object sentence = sentences.get(j);
				
				SVOSentenceDTO svoSentence = null;
				PostconditionSentenceDTO postConditionSentence = null;
				PreconditionSentenceDTO preConditionSentence = null;
				ControlSentenceDTO controlSentence = null;
				ConditionSentenceDTO conditionSentence = null;
				
				UseCaseTestScenarioSentence uctss = null;
				
				if (sentence instanceof SVOSentenceDTO){
					num=num+1;
					sentence = (SVOSentenceDTO)sentences.get(j);
					uctss = tsFacade.createUseCaseTestScenarioSentence(ucts, ((SVOSentenceDTO) sentence).getFullSentenceText());
					ucts.getSentences().add(uctss);
					SVOSentence svo = tsFacade.createSVOSentence(((SVOSentenceDTO) sentence).getFullSentenceText());
					svo.setNumber(num);
					uctss.setScenarioSentence(svo);
					
					VerbPhraseDTO vp = ((SVOSentenceDTO) sentence).getPredicate();
					if (vp != null){						
						DomainStatementDTO dsDTO = rslFacade.getDomainStatementByPhrase(vp);
						if (dsDTO != null){
							DomainStatement ds = tsFacade.getDomainStatementByUid(dsDTO.getPhraseDTO().getUid());
							svo.setPredicate(ds);
						}
					}
				
					
					
					//-------- do usuniecia po wyrzuceniu z metmodelu objektów --------------------
					/*
					if (vp != null){
						if (vp instanceof SimpleVerbPhraseDTO){
							SimpleVerbPhraseDTO svp = (SimpleVerbPhraseDTO)vp;
							NounPhraseDTO np = svp.getObject();
							if (np != null){
								DirectObject diObj = tsFacade.createDirectObject(svo, np.getNounText());
								String[] names = {np.getNoun().getName()};
								NotionDTO notion = ((BusinessLayerFacade)rslFacade).getNotionDTO(names);
								if (notion != null){
									diObj.setGeneralDomain(notion.getDescription());
								}
								svo.setDirectObject(diObj);
							}
						} else if (vp instanceof ComplexVerbPhraseDTO){
							ComplexVerbPhraseDTO cvp = (ComplexVerbPhraseDTO)vp;
							SimpleVerbPhraseDTO svp = cvp.getSimpleVerbPhrase();
							if (svp != null){
								NounPhraseDTO snp = svp.getObject();
								if (snp != null){
									DirectObject diObj = tsFacade.createDirectObject(svo, snp.getNounText());
									NotionDTO notion = rslFacade.getNotionDTO(snp);
									if (notion != null){
										diObj.setGeneralDomain(notion.getDescription());
									}
									svo.setDirectObject(diObj);
								}
							}
							NounPhraseDTO cnp = cvp.getObject();
							if (cnp != null){
								IndirectObject inObj = tsFacade.createIndirectObject(svo, cnp.getNounText());
								NotionDTO notion = rslFacade.getNotionDTO(cnp.getNoun());
								if (notion != null){
									inObj.setGeneralDomain(notion.getDescription());
								}
								svo.setIndirectObject(inObj);
							}
							
						}
					}
					*/
					//----------------------------------------------------------
				} else if (sentence instanceof InvocationSentenceDTO){
					InvocationSentenceDTO invSentence = (InvocationSentenceDTO)sentences.get(j);
						
					uctss = tsFacade.createUseCaseTestScenarioSentence(ucts, invSentence.getInclusionType().name()+" "+((InvocationSentenceDTO) sentence).getInvokedUseCase().getName());
					ucts.getSentences().add(uctss);
					ControlSentence control = tsFacade.createControlSentence(invSentence.getInclusionType().name()+" "+((InvocationSentenceDTO) sentence).getInvokedUseCase().getName());
					uctss.setScenarioSentence(control);
					if (((InvocationSentenceDTO) sentence).getInvocationSource().equals(uc)){
						TestInvocationRelationship testInvokeRelation = tsFacade.createTestInvocationRelationship(uct, null);
						InvocationRelationshipDTO invokeDTO = null;
						for (int k=0; k<invokeDTOs.size(); k++){
							if (invokeDTOs.get(i) != null){
								invokeDTO = (InvocationRelationshipDTO)invokeDTOs.get(k);				
								if (invokeDTO.getTarget().equals(invSentence.getInvokedUseCase()))
									break;
								else
									invokeDTO = null;
							}			
						}
						if (invokeDTO != null){
							testInvokeRelation.setUid(invokeDTO.getUid());					
							includeRelationships.add(testInvokeRelation);
							control.setInvocation(testInvokeRelation);
						}
					}
				} else if (sentence instanceof ConditionSentenceDTO){
					sentence = (ConditionSentenceDTO)sentences.get(j);
					
					uctss = tsFacade.createUseCaseTestScenarioSentence(ucts, ((ConditionSentenceDTO) sentence).getSentenceText());
					ucts.getSentences().add(uctss);
					ConditionSentence condition = tsFacade.createConditionSentence(((ConditionSentenceDTO) sentence).getSentenceText());
					uctss.setScenarioSentence(condition);
					
				} else if (sentence instanceof PreconditionSentenceDTO){
					sentence = (PreconditionSentenceDTO)sentences.get(j);
					
					Condition condition = tsFacade.createCondition(ucts, ((PreconditionSentenceDTO) sentence).getSentenceText(), "pre");
					ucts.setPrecondition(condition);
				} else if (sentence instanceof PostconditionSentenceDTO){
					sentence = (PostconditionSentenceDTO)sentences.get(j);
					
					Condition condition = tsFacade.createCondition(ucts, ((PostconditionSentenceDTO) sentence).getSentenceText(), "post");
					ucts.setPostcondition(condition);
				}
				/*
				sentence = (ConstrainedLanguageSentenceDTO)sentences.get(j);
				uctss = tsFacade.createUseCaseTestScenarioSentence(ucts, sentence.toString());
				ucts.getEReference0().add(uctss);
				*/
				
			}
			
			
		}
	}

	
}