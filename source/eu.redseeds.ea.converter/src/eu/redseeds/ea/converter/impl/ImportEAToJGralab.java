// Author:- John Paul Brogan HWU Date:- 01/06/2008 (refactoring done by Edgars Celms, UL)

package eu.redseeds.ea.converter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import de.uni_koblenz.jgralab.GraphIO;
import eu.redseeds.ea.converter.Activator;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.ea.Attribute;
import eu.redseeds.scl.ea.Connector;
import eu.redseeds.scl.ea.ConnectorEnd;
import eu.redseeds.scl.ea.Element;
import eu.redseeds.scl.ea.Method;
import eu.redseeds.scl.ea.Model;
import eu.redseeds.scl.ea.Package;
import eu.redseeds.scl.ea.Parameter;
import eu.redseeds.scl.ea.TaggedValue;

public class ImportEAToJGralab {

	private JavaEAAPI ea_api = null;
	private SCLGraph sclGraph = null;
	private String eaModel = null;
	private org.sparx.Repository eaRep = null;
	// private Map< org.sparx.Element, eu.redseeds.scl.ea.Element >
	// EAElem_JGElem; // contains .....
	private Map<String, eu.redseeds.scl.ea.Element> EAElem_JGElem; // contains
	// .....
	private Map<String, eu.redseeds.scl.ea.Method> EAMeth_JGMeth; // contains

	// .....

	public ImportEAToJGralab() {
	}

	public void importDataFromEA(String repLocation, String tgLocation,
			SCLGraph tgGraph, String eaModel) {
		try {

			EAElem_JGElem = new HashMap<String, eu.redseeds.scl.ea.Element>();
			EAMeth_JGMeth = new HashMap<String, eu.redseeds.scl.ea.Method>();
			this.eaModel = eaModel;

			ea_api = new JavaEAAPI();
			// Activator.log("after JavaEAAPI", Status.INFO);
			// create and open the .eap EA repository project file
			eaRep = ea_api.openRepositoryFile(repLocation);

			// load the TG graph
			if (tgGraph == null) {
				sclGraph = (eu.redseeds.scl.SCLGraph) GraphIO
						.loadGraphFromFile(tgLocation, null);
			} else {
				sclGraph = tgGraph;
			}

			clearEADataFromGraph();
			processModels();
			processAttributesMethodsClassifiers();
			processConnectors();

			ea_api.exitRepositoryFile();
			// GraphIO.saveGraphToFile(tgLocation, sclGraph, null); //TODO
			// refresh SC ???

		} catch (Exception ex) {
			Activator.logException(ex);
			// e.printStackTrace();
		}
	} // end of importDataFromEA(...)

	private void clearEADataFromGraph() {
		List<Model> delModList = new ArrayList<Model>();
		for (Model mod : sclGraph.getEa$ModelVertices()) {
			delModList.add(mod);
		}

		for (Model mod : delModList) {
			mod.delete();
		}

		List<Connector> delConnList = new ArrayList<Connector>();
		for (Connector conn : sclGraph.getEa$ConnectorVertices()) {
			delConnList.add(conn);
		}

		// Activator.log("delConnList ->" + delConnList.size(), Status.INFO);
		for (Connector conn : delConnList) {
			conn.delete();
		}
	}

	// create models and export all objects of this model
	private void processModels() {
		org.sparx.Collection<org.sparx.Package> models = eaRep.GetModels();

		for (org.sparx.Package eaMod : models) {
			// if only one particular model has to be extracted
			if (eaModel != null && !eaModel.isEmpty()) {
				if (eaModel.equals(eaMod.GetName())) {
					Model tgMod = createTgModel(eaMod);
					processPackagePackages(eaMod, tgMod);
					break;
				}
				continue;
			}
			// all models to be extracted, except "Requirements Visualisation"
			else {
				if (!eaMod.GetName().equals("Requirements Visualisation")) {
					Model tgMod = createTgModel(eaMod);
					processPackagePackages(eaMod, tgMod);
				}
			}
		}
	}

