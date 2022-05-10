package eu.redset.emf.model.tsl.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.redseeds.common.SCProjectHelper;
import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.BLTestInstance;
import eu.redset.emf.model.tsl.CCondition;
import eu.redset.emf.model.tsl.Condition;
import eu.redset.emf.model.tsl.ConditionSentence;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DirectObject;
import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.GUITestInstance;
import eu.redset.emf.model.tsl.IndirectObject;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.NFTestInstance;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.NotionAttribute;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestInstance;
import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestParameter;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.TestValue;
import eu.redset.emf.model.tsl.TslFactory;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenarioInstance;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;
import eu.redset.emf.model.tsl.util.TSLModelHelper;


public class TSLBusinessLayerFacadeImpl implements TSLBusinessLayerFacade {

	private TestSpecification testSpec = null;
	private TslFactory tslFactory;
	private Resource resource;
	
	public TSLBusinessLayerFacadeImpl(){
		TslPackageImpl.init();
		// Retrieve the default factory singleton
		tslFactory = TslFactory.eINSTANCE;
	}

	@Override
	public void setTestSpecification(TestSpecification modelObject) {
		// TODO Auto-generated method stub
		testSpec = modelObject;
	}

	@Override
	public void createTestSpecification(String name) {
		// TODO Auto-generated method stub
		testSpec = tslFactory.createTestSpecification();
		testSpec.setName(name);
	}

	@Override
	public TestSpecification getTestSpecification() {
		// TODO Auto-generated method stub
		return testSpec;
	}

	@Override
	public TestPackage createTestPackage(TestPackage parent, String name) {
		TestPackage tp = null;
		tp = tslFactory.createTestPackage();
		tp.setName(name);
		if (parent != null){
			parent.getEReference0().add(tp);
		}
		return tp;
	}

	@Override
	public void createInitialTestSpecificationStructure() {
		TestPackage uctp, tsp, abstractTestsPackage, concreteTestsPackage, notionsPackage, nfTestsPackage, blTestsPackage, guiTestsPackage;
		
		uctp = tslFactory.createTestPackage();
		uctp.setName("Use Case Tests");
		tsp = tslFactory.createTestPackage();
		tsp.setName("Test Scenarios");
		abstractTestsPackage = tslFactory.createTestPackage();
		abstractTestsPackage.setName("Abstract Tests");
		concreteTestsPackage = tslFactory.createTestPackage();
		concreteTestsPackage.setName("Concrete Tests");
		notionsPackage = tslFactory.createTestPackage();
		notionsPackage.setName("Notions");
		nfTestsPackage = tslFactory.createTestPackage();
		nfTestsPackage.setName("Non Functional Tests");
		blTestsPackage = tslFactory.createTestPackage();
		blTestsPackage.setName("Business Logic Tests");
		guiTestsPackage = tslFactory.createTestPackage();
		guiTestsPackage.setName("GUI Tests");
		this.testSpec.getEReference0().add(abstractTestsPackage);
		this.testSpec.getEReference0().add(concreteTestsPackage);
		abstractTestsPackage.getEReference0().add(uctp);
		abstractTestsPackage.getEReference0().add(nfTestsPackage);
		abstractTestsPackage.getEReference0().add(notionsPackage);
		abstractTestsPackage.getEReference0().add(blTestsPackage);
		abstractTestsPackage.getEReference0().add(guiTestsPackage);
		concreteTestsPackage.getEReference0().add(tsp);
		
	}

	@Override
	public Test createTest(TestPackage parent, String name) {
		return null;
	}

	@Override
	public TestPackage getUseCaseTestPackage() {
		for (TestPackage tp:testSpec.getEReference0()){
			if (tp.getName().equals("Abstract Tests"))
				for(TestPackage tpi:tp.getEReference0())
					if(tpi.getName().equals("Use Case Tests"))
						return tpi;
		}
		return null;
	}
	
	@Override
	public TestPackage getNotionsPackage() {
		for (TestPackage tp:testSpec.getEReference0()){
			if (tp.getName().equals("Abstract Tests"))
				for(TestPackage tpi:tp.getEReference0())
					if(tpi.getName().equals("Notions"))
						return tpi;
		}
		return null;
	}

	@Override
	public UseCaseTest getUseCaseTestByUid(String uid) {
		List uctList = this.getAllUseCaseTests();
		if (uctList.size()>0){
			for (int i=0; i<uctList.size(); i++){
				if (((UseCaseTest)uctList.get(i)).getUid().equals(uid)){
					return (UseCaseTest)uctList.get(i);
				}					 
			}
		}
		return null;
	}
	
