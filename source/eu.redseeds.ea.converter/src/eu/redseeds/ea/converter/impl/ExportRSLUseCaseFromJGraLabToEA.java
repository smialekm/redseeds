package eu.redseeds.ea.converter.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Status;
import org.sparx.Connector;
import org.sparx.Diagram;
import org.sparx.Element;
import org.sparx.Method;
import org.sparx.Package;
import org.sparx.Scenario;
import org.sparx.ScenarioStep;
import org.xml.sax.SAXException;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import eu.redseeds.ea.converter.Activator;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.actors.ActorsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainSpecification;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionSpecialisation;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsSpecification;
import eu.redseeds.scl.sclkernel.SCLElementsPackage;
import eu.redseeds.scl.sclkernel.Stereotype;


public class ExportRSLUseCaseFromJGraLabToEA {

	private eu.redseeds.scl.SCLGraph graph;
	private JavaEAAPI ea_api = null;
	
	public void exportDataToEA(String repLocation, String tgLocation,
			Graph tgGraph) {
		try {
			Map<UseCaseDTO,Element> useCaseMap = new HashMap<UseCaseDTO,Element>();
			Map<NotionDTO,Element> notionMap = new HashMap<NotionDTO,Element>();
			ea_api = new JavaEAAPI();
			ea_api.openRepositoryFile(repLocation);
			if (tgGraph == null) graph = (SCLGraph) GraphIO.loadGraphFromFile(tgLocation, null);
			else  graph = (SCLGraph) tgGraph;
			ea_api.deleteModel("Requirements");
			ea_api.deleteModel("Model");
			Package model = ea_api.addModel("Requirements", "Model");
			Package pack = ea_api.addPackage(model, "Requirements Specification", "Package", "");
			pack.SetFlags("isModel=1;VICON=1;");
			if(!(pack.Update()))
	    		Activator.log("EA package update error: " + pack.GetLastError(), Status.ERROR);
			Package pack2 = ea_api.addPackage(model, "Domain Specification", "Package", "");
			pack2.SetFlags("isModel=1;VICON=3;");
			if(!(pack2.Update()))
	    		Activator.log("EA package update error: " + pack2.GetLastError(), Status.ERROR);
			for (DomainSpecification ds:graph.getDomainSpecificationVertices())
				for (DomainElementsPackage p:ds.getDomainElementsPackageList()){
					if (p instanceof ActorsPackage){
						Package pack3 = ea_api.addPackage(pack2, p.getName(), "Package", "");
						exportActorToEA((ActorsPackage) p,pack3);
					} else if (p instanceof NotionsPackage){
						Package pack3 = ea_api.addPackage(pack2, p.getName(), "Package", "");
						exportNotionToEA((NotionsPackage) p,pack3,notionMap);
					}
				}
			for (RequirementsSpecification rs:graph.getRequirementsSpecificationVertices())
				for (RequirementsPackage rp:rs.getRequirementsPackageList())
					if (!rp.getName().equals("RecoveredScenarios"))
						exportPackageToEA(rp,pack,useCaseMap);
			for(UseCaseDTO uc:useCaseMap.keySet()) exportUseCaseScenariosToEA(uc,useCaseMap.get(uc),useCaseMap);
			exportRelations(notionMap);
		} catch (Exception ex) {
			Activator.logException(ex);
			// e.printStackTrace();
		}
	}