	private Model createTgModel(org.sparx.Package eaMod) {
		Model tmpMod = sclGraph.createEa$Model();
		tmpMod.setName(eaMod.GetName());
		tmpMod.setGuid(eaMod.GetPackageGUID()); // ???
		return tmpMod;
	}

	// recursive method to process all subpackages of particular package
	private void processPackagePackages(org.sparx.Package eaPack, Package tgPack) {

		org.sparx.Collection<org.sparx.Package> pkgs = eaPack.GetPackages();
		for (org.sparx.Package tmpEaPack : pkgs) {
			Package newTgPack = sclGraph.createEa$Package();
			newTgPack.setName(tmpEaPack.GetName());
			tgPack.addPackages(newTgPack);

			processPackagePackages(tmpEaPack, newTgPack); // recursive call
		}

		processPackageElements(eaPack, tgPack);
	}

	// method to process all elements of particular package
	private void processPackageElements(org.sparx.Package eaPack, Package tgPack) {
		org.sparx.Collection<org.sparx.Element> elems = eaPack.GetElements();
		for (org.sparx.Element tmpEaElem : elems) {
			if (tmpEaElem.GetParentID() > 0) {
				continue;
			}
			Element newTgElem = sclGraph.createEa$Element();
			EAElem_JGElem.put(tmpEaElem.GetElementGUID(), newTgElem);
			updateTgElement(tmpEaElem, newTgElem);
			tgPack.addElements(newTgElem);

			processElementElements(tmpEaElem, newTgElem);
		}

		// process the associated element object for this package, used also to
		// store redseeds_uid
		org.sparx.Element specialEAElement = eaPack.GetElement();

		if (specialEAElement == null) {
			// Activator.log("elem == null :" + eaPack.GetName(), Status.INFO);
			return;
		}

		Element newTgElem = sclGraph.createEa$Element();
		EAElem_JGElem.put(specialEAElement.GetElementGUID(), newTgElem);
		updateTgElement(specialEAElement, newTgElem);
		tgPack.addElements(newTgElem);
	}

	// recursive method to process all subelements of particular element
	private void processElementElements(org.sparx.Element eaParentEl,
			Element parentEl) {
		org.sparx.Collection<org.sparx.Element> elems = eaParentEl
				.GetElements();
		for (org.sparx.Element tmpEaElem : elems) {
			Element newTgElem = sclGraph.createEa$Element();
			EAElem_JGElem.put(tmpEaElem.GetElementGUID(), newTgElem);
			updateTgElement(tmpEaElem, newTgElem);
			parentEl.addElements(newTgElem);

			processElementElements(tmpEaElem, newTgElem); // recursive call
		}
	}

	// update tgElement
	private void updateTgElement(org.sparx.Element eaElem, Element tgElem) {

		tgElem.setName(eaElem.GetName());
		tgElem.setGuid(eaElem.GetElementGUID());
		tgElem.setStereotype(eaElem.GetStereotype());
		tgElem.setType(eaElem.GetType());

		// type mismatch for isAbstract ??
		if (eaElem.GetAbstract() != null
				&& eaElem.GetAbstract().equals("false")) {
			tgElem.setIsAbstract(false);
		} else if (eaElem.GetAbstract() != null
				&& eaElem.GetAbstract().equals("true")) {
			tgElem.setIsAbstract(true);
		}

		tgElem.setIsLeaf(eaElem.GetIsLeaf());
		tgElem.setSubtype(eaElem.GetSubtype());
		tgElem.setTag(eaElem.GetTag());
		tgElem.setType(eaElem.GetType());
		tgElem.setVisibility(eaElem.GetVisibility());

		org.sparx.Collection<org.sparx.TaggedValue> tagValues = eaElem
				.GetTaggedValues();
		for (org.sparx.TaggedValue tagValue : tagValues) {
			TaggedValue newTagValue = sclGraph.createTaggedValue();
			newTagValue.setName(tagValue.GetName());
			newTagValue.setValue(tagValue.GetValue());
			newTagValue.setGuid(tagValue.GetPropertyGUID());
			tgElem.addTaggedValues(newTagValue);
		}
	}

