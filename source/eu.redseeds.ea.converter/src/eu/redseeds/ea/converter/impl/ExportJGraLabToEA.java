package eu.redseeds.ea.converter.impl;

//import org.sparx.*;

/*
 import lv.lu.mii.mola.mm.L0GraphClass;
 import lv.lu.mii.mola.mm.ea.Package;
 import lv.lu.mii.mola.mm.ea.Element;
 import lv.lu.mii.mola.mm.ea.Attribute;
 import lv.lu.mii.mola.mm.ea.Method;
 import lv.lu.mii.mola.mm.ea.Parameter;
 */
//import eu.redseeds.scl.SCLGraph;
//import eu.redseeds.ea.converter.Activator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Status;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.common.diagram.image.DiagramImageManager;
import eu.redseeds.ea.converter.Activator;
import eu.redseeds.scl.ea.Attribute;
import eu.redseeds.scl.ea.Connector;
import eu.redseeds.scl.ea.ConnectorEnd;
import eu.redseeds.scl.ea.Element;
import eu.redseeds.scl.ea.Method;
import eu.redseeds.scl.ea.Model;
import eu.redseeds.scl.ea.Package;
import eu.redseeds.scl.ea.Parameter;
import eu.redseeds.scl.ea.TaggedValue;

public class ExportJGraLabToEA {

	// private Map< lv.lu.mii.mola.mm.ea.Element, org.sparx.Element >
	// JGElem_EAElem; // contains .....
	// private Map< lv.lu.mii.mola.mm.ea.Package, org.sparx.Package >
	// JGPack_EAPack; // contains .....
	private Map<eu.redseeds.scl.ea.Element, org.sparx.Element> JGElem_EAElem; // contains
																				// .....
	private Map<eu.redseeds.scl.ea.Package, org.sparx.Package> JGPack_EAPack; // contains
																				// .....
																				// (not
																				// really
																				// used)
	private Map<eu.redseeds.scl.ea.Method, org.sparx.Method> JGMeth_EAMeth; // contains
																			// .....
	private List<org.sparx.Diagram> EADiagramsList; // contains references to EA
													// diagrams, used for
													// autolayouting ...

	/*
	 * another maps - maybe will be usable for interaction diagrams ???
	 * JGAttr_EAAttr JGMeth_EAMeth JGParam_EAParm
	 */

	// private List<org.sparx.Package> eaModels;
	// private GraphClass gc;
	private Graph graph;
	private JavaEAAPI ea_api = null;

	public ExportJGraLabToEA() {
		// ??? is it really necessary?
	}

	public void exportDataToEA(String repLocation, String tgLocation,
			Graph tgGraph, boolean layoutBitmapSave, boolean genCode) {
		try {
			// JGElem_EAElem = new HashMap< lv.lu.mii.mola.mm.ea.Element,
			// org.sparx.Element> ();
			// JGPack_EAPack = new HashMap<
			// lv.lu.mii.mola.mm.ea.Package,org.sparx.Package> ();
			JGElem_EAElem = new HashMap<eu.redseeds.scl.ea.Element, org.sparx.Element>();
			JGPack_EAPack = new HashMap<eu.redseeds.scl.ea.Package, org.sparx.Package>();
			JGMeth_EAMeth = new HashMap<eu.redseeds.scl.ea.Method, org.sparx.Method>();

			EADiagramsList = new ArrayList<org.sparx.Diagram>();

			// Activator.log("before JavaEAAPI :" + repLocation, Status.INFO);
			ea_api = new JavaEAAPI();
			// Activator.log("after JavaEAAPI", Status.INFO);
			// create and open the .eap EA repository project file
			ea_api.openRepositoryFile(repLocation);
			// Activator.log("after openRepositoryFile", Status.INFO);

			// load the TG graph
			if (tgGraph == null) {
				graph = GraphIO.loadGraphFromFile(tgLocation, null);
			} else {
				graph = tgGraph;
				// graph = GraphIO.loadGraphFromFile("SCL_Script.tg", null);
			}

			// get the GraphClass for graph
			// gc = graph.getGraphClass();

			// ea_api.addModel("TestModel", "Model");
			// first iteration - create model, packages and elements
			// eaModels = createEAModels();
			createEAModels();
			// next iterations - create Attributes, methods, classifiers,
			// connectors, ...
			processAttributesMethodsClassifiers();
			processConnectors();
			if (layoutBitmapSave) {
				autoLauyoutSaveDiagrams();
			} else {
				autoLauyoutDiagrams();
			}
			
			if(genCode){
				int lastPathSepPosition = tgLocation.lastIndexOf('/');
				String scProjectCodePath = tgLocation.substring(0, lastPathSepPosition) + "/CurrentSC/Code";
				ea_api.generateCodeForModel(scProjectCodePath);
			}

			ea_api.exitRepositoryFile();

		} catch (Exception ex) {
			Activator.logException(ex);
			// e.printStackTrace();
		}
	} // end of exportDataToEA(...)
	