	private void exportRelations(Map<NotionDTO, Element> notionMap) {
		for (NotionDTO not:notionMap.keySet()){
			Element target = notionMap.get(not);
			for (NotionDTO attr:not.getNotionDTOAttributeList()){
				Element source = notionMap.get(attr);
				Connector cnctr = source.GetConnectors().AddNew("", "Aggregation");
				cnctr.SetSupplierID(target.GetElementID());
				cnctr.Update();
				source.GetConnectors().Refresh();
			}
		}
		for (DomainElementRelationship der:graph.getDomainElementRelationshipVertices())
			if (!der.getSourceList().isEmpty() && notionMap.keySet().contains(der.getSourceList().get(0)) && !der.getTargetList().isEmpty() && notionMap.keySet().contains(der.getTargetList().get(0))){
				Element source = notionMap.get(der.getSourceList().get(0));
				Element target = notionMap.get(der.getTargetList().get(0));
				Connector cnctr = source.GetConnectors().AddNew("", "Association");
				cnctr.SetSupplierID(target.GetElementID());
				cnctr.GetClientEnd().SetCardinality(der.getSourceMultiplicity());
				cnctr.GetSupplierEnd().SetCardinality(der.getTargetMultiplicity());
				if (!der.isDirected()) cnctr.SetDirection("Unspecified");
				cnctr.Update();
				source.GetConnectors().Refresh();
			}
		for (NotionSpecialisation gen:graph.getNotionSpecialisationVertices())
			if (!gen.getSourceList().isEmpty() && notionMap.keySet().contains(gen.getSourceList().get(0)) && !gen.getTargetList().isEmpty() && notionMap.keySet().contains(gen.getTargetList().get(0))){
				Element source = notionMap.get(gen.getSourceList().get(0));
				Element target = notionMap.get(gen.getTargetList().get(0));
				Connector cnctr = source.GetConnectors().AddNew("", "Generalization");
				cnctr.SetSupplierID(target.GetElementID());
				cnctr.Update();
				source.GetConnectors().Refresh();
			}
	}

	private void exportNotionToEA(NotionsPackage gnp, Package pack, Map<NotionDTO,Element> notionMap) {
		for (SCLElementsPackage np:gnp.getNestedPackageList()){
			Package pack2 = ea_api.addPackage(pack, np.getName(), "Package", "");
			exportNotionToEA((NotionsPackage) np, pack2,notionMap);
		}
		List<Element> elements = new ArrayList<Element>();
		for (Notion not:gnp.getNotionList()){
			Element el = ea_api.addPackageElement(pack, ((NotionDTO) not).getName(), "Class", "");
			el.SetNotes(((NotionDTO) not).getDescription());
			el.SetStereotype(getNotionTypeName((NotionDTO) not));
			if(!(el.Update()))
				Activator.log("EA element update error: " + el.GetLastError(), Status.ERROR);
			elements.add(el);
			notionMap.put((NotionDTO) not, el);
			for (DomainStatementDTO ds:((NotionDTO) not).getDomainStatementDTOList()){
				Method met = el.GetMethods().AddNew(ds.getPhraseDTO().toString(), "void");
				met.SetNotes(ds.getDescription());
				met.SetStereotype(ActionTypesEnum.getActionTypeToDisplay(ds));
				met.Update();
				el.GetMethods().Refresh();
			}
		}
		Diagram diag = ea_api.addDiagram(pack, gnp.getName(), "Class", "");
		for (Element el:elements) ea_api.addDiagramElement(diag, el.GetElementID(), null);
		ea_api.setDiagramAutoLayout(diag);
	}

	private String getNotionTypeName(NotionDTO notion){
		String kind=NotionTypesEnum.EMPTY;
		loop:
		for (Stereotype s:((Notion) notion).getStereotypeList()) if (Arrays.asList(NotionTypesEnum.tags()).contains(s.getName())){
			for (NotionTypesEnum val:NotionTypesEnum.values())
				if (val.tag().equals(s.getName())){
					kind = val.toString().replace('_', ' ');
					if (s.getName().equals(NotionTypesEnum.Attribute.tag()) && ! notion.getExtendedDataType().isEmpty())
						kind +=" ("+AttributeDataTypesEnum.getExtendedDataTypeToDisplay(notion)+")";
					break loop;
				}
		}
		return kind;
	}
	
	
	private void exportActorToEA(ActorsPackage gap, Package pack) {
		for (SCLElementsPackage ap:gap.getNestedPackageList()){
			Package pack2 = ea_api.addPackage(pack, ap.getName(), "Package", "");
			exportActorToEA((ActorsPackage) ap, pack2);
		}
		List<Element> elements = new ArrayList<Element>();
		for (Actor act:gap.getActorList()){
			Element el = ea_api.addPackageElement(pack, ((ActorDTO) act).getName(), "Actor", "");
			el.SetNotes(((ActorDTO) act).getDescription());
			if(!(el.Update()))
				Activator.log("EA element update error: " + el.GetLastError(), Status.ERROR);
			elements.add(el);
		}
		Diagram diag = ea_api.addDiagram(pack, gap.getName(), "Class", "");
		for (Element el:elements) ea_api.addDiagramElement(diag, el.GetElementID(), null);
		ea_api.setDiagramAutoLayout(diag);
	}