	// update tgAttribute
	private void updateTgAttribute(org.sparx.Attribute eaAttr, Attribute tgAttr) {
		tgAttr.setGuid(eaAttr.GetAttributeGUID());
		tgAttr.setName(eaAttr.GetName());
		tgAttr.setStereotype(eaAttr.GetStereotype());
		tgAttr.setType(eaAttr.GetType());
		tgAttr.setAllowDuplicates(eaAttr.GetAllowDuplicates());
		tgAttr.setDefaultValue(eaAttr.GetDefault());
		tgAttr.setIsConst(eaAttr.GetIsConst());
		tgAttr.setIsDerived(eaAttr.GetIsDerived());
		tgAttr.setIsOrdered(eaAttr.GetIsOrdered());
		tgAttr.setIsStatic(eaAttr.GetIsStatic());
		tgAttr.setLowerBound(eaAttr.GetLowerBound());
		tgAttr.setUpperBound(eaAttr.GetUpperBound());
		tgAttr.setVisibility(eaAttr.GetVisibility());

		org.sparx.Collection<org.sparx.AttributeTag> attrTags = eaAttr
				.GetTaggedValues();
		for (org.sparx.AttributeTag tagValue : attrTags) {
			TaggedValue newTagValue = sclGraph.createTaggedValue();
			newTagValue.setName(tagValue.GetName());
			newTagValue.setValue(tagValue.GetValue());
			newTagValue.setGuid(tagValue.GetTagGUID());
			tgAttr.addTaggedValues(newTagValue);
		}

		int tmpClassifier = eaAttr.GetClassifierID();
		if (tmpClassifier > 0) { // workaround for strange EA behaviour
			org.sparx.Element eaClassElem = eaRep.GetElementByID(tmpClassifier);
			if (eaClassElem != null) {
				Element jgClassElem = EAElem_JGElem.get(eaClassElem
						.GetElementGUID());
				if (jgClassElem != null) {
					tgAttr.addClassifier(jgClassElem);
				}
			}
		}
	}

