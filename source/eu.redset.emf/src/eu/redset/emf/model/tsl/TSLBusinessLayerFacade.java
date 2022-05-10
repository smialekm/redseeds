package eu.redset.emf.model.tsl;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;


public interface TSLBusinessLayerFacade {
	
	void setModelResources(Resource modelResource);
	
	void setTestSpecification(TestSpecification modelObject);

	void createTestSpecification(String name);

	TestSpecification getTestSpecification();

	TestPackage createTestPackage(TestPackage parent, String name);
	
	void createInitialTestSpecificationStructure();
	
	Test createTest(TestPackage parent, String name);
	
	TestPackage getUseCaseTestPackage();
	
	TestPackage getNotionsPackage();
	
	TestPackage getNFTestsPackage();
	
	TestPackage getBLTestsPackage();
	
	TestPackage getGUITestsPackage();
	
	Notion createNotion(TestPackage parent, String name);
	
	Notion getNotionByUid(String uid);
	
	NFTest getNFTestByUid(String uid);
	
	NFTest getNFTestByName(String name);
	
	BLTest getBLTestByName(String name);
	
	GUITest getGUITestByName(String name);
	
	NFTest createNFTest(TestPackage parent, String name);
	
	BLTest createBLTest(TestPackage parent, String name);
	
	GUITest createGUITest(TestPackage parent, String name);
	
	NFTestInstance createNFTestInstance(TestInstance parent, String name);
	
	BLTestInstance createBLTestInstance(TestInstance parent, String name);
	
	GUITestInstance createGUITestInstance(TestInstance parent, String name);
	
	TestParameter createTestParameter(Test parentTest, String name);
	
	DomainStatement getDomainStatementByUid(String uid);
	
	TestInvocationRelationship createTestInvocationRelationship(UseCaseTest invokingUCT, UseCaseTest invokedUCT);
	
	TestValue createTestValue(DomainObject domainObject);
	
	TestRelationship createTestRelationship(Test parentTest, Test childTest);
	
	NotionAttribute createNotionAttribute(Notion parent, String name);
	
	UseCaseTest createUseCaseTest(TestPackage parent, String name);
	
	DomainStatement createDomainStatement(Notion notion, String name);
	
	UseCaseTestScenario createUseCaseTestScenario(UseCaseTest parent, String name);
	
	UseCaseTest getUseCaseTestByUid(String uid);
	
	UseCaseTestScenarioSentence createUseCaseTestScenarioSentence(UseCaseTestScenario parent, String name);
	
	SVOSentence createSVOSentence(String name);
	
	ConditionSentence createConditionSentence(String name);
	
	ControlSentence createControlSentence(String name);
	
	Condition createCondition(UseCaseTestScenario parent, String name, String type);
	
	CCondition createCCondition(Condition cond);
	
	DomainObject createDirectDomainObject(SVOSentence parent, String name);
	
	DomainObject createIndirectDomainObject(SVOSentence parent, String name);
	
	//IndirectObject createIndirectObject(SVOSentence parent, String name);
	
	TestScenario createTestScenario(TestPackage parent, String name);
	
	TestCase createTestCase(TestScenario parent, UseCaseTestScenario ucts);
	
	TestCase createTestCase(ControlSentence parent, UseCaseTestScenario ucts);
	
	TestCaseSentence createTestCaseSentence(TestCase parent, UseCaseTestScenarioSentence uctss);
	
	List<UseCaseTest> getAllUseCaseTests();
	
	List<Notion> getAllNotions();
	
	List<DomainStatement> getAllDomainStatements();
		
	List<UseCaseTestScenario> getAllUseCaseTestScenarios();
	
	List<NFTest> getAllNFTests();
	
	List<BLTest> getAllBLTests();
	
	List<GUITest> getAllGUITests();
	
	TestCase getTestCase(ScenarioSentence sentence);

}