	@Override
	public TestPackage getNFTestsPackage() {
		for (TestPackage tp:testSpec.getEReference0()){
			if (tp.getName().equals("Abstract Tests"))
				for(TestPackage tpi:tp.getEReference0())
					if(tpi.getName().equals("Non Functional Tests"))
						return tpi;
		}
		return null;
	}
	
	@Override
	public TestPackage getBLTestsPackage() {
		for (TestPackage tp:testSpec.getEReference0()){
			if (tp.getName().equals("Abstract Tests"))
				for(TestPackage tpi:tp.getEReference0())
					if(tpi.getName().equals("Business Logic Tests"))
						return tpi;
		}
		return null;
	}
	
	@Override
	public TestPackage getGUITestsPackage() {
		for (TestPackage tp:testSpec.getEReference0()){
			if (tp.getName().equals("Abstract Tests"))
				for(TestPackage tpi:tp.getEReference0())
					if(tpi.getName().equals("GUI Tests"))
						return tpi;
		}
		return null;
	}

	@Override
	public Notion createNotion(TestPackage parent, String name) {
		Notion notion = null;
		notion = tslFactory.createNotion();
		notion.setNotionName(name);
		if (parent != null){
			parent.getNotions().add(notion);
		}		
		return notion;
	}
	
	@Override
	public TestRelationship createTestRelationship(Test parentTest,
			Test childTest) {
		TestRelationship relation = null;
		if (parentTest != null && childTest !=null){
			relation = tslFactory.createTestRelationship();
			parentTest.getSource().add(relation);
			relation.setTarget(childTest);
		}		
		return relation;
	}

	@Override
	public Notion getNotionByUid(String uid) {
		List notions = this.getAllNotions();
		if (notions.size()>0){
			for (int i=0; i<notions.size(); i++){
				if (((Notion)notions.get(i)).getUid().equals(uid))
					return (Notion)notions.get(i); 
			}
		}
		return null;
	}
	
	@Override
	public DomainStatement getDomainStatementByUid(String uid) {
		List dsList = this.getAllDomainStatements();
		if (dsList.size()>0){
			for (int i=0; i<dsList.size(); i++){
				if (((DomainStatement)dsList.get(i)).getUid().equals(uid)){
					return (DomainStatement)dsList.get(i);
				}
					 
			}
		}
		return null;
	}
	
	@Override
	public NFTest createNFTest(TestPackage parent, String name) {
		NFTest nft = null;
		nft = tslFactory.createNFTest();
		nft.setName(name);
		if (parent != null){
			parent.getNFTests().add(nft);
		}		
		return nft;
	}
	

	
	@Override
	public BLTest getBLTestByName(String name) {
		List bltList = this.getAllBLTests();
		if (bltList.size()>0){
			for (int i=0; i<bltList.size(); i++){
				if (((BLTest)bltList.get(i)).getName().equals(name)){
					return (BLTest)bltList.get(i);
				}					 
			}
		}
		return null;
	}

	@Override
	public GUITest getGUITestByName(String name) {
		List guitList = this.getAllGUITests();
		if (guitList.size()>0){
			for (int i=0; i<guitList.size(); i++){
				if (((NFTest)guitList.get(i)).getName().equals(name)){
					return (GUITest)guitList.get(i);
				}					 
			}
		}
		return null;
	}

	@Override
	public BLTest createBLTest(TestPackage parent, String name) {
		BLTest blt = null;
		if (getBLTestByName(name) != null)
			return null;
		blt = tslFactory.createBLTest();
		blt.setName(name);
		if (parent != null){
			parent.getBLTests().add(blt);
		}		
		return blt;
	}
	
	@Override
	public GUITest createGUITest(TestPackage parent, String name) {
		GUITest guit = null;
		if (getGUITestByName(name) != null)
			return null;
		guit = tslFactory.createGUITest();
		guit.setName(name);
		if (parent != null){
			parent.getGUITests().add(guit);
		}		
		return guit;
	}


	@Override
	public NFTestInstance createNFTestInstance(TestInstance parent, String name) {
		NFTestInstance nft = null;
		nft = tslFactory.createNFTestInstance();
		nft.setName(name);
		if (parent != null){
			parent.getAttachedTests().add(nft);
		}		
		return nft;
	}
	