	// update tgMethod
	private void updateTgMethod(org.sparx.Method eaMeth, Method tgMeth) {

		tgMeth.setGuid(eaMeth.GetMethodGUID());
		tgMeth.setName(eaMeth.GetName());
		tgMeth.setStereotype(eaMeth.GetStereotype());
		tgMeth.setType(eaMeth.GetReturnType()); // ??
		tgMeth.setCode(eaMeth.GetCode());
		tgMeth.setIsAbstract(eaMeth.GetAbstract());
		tgMeth.setIsStatic(eaMeth.GetIsStatic());
		tgMeth.setThrowsException(eaMeth.GetThrows());
		tgMeth.setVisibility(eaMeth.GetVisibility());

		org.sparx.Collection<org.sparx.MethodTag> methTags = eaMeth
				.GetTaggedValues();
		for (org.sparx.MethodTag tagValue : methTags) {
			TaggedValue newTagValue = sclGraph.createTaggedValue();
			newTagValue.setName(tagValue.GetName());
			newTagValue.setValue(tagValue.GetValue());
			newTagValue.setGuid(tagValue.GetTagGUID());
			tgMeth.addTaggedValues(newTagValue);
		}

		int tmpClassifier = new Integer(eaMeth.GetClassifierID()); // ?? String
		if (tmpClassifier > 0) { // workaround for strange EA behaviour
			org.sparx.Element eaClassElem = eaRep.GetElementByID(tmpClassifier);
			if (eaClassElem != null) {
				Element jgClassElem = EAElem_JGElem.get(eaClassElem
						.GetElementGUID());
				if (jgClassElem != null) {
					tgMeth.addClassifier(jgClassElem);
				}
			}
		}

		org.sparx.Collection<org.sparx.Parameter> methParams = eaMeth
				.GetParameters();

		// sort parameters by Position attribute - to ensure right ordering in
		// tg
		SortedMap<Integer, org.sparx.Parameter> tmpSortedMap = new TreeMap<Integer, org.sparx.Parameter>();
		for (org.sparx.Parameter eaParam : methParams) {
			tmpSortedMap.put(eaParam.GetPosition(), eaParam);
		}

		for (Integer tmpKey : tmpSortedMap.keySet()) {
			org.sparx.Parameter eaParam = tmpSortedMap.get(tmpKey);
			// for (org.sparx.Parameter eaParam : methParams) {
			Parameter newParameter = sclGraph.createEa$Parameter();
			newParameter.setName(eaParam.GetName());
			newParameter.setGuid(eaParam.GetParameterGUID());
			newParameter.setStereotype(eaParam.GetStereotype());
			newParameter.setType(eaParam.GetType());
			newParameter.setDefaultValue(eaParam.GetDefault());
			newParameter.setKind(eaParam.GetKind()); // ?? :: for enums in
			// jgralab
			newParameter.setPosition(eaParam.GetPosition());

			// workaround - no taggedvalues support in ea for parameters
			// store redseeds_uid in StyleEx attribute in a format:
			// redseeds_uidxxxxx....xxxredseeds_uid
			String tmpUID = eaParam.GetNotes();
			if (tmpUID != null && !tmpUID.isEmpty()) {
				int startIdx = tmpUID.indexOf("redseeds_uid");
				int endIdx = tmpUID.lastIndexOf("redseeds_uid");
				if (startIdx != -1 && endIdx != -1 && startIdx != endIdx) {
					tmpUID = tmpUID.substring(startIdx + 12, endIdx);
					TaggedValue newTagValue = sclGraph.createTaggedValue();
					newTagValue.setName("redseeds_uid");
					newTagValue.setValue(tmpUID);
					newParameter.addTaggedValues(newTagValue);
				}
			}

			tmpClassifier = new Integer(eaParam.GetClassifierID()); // ?? String
			if (tmpClassifier > 0) { // workaround for strange EA behaviour
				org.sparx.Element eaClassElem = eaRep
						.GetElementByID(tmpClassifier);
				if (eaClassElem != null) {
					Element jgClassElem = EAElem_JGElem.get(eaClassElem
							.GetElementGUID());
					if (jgClassElem != null) {
						newParameter.addClassifier(jgClassElem);
					}
				}
			}

			tgMeth.addParameters(newParameter);
		}
	}

	private void updateTgConnector(org.sparx.Connector eaConn, Connector tgConn) {
		tgConn.setGuid(eaConn.GetConnectorGUID());
		tgConn.setName(eaConn.GetName());
		tgConn.setStereotype(eaConn.GetStereotype());
		tgConn.setType(eaConn.GetType());
		tgConn.setDirection(eaConn.GetDirection());
		tgConn.setSequenceNo(eaConn.GetSequenceNo());
		tgConn.setSubtype(eaConn.GetSubtype());
		tgConn.setTransitionGuard(eaConn.GetTransitionGuard());

		org.sparx.Collection<org.sparx.ConnectorTag> connTags = eaConn
				.GetTaggedValues();
		for (org.sparx.ConnectorTag tagValue : connTags) {
			TaggedValue newTagValue = sclGraph.createTaggedValue();
			newTagValue.setName(tagValue.GetName());
			newTagValue.setValue(tagValue.GetValue());
			newTagValue.setGuid(tagValue.GetTagGUID());
			tgConn.addTaggedValues(newTagValue);

			// if message then try to set reference to the appropriate method
			if (eaConn.GetType().equals("Sequence")
					&& tagValue.GetName().equals("operation_guid")) {
				Method jgMeth = EAMeth_JGMeth.get(tagValue.GetValue());
				if (jgMeth != null) {
					newTagValue.addReferencedObj(jgMeth);
				}
			}
		}

		// if message is return message, PDATA4 == 1
        if (eaConn.GetType().equals("Sequence") &&
                eaConn.MiscData(3).equals("1") ) {
            tgConn.setStereotype("return");
        }

	}