	// create models and export all objects of this model
	private List<org.sparx.Package> createEAModels() {

		org.sparx.Package tmpEAmodel;
		List<org.sparx.Package> eaModList = new ArrayList<org.sparx.Package>();

		for (Model mod : ((eu.redseeds.scl.SCLGraph) graph)
				.getEa$ModelVertices()) {
			ea_api.deleteModel(mod.getName()); // delete already existing model
												// with the same name (if any
												// exists)
			tmpEAmodel = ea_api.addModel(mod.getName(), "Model");
			eaModList.add(tmpEAmodel);
			createEAPackageChildren(tmpEAmodel, mod);
		}

		if (eaModList.size() > 0) {
			ea_api.deleteModel("Model");
		}

		return eaModList;

		/*
		 * previous version of sclschema.jar VertexClass vc =
		 * gc.getVertexClass(new QualifiedName("ea.Package")); if (vc == null)
		 * return null;
		 * 
		 * //for ( Package pck : ((eu.redseeds.scl.SCLGraph
		 * )graph).getEa_PackageVertices() ) { //} the same ..... !!!
		 * 
		 * for (Vertex v : graph.vertices(vc)) { if ( ((Package) v).isIsModel()
		 * == true) { Package pck = (Package) v; eaModel =
		 * ea_api.addModel(pck.getName(), "Model");
		 * createEAPackageChildren(eaModel, pck); return eaModel; } } return
		 * null;
		 */
	}

	// recursive method to process all subpackages of particular package
	private void createEAPackageChildren(org.sparx.Package eaPck, Package pck) {
		JGPack_EAPack.put(pck, eaPck);
		for (Package p : pck.getPackagesList()) {
			org.sparx.Package pEA = ea_api.addPackage(eaPck, p.getName(),
					"Package", "");
			createEAPackageChildren(pEA, p);
		}
		createEAPackageElement(eaPck, pck);
	}