	@Override
	public BLTestInstance createBLTestInstance(TestInstance parent, String name) {
		BLTestInstance blt = null;
		blt = tslFactory.createBLTestInstance();
		blt.setName(name);
		if (parent != null){
			parent.getAttachedTests().add(blt);
		}		
		return blt;
	}
	
	@Override
	public GUITestInstance createGUITestInstance(TestInstance parent, String name) {
		GUITestInstance guit = null;
		guit = tslFactory.createGUITestInstance();
		guit.setName(name);
		if (parent != null){
			parent.getAttachedTests().add(guit);
		}		
		return guit;
	}
	
	@Override
	public DomainStatement createDomainStatement(Notion notion, String name) {
		DomainStatement ds = null;
		ds = tslFactory.createDomainStatement();
		ds.setPhraseName(name);
		if (notion != null){
			notion.getDomainStatements().add(ds);
		}		
		return ds;
	}
	
	@Override
	public TestParameter createTestParameter(Test parentTest, String name) {
		TestParameter tp = null;
		tp = tslFactory.createTestParameter();
		tp.setName(name);
		if (parentTest != null){
			parentTest.getTestParameters().add(tp);
		}		
		return tp;
	}
	
	@Override
	public NotionAttribute createNotionAttribute(Notion parent, String name) {
		NotionAttribute attr = null;
		attr = tslFactory.createNotionAttribute();
		attr.setName(name);
		if (parent != null){
			parent.getAttributes().add(attr);
		}		
		return attr;
	}
	
	@Override
	public UseCaseTest createUseCaseTest(TestPackage parent, String name) {
		UseCaseTest uct = null;
		uct = tslFactory.createUseCaseTest();
		uct.setName(name);
		if (parent != null){
			parent.getEReference1().add(uct);
		}		
		return uct;
	}

	@Override
	public UseCaseTestScenario createUseCaseTestScenario(UseCaseTest parent,
			String name) {
		UseCaseTestScenario ucts = null;
		ucts = tslFactory.createUseCaseTestScenario();
		ucts.setName(name);
		if (parent != null){
			parent.getEReference0().add(ucts);
		}
		return ucts;
	}

	@Override
	public UseCaseTestScenarioSentence createUseCaseTestScenarioSentence(
			UseCaseTestScenario parent, String name) {
		UseCaseTestScenarioSentence uctss = null;
		uctss = tslFactory.createUseCaseTestScenarioSentence();
		uctss.setName(name);
		if (parent != null){
			parent.getSentences().add(uctss);
		}
		return uctss;
	}
	
	@Override
	public SVOSentence createSVOSentence(String name) {
		SVOSentence svoSentence = null;
		svoSentence = tslFactory.createSVOSentence();
		svoSentence.setName(name);

		return svoSentence;
	}
	
	@Override
	public ConditionSentence createConditionSentence(String name) {
		ConditionSentence conditionSentence = null;
		conditionSentence = tslFactory.createConditionSentence();
		conditionSentence.setName(name);
		return conditionSentence;
	}

	@Override
	public ControlSentence createControlSentence(String name) {
		ControlSentence controlSentence = null;
		controlSentence = tslFactory.createControlSentence();
		controlSentence.setName(name);

		return controlSentence;
	}

	@Override
	public Condition createCondition(UseCaseTestScenario parent, String name,
			String type) {
		Condition condition = null;
		condition = tslFactory.createCondition();
		condition.setName(name);
		if (parent != null){
			if (type.equals("post"))
				parent.setPostcondition(condition);
			else if (type.equals("pre"))
				parent.setPrecondition(condition);
		}
		return condition;
	}
	
	@Override
	public DomainObject createDirectDomainObject(SVOSentence parent, String name) {
		DomainObject obj = null;
		obj = tslFactory.createDomainObject();
		obj.setName(name);
		if (parent != null){
				parent.setDirectObject(obj);
		}
		return obj;
	}

	@Override
	public DomainObject createIndirectDomainObject(SVOSentence parent, String name) {
		DomainObject obj = null;
		obj = tslFactory.createDomainObject();
		obj.setName(name);
		if (parent != null){
				parent.setIndirectObject(obj);
		}
		return obj;
	}
	
	@Override
	public TestScenario createTestScenario(TestPackage parent, String name) {
		TestScenario ts = null;
		ts = tslFactory.createTestScenario();
		ts.setName(name);
		if (parent != null){
			parent.getEReference3().add(ts);
		}
		return ts;
	}