	private void updateTgConnectorEnd(org.sparx.ConnectorEnd eaConnEnd,
			ConnectorEnd tgConnEnd) {
		// tgConnEnd.setGuid(eaConnEnd.getGuid);
		tgConnEnd.setName(eaConnEnd.GetRole());
		tgConnEnd.setStereotype(eaConnEnd.GetStereotype());
		// tgConnEnd.setType(eaConnEnd.GetType());
		tgConnEnd.setAggregation(eaConnEnd.GetAggregation());

		// type mismatch for allowDuplicates ??
		String strAllowDuplicates = "false";
		if (eaConnEnd.GetAllowDuplicates()) {
			strAllowDuplicates = "true";
		}
		tgConnEnd.setAllowDuplicates(strAllowDuplicates);

		tgConnEnd.setCardinality(eaConnEnd.GetCardinality());
		tgConnEnd.setConstraint(eaConnEnd.GetConstraint());
		tgConnEnd.setDerived(eaConnEnd.GetDerived());
		tgConnEnd.setDerivedUnion(eaConnEnd.GetDerivedUnion());

		// type mismatch for IsChangable ??
		if (eaConnEnd.GetIsChangeable() != null
				&& eaConnEnd.GetIsChangeable().equals("false")) {
			tgConnEnd.setIsChangable(false);
		} else if (eaConnEnd.GetIsChangeable() != null
				&& eaConnEnd.GetIsChangeable().equals("true")) {
			tgConnEnd.setIsChangable(true);
		}

		tgConnEnd.setIsNavigable(eaConnEnd.GetIsNavigable());
		tgConnEnd.setNavigable(eaConnEnd.GetNavigable());
		tgConnEnd.setOrdering(eaConnEnd.GetOrdering());
		tgConnEnd.setQualifier(eaConnEnd.GetQualifier());
		tgConnEnd.setRoleType(eaConnEnd.GetRoleType());
		tgConnEnd.setVisibility(eaConnEnd.GetVisibility());

		org.sparx.Collection<org.sparx.RoleTag> connEndTags = eaConnEnd
				.GetTaggedValues();
		for (org.sparx.RoleTag tagValue : connEndTags) {
			TaggedValue newTagValue = sclGraph.createTaggedValue();
			newTagValue.setName(tagValue.GetTag());
			newTagValue.setValue(tagValue.GetValue());
			tgConnEnd.addTaggedValues(newTagValue);
		}
	}

	// next iteration
	// process all Attributes and Methods with parameters
	// process classifiers for Elements, Attributes, Methods, Parameters
	// process TaggedValues for Attributes and Methods
	private void processAttributesMethodsClassifiers() {
		for (String eaElemGUID : EAElem_JGElem.keySet()) {
			org.sparx.Element eaElem = eaRep.GetElementByGuid(eaElemGUID);
			Element jgElem = EAElem_JGElem.get(eaElem.GetElementGUID());
			if (jgElem == null) {
				continue; // security guard :)
			}

			// process Element classifier
			int tmpClassifier = eaElem.GetClassfierID();
			if (tmpClassifier > 0) { // workaround for strange EA behaviour
				org.sparx.Element eaClassElem = eaRep
						.GetElementByID(tmpClassifier);
				if (eaClassElem != null) {
					Element jgClassElem = EAElem_JGElem.get(eaClassElem
							.GetElementGUID());
					if (jgClassElem != null) {
						jgElem.addClassifier(jgClassElem);
					}
				}
			}

			// process Element attributes
			org.sparx.Collection<org.sparx.Attribute> eaAttrs = eaElem
					.GetAttributes();
			for (org.sparx.Attribute eaAttr : eaAttrs) {
				Attribute newAttribute = sclGraph.createEa$Attribute();
				updateTgAttribute(eaAttr, newAttribute);
				jgElem.addAttributes(newAttribute);
			}

			// process Element methods
			org.sparx.Collection<org.sparx.Method> eaMeths = eaElem
					.GetMethods();
			for (org.sparx.Method eaMeth : eaMeths) {
				Method newMethod = sclGraph.createMethod();
				updateTgMethod(eaMeth, newMethod);
				jgElem.addMethods(newMethod);
				EAMeth_JGMeth.put(eaMeth.GetMethodGUID(), newMethod);
			}

		} // for ( Element elem : JGElem_EAElem.keySet() )
	} // end of processAttributesMethodsClassifiers()

