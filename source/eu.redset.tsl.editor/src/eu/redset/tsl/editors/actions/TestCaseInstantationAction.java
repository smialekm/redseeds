package eu.redset.tsl.editors.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;


import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.BLTestInstance;
import eu.redset.emf.model.tsl.ConditionSentence;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.GUITestInstance;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.NFTestInstance;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestInstance;
import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TestParameter;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;
import eu.redset.emf.model.tsl.util.TSLModelHelper;

public class TestCaseInstantationAction {

	private TestScenario ts;
	private TSLBusinessLayerFacade tslFacade;
	private Map<ControlSentence, UseCaseTest> invokesMap;// = new HashMap<ControlSentence, UseCaseTest>();
	
	
	public TestCaseInstantationAction(TestScenario ts){
		this.ts = ts;
		//this.ucts = ucts;
		this.tslFacade = SCProjectContainer.instance().getSCProject(ts).getTestSpecificationContainer().getTSLFacade(ts);
		invokesMap = new HashMap<ControlSentence, UseCaseTest>();
	}
	
	private int calculateInternalTCOrderNumber(TestCase tc){
		int orderNumber = 0;//tc.getOrderNumber()*10;
		List sentences = tc.getSentences();
		for (Object o : sentences){
			TestCaseSentence tcs = (TestCaseSentence)o;
			if (tcs.getScenarioSentence() instanceof ControlSentence){
				ControlSentence cs = (ControlSentence)tcs.getScenarioSentence();
				for (TestInstance ti : cs.getAttachedTests()){
					if (ti instanceof TestCase){
						orderNumber = orderNumber+1;
					}
				}
			}
		}
		return orderNumber;
	}
	
	private String calculateGlobalTCOrderNumber(TestCase tc){
		String orderNumber = "";
		orderNumber = orderNumber+tc.getOrderNumber();
		EObject eObj = tc.eContainer();
		while (!(eObj instanceof TestScenario)) {
			if (eObj instanceof TestCase){
				orderNumber = Integer.toString(((TestCase)eObj).getOrderNumber())+"."+orderNumber;
			}
			eObj = eObj.eContainer();
		}
		return orderNumber;
	}
	
	public TestCase createTestCase(Test parentTest, UseCaseTestScenario ucts){
		TestCase tc = null;
		if (parentTest instanceof TestScenario){
			tc = tslFacade.createTestCase((TestScenario)parentTest, ucts);
			tc.setOrderNumber(((TestScenario)parentTest).getEReference1().size());
			tc.setOrderNumberGlobal(Integer.toString(tc.getOrderNumber()));
		} else if (parentTest instanceof ControlSentence){
			ControlSentence cs = (ControlSentence)parentTest;
			tc = tslFacade.createTestCase(cs, ucts);
			TestCase parentTestCase = tslFacade.getTestCase((ScenarioSentence)parentTest);
			tc.setOrderNumber(calculateInternalTCOrderNumber(parentTestCase));
			tc.setOrderNumberGlobal(calculateGlobalTCOrderNumber(tc));
		}
		
		if (tc != null){
			createBasicData(tc, ucts, ts);
			createConditions(tc, ucts);
			createAttachedTests(tc, ucts.getSource());
			createSenteces(tc, ucts);
		}
		//updateTSContext(ts, tc);
		return tc;
	}
	/*
	private void updateTSContext(TestScenario ts, TestCase tc){
		List tsContext = ts.getDomainObjects();
		for (TestCaseSentence tcs : tc.getSentences()){
			if (tcs.getScenarioSentence() instanceof SVOSentence){
				SVOSentence svo = (SVOSentence)tcs.getScenarioSentence();
				//if (svo.get)
			}
		}
	}
		*/
	/*
	public TestCase createTestCaseForTestScenario(TestScenario tsi, UseCaseTestScenario uctsi){
		TestCase tc;
		tc = tslFacade.createTestCase(ts, uctsi);
		createBasicData(tc, uctsi, tsi);
		createConditions(tc, uctsi);
		createAttachedTests(tc, uctsi.getSource());
		createSenteces(tc, uctsi);
		return tc;
	}
	*/
	
	private void createBasicData(TestCase tci, UseCaseTestScenario uctsi, TestScenario tsi){
		tci.setUcName(((UseCaseTest)uctsi.eContainer()).getName());
		tci.setUcScenarioName(uctsi.getName());
		tci.setUcTrail(TSLModelHelper.instance().getFullPath((UseCaseTest)uctsi.eContainer(),false));	
		//if (tsi != null)
		//	tci.setOrderNumber(tsi.getEReference1().size());
	}
	
