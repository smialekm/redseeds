/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.Condition;
import eu.redset.emf.model.tsl.ConditionSentence;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DirectObject;
import eu.redset.emf.model.tsl.IndirectObject;
import eu.redset.emf.model.tsl.NonFunctionalTest;
import eu.redset.emf.model.tsl.ObjectAttribute;
import eu.redset.emf.model.tsl.Postcondition;
import eu.redset.emf.model.tsl.Precondition;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.TslFactory;
import eu.redset.emf.model.tsl.TslPackage;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenarioInstance;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;
import eu.redset.emf.model.tsl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TslFactoryImpl extends EFactoryImpl implements TslFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TslFactory init() {
		try {
			TslFactory theTslFactory = (TslFactory)EPackage.Registry.INSTANCE.getEFactory("http://tsl/1.0"); 
			if (theTslFactory != null) {
				return theTslFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TslFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TslFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TslPackage.TEST_SPECIFICATION: return createTestSpecification();
			case TslPackage.TEST_PACKAGE: return createTestPackage();
			case TslPackage.TEST: return createTest();
			case TslPackage.USE_CASE_TEST: return createUseCaseTest();
			case TslPackage.USE_CASE_TEST_SCENARIO: return createUseCaseTestScenario();
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE: return createUseCaseTestScenarioSentence();
			case TslPackage.TEST_SCENARIO: return createTestScenario();
			case TslPackage.TEST_CASE: return createTestCase();
			case TslPackage.TEST_CASE_SENTENCE: return createTestCaseSentence();
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE: return createUseCaseTestScenarioInstance();
			case TslPackage.SVO_SENTENCE: return createSVOSentence();
			case TslPackage.CONTROL_SENTENCE: return createControlSentence();
			case TslPackage.CONDITION_SENTENCE: return createConditionSentence();
			case TslPackage.PRECONDITION: return createPrecondition();
			case TslPackage.POSTCONDITION: return createPostcondition();
			case TslPackage.CONDITION: return createCondition();
			case TslPackage.TEST_RELATIONSHIP: return createTestRelationship();
			case TslPackage.TEST_INVOCATION_RELATIONSHIP: return createTestInvocationRelationship();
			case TslPackage.DOMAIN_OBJECT: return createDomainObject();
			case TslPackage.NOTION_ATTRIBUTE: return createNotionAttribute();
			case TslPackage.NF_TEST: return createNFTest();
			case TslPackage.GUI_TEST: return createGUITest();
			case TslPackage.BL_TEST: return createBLTest();
			case TslPackage.NOTION: return createNotion();
			case TslPackage.DOMAIN_STATEMENT: return createDomainStatement();
			case TslPackage.CCONDITION: return createCCondition();
			case TslPackage.TEST_VALUE: return createTestValue();
			case TslPackage.TEST_PARAMETER: return createTestParameter();
			case TslPackage.TEST_INSTANCE: return createTestInstance();
			case TslPackage.NF_TEST_INSTANCE: return createNFTestInstance();
			case TslPackage.GUI_TEST_INSTANCE: return createGUITestInstance();
			case TslPackage.BL_TEST_INSTANCE: return createBLTestInstance();
			case TslPackage.TEST_DATA_VALUES_CONTEXT: return createTestDataValuesContext();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TslPackage.DOMAIN_OBJECT_TYPE:
				return createDomainObjectTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TslPackage.DOMAIN_OBJECT_TYPE:
				return convertDomainObjectTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestSpecification createTestSpecification() {
		TestSpecificationImpl testSpecification = new TestSpecificationImpl();
		return testSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestPackage createTestPackage() {
		TestPackageImpl testPackage = new TestPackageImpl();
		return testPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Test createTest() {
		TestImpl test = new TestImpl();
		return test;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTest createUseCaseTest() {
		UseCaseTestImpl useCaseTest = new UseCaseTestImpl();
		return useCaseTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenario createUseCaseTestScenario() {
		UseCaseTestScenarioImpl useCaseTestScenario = new UseCaseTestScenarioImpl();
		return useCaseTestScenario;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenarioSentence createUseCaseTestScenarioSentence() {
		UseCaseTestScenarioSentenceImpl useCaseTestScenarioSentence = new UseCaseTestScenarioSentenceImpl();
		return useCaseTestScenarioSentence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestScenario createTestScenario() {
		TestScenarioImpl testScenario = new TestScenarioImpl();
		return testScenario;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCase createTestCase() {
		TestCaseImpl testCase = new TestCaseImpl();
		return testCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCaseSentence createTestCaseSentence() {
		TestCaseSentenceImpl testCaseSentence = new TestCaseSentenceImpl();
		return testCaseSentence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenarioInstance createUseCaseTestScenarioInstance() {
		UseCaseTestScenarioInstanceImpl useCaseTestScenarioInstance = new UseCaseTestScenarioInstanceImpl();
		return useCaseTestScenarioInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SVOSentence createSVOSentence() {
		SVOSentenceImpl svoSentence = new SVOSentenceImpl();
		return svoSentence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlSentence createControlSentence() {
		ControlSentenceImpl controlSentence = new ControlSentenceImpl();
		return controlSentence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionSentence createConditionSentence() {
		ConditionSentenceImpl conditionSentence = new ConditionSentenceImpl();
		return conditionSentence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Precondition createPrecondition() {
		PreconditionImpl precondition = new PreconditionImpl();
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Postcondition createPostcondition() {
		PostconditionImpl postcondition = new PostconditionImpl();
		return postcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition createCondition() {
		ConditionImpl condition = new ConditionImpl();
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestRelationship createTestRelationship() {
		TestRelationshipImpl testRelationship = new TestRelationshipImpl();
		return testRelationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestInvocationRelationship createTestInvocationRelationship() {
		TestInvocationRelationshipImpl testInvocationRelationship = new TestInvocationRelationshipImpl();
		return testInvocationRelationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainObject createDomainObject() {
		DomainObjectImpl domainObject = new DomainObjectImpl();
		return domainObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotionAttribute createNotionAttribute() {
		NotionAttributeImpl notionAttribute = new NotionAttributeImpl();
		return notionAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NFTest createNFTest() {
		NFTestImpl nfTest = new NFTestImpl();
		return nfTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GUITest createGUITest() {
		GUITestImpl guiTest = new GUITestImpl();
		return guiTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BLTest createBLTest() {
		BLTestImpl blTest = new BLTestImpl();
		return blTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Notion createNotion() {
		NotionImpl notion = new NotionImpl();
		return notion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainStatement createDomainStatement() {
		DomainStatementImpl domainStatement = new DomainStatementImpl();
		return domainStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CCondition createCCondition() {
		CConditionImpl cCondition = new CConditionImpl();
		return cCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestValue createTestValue() {
		TestValueImpl testValue = new TestValueImpl();
		return testValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestParameter createTestParameter() {
		TestParameterImpl testParameter = new TestParameterImpl();
		return testParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestInstance createTestInstance() {
		TestInstanceImpl testInstance = new TestInstanceImpl();
		return testInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NFTestInstance createNFTestInstance() {
		NFTestInstanceImpl nfTestInstance = new NFTestInstanceImpl();
		return nfTestInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GUITestInstance createGUITestInstance() {
		GUITestInstanceImpl guiTestInstance = new GUITestInstanceImpl();
		return guiTestInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BLTestInstance createBLTestInstance() {
		BLTestInstanceImpl blTestInstance = new BLTestInstanceImpl();
		return blTestInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestDataValuesContext createTestDataValuesContext() {
		TestDataValuesContextImpl testDataValuesContext = new TestDataValuesContextImpl();
		return testDataValuesContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainObjectType createDomainObjectTypeFromString(EDataType eDataType, String initialValue) {
		DomainObjectType result = DomainObjectType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDomainObjectTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TslPackage getTslPackage() {
		return (TslPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TslPackage getPackage() {
		return TslPackage.eINSTANCE;
	}

} //TslFactoryImpl