	// next iteration
	// process connectors and connectorEnds for all Elements
	// (associations in "normal" diagrams, messages in sequence diagrams)
	// process tagged values for connectors and connectorEnds
	private void processConnectors() {
		// sort messages by SequenceNo attribute - to ensure right ordering in
		// tg for messages
		// TODO to invent another solution in case of performance problems!!!
		List<org.sparx.Connector> tmpSortedConnList = new ArrayList<org.sparx.Connector>();

		for (String eaElemGUID : EAElem_JGElem.keySet()) {
			org.sparx.Element eaElem = eaRep.GetElementByGuid(eaElemGUID);
			if (eaElem == null) {
				break;
			}
			org.sparx.Collection<org.sparx.Connector> eaConnectors = eaElem
					.GetConnectors();
			for (org.sparx.Connector eaConnector : eaConnectors) {
				if (ConnectorListContainsKey(tmpSortedConnList, eaConnector) == false) {
					tmpSortedConnList.add(eaConnector);
				}
			}
		}
		Collections.sort(tmpSortedConnList,
				new Comparator<org.sparx.Connector>() {
					public int compare(org.sparx.Connector o1,
							org.sparx.Connector o2) {
						return o1.GetSequenceNo() - o2.GetSequenceNo();
					}
				});
		// --- end of sort messages ---------------

		for (org.sparx.Connector eaConnector : tmpSortedConnList) {
			org.sparx.Element eaElem = eaRep.GetElementByID(eaConnector
					.GetSupplierID());
			org.sparx.Element eaClElem = eaRep.GetElementByID(eaConnector
					.GetClientID());
			// Element jgElem = GetValue_From_EAJGMap(eaElem);
			// Element jgClElem = GetValue_From_EAJGMap(eaClElem);
			Element jgElem = EAElem_JGElem.get(eaElem.GetElementGUID());
			Element jgClElem = EAElem_JGElem.get(eaClElem.GetElementGUID());
			if (jgElem == null || jgClElem == null) {
				continue; // security guard :)
			}

			Connector newConnector = sclGraph.createEa$Connector();
			updateTgConnector(eaConnector, newConnector);

			ConnectorEnd newSuplConnectorEnd = sclGraph.createEa$ConnectorEnd();
			updateTgConnectorEnd(eaConnector.GetSupplierEnd(),
					newSuplConnectorEnd);
			newConnector.addSupplierEnd(newSuplConnectorEnd);

			ConnectorEnd newClConnectorEnd = sclGraph.createEa$ConnectorEnd();
			updateTgConnectorEnd(eaConnector.GetClientEnd(), newClConnectorEnd);
			newConnector.addClientEnd(newClConnectorEnd);

			jgElem.addConn_su(newConnector);
			jgClElem.addConn_cl(newConnector);
		} // for ( org.sparx.Connector eaConnector : tmpSortedConnList )
	}

