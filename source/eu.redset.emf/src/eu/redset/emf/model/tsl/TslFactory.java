/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eu.redset.emf.model.tsl.TslPackage
 * @generated
 */
public interface TslFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TslFactory eINSTANCE = eu.redset.emf.model.tsl.impl.TslFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Test Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Specification</em>'.
	 * @generated
	 */
	TestSpecification createTestSpecification();

	/**
	 * Returns a new object of class '<em>Test Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Package</em>'.
	 * @generated
	 */
	TestPackage createTestPackage();

	/**
	 * Returns a new object of class '<em>Test</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test</em>'.
	 * @generated
	 */
	Test createTest();

	/**
	 * Returns a new object of class '<em>Use Case Test</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Use Case Test</em>'.
	 * @generated
	 */
	UseCaseTest createUseCaseTest();

	/**
	 * Returns a new object of class '<em>Use Case Test Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Use Case Test Scenario</em>'.
	 * @generated
	 */
	UseCaseTestScenario createUseCaseTestScenario();

	/**
	 * Returns a new object of class '<em>Use Case Test Scenario Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Use Case Test Scenario Sentence</em>'.
	 * @generated
	 */
	UseCaseTestScenarioSentence createUseCaseTestScenarioSentence();

	/**
	 * Returns a new object of class '<em>Test Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Scenario</em>'.
	 * @generated
	 */
	TestScenario createTestScenario();

	/**
	 * Returns a new object of class '<em>Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case</em>'.
	 * @generated
	 */
	TestCase createTestCase();

	/**
	 * Returns a new object of class '<em>Test Case Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case Sentence</em>'.
	 * @generated
	 */
	TestCaseSentence createTestCaseSentence();

	/**
	 * Returns a new object of class '<em>Use Case Test Scenario Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Use Case Test Scenario Instance</em>'.
	 * @generated
	 */
	UseCaseTestScenarioInstance createUseCaseTestScenarioInstance();

	/**
	 * Returns a new object of class '<em>SVO Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SVO Sentence</em>'.
	 * @generated
	 */
	SVOSentence createSVOSentence();

	/**
	 * Returns a new object of class '<em>Control Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control Sentence</em>'.
	 * @generated
	 */
	ControlSentence createControlSentence();

	/**
	 * Returns a new object of class '<em>Condition Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Condition Sentence</em>'.
	 * @generated
	 */
	ConditionSentence createConditionSentence();

	/**
	 * Returns a new object of class '<em>Precondition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Precondition</em>'.
	 * @generated
	 */
	Precondition createPrecondition();

	/**
	 * Returns a new object of class '<em>Postcondition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Postcondition</em>'.
	 * @generated
	 */
	Postcondition createPostcondition();

	/**
	 * Returns a new object of class '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Condition</em>'.
	 * @generated
	 */
	Condition createCondition();

	/**
	 * Returns a new object of class '<em>Test Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Relationship</em>'.
	 * @generated
	 */
	TestRelationship createTestRelationship();

	/**
	 * Returns a new object of class '<em>Test Invocation Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Invocation Relationship</em>'.
	 * @generated
	 */
	TestInvocationRelationship createTestInvocationRelationship();

	/**
	 * Returns a new object of class '<em>Domain Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Object</em>'.
	 * @generated
	 */
	DomainObject createDomainObject();

	/**
	 * Returns a new object of class '<em>Notion Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Notion Attribute</em>'.
	 * @generated
	 */
	NotionAttribute createNotionAttribute();

	/**
	 * Returns a new object of class '<em>NF Test</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>NF Test</em>'.
	 * @generated
	 */
	NFTest createNFTest();

	/**
	 * Returns a new object of class '<em>GUI Test</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>GUI Test</em>'.
	 * @generated
	 */
	GUITest createGUITest();

	/**
	 * Returns a new object of class '<em>BL Test</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>BL Test</em>'.
	 * @generated
	 */
	BLTest createBLTest();

	/**
	 * Returns a new object of class '<em>Notion</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Notion</em>'.
	 * @generated
	 */
	Notion createNotion();

	/**
	 * Returns a new object of class '<em>Domain Statement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Statement</em>'.
	 * @generated
	 */
	DomainStatement createDomainStatement();

	/**
	 * Returns a new object of class '<em>CCondition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CCondition</em>'.
	 * @generated
	 */
	CCondition createCCondition();

	/**
	 * Returns a new object of class '<em>Test Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Value</em>'.
	 * @generated
	 */
	TestValue createTestValue();

	/**
	 * Returns a new object of class '<em>Test Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Parameter</em>'.
	 * @generated
	 */
	TestParameter createTestParameter();

	/**
	 * Returns a new object of class '<em>Test Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Instance</em>'.
	 * @generated
	 */
	TestInstance createTestInstance();

	/**
	 * Returns a new object of class '<em>NF Test Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>NF Test Instance</em>'.
	 * @generated
	 */
	NFTestInstance createNFTestInstance();

	/**
	 * Returns a new object of class '<em>GUI Test Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>GUI Test Instance</em>'.
	 * @generated
	 */
	GUITestInstance createGUITestInstance();

	/**
	 * Returns a new object of class '<em>BL Test Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>BL Test Instance</em>'.
	 * @generated
	 */
	BLTestInstance createBLTestInstance();

	/**
	 * Returns a new object of class '<em>Test Data Values Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Data Values Context</em>'.
	 * @generated
	 */
	TestDataValuesContext createTestDataValuesContext();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TslPackage getTslPackage();

} //TslFactory