	private Package exportPackageToEA(RequirementsPackage rp,
			Package pack,
			Map<UseCaseDTO,Element> useCaseMap) throws SAXException, IOException {
		Package pkg = ea_api.addPackage(pack, rp.getName(), "Package", "");
		for (SCLElementsPackage rqp:rp.getNestedPackageList()) exportPackageToEA((RequirementsPackage) rqp, pkg, useCaseMap);
		List<Element> elements = new ArrayList<Element>();
		for (Requirement req:rp.getRequirementList()){
			if (req instanceof RSLUseCase) elements.add(exportUseCaseToEA((UseCaseDTO) req,pkg,useCaseMap));
			else elements.add(exportRequirementsToEA((RequirementDTO) req,pkg));
		}
		Diagram diag = ea_api.addDiagram(pkg, rp.getName(), "Use Case", "");
		for (Element el:elements) ea_api.addDiagramElement(diag, el.GetElementID(), null);
		List<Element> actors = new ArrayList<Element>();
		for (Requirement req:rp.getRequirementList())
			if (req instanceof RSLUseCase)
				for (ActorDTO act:((UseCaseDTO) req).getActors())
					if (!actors.contains(act)){
						Element el = ea_api.getElement(act.getName(),"Actor");
						if (null!=el) ea_api.addDiagramElement(diag, el.GetElementID(), null);
					}
		ea_api.setDiagramAutoLayout(diag);
		return pkg;
	}

	private Element exportRequirementsToEA(RequirementDTO req, Package pkg) {
		Element el = ea_api.addPackageElement(pkg, req.getName(), "Requirement", "");
		el.SetNotes(req.getDescription());
		if(!(el.Update()))
			Activator.log("EA element update error: " + el.GetLastError(), Status.ERROR);
		return el;
	}

	private Element exportUseCaseToEA(UseCaseDTO uc, Package pkg, Map<UseCaseDTO,Element> useCaseMap) throws SAXException, IOException {
		Element el = ea_api.addPackageElement(pkg, uc.getName(), "UseCase", "");
		el.SetNotes(uc.getDescription());
		if(!(el.Update()))
			Activator.log("EA element update error: " + el.GetLastError(), Status.ERROR);
		useCaseMap.put(uc, el);
		for (ActorDTO act:uc.getDependentActors()){
			Element actor = ea_api.getElement(act.getName(), "Actor");
			Connector cnctr = actor.GetConnectors().AddNew("", "UseCase");
			cnctr.SetSupplierID(el.GetElementID());
			cnctr.Update();
			actor.GetConnectors().Refresh();
		}
		return el;
	}
	
	private void exportUseCaseScenariosToEA(UseCaseDTO uc, Element el, Map<UseCaseDTO,Element> useCaseMap) throws SAXException, IOException {
		Map<ConstrainedLanguageScenarioDTO,Scenario> map = new HashMap<ConstrainedLanguageScenarioDTO,Scenario>();
		Scenario sc = ea_api.addScenario(el, uc.getConstrainedLanguageScenarioDTOList().get(0).getName(),"Basic Path","");
		map.put(uc.getConstrainedLanguageScenarioDTOList().get(0), sc);
		exportSentencesToEA(uc.getConstrainedLanguageScenarioDTOList().get(0),sc,el,0,map,useCaseMap);
	}

