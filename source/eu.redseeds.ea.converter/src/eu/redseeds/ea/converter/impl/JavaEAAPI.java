package eu.redseeds.ea.converter.impl;

//import org.sparx.Collection;
import org.eclipse.core.runtime.Status;
import org.sparx.AttributeTag;
import org.sparx.Collection;
import org.sparx.Connector;
import org.sparx.ConnectorTag;
import org.sparx.Diagram;
import org.sparx.DiagramObject;
import org.sparx.Element;
import org.sparx.Method;
import org.sparx.MethodTag;
import org.sparx.Parameter;
import org.sparx.Project;
import org.sparx.RoleTag;
import org.sparx.Scenario;
import org.sparx.ScenarioExtension;
import org.sparx.ScenarioStep;
import org.sparx.TaggedValue;

import eu.redseeds.ea.converter.Activator;

public class JavaEAAPI {

	private org.sparx.Repository eaRep;

	JavaEAAPI() {
		eaRep = new org.sparx.Repository();
	}

	public org.sparx.Repository openRepositoryFile(String file) {
 		eaRep.OpenFile(file);
   		//System.out.println("\nEA project file open.\n");
 		return eaRep;
    }
	
	public void generateCodeForModel(String scProjectCodePath) {
		org.sparx.Collection<org.sparx.Package> models = eaRep.GetModels();
		Project proj = eaRep.GetProjectInterface();
		String extraOptions = "recurse=1;overwrite=1;dir=" + scProjectCodePath;
	    for (short i=0; i<models.GetCount();i++) {
	    	org.sparx.Package tmpMod = models.GetAt(i);
	    	org.sparx.Collection<org.sparx.Package> packages = tmpMod.GetPackages();
	    	for(short j=0; j<packages.GetCount(); j++) {
	    		org.sparx.Package pack = packages.GetAt(j);
	    		//find app package
	    		if (pack.GetName().equals("app")) {
	    			/*for(org.sparx.Package dtoPackage : pack.GetPackages()){
	    				//find DTO package and generate DDL
	    				if(dtoPackage.GetName().equals("DTO")){
	    					org.sparx.Package ddlPackage = addPackage(pack, "DDL", "Package", "");
	    					proj.TransformPackage("DDL", dtoPackage.GetPackageGUID(), ddlPackage.GetPackageGUID(), null);
	    					break;
	    				}
	    			}*/
		    		proj.GeneratePackage(pack.GetPackageGUID(), extraOptions);
		    		break;
		    	}
	    	}
	    }
	}

	public void deleteModel(String modelName) {
		// Delete Model with name modelName
	    org.sparx.Collection<org.sparx.Package> models = eaRep.GetModels();
	    for (short i=0; i<models.GetCount();i++) {
	    	org.sparx.Package tmpMod = models.GetAt(i);
	    	if (tmpMod.GetName().equals(modelName)) {
	    		models.Delete(i);
	    		models.Refresh();
	    		break;
	    	}
	    }
		/*
		org.sparx.Collection<org.sparx.Package> models = eaRep.GetModels();
	    org.sparx.Package delMod = models.GetByName(modelName);
	    if (delMod == null) return;
	    org.sparx.Collection<org.sparx.Package> delPack = delMod.GetPackages();
	    for (short i=0; i<delPack.GetCount()-1; i++) {
	    	delPack.Delete(i);
	    }
	    delPack.Refresh();
	    //delete model....
		*/
	}

	public org.sparx.Package addModel(String modelName, String modelType) {
	// Add a new Model to the models collection.
    	org.sparx.Collection<org.sparx.Package> models = eaRep.GetModels();
    	org.sparx.Package model = (org.sparx.Package)models.AddNew(modelName, modelType);
    	// Update the com object.
    	if(!(model.Update()))
    		Activator.log("EA model update error: " + model.GetLastError(), Status.ERROR);
    	else{
    		// Refresh the models collection.
    		models.Refresh();
    		//System.out.println("Successfully added new model.\n");
    	}
    	return model;
    }