	@Override
	public CCondition createCCondition(Condition cond) {
		
		CCondition ccond = null;
		ccond = tslFactory.createCCondition();
		if (cond != null){
			if (cond.getName() == null)
				ccond.setName("");
			else
				ccond.setName(cond.getName());
		} else 
			ccond.setName("");
		return ccond;
	}
	
	@Override
	public TestCase createTestCase(ControlSentence parent, UseCaseTestScenario ucts) {
		TestCase tc = null;
		tc = tslFactory.createTestCase();
		tc.setName(ucts.getName());
		if (parent != null){
			parent.getAttachedTests().add(tc);
		}
		return tc;
	}
	
	@Override
	public TestCase createTestCase(TestScenario parent, UseCaseTestScenario ucts) {
		TestCase tc = null;
		tc = tslFactory.createTestCase();
		tc.setName(ucts.getName());
		if (parent != null){
			parent.getEReference1().add(tc);
		}
		return tc;
		/*
		UseCaseTestScenarioInstance inst = null;
		inst = tslFactory.createUseCaseTestScenarioInstance();
		inst.setName(ucts.getName());
		inst.setUcName(((UseCaseTest)ucts.eContainer()).getName());
		inst.setUcTrail(TSLModelHelper.instance().getFullPath((UseCaseTest)ucts.eContainer(),false));
		tc.setEReference1(inst);
		

		Condition preOrigin = ucts.getPrecondition();
		String preName;
		if (preOrigin != null)
			preName = preOrigin.getName();
		else preName = "-";
		Condition pre = this.createCondition(inst, preName, "pre");
		inst.setPrecondition(pre);

		Condition postOrigin = ucts.getPostcondition();
		String postName;
		if (postOrigin != null)
			postName = postOrigin.getName();
		else postName = "-";
		Condition post = this.createCondition(inst, postName, "post");
		inst.setPostcondition(post);
		*/
		/*
		TestCaseSentence tcSen;
		UseCaseTestScenarioSentence uctsSen;
		for (int i=0; i< ucts.getSentences().size(); i++){
			uctsSen = (UseCaseTestScenarioSentence)ucts.getSentences().get(i);
			tcSen = this.createTestCaseSentence(inst, uctsSen.getName());
			ScenarioSentence sen = uctsSen.getScenarioSentence();
			if (sen instanceof SVOSentence){
				SVOSentence svo = this.createSVOSentence(((SVOSentence) sen).getName());
				svo.setNumber(((SVOSentence) sen).getNumber());
				DirectObject diObjOrigin = ((SVOSentence) sen).getDirectObject();
				IndirectObject inObjOrigin = ((SVOSentence) sen).getIndirectObject();
				if (diObjOrigin != null){
					DirectObject diObj = this.createDirectObject(svo, diObjOrigin.getName());
					diObj.setGeneralDomain(diObjOrigin.getGeneralDomain());
					diObj.setInstanceDomain("-");
				}
				if (inObjOrigin != null){
					IndirectObject inObj = this.createIndirectObject(svo, inObjOrigin.getName());
					inObj.setGeneralDomain(inObjOrigin.getGeneralDomain());
					inObj.setInstanceDomain("-");
				}
				tcSen.setEReference1(svo);
			} else if (sen instanceof ControlSentence){
				ControlSentence control = this.createControlSentence(((ControlSentence) sen).getName());
				tcSen.setEReference1(control);
			} else if (sen instanceof ConditionSentence){
				ConditionSentence cond = this.createConditionSentence(((ConditionSentence) sen).getName());
				tcSen.setEReference1(cond);
			}
			inst.getEReference3().add(tcSen);
		}
		//getAllUseCaseTests();
		return tc;
		*/
	}


	@Override
	public List<Notion> getAllNotions() {
		List allNotions = new ArrayList();
		TreeIterator iterator = resource.getAllContents();
		for (Iterator i = resource.getAllContents(); i.hasNext(); ){
			Object obj = i.next();
			if (obj instanceof Notion)
				allNotions.add(obj);
		}
		return allNotions;
	}
	
	@Override
	public List<DomainStatement> getAllDomainStatements() {
		List allDS = new ArrayList();
		TreeIterator iterator = resource.getAllContents();
		for (Iterator i = resource.getAllContents(); i.hasNext(); ){
			Object obj = i.next();
			if (obj instanceof DomainStatement){
				allDS.add(obj);
			}
				
		}
		return allDS;
	}
	
