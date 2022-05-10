package eu.remics.recovery.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.ActorOrSystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InclusionType;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.usecases.UseCase;

public class TransformationHelper {
	
	public static String removeRecoveredScenarios(){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		String cache="<recoveredScenarios>";
		for (UseCaseDTO uc:rmh.getTemporaryUseCases()){
			cache+="<script name=\""+uc.getName()+"\" id=\""+((RSLUseCase) uc).getId()+"\" description=\""+uc.getDescription().replaceAll("\n", "\\\\n")+"\">";
			if (!uc.getConstrainedLanguageScenarioDTOList().isEmpty()){
				cache+="<scenario name=\""+uc.getConstrainedLanguageScenarioDTOList().get(0).getName()+"\">";
				for(ConstrainedLanguageSentenceDTO sent:uc.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList()){
					if (sent instanceof SVOSentenceDTO){
						cache+="<svo recipient=\""+((ActorOrSystemElement)((SVOSentenceDTO) sent).getRecipient()).getId()+"\">";
						cache+="<subject nounText=\""+((SVOSentenceDTO) sent).getSubject().getNounText()+(null!=((SVOSentenceDTO) sent).getSubject().getNoun()?"\" id=\""+((Noun)((SVOSentenceDTO) sent).getSubject().getNoun()).getId():"")+"\"/>";
						if (((SVOSentenceDTO) sent).getPredicate() instanceof ComplexVerbPhraseDTO){
							ComplexVerbPhraseDTO cvp = (ComplexVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate();
							cache+="<cvp>";
							cache+="<prep name=\""+cvp.getPreposition().getName()+"\" uid=\""+cvp.getPreposition().getSynonymUid()+"\"/>";
							cache+="<np nounText=\""+cvp.getObject().getNounText()+(null!=cvp.getObject().getNoun()?"\" id=\""+((Noun) cvp.getObject().getNoun()).getId():"")+"\"/>";
							SimpleVerbPhraseDTO svp = ((ComplexVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase();
							cache+="<svp>";
							cache+="<verb name=\""+svp.getVerb().getName()+"\" uid=\""+svp.getVerb().getSynonymUid()+"\"/>";
							cache+="<np nounText=\""+svp.getObject().getNounText()+(null!=svp.getObject().getNoun()?"\" id=\""+((Noun) svp.getObject().getNoun()).getId():"")+"\"/>";
							cache+="</svp>";
							cache+="</cvp>";
						} else {
							SimpleVerbPhraseDTO svp = (SimpleVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate();
							cache+="<svp>";
							cache+="<verb name=\""+svp.getVerb().getName()+"\" uid=\""+svp.getVerb().getSynonymUid()+"\"/>";
							cache+="<np nounText=\""+svp.getObject().getNounText()+(null!=svp.getObject().getNoun()?"\" id=\""+((Noun) svp.getObject().getNoun()).getId():"")+"\"/>";
							cache+="</svp>";
						}
						cache+="</svo>";
					} else if (sent instanceof InvocationSentenceDTO) cache+="<inv id=\">"+((RSLUseCase)((InvocationSentenceDTO) sent).getInvokedUseCase()).getId()+"\"/>";
				}
				cache+="</scenario>";
			}
			cache+="</script>";
		}
		cache+="</recoveredScenarios>";
		boolean end=false;
		shell:
		while(!end){
			for (UseCaseDTO uc:rmh.getTemporaryUseCases()){
				uc.delete();
				continue shell;
			}
			end=true;
		}
		if (null!=rmh.getRecoveredScenariosPackage()) rmh.getRecoveredScenariosPackage().delete();
		return cache;
	}
	
	public static void restoreRecoveredScenarios(String cache) throws SAXException, IOException{
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		RequirementsPackageDTO rs=rmh.getBussinessLayerFacade().createRequirementsPackageDTO();
		rs.setName("RecoveredScenarios");
		rmh.getRequirementsSpecification().addRequirementsPackageDTO(rs);
		DOMParser parser = new DOMParser();
		parser.parse(new InputSource(new StringReader(cache)));
		Map<Integer,UseCaseDTO> map = new HashMap<Integer,UseCaseDTO>();
		for(int i = 0; i < parser.getDocument().getDocumentElement().getChildNodes().getLength(); i++){
			UseCaseDTO uc = rmh.getBussinessLayerFacade().createUseCaseDTO();
			uc.setName(parser.getDocument().getDocumentElement().getChildNodes().item(i).getAttributes().getNamedItem("name").getNodeValue());
			uc.setDescription(parser.getDocument().getDocumentElement().getChildNodes().item(i).getAttributes().getNamedItem("description").getNodeValue().replaceAll("\\\\n", "\n"));
			if (parser.getDocument().getDocumentElement().getChildNodes().item(i).hasChildNodes()){
				map.put(Integer.parseInt(parser.getDocument().getDocumentElement().getChildNodes().item(i).getAttributes().getNamedItem("id").getNodeValue()),uc);
				ConstrainedLanguageScenarioDTO scen = rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
				scen.setName(parser.getDocument().getDocumentElement().getChildNodes().item(i).getFirstChild().getAttributes().getNamedItem("name").getNodeValue());
				uc.addConstrainedLanguageScenario(scen);
				if (null!=rmh.getRecoveredScenariosPackage()) rmh.getRecoveredScenariosPackage().addUseCase(uc);
			}
		}
		for(int i = 0; i < parser.getDocument().getDocumentElement().getChildNodes().getLength(); i++){
			if (!map.containsKey(Integer.parseInt(parser.getDocument().getDocumentElement().getChildNodes().item(i).getAttributes().getNamedItem("id").getNodeValue()))) continue;
			ConstrainedLanguageScenarioDTO scen = map.get(Integer.parseInt(parser.getDocument().getDocumentElement().getChildNodes().item(i).getAttributes().getNamedItem("id").getNodeValue())).getConstrainedLanguageScenarioDTOList().get(0);
			for (int j = 0; j < parser.getDocument().getDocumentElement().getChildNodes().item(i).getFirstChild().getChildNodes().getLength(); j++){
				Node node = parser.getDocument().getDocumentElement().getChildNodes().item(i).getFirstChild().getChildNodes().item(j);
				if (node.getNodeName().equals("inv")){
					InvocationSentenceDTO inv = rmh.getBussinessLayerFacade().createInvocationSentenceDTO();
					inv.setInclusionType(InclusionType.INSERT);
					if (map.containsKey(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()))) inv.setInvokedUseCase(map.get(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue())));
					else inv.setInvokedUseCase(rmh.getBussinessLayerFacade().getUseCaseByVertexID(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue())));
					scen.addScenarioStep(inv);
				} else if (node.getNodeName().equals("svo")) {
					SVOSentenceDTO svo = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
					svo.setRecipient(rmh.getActorOrSystemElementByVertexID(Integer.parseInt(node.getAttributes().getNamedItem("recipient").getNodeValue())));
					NounPhraseDTO subject = rmh.getBussinessLayerFacade().createNounPhraseDTO();
					subject.setNounText(node.getChildNodes().item(0).getAttributes().getNamedItem("nounText").getNodeValue());
					if (null!=node.getChildNodes().item(0).getAttributes().getNamedItem("id")) subject.setNoun(rmh.getNounByVertexId(Integer.parseInt(node.getChildNodes().item(0).getAttributes().getNamedItem("id").getNodeValue())));
					svo.setSubject(subject);
					node = node.getChildNodes().item(1);
					if (node.getNodeName().equals("cvp")){
						ComplexVerbPhraseDTO cvp = rmh.getBussinessLayerFacade().createComplexVerbPhraseDTO();
						cvp.setObject(rmh.getBussinessLayerFacade().createNounPhraseDTO());
						cvp.getObject().setNounText(node.getChildNodes().item(1).getAttributes().getNamedItem("nounText").getNodeValue());
						if (null!=node.getChildNodes().item(1).getAttributes().getNamedItem("id")) cvp.getObject().setNoun(rmh.getNounByVertexId(Integer.parseInt(node.getChildNodes().item(1).getAttributes().getNamedItem("id").getNodeValue())));
						cvp.setPreposition(rmh.getBussinessLayerFacade().createPrepostitionDTO());
						cvp.getPreposition().setName(node.getChildNodes().item(0).getAttributes().getNamedItem("name").getNodeValue());
						cvp.getPreposition().setSynonymUid(Long.parseLong(node.getChildNodes().item(0).getAttributes().getNamedItem("uid").getNodeValue()));
						node=node.getChildNodes().item(2);
						SimpleVerbPhraseDTO svp = rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
						svp.setObject(rmh.getBussinessLayerFacade().createNounPhraseDTO());
						svp.getObject().setNounText(node.getChildNodes().item(1).getAttributes().getNamedItem("nounText").getNodeValue());
						if (null!=node.getChildNodes().item(1).getAttributes().getNamedItem("id")) svp.getObject().setNoun(rmh.getNounByVertexId(Integer.parseInt(node.getChildNodes().item(1).getAttributes().getNamedItem("id").getNodeValue())));
						svp.setVerb(rmh.getBussinessLayerFacade().createVerbDTO());
						svp.getVerb().setName(node.getChildNodes().item(0).getAttributes().getNamedItem("name").getNodeValue());
						svp.getVerb().setSynonymUid(Long.parseLong(node.getChildNodes().item(0).getAttributes().getNamedItem("uid").getNodeValue()));
						cvp.setSimpleVerbPhrase(svp);
						svo.setPredicate(cvp);
					} else if (node.getNodeName().equals("svp")){
						SimpleVerbPhraseDTO svp = rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
						svp.setObject(rmh.getBussinessLayerFacade().createNounPhraseDTO());
						svp.getObject().setNounText(node.getChildNodes().item(1).getAttributes().getNamedItem("nounText").getNodeValue());
						if (null!=node.getChildNodes().item(1).getAttributes().getNamedItem("id")) svp.getObject().setNoun(rmh.getNounByVertexId(Integer.parseInt(node.getChildNodes().item(1).getAttributes().getNamedItem("id").getNodeValue())));
						svp.setVerb(rmh.getBussinessLayerFacade().createVerbDTO());
						svp.getVerb().setName(node.getChildNodes().item(0).getAttributes().getNamedItem("name").getNodeValue());
						svp.getVerb().setSynonymUid(Long.parseLong(node.getChildNodes().item(0).getAttributes().getNamedItem("uid").getNodeValue()));
						svo.setPredicate(svp);
					}
					scen.addScenarioStep(svo);
				}
			}
		}
	}
	
	public static String backupDuplicateUseCaseStructure(){
		String cache="<ducs>";
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		for (Dependency dep:rmh.getBussinessLayerFacade().getDependencyVertices()) if (dep.getClientList().size()>0 && dep.getClientList().get(0) instanceof eu.redseeds.scl.uml.usecases.Actor && !((eu.redseeds.scl.uml.usecases.Actor)dep.getClientList().get(0)).getAllocationToRSLList().isEmpty() && dep.getSupplierList().size()>0 && dep.getSupplierList().get(0) instanceof eu.redseeds.scl.uml.usecases.UseCase && !((eu.redseeds.scl.uml.usecases.UseCase)dep.getSupplierList().get(0)).getAllocationToRSLList().isEmpty()){
			Integer actorid = null;
			shell:
			for (IsAllocatedTo alloc:((eu.redseeds.scl.uml.usecases.Actor)dep.getClientList().get(0)).getAllocationToRSLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
					actorid=((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) alloc.getAllocationSourceList().get(0)).getId();
					break shell;
			}
			if (null==actorid) continue;
			Integer usecaseid = null;
			shell:
			for (IsAllocatedTo alloc:((eu.redseeds.scl.uml.usecases.UseCase)dep.getSupplierList().get(0)).getAllocationToRSLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
					usecaseid=((RSLUseCase) alloc.getAllocationSourceList().get(0)).getId();
					break shell;
			}
			if (null==usecaseid) continue;
			cache+="<assoc actor=\""+actorid+"\" useCase=\""+usecaseid+"\" />";
		}
		cache+="</ducs>";
		return cache;
	}
	
	public static void restoreDuplicatedUseCaseStructure(String cache) throws SAXException, IOException{
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		boolean end = false;
		outershell:
		while (!end) {
			for (RSLUseCase uc:rmh.getBussinessLayerFacade().getRSLUseCaseVertices()){
				boolean find = false;
				shell:
				for (IsAllocatedTo alloc: uc.getAllocationToUMLList()) if (!alloc.isIsGenerated() && !alloc.getAllocationTargetList().isEmpty() && alloc.getAllocationTargetList().get(0) instanceof UseCase){
					for (Stereotype ster:alloc.getStereotypeList()) if(ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
						find=true;
						break shell;
					}
				}
				if (!find){
					UseCase umluc = rmh.getBussinessLayerFacade().createUseCase();
					IsAllocatedTo iat = rmh.getBussinessLayerFacade().createIsAllocatedTo();
					uc.addAllocationToUML(iat);
					umluc.addAllocationToRSL(iat);
					Stereotype ster = rmh.getBussinessLayerFacade().createSclkernel$Stereotype();
					ster.setName("DuplicatedUseCaseStructureForUseRelationship");
					iat.addStereotype(ster);
					continue outershell;
				}
			}
			end=true;
		}
		end=false;
		outershell:
		while(!end){
			for (ActorOrSystemElement act:rmh.getBussinessLayerFacade().getActorOrSystemElementVertices()) if (act instanceof Actor){
				boolean find = false;
				shell:
				for (IsAllocatedTo alloc: act.getAllocationToUMLList()) if (!alloc.isIsGenerated() && !alloc.getAllocationTargetList().isEmpty() && alloc.getAllocationTargetList().get(0) instanceof eu.redseeds.scl.uml.usecases.Actor){
					for (Stereotype ster:alloc.getStereotypeList()) if(ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
						find=true;
						break shell;
					}
				}
				if (!find){
					eu.redseeds.scl.uml.usecases.Actor umlact = rmh.getBussinessLayerFacade().createUml$usecases$Actor();
					IsAllocatedTo iat = rmh.getBussinessLayerFacade().createIsAllocatedTo();
					act.addAllocationToUML(iat);
					umlact.addAllocationToRSL(iat);
					Stereotype ster = rmh.getBussinessLayerFacade().createSclkernel$Stereotype();
					ster.setName("DuplicatedUseCaseStructureForUseRelationship");
					iat.addStereotype(ster);
					continue outershell;
				}
			}
		end=true;
		}
		DOMParser parser = new DOMParser();
		parser.parse(new InputSource(new StringReader(cache)));
		for(int i = 0; i < parser.getDocument().getDocumentElement().getChildNodes().getLength(); i++){
			NamedElement supplier = null;
			shell:
			for (IsAllocatedTo alloc:((RSLUseCase) rmh.getBussinessLayerFacade().getUseCaseByVertexID(Integer.parseInt(parser.getDocument().getDocumentElement().getChildNodes().item(i).getAttributes().getNamedItem("useCase").getNodeValue()))).getAllocationToUMLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
				supplier = (NamedElement) alloc.getAllocationTargetList().get(0);
				break shell;		
			}
			NamedElement client = null;
			shell:
			for (IsAllocatedTo alloc:((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) rmh.getActorByVertexID(Integer.parseInt(parser.getDocument().getDocumentElement().getChildNodes().item(i).getAttributes().getNamedItem("actor").getNodeValue()))).getAllocationToUMLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
				client = (NamedElement) alloc.getAllocationTargetList().get(0);
				break shell;
			}
			boolean exist = false;
			for (Dependency dep : rmh.getBussinessLayerFacade()
					.getDependencyVertices())
				if (!dep.getSupplierList().isEmpty() && dep.getSupplierList().get(0).getId() == supplier.getId()
						&& dep.getClientList().get(0).getId() == client.getId()) {
					exist = true;
					break;
				}
			if (!exist) {
				Dependency de = rmh.getBussinessLayerFacade()
						.createDependency();
				de.addSupplier(supplier);
				de.addClient(client);
			}
		}
	}
	
}