	private String exportSentencesToEA(ConstrainedLanguageScenarioDTO scen, Scenario sc, Element el, int t, Map<ConstrainedLanguageScenarioDTO,Scenario> map, Map<UseCaseDTO,Element> useCaseMap) {
		int c = 0;
		boolean j = true;
		Scenario nsc = null;
		String rejoin = null;
		for (ConstrainedLanguageSentenceDTO sent:scen.getScenarioSentenceList()){
			if (c>=t){
				if (sent instanceof SVOSentenceDTO || sent instanceof InvocationSentenceDTO){
					if (sent instanceof SVOSentenceDTO){
						if (null!=((SVOSentenceDTO) sent).getSubject()){
							if (null==((BusinessLayerFacade) graph).getActorDTO(((SVOSentenceDTO) sent).getSubject())) j = false;
							else j = true;
						} else j=!j;
					} else if (sent instanceof InvocationSentenceDTO){
						ConstrainedLanguageSentenceDTO nextSentence = scen.getNextSentence(sent);
						while (!(nextSentence instanceof SVOSentence) && null!=nextSentence) nextSentence=scen.getNextSentence(nextSentence);
						if (null==nextSentence) j=!j;
						else if (null!=((SVOSentenceDTO) nextSentence).getSubject()){
							if (null==((BusinessLayerFacade) graph).getActorDTO(((SVOSentenceDTO) nextSentence).getSubject())) j = false;
							else j = true;
						} else j=!j;
					}
					ScenarioStep st;
					if (sent instanceof SVOSentence)
						st = ea_api.addStep(sc,((SVOSentenceDTO)sent).getFullSentenceText(), j?"1":"0");
					else {
						UseCaseDTO iuc = ((InvocationSentenceDTO) sent).getInvokedUseCase();
						st = ea_api.addStep(sc, "[Invokes: "+(null!=iuc?iuc.getName():"")+"]", j?"1":"0");
						if (null!=iuc){
							st.SetLink(useCaseMap.get(iuc).GetElementGUID());
							st.Update();
							sc.GetSteps().Refresh();
							sc.Update();
						}
					}
					if (null!=nsc){
						ea_api.addExtension(st, sc, nsc.GetName(), nsc.GetScenarioGUID(), rejoin);
						nsc = null;
						rejoin = null;
					}
				} else if (sent instanceof ConditionSentenceDTO){
					loop:
					for (ConstrainedLanguageScenarioDTO nextScen:scen.getPreviousSentence(sent).getOwningScenarios())
						if (scen.getParent().getConstrainedLanguageScenarioDTOList().indexOf(nextScen)>scen.getParent().getConstrainedLanguageScenarioDTOList().indexOf(scen)){
							for (ConstrainedLanguageScenarioDTO scnr:scen.getPreviousSentence(sent).getOwningScenarios())
								if (scen.getParent().getConstrainedLanguageScenarioDTOList().indexOf(nextScen)<scen.getParent().getConstrainedLanguageScenarioDTOList().indexOf(scen)){
									if (null!=nextScen.getNextSentence(nextScen.getNextSentence(scen.getPreviousSentence(sent))) && null!=scnr.getNextSentence(scnr.getNextSentence(scen.getPreviousSentence(sent))) && ((ConstrainedLanguageSentence) nextScen.getNextSentence(nextScen.getNextSentence(scen.getPreviousSentence(sent)))).getId()==((ConstrainedLanguageSentence) scnr.getNextSentence(scnr.getNextSentence(scen.getPreviousSentence(sent)))).getId())
										continue loop;
								}
							String name = nextScen.getName();
							if (containsScenarioOfName(name,el)){
								int l = 1;
								while (containsScenarioOfName(name+l,el)) l++;
								name=name+l;
							}
							nsc = ea_api.addScenario(el, name,"Alternate","");
							map.put(nextScen, nsc);
							rejoin = exportSentencesToEA(nextScen,nsc,el,scen.getScenarioSentenceList().indexOf(sent)+1, map, useCaseMap);
							if(!(nsc.Update()))
								Activator.log("EA scenario update error: " + nsc.GetLastError(), Status.ERROR);
						}
				}
			}
			c++;
		}
		if (scen.getLastSentence() instanceof RejoinSentenceDTO){
			ConstrainedLanguageSentenceDTO lsent = ((RejoinSentenceDTO) scen.getLastSentence()).getRejoinedSentence();
			if (null==lsent) return null;
			ConstrainedLanguageScenarioDTO lscen = lsent.getOwningScenarios().get(0);
			short i = 0;
			for(ConstrainedLanguageSentenceDTO sent:lscen.getScenarioSentenceList()){
				if (((ConstrainedLanguageSentence)sent).getId()==((ConstrainedLanguageSentence)lsent).getId()) break;
				if (sent instanceof SVOSentenceDTO || sent instanceof InvocationSentenceDTO) i++;
			}
			return map.get(lscen).GetSteps().GetAt(i).GetStepGUID();
		}
		return null;
	}

	private boolean containsScenarioOfName(String name, Element el){
		for (Scenario scen:el.GetScenarios()) if (scen.GetName().equals(name)) return true;
		return false;
	}
	
}