	// method to process all elements of particular package
	private void createEAPackageElement(org.sparx.Package eaPck, Package pck) {
		org.sparx.Diagram tmpClassDrg = null;
		org.sparx.Diagram tmpCompDrg = null;
		org.sparx.Diagram tmpInteractionDrg = null;
		org.sparx.Diagram tmpActivityDrg = null;

		for (Element e : pck.getElementsList()) {
			org.sparx.Element elem = null;
			if (e.getType().equals("Package")) { // element to code redseeds_uid
													// for this package
				elem = eaPck.GetElement();
			} else {
				elem = ea_api.addPackageElement(eaPck, e.getName(),
						e.getType(), e.getStereotype());
			}

			if (elem == null) {
				// Activator.log("elem == null :" + pck.getName(), Status.INFO);
				continue;
			}

			// class diagrams
			if (e.getType().equals("Class") || e.getType().equals("Actor")
					|| e.getType().equals("Interface")) {
				if (tmpClassDrg == null) {
					org.sparx.Diagram tmpDrg = ea_api.addDiagram(eaPck, pck
							.getName(), "Logical", null);
					EADiagramsList.add(tmpDrg);
					tmpClassDrg = tmpDrg;
				}
				ea_api.addDiagramElement(tmpClassDrg, elem.GetElementID(), null // dimensions
						);
			}

			// component diagrams
			if (e.getType().equals("Component")) {
				if (tmpCompDrg == null) {
					org.sparx.Diagram tmpDrg = ea_api.addDiagram(eaPck, pck
							.getName(), "Component", null);
					EADiagramsList.add(tmpDrg);
					tmpCompDrg = tmpDrg;
				}
				ea_api.addDiagramElement(tmpCompDrg, elem.GetElementID(), null // dimensions
						);
				createEAElementChildren(elem, e, tmpCompDrg);
			}

			// interaction (sequence) diagrams
			else {
				if (e.getType().equals("Interaction")) {
					if (tmpInteractionDrg == null) {
						org.sparx.Diagram tmpDrg = ea_api.addDiagram(eaPck, e
								.getName(), "Sequence", null);
						EADiagramsList.add(tmpDrg);
						tmpInteractionDrg = tmpDrg;
					}
					createEAElementChildren(elem, e, tmpInteractionDrg);
					tmpInteractionDrg = null;
				}
				// activity diagrams
				else if (e.getType().equals("Activity")) {
					if (tmpActivityDrg == null) {
						org.sparx.Diagram tmpDrg = ea_api.addDiagram(elem, e
								.getName(), "Use Case", null);
						EADiagramsList.add(tmpDrg);
						tmpActivityDrg = tmpDrg;
					}
					createEAElementChildren(elem, e, tmpActivityDrg);
					tmpActivityDrg = null;
				} else {
					createEAElementChildren(elem, e, null);
				}

				// component diagrams
				/*
				 * if (e.getType().equals("Component")) { if (tmpCompDrg ==
				 * null) { org.sparx.Diagram tmpDrg = ea_api.addDiagram(eaPck,
				 * pck.getName(), "Component", null);
				 * EADiagramsList.add(tmpDrg); tmpCompDrg = tmpDrg; }
				 * ea_api.addDiagramElement(tmpCompDrg, elem.GetElementID(),
				 * null // dimensions ); createEAElementChildren(elem, e,
				 * tmpCompDrg); }
				 */

				// interaction (sequence) diagrams
				/*
				 * else { if (e.getType().equals("Interaction")) { if
				 * (tmpInteractionDrg == null) { org.sparx.Diagram tmpDrg =
				 * ea_api.addDiagram(eaPck, e.getName(), "Sequence", null);
				 * EADiagramsList.add(tmpDrg); tmpInteractionDrg = tmpDrg; }
				 * createEAElementChildren(elem, e, tmpInteractionDrg);
				 * tmpInteractionDrg = null; }
				 * 
				 * else { createEAElementChildren(elem, e, null); }
				 */
			}
			processEAElement(elem, e);
		}
		// ea_api.setDiagramAutoLayout(tmpClassDrg);
		// ea_api.setDiagramAutoLayout(tmpCompDrg);
	}

	// recursive method to process all subelements of particular element
	private void createEAElementChildren(org.sparx.Element eaParentEl,
			Element parentEl, org.sparx.Diagram drg) {
		for (Element e : parentEl.getElementsList()) {
			org.sparx.Element eaElem = ea_api.addElementElement(eaParentEl, e
					.getName(), e.getType(), e.getStereotype());

			if (drg != null) {
				ea_api.addDiagramElement(drg, eaElem.GetElementID(), null // dimensions
						);
			}

			processEAElement(eaElem, e);
			createEAElementChildren(eaElem, e, drg);
		}
	}

	// process eaElement on the first iteration
	private void processEAElement(org.sparx.Element eaEl, Element elem) {
		JGElem_EAElem.put(elem, eaEl);
		ea_api.updateElement(eaEl, elem.getSubtype(), elem.isIsAbstract(), // ??
				elem.getTag(), elem.getVisibility(), elem.isIsLeaf());

		for (TaggedValue tgVal : elem.getTaggedValuesList()) {
			ea_api.addTaggedValueToElement(eaEl, tgVal.getName(), tgVal
					.getValue());
		}
	}