	// because of strange EA API behaviour - each request for EA objects returns
	// the same object
	// as different one from JAVA point of view and obj1.equal(obj1) are not
	// implemented properly !!!
	private boolean ConnectorListContainsKey(
			List<org.sparx.Connector> connList, org.sparx.Connector eaConnector) {
		for (org.sparx.Connector eaConn : connList) {
			if (eaConn.GetConnectorGUID()
					.equals(eaConnector.GetConnectorGUID())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * //next iteration //process connectors and connectorEnds for all Elements
	 * // (associations in "normal" diagrams, messages in sequence diagrams)
	 * //process tagged values for connectors and connectorEnds private void
	 * processConnectors() {
	 * 
	 * // Map < org.sparx.Connector, eu.redseeds.scl.ea.Connector > crEaConnMap
	 * = // new HashMap < org.sparx.Connector, eu.redseeds.scl.ea.Connector> ();
	 * Map < String, eu.redseeds.scl.ea.Connector > crEaConnMap = new HashMap <
	 * String, eu.redseeds.scl.ea.Connector> ();
	 * 
	 * //sort messages by SequenceNo attribute - to ensure right ordering in tg
	 * for messages List <org.sparx.Connector> tmpSortedConnList = new ArrayList
	 * <org.sparx.Connector> (); // List <String> tmpSortedConnList = new
	 * ArrayList <String> ();
	 * 
	 * for ( org.sparx.Element eaElem : EAElem_JGElem.keySet() ) { if
	 * (eaElem.GetType() != null && eaElem.GetType().equals("Sequence")) {
	 * org.sparx.Collection<org.sparx.Connector> eaConnectors =
	 * eaElem.GetConnectors(); for (org.sparx.Connector eaConnector :
	 * eaConnectors) { // if ( tmpSortedConnList.contains(eaConnector) == false
	 * ) if ( ConnectorListContainsKey(tmpSortedConnList,eaConnector) == false )
	 * tmpSortedConnList.add(eaConnector); } } } Collections.sort(
	 * tmpSortedConnList, new Comparator <org.sparx.Connector > () { public int
	 * compare(org.sparx.Connector o1, org.sparx.Connector o2) { return
	 * o1.GetSequenceNo() - o2.GetSequenceNo(); } }); // --- end of sort
	 * messages --------------- Activator.log("tmpSortedConnList -> " +
	 * tmpSortedConnList, Status.INFO);
	 * 
	 * for ( org.sparx.Element eaElem : EAElem_JGElem.keySet() ) { Element
	 * jgElem = EAElem_JGElem.get( eaElem ); if (jgElem == null) continue;
	 * //security guard :)
	 * 
	 * //process Element connectors org.sparx.Collection<org.sparx.Connector>
	 * eaConnectors = eaElem.GetConnectors(); for (org.sparx.Connector
	 * eaConnector : eaConnectors) { if
	 * (crEaConnMap.containsKey(eaConnector.GetConnectorGUID()) == false) {
	 * //not yet created Connector newConnector = sclGraph.createEa_Connector();
	 * Activator.log("createEa_Connector -> " + eaConnector.GetConnectorGUID(),
	 * Status.INFO);
	 * 
	 * updateTgConnector(eaConnector, newConnector);
	 * crEaConnMap.put(eaConnector.GetConnectorGUID(), newConnector);
	 * 
	 * ConnectorEnd newSuplConnectorEnd = sclGraph.createEa_ConnectorEnd();
	 * updateTgConnectorEnd(eaConnector.GetSupplierEnd(), newSuplConnectorEnd);
	 * newConnector.addSupplierEnd(newSuplConnectorEnd);
	 * 
	 * ConnectorEnd newClConnectorEnd = sclGraph.createEa_ConnectorEnd();
	 * updateTgConnectorEnd(eaConnector.GetClientEnd(), newClConnectorEnd);
	 * newConnector.addClientEnd(newClConnectorEnd);
	 * 
	 * if (eaConnector.GetSupplierID() == eaElem.GetElementID())
	 * jgElem.addConn_su(newConnector); else jgElem.addConn_cl(newConnector);
	 * 
	 * } else { //already created Connector tgConn =
	 * crEaConnMap.get(eaConnector.GetConnectorGUID()); if
	 * (eaConnector.GetSupplierID() == eaElem.GetElementID())
	 * jgElem.addConn_su(tgConn); else jgElem.addConn_cl(tgConn); } } // for
	 * (org.sparx.Connector eaConnector : eaConnectors) } //for (
	 * org.sparx.Element eaElem : EAElem_JGElem.keySet() ) }
	 */
}