	@Override
	public List getAllUseCaseTests() {
		List allUct = new ArrayList();
		TreeIterator iterator = resource.getAllContents();
		for (Iterator i = resource.getAllContents(); i.hasNext(); ){
			Object obj = i.next();
			if (obj instanceof UseCaseTest)
				allUct.add(obj);
		}
		return allUct;
	}
	
	public List getAllUseCaseTestScenarios() {
		List allUcts = new ArrayList();
		TreeIterator iterator = resource.getAllContents();
		for (Iterator i = resource.getAllContents(); i.hasNext(); ){
			Object obj = i.next();
			if ((obj instanceof UseCaseTestScenario) && !(obj instanceof UseCaseTestScenarioInstance)){
				allUcts.add(obj);
			}
		}
		return allUcts;
	}

	@Override
	public void setModelResources(Resource modelResource) {
		// TODO Auto-generated method stub
		this.resource = modelResource;
	}

	@Override
	public TestCaseSentence createTestCaseSentence(
			TestCase parent, UseCaseTestScenarioSentence uctss) {
		TestCaseSentence tcs = null;
		tcs = tslFactory.createTestCaseSentence();
		tcs.setName(uctss.getName());
		if (parent != null){
			parent.getSentences().add(tcs);
		}
		return tcs;
	}

	@Override
	public NFTest getNFTestByUid(String uid) {
		List nftList = this.getAllNFTests();
		if (nftList.size()>0){
			for (int i=0; i<nftList.size(); i++){
				if (((NFTest)nftList.get(i)).getUid().equals(uid)){
					return (NFTest)nftList.get(i);
				}					 
			}
		}
		return null;
	}

	@Override
	public NFTest getNFTestByName(String name) {
		List nftList = this.getAllNFTests();
		if (nftList.size()>0){
			for (int i=0; i<nftList.size(); i++){
				if (((NFTest)nftList.get(i)).getName().equals(name)){
					return (NFTest)nftList.get(i);
				}					 
			}
		}
		return null;
	}

	@Override
	public List<NFTest> getAllNFTests() {
		List allNFTs = new ArrayList();
		TreeIterator iterator = resource.getAllContents();
		for (Iterator i = resource.getAllContents(); i.hasNext(); ){
			Object obj = i.next();
			if (obj instanceof NFTest){
				allNFTs.add(obj);
			}
				
		}
		return allNFTs;
	}
	
	@Override
	public List<BLTest> getAllBLTests() {
		List allBLTs = new ArrayList();
		TreeIterator iterator = resource.getAllContents();
		for (Iterator i = resource.getAllContents(); i.hasNext(); ){
			Object obj = i.next();
			if (obj instanceof BLTest){
				allBLTs.add(obj);
			}
				
		}
		return allBLTs;
	}

	@Override
	public List<GUITest> getAllGUITests() {
		List allGUITs = new ArrayList();
		TreeIterator iterator = resource.getAllContents();
		for (Iterator i = resource.getAllContents(); i.hasNext(); ){
			Object obj = i.next();
			if (obj instanceof GUITest){
				allGUITs.add(obj);
			}
				
		}
		return allGUITs;
	}

	@Override
	public TestInvocationRelationship createTestInvocationRelationship(
			UseCaseTest invokingUCT, UseCaseTest invokedUCT) {
		TestInvocationRelationship invoke = tslFactory.createTestInvocationRelationship();
		
		if (invokingUCT != null)
			invokingUCT.getInvocationSource().add(invoke);
		if (invokedUCT != null)
			invoke.setInvocationTarget(invokedUCT);
		// TODO Auto-generated method stub
		return invoke;
	}
	
	@Override
	public TestValue createTestValue(DomainObject domainObject) {
		TestValue tv = tslFactory.createTestValue();
		if (domainObject != null){
			domainObject.getInputData().add(tv);
			return tv;
		}
		return null;
	}

	@Override
	public TestCase getTestCase(ScenarioSentence sentence) {
		EObject senContainer = sentence.eContainer();
		if (senContainer instanceof TestCaseSentence){
			EObject tcSenContainer = senContainer.eContainer();
			if (tcSenContainer instanceof TestCase)
				return (TestCase)tcSenContainer;
		}
		return null;
	}


}