	// next iteration
	// process all Attributes and Methods with parameters
	// process classifiers for Elements, Attributes, Methods, Parameters
	// process TaggedValues for Attributes and Methods
	private void processAttributesMethodsClassifiers() {
		for (Element elem : JGElem_EAElem.keySet()) {
			org.sparx.Element eaElem = JGElem_EAElem.get(elem);
			if (eaElem == null) {
				continue; // security guard :)
			}

			// process classifiers for Elements
			for (Element clElem : elem.getClassifierList()) {
				ea_api
						.addClassifierToElement(JGElem_EAElem.get(clElem),
								eaElem);
				break;
			}

			// process attributes
			for (Attribute attr : elem.getAttributesList()) {
				String defValue = attr.getDefaultValue();
				if(defValue != null){
					defValue = defValue.replace("\\u0022", "\"");
					attr.setDefaultValue(defValue);
				}
				
				org.sparx.Attribute eaAttr = ea_api.addElementAttribute(eaElem,
						attr.getName(), attr.getType(), attr.getStereotype(),
						attr.isIsStatic(), attr.isIsOrdered(), attr
								.isAllowDuplicates(), attr.getVisibility(),
						attr.getLowerBound(), attr.getUpperBound(), attr
								.isIsDerived(), attr.getDefaultValue(), attr
								.isIsConst());

				for (TaggedValue tgVal : attr.getTaggedValuesList()) {
					ea_api.addTaggedValueToAttribute(eaAttr, tgVal.getName(),
							tgVal.getValue());
				}

				for (Element clElem : attr.getClassifierList()) {
					ea_api.addClassifierToAttribute(JGElem_EAElem.get(clElem),
							eaAttr);
					break;
				}
			} // end of attributes

			// process methods
			for (Method meth : elem.getMethodsList()) {
				
				String code = meth.getCode();
				if(code != null){
					code = code.replace("\\u0022", "\"");
				}
				
				org.sparx.Method eaMeth = ea_api.addMethod(eaElem, meth
						.getName(), meth.getType(), code, meth.getStereotype(), meth.getVisibility(), meth.isIsStatic(), meth.isIsAbstract(), meth.getThrowsException());
				JGMeth_EAMeth.put(meth, eaMeth);

				for (TaggedValue tgVal : meth.getTaggedValuesList()) {
					ea_api.addTaggedValueToMethod(eaMeth, tgVal.getName(),
							tgVal.getValue());
				}

				for (Element clElem : meth.getClassifierList()) {
					ea_api.addClassifierToMethod(JGElem_EAElem.get(clElem),
							eaMeth);
					break;
				}

				// process parameters
				for (Parameter param : meth.getParametersList()) {
					String tmpKind = param.getKind();
					if (tmpKind != null) {
						String[] result = tmpKind.split("::");
						tmpKind = result[result.length - 1].toLowerCase();
					}
					org.sparx.Parameter eaParam = ea_api.addMethodParameter(
							eaMeth, param.getName(), param.getType(), param
									.getDefaultValue(), param.getPosition(),
							tmpKind);

					for (Element clElem : param.getClassifierList()) {
						ea_api.addClassifierToParameter(JGElem_EAElem
								.get(clElem), eaParam);
						break;
					}

					// workaround - no taggedvalues support in ea for parameters
					// store redseeds_uid in StyleEx attribute !!!
					for (TaggedValue tgVal : param.getTaggedValuesList()) {
						if (eaParam != null
								&& tgVal.getName().equals("redseeds_uid")) {
							eaParam.SetNotes("redseeds_uid" + tgVal.getValue()
									+ "redseeds_uid");
							if (!eaParam.Update()) {
								Activator.log("EA parameter update error: "
										+ eaParam.GetLastError(), Status.ERROR);
							}
						}
						break;
					}

				} // end of parameters
			} // end of methods

		} // for ( Element elem : JGElem_EAElem.keySet() )
	} // end of processAttributesMethodsClassifiers()