	public org.sparx.Package addPackage(org.sparx.Package model,
    		               String subPackageName,
    		               String subPackageType,
    		               String stereotype) {

    	org.sparx.Package pkg = null;
    	org.sparx.Collection<org.sparx.Package> pkgs = model.GetPackages();
    	pkg = (org.sparx.Package)pkgs.AddNew(subPackageName, subPackageType);
    	if(!(pkg.Update()))
    		Activator.log("EA package update error: " + pkg.GetLastError(), Status.ERROR);
    	pkg.SetStereotypeEx(stereotype);
    	if(!(pkg.Update()))
    		Activator.log("EA package update error: " + pkg.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added new package.\n");
    	return pkg;
    }

	public org.sparx.Element addPackageElement(org.sparx.Package pkg, String elemName,String elemType,String elemStereotype)
    {
		org.sparx.Collection<org.sparx.Element> elems = pkg.GetElements();
		org.sparx.Element elem = (org.sparx.Element)elems.AddNew(elemName,elemType);
    	elem.SetStereotype(elemStereotype);
    	elem.Update();
    	elems.Refresh();
    	//System.out.println("Successfully added new package element to model.\n");
    	return elem;
    }

	public org.sparx.Element addElementElement(org.sparx.Element parentEl, String elemName,String elemType,String elemStereotype)
    {
		org.sparx.Collection<org.sparx.Element> elems = parentEl.GetElements();
		org.sparx.Element elem = (org.sparx.Element)elems.AddNew(elemName,elemType);
    	elem.SetStereotype(elemStereotype);
    	elem.Update();
    	elems.Refresh();
    	//System.out.println("Successfully added new package element to model.\n");
    	return elem;
    }

	public void updateElement(org.sparx.Element elem, int subtype,
													  boolean isAbstract,
													  String tag,
													  String visibility,
													  boolean isLeaf)
	{
    	elem.SetSubtype(subtype);

    	//type mismatch
    	if (isAbstract)
    		elem.SetAbstract("true"); //String ??
    	else
    		elem.SetAbstract("false"); //String ??

    	elem.SetTag(tag);
    	elem.SetVisibility(visibility);
    	elem.SetIsLeaf(isLeaf);

    	elem.Update();
    	elem.Refresh();
    	//System.out.println("Element successfully updated.\n");
    }

	public org.sparx.Attribute addElementAttribute(org.sparx.Element parentEl,
			                                       String attribName,
			                                       String attribType,
			                                       String stereotype,
			                                       boolean isStatic,
			                                       boolean isOrdered,
			                                       boolean allowDuplicates,
			                                       String setVisibility,
			                                       String lowerBound,
			                                       String upperBound,
			                                       boolean isDerived,
			                                       String defaultValue,
			                                       boolean isConst )
    {
		// Create an attribute.
		org.sparx.Collection<org.sparx.Attribute> attributes = parentEl.GetAttributes();
		org.sparx.Attribute attribute = (org.sparx.Attribute)attributes.AddNew(attribName,attribType);
		attribute.SetStereotype(stereotype);
		attribute.SetIsStatic(isStatic);
		attribute.SetIsOrdered(isOrdered);
		attribute.SetAllowDuplicates(allowDuplicates);
		attribute.SetVisibility(setVisibility);
		attribute.SetLowerBound(lowerBound);
		attribute.SetUpperBound(upperBound);
		attribute.SetIsDerived(isDerived);
		attribute.SetDefault(defaultValue);
		attribute.SetIsConst(isConst);
//		attribute.SetAttributeGUID(attrGuid);
//		attribute.SetClassifierID(classifId);

		attribute.Update();
		attributes.Refresh();
		//System.out.println("Successfully added new attribute to element.\n");
		return attribute;
    }

    public org.sparx.Method addMethod(org.sparx.Element parentEl,
    		                          String methodName,
   		                              String type,
   		                              String code,
   		                              String stereotype,
   		                              String visibility,
   		                              boolean isStatic,
   		                              boolean isAbstract,
   		                              String throwsException)
    {
    	// Create a new method.
    	org.sparx.Collection<org.sparx.Method> methods = parentEl.GetMethods();
    	Method method = (Method)methods.AddNew(methodName, type);
    	method.SetCode(code);
    	method.SetStereotype(stereotype);
    	method.SetVisibility(visibility);
    	method.SetIsStatic(isStatic);
    	method.SetAbstract(isAbstract);
    	method.SetThrows(throwsException);
    	
    	method.Update();
    	methods.Refresh();
		//System.out.println("Successfully added new method to element.\n");
		return method;
    }
    
    public Scenario addScenario(org.sparx.Element parentEl,
            String scenarioName,
            String type,
            String notes){
    	Collection<Scenario> scenarios = parentEl.GetScenarios();
    	Scenario scenario = scenarios.AddNew(scenarioName,type);
    	scenario.SetNotes(notes);
    	scenario.Update();
    	scenarios.Refresh();
    	return scenario;
    }
    
    public ScenarioStep addStep(org.sparx.Scenario parentSc,
            String stepName,
            String type){
    	Collection<ScenarioStep> steps = parentSc.GetSteps();
    	ScenarioStep step = steps.AddNew(stepName,type);
    	step.Update();
    	steps.Refresh();
    	parentSc.Update();
    	return step;
    }
    
    public ScenarioExtension addExtension(org.sparx.ScenarioStep parentSt,
    		org.sparx.Scenario parentSc,
            String extensionName,
            String type,
            String join){
    	Collection<ScenarioExtension> extensions = parentSt.GetExtensions();
    	ScenarioExtension extension = extensions.AddNew(extensionName,type);
    	if (null!=join){
    		extension.Update();
        	extensions.Refresh();
        	parentSt.Update();
        	parentSc.GetSteps().Refresh();
        	parentSc.Update();
        	extension.SetJoin(join);
    	}
    	extension.Update();
    	extensions.Refresh();
    	parentSt.Update();
    	parentSc.GetSteps().Refresh();
    	parentSc.Update();
    	return extension;
    }
    
    public org.sparx.Parameter addMethodParameter(org.sparx.Method parentMeth,
    											  String name,
    											  String type,
    											  String defaultValue,
    											  int position,
    											  String kind)

    {
    	org.sparx.Collection<org.sparx.Parameter> parameters = parentMeth.GetParameters();
    	Parameter parameter = null;
    	parameter = (Parameter)parameters.AddNew(name, type);
    	parameter.SetDefault(defaultValue);
    	parameter.SetPosition(position);
    	parameter.SetKind(kind);
    	if(!parameter.Update())
    		Activator.log("EA parameter update error: " + parameter.GetLastError(), Status.ERROR);
   		parameters.Refresh();

		//System.out.println("Successfully added new parameters to method.\n");
		return parameter;
    }

    public org.sparx.Diagram addDiagram(org.sparx.Package pkg,String diagramName,String diagramType,String notes)
    {
    	// Create a diagram within a package.
    	org.sparx.Collection<org.sparx.Diagram> diagrams = pkg.GetDiagrams();
    	Diagram diagram = (Diagram)diagrams.AddNew(diagramName,diagramType);
    	diagram.SetNotes(notes);
    	diagram.SetShowDetails(0);
    	if(!diagram.Update())
    		Activator.log("EA diagram update error: " + diagram.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added new diagram to model.\n");
    	return diagram;
    }

    public org.sparx.Diagram addDiagram(org.sparx.Element elem,String diagramName,String diagramType,String notes)
    {
    	// Create a diagram within an element.
    	org.sparx.Collection<org.sparx.Diagram> diagrams = elem.GetDiagrams();
    	Diagram diagram = (Diagram)diagrams.AddNew(diagramName,diagramType);
    	diagram.SetNotes(notes);
    	diagram.SetShowDetails(0);
    	if(!diagram.Update())
    		Activator.log("EA diagram update error: " + diagram.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added new diagram to model.\n");

    	return diagram;
    }

    public void addDiagramElement(org.sparx.Diagram diagram,
    		                      int ObjectId,
    		                      String dObjDims)
    {
    	// Add an element to a diagram within a package.
    	// Note the optional use of the element rectangle setting using
    	// left,right,top and bottom dimensions in the AddNew API method call.

    	//org.sparx.Collection<org.sparx.Element> diagramElems = pkg.GetElements();
    	//Element diagramElem = (Element)diagramElems.AddNew(diagramElemName,diagramElemType);
    	//diagramElem.SetStereotype(stereotype);
    	//diagramElem.Update();

    	// add element to diagram - supply optional rectangle co-ordinates
    	org.sparx.Collection<org.sparx.DiagramObject>  diagramObjects = diagram.GetDiagramObjects();
    	DiagramObject diagramObject = (DiagramObject)diagramObjects.AddNew(dObjDims,"");
    	diagramObject.SetElementID(ObjectId);
    	if(!diagramObject.Update())
    		Activator.log("EA diagram element update error: " + diagramObject.GetLastError(), Status.ERROR);

    	//System.out.println("Successfully added new diagram element to diagram.\n");
    	//return diagramElem.GetElementID();
    }

	// Set the EA diagram autolayout
    public  void setDiagramAutoLayout(org.sparx.Diagram diagram) {
    	if (diagram == null) return;

    	diagram.Update();

    	Project pr = eaRep.GetProjectInterface();

    	//Activator.log("diagramStyleAdv->" + diagram.GetStyleEx(), Status.INFO);
    	//Activator.log("diagramStyleExt->" + diagram.GetExtendedStyle(), Status.INFO);

        if ( diagram.GetType().equals("Use Case") ) { // somehow :) "lsLayoutDirectionDown" Style should be set !!!
        	// constants taken from VB project :)
        	// lsLayoutDirectionUp - 65536 (&H10000)
        	// lsLayoutDirectionDown - 131072 (&H20000)
        	// lsLayoutDirectionLeft - 262144 (&H40000)
        	// lsLayoutDirectionRight - 524288 (&H80000)
        	pr.LayoutDiagramEx(diagram.GetDiagramGUID(),0x20000,4,20,50,true);
        }
        else {
        	pr.LayoutDiagramEx(diagram.GetDiagramGUID(),1,4,60,40,true);
        }
        pr.ReloadProject();
    	//System.out.println("setDiagramAutoLayout\n");
    }

    public void saveBitmap(org.sparx.Diagram dgrm, String filePath)
    {
    	Project pr = eaRep.GetProjectInterface();
    	pr.PutDiagramImageToFile(pr.GUIDtoXML(dgrm.GetDiagramGUID()), filePath+DiagramImageNameResolver.getInstanceImageNameResolver(dgrm).getImageName(), 1);
    	//System.out.println("savedDiagramToBitmap\n");
    }

	// Add tagged value to the element
    public org.sparx.TaggedValue addTaggedValueToElement(org.sparx.Element elem,
                                                         String tagName,
                                                         String Value) {
    	org.sparx.Collection<org.sparx.TaggedValue> tagValues = elem.GetTaggedValues();
    	TaggedValue tagValue = (TaggedValue) tagValues.AddNew(tagName, Value);
    	if(!tagValue.Update())
    		Activator.log("EA tagged value update error: " + tagValue.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added new tagged value to element.\n");
    	return tagValue;
    }

	// Add tagged value to the attribute
    public org.sparx.AttributeTag addTaggedValueToAttribute(org.sparx.Attribute attr,
                                                            String tagName,
                                                            String Value) {
    	org.sparx.Collection<org.sparx.AttributeTag> attrTags = attr.GetTaggedValues();
    	AttributeTag attrTag = (AttributeTag) attrTags.AddNew(tagName, Value);
    	if(!attrTag.Update())
    		Activator.log("EA attribuet tag update error: " + attrTag.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added new tagged value to attribute.\n");
    	return attrTag;
    }

	// Add tagged value to the method
    public org.sparx.MethodTag addTaggedValueToMethod(org.sparx.Method meth,
                                                            String tagName,
                                                            String Value) {
    	org.sparx.Collection<org.sparx.MethodTag> methTags = meth.GetTaggedValues();
    	MethodTag methTag = (MethodTag) methTags.AddNew(tagName, Value);
    	if(!methTag.Update())
    		Activator.log("EA method tag update error: " + methTag.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added new tagged value to method.\n");
    	return methTag;
    }

	// Add tagged value to the connector
    public org.sparx.ConnectorTag addTaggedValueToConnector(org.sparx.Connector conn,
                                                            String tagName,
                                                            String Value) {
    	org.sparx.Collection<org.sparx.ConnectorTag> connTags = conn.GetTaggedValues();
    	ConnectorTag connTag = (ConnectorTag) connTags.AddNew(tagName, Value);
    	if(!connTag.Update())
    		Activator.log("EA connector tag update error: " + connTag.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added new tagged value to connector.\n");
    	return connTag;
    }

    // Add tagged value to the connectorEnd
    public org.sparx.RoleTag addTaggedValueToConnectorEnd(org.sparx.ConnectorEnd connEnd,
                                                               String tagName,
                                                               String Value) {
    	org.sparx.Collection<org.sparx.RoleTag> connEndTags = connEnd.GetTaggedValues();
    	RoleTag connEndTag = (RoleTag) connEndTags.AddNew(tagName, Value);
    	if(!connEndTag.Update())
    		Activator.log("EA role tag update error: " + connEndTag.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added new tagged value to connectorEnd.\n");
    	return connEndTag;
    }

	// Add classifier to the attribute
    public void addClassifierToAttribute(org.sparx.Element elem,
    													org.sparx.Attribute attr) {
    	attr.SetClassifierID(elem.GetElementID()); //??int
    	if(!attr.Update())
    		Activator.log("EA attribute update error: " + attr.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added classifier to attribute.\n");
    }

	// Add classifier to the method
    public void addClassifierToMethod(org.sparx.Element elem,
    									 org.sparx.Method meth) {
//    	meth.SetClassifierID(elem.GetElementGUID()); //??String
    	meth.SetClassifierID(new Integer(elem.GetElementID()).toString()); //??String
    	if(!meth.Update())
    		Activator.log("EA method update error: " + meth.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added classifier to method.\n");
    }

	// Add classifier to the parameter
    public void addClassifierToParameter(org.sparx.Element elem,
    									 org.sparx.Parameter param) {
//    	param.SetClassifierID(elem.GetElementGUID()); //??String
    	param.SetClassifierID(new Integer(elem.GetElementID()).toString()); //??String
    	if(!param.Update())
    		Activator.log("EA parameter update error: " + param.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added classifier to parameter.\n");
    }

	// Add classifier to the element
    public void addClassifierToElement(org.sparx.Element elem,
    								   org.sparx.Element elem2) {
    	elem2.SetClassifierID(elem.GetElementID()); //??int
    	if(!elem2.Update())
    		Activator.log("EA classifier update error: " + elem2.GetLastError(), Status.ERROR);
    	//System.out.println("Successfully added classifier to element.\n");
    }

    public org.sparx.Connector addConnector(org.sparx.Element source,
    										org.sparx.Element target,
    										String connName,
    										String connType,
    										String connStereotype,
    										String connDirection,
    										String connSubType,
    										int connSequenceNo,
    										String transGuard)
    {
    	org.sparx.Collection<org.sparx.Connector> sourceConnectors = source.GetConnectors();
    	Connector con = (Connector)sourceConnectors.AddNew(connName, connType);

    	// Replace ID with a suitable one from the model.
    	con.SetSupplierID(target.GetElementID());
    	con.SetStereotype(connStereotype);
    	con.SetDirection(connDirection);
    	con.SetSubtype(connSubType);
    	con.SetSequenceNo(connSequenceNo);
    	con.SetTransitionGuard(transGuard);
    	if(!con.Update())
    		Activator.log("EA connector update error: " + con.GetLastError(), Status.ERROR);
    	sourceConnectors.Refresh();
    	//System.out.println("Connector Created.");

    	//Collection constraints = con.GetConstraints();
    	//ConnectorConstraint constraint = (ConnectorConstraint)constraints.AddNew(cnstNm,cnstTp);
    	//if(!constraint.Update()) System.out.println(constraint.GetLastError());

    	return con;
    }

    public void updateConnectorEnd(org.sparx.ConnectorEnd connEnd,
    												String roleName,
    												String roleType,
    												String qualifier,
    												String navigable,
    												String cardinality,
    												String allowDuplicates,
    												int aggregation,
    												boolean isNavigable,
    												boolean isChangeable,
    												boolean derived,
    												boolean derivedUnion,
    												String Visibility,
    												String constraint,
    												int ordering ) {
    	connEnd.SetRole(roleName);
    	connEnd.SetRoleType(roleType);
    	connEnd.SetQualifier(qualifier);
    	connEnd.SetNavigable(navigable);
    	connEnd.SetCardinality(cardinality);

       	// type mismatch for allowDuplicates ??
		if (allowDuplicates != null && allowDuplicates.equals("false"))
			connEnd.SetAllowDuplicates(false);
		else if (allowDuplicates != null)
			connEnd.SetAllowDuplicates(true);

		connEnd.SetAggregation(aggregation);
    	connEnd.SetIsNavigable(isNavigable);

    	// type mismatch for isChangeable ??
    	if (isChangeable)
    		connEnd.SetIsChangeable("true");
    	else
    		connEnd.SetIsChangeable("false");

    	connEnd.SetDerived(derived);
    	connEnd.SetDerivedUnion(derivedUnion);
    	connEnd.SetVisibility(Visibility);
    	connEnd.SetConstraint(constraint);
    	connEnd.SetOrdering(ordering);
    	if(!connEnd.Update())
    		Activator.log("EA connector end update error: " + connEnd.GetLastError(), Status.ERROR);

    	//System.out.println("ConnectorEnd is updated");
    }



    public void exitRepositoryFile()
	{
		try{eaRep.Exit(); System.out.println("\nEA project file closed.\n");}
		catch(Exception e){System.out.println(e.toString());}
	}

	public Element getElement(String name, String type) {
		Collection<Element> result = eaRep.GetElementsByQuery("Element Name", name);
		for (Element el:result) if (type.equals(el.GetType())) return el;
		return null;
	}
}