	private void createConditions(TestCase tci, UseCaseTestScenario uctsi){
		tci.setPrecondition(tslFacade.createCCondition(uctsi.getPrecondition()));
		tci.setPostcondition(tslFacade.createCCondition(uctsi.getPostcondition()));
	}
	
	private void createSenteces(TestCase tci, UseCaseTestScenario uctsi){
		TestCaseSentence tcSen;
		UseCaseTestScenarioSentence uctsSen;
		for (int i=0; i< uctsi.getSentences().size(); i++){
			uctsSen = (UseCaseTestScenarioSentence)uctsi.getSentences().get(i);
			tcSen = tslFacade.createTestCaseSentence(tci, uctsSen);
			ScenarioSentence sen = uctsSen.getScenarioSentence();
			if (sen instanceof SVOSentence){
				SVOSentence svo = tslFacade.createSVOSentence(((SVOSentence) sen).getName());
				svo.setNumber(((SVOSentence) sen).getNumber());
				DomainStatement svoPredicate = ((SVOSentence) sen).getPredicate();
				createAttachedTests(svo, svoPredicate.getSource());
				
				//DomainStatement svoPredicate = ((SVOSentence) sen).getPredicate();
				if (svoPredicate.getDirectNotion() != null) {
					DomainObject diObj = tslFacade.createDirectDomainObject(svo, svoPredicate.getDirectNotion().getNotionName());
					
					createAttachedTests(diObj, svoPredicate.getDirectNotion().getSource());
				}
				
				if (svoPredicate.getIndirectNotion() != null) {
					DomainObject inObj = tslFacade.createIndirectDomainObject(svo, svoPredicate.getIndirectNotion().getNotionName());
					createAttachedTests(inObj, svoPredicate.getDirectNotion().getSource());
				}
				tcSen.setScenarioSentence(svo);
			} else if (sen instanceof ControlSentence){
				ControlSentence control = tslFacade.createControlSentence(((ControlSentence) sen).getName());
				tcSen.setScenarioSentence(control);
				TestInvocationRelationship tir = ((ControlSentence)sen).getInvocation();
				if (tir != null){
					if (tir.getInvocationTarget() != null){
						invokesMap.put(control, tir.getInvocationTarget());
					}
				}
			} else if (sen instanceof ConditionSentence){
				ConditionSentence cond = tslFacade.createConditionSentence(((ConditionSentence) sen).getName());
				tcSen.setScenarioSentence(cond);
			}		
			tci.getSentences().add(tcSen);
		} //for
	} //method
	
	private void createDomainObject(){
		
	}
	
	/*
	private void addDomainObjectToTestScenarioContext(DomainObject obj, TestScenario testScenario){
		if (testScenario != null){
			List tsContext = testScenario.getDomainObjects();
			
			for (Object dObj : tsContext){
				if (obj.getName().equals(((Notion)dObj).getName()))
					return;
			}
			//tsContext
		}
	}
	*/
	
	private void createAttachedTests(TestInstance parentTest, List<TestRelationship> testRelationships){
		for (TestRelationship tr : testRelationships){
			Test t = tr.getTarget();
			if (t instanceof NFTest){
				NFTest nft = (NFTest)t;
				NFTestInstance nfti = tslFacade.createNFTestInstance(parentTest, nft.getName());
				nfti.setDescription(nft.getDescription());
				for (TestParameter tp : nft.getTestParameters()){
					TestParameter tpi = tslFacade.createTestParameter(nfti, tp.getName());
					tpi.setDomain(tp.getDomain());
				}
			} else if (t instanceof GUITest) {
				GUITest guit = (GUITest)t;
				GUITestInstance guiti = tslFacade.createGUITestInstance(parentTest, guit.getName());
				guiti.setDescription(guit.getDescription());
				for (TestParameter tp : guit.getTestParameters()){
					TestParameter tpi = tslFacade.createTestParameter(guiti, tp.getName());
					tpi.setDomain(tp.getDomain());
				}
			} else if (t instanceof BLTest) {
				BLTest blt = (BLTest)t;
				BLTestInstance blti = tslFacade.createBLTestInstance(parentTest, blt.getName());
				blti.setDescription(blt.getDescription());
				for (TestParameter tp : blt.getTestParameters()){
					TestParameter tpi = tslFacade.createTestParameter(blti, tp.getName());
					tpi.setDomain(tp.getDomain());
				}
			}
		}
	}



	public Map<ControlSentence, UseCaseTest> getInvokesMap() {
		return invokesMap;
	}	
	
}