	// next iteration
	// process connectors and connectorEnds for all Elements
	// (associations in "normal" diagrams, messages in sequence diagrams)
	// process tagged values for connectors and connectorEnds
	private void processConnectors() {
		for (Element clElem : JGElem_EAElem.keySet()) {
			org.sparx.Element eaElem = JGElem_EAElem.get(clElem);
			if (eaElem == null) {
				continue; // security guard :)
			}

			for (Connector clConn : clElem.getConn_clList()) {

				Element suppElement = null;
				ConnectorEnd clConnEnd = null;
				ConnectorEnd suppConnEnd = null;
				// get all info about connectors
				List<? extends Element> tmpList = clConn.getSupplierList();
				if (tmpList.size() > 0) {
					suppElement = tmpList.get(0);
				}
				List<? extends ConnectorEnd> tmpList2 = clConn
						.getClientEndList();
				if (tmpList2.size() > 0) {
					clConnEnd = tmpList2.get(0);
				}
				tmpList2 = clConn.getSupplierEndList();
				if (tmpList2.size() > 0) {
					suppConnEnd = tmpList2.get(0);
				}
				// if (suppElement == null || clConnEnd == null || suppConnEnd
				// == null) // no ends in Dependency case !!!
				// continue;
				if (suppElement == null) {
					continue;
					// -----------------------
				}

				org.sparx.Connector eaConn = ea_api.addConnector(JGElem_EAElem
						.get(clElem), JGElem_EAElem.get(suppElement), clConn
						.getName(), clConn.getType(), clConn.getStereotype(),
						clConn.getDirection(), clConn.getSubtype(), clConn
								.getSequenceNo(), clConn.getTransitionGuard());
				if (clConnEnd != null) {
					ea_api.updateConnectorEnd(eaConn.GetClientEnd(), clConnEnd
							.getName(), clConnEnd.getRoleType(), clConnEnd
							.getQualifier(), clConnEnd.getNavigable(),
							clConnEnd.getCardinality(), clConnEnd
									.getAllowDuplicates(), clConnEnd
									.getAggregation(), clConnEnd
									.isIsNavigable(),
							clConnEnd.isIsChangable(), clConnEnd.isDerived(),
							clConnEnd.isDerivedUnion(), clConnEnd
									.getVisibility(),
							clConnEnd.getConstraint(), clConnEnd.getOrdering());
				}

				if (suppConnEnd != null) {
					ea_api
							.updateConnectorEnd(eaConn.GetSupplierEnd(),
									suppConnEnd.getName(), suppConnEnd
											.getRoleType(), suppConnEnd
											.getQualifier(), suppConnEnd
											.getNavigable(), suppConnEnd
											.getCardinality(), suppConnEnd
											.getAllowDuplicates(), suppConnEnd
											.getAggregation(), suppConnEnd
											.isIsNavigable(), suppConnEnd
											.isIsChangable(), suppConnEnd
											.isDerived(), suppConnEnd
											.isDerivedUnion(), suppConnEnd
											.getVisibility(), suppConnEnd
											.getConstraint(), suppConnEnd
											.getOrdering());
				}

				for (TaggedValue tgVal : clConn.getTaggedValuesList()) {
					if (tgVal.getName().equals("operation_guid")) { // message
																	// in
																	// sequence
																	// diagram
						Method jgrMeth = (Method) tgVal.getReferencedObjList()
								.get(0);
						if (jgrMeth == null) {
							break;
						}
						org.sparx.Method eaMeth = JGMeth_EAMeth.get(jgrMeth);
						if (eaMeth == null) {
							break;
						}
						ea_api.addTaggedValueToConnector(eaConn, tgVal
								.getName(), eaMeth.GetMethodGUID());
					} else {
						ea_api.addTaggedValueToConnector(eaConn, tgVal
								.getName(), tgVal.getValue());
					}
				}

				if (clConnEnd != null) {
					for (TaggedValue tgVal : clConnEnd.getTaggedValuesList()) {
						ea_api.addTaggedValueToConnectorEnd(eaConn
								.GetClientEnd(), tgVal.getName(), tgVal
								.getValue());
					}
				}

				if (suppConnEnd != null) {
					for (TaggedValue tgVal : suppConnEnd.getTaggedValuesList()) {
						ea_api.addTaggedValueToConnectorEnd(eaConn
								.GetSupplierEnd(), tgVal.getName(), tgVal
								.getValue());
					}
				}

			} // for ( Connector clConn: clElem.getConn_clList() )

		} // for ( Element elem : JGElem_EAElem.keySet() )

	}

	// autolayot all created diagrams
	private void autoLauyoutDiagrams() {
		for (org.sparx.Diagram tmpDrg : EADiagramsList) {
			ea_api.setDiagramAutoLayout(tmpDrg);
		}
	}

	// autolayot and save all created diagrams as bitmaps
	private void autoLauyoutSaveDiagrams() {
		String hold = DiagramImageManager.getInstance()
				.getDiagramImageFolderAbsolutePath();
		for (org.sparx.Diagram tmpDrg : EADiagramsList) {
			ea_api.setDiagramAutoLayout(tmpDrg);
			ea_api.saveBitmap(tmpDrg, hold);
		}
		SCProjectHelper.refreshActiveProject();
	}

} // end of ExportJGraLabToEA
