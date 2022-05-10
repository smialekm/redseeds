/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.util;

import eu.redset.emf.model.tsl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see eu.redset.emf.model.tsl.TslPackage
 * @generated
 */
public class TslAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TslPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TslAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TslPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TslSwitch<Adapter> modelSwitch =
		new TslSwitch<Adapter>() {
			@Override
			public Adapter caseTestSpecification(TestSpecification object) {
				return createTestSpecificationAdapter();
			}
			@Override
			public Adapter caseTestPackage(TestPackage object) {
				return createTestPackageAdapter();
			}
			@Override
			public Adapter caseTest(Test object) {
				return createTestAdapter();
			}
			@Override
			public Adapter caseUseCaseTest(UseCaseTest object) {
				return createUseCaseTestAdapter();
			}
			@Override
			public Adapter caseUseCaseTestScenario(UseCaseTestScenario object) {
				return createUseCaseTestScenarioAdapter();
			}
			@Override
			public Adapter caseUseCaseTestScenarioSentence(UseCaseTestScenarioSentence object) {
				return createUseCaseTestScenarioSentenceAdapter();
			}
			@Override
			public Adapter caseTestScenario(TestScenario object) {
				return createTestScenarioAdapter();
			}
			@Override
			public Adapter caseTestCase(TestCase object) {
				return createTestCaseAdapter();
			}
			@Override
			public Adapter caseTestCaseSentence(TestCaseSentence object) {
				return createTestCaseSentenceAdapter();
			}
			@Override
			public Adapter caseUseCaseTestScenarioInstance(UseCaseTestScenarioInstance object) {
				return createUseCaseTestScenarioInstanceAdapter();
			}
			@Override
			public Adapter caseScenarioSentence(ScenarioSentence object) {
				return createScenarioSentenceAdapter();
			}
			@Override
			public Adapter caseSVOSentence(SVOSentence object) {
				return createSVOSentenceAdapter();
			}
			@Override
			public Adapter caseControlSentence(ControlSentence object) {
				return createControlSentenceAdapter();
			}
			@Override
			public Adapter caseConditionSentence(ConditionSentence object) {
				return createConditionSentenceAdapter();
			}
			@Override
			public Adapter casePrecondition(Precondition object) {
				return createPreconditionAdapter();
			}
			@Override
			public Adapter casePostcondition(Postcondition object) {
				return createPostconditionAdapter();
			}
			@Override
			public Adapter caseCondition(Condition object) {
				return createConditionAdapter();
			}
			@Override
			public Adapter caseTestRelationship(TestRelationship object) {
				return createTestRelationshipAdapter();
			}
			@Override
			public Adapter caseTestInvocationRelationship(TestInvocationRelationship object) {
				return createTestInvocationRelationshipAdapter();
			}
			@Override
			public Adapter caseDomainObject(DomainObject object) {
				return createDomainObjectAdapter();
			}
			@Override
			public Adapter caseNotionAttribute(NotionAttribute object) {
				return createNotionAttributeAdapter();
			}
			@Override
			public Adapter caseNFTest(NFTest object) {
				return createNFTestAdapter();
			}
			@Override
			public Adapter caseGUITest(GUITest object) {
				return createGUITestAdapter();
			}
			@Override
			public Adapter caseBLTest(BLTest object) {
				return createBLTestAdapter();
			}
			@Override
			public Adapter caseNotion(Notion object) {
				return createNotionAdapter();
			}
			@Override
			public Adapter caseDomainStatement(DomainStatement object) {
				return createDomainStatementAdapter();
			}
			@Override
			public Adapter caseCCondition(CCondition object) {
				return createCConditionAdapter();
			}
			@Override
			public Adapter caseTestValue(TestValue object) {
				return createTestValueAdapter();
			}
			@Override
			public Adapter caseTestParameter(TestParameter object) {
				return createTestParameterAdapter();
			}
			@Override
			public Adapter caseTestInstance(TestInstance object) {
				return createTestInstanceAdapter();
			}
			@Override
			public Adapter caseNFTestInstance(NFTestInstance object) {
				return createNFTestInstanceAdapter();
			}
			@Override
			public Adapter caseGUITestInstance(GUITestInstance object) {
				return createGUITestInstanceAdapter();
			}
			@Override
			public Adapter caseBLTestInstance(BLTestInstance object) {
				return createBLTestInstanceAdapter();
			}
			@Override
			public Adapter caseTestDataValuesContext(TestDataValuesContext object) {
				return createTestDataValuesContextAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestSpecification <em>Test Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestSpecification
	 * @generated
	 */
	public Adapter createTestSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestPackage <em>Test Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestPackage
	 * @generated
	 */
	public Adapter createTestPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.Test <em>Test</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.Test
	 * @generated
	 */
	public Adapter createTestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.UseCaseTest <em>Use Case Test</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.UseCaseTest
	 * @generated
	 */
	public Adapter createUseCaseTestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.UseCaseTestScenario <em>Use Case Test Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenario
	 * @generated
	 */
	public Adapter createUseCaseTestScenarioAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence <em>Use Case Test Scenario Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioSentence
	 * @generated
	 */
	public Adapter createUseCaseTestScenarioSentenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestScenario <em>Test Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestScenario
	 * @generated
	 */
	public Adapter createTestScenarioAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestCase
	 * @generated
	 */
	public Adapter createTestCaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestCaseSentence <em>Test Case Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestCaseSentence
	 * @generated
	 */
	public Adapter createTestCaseSentenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance <em>Use Case Test Scenario Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioInstance
	 * @generated
	 */
	public Adapter createUseCaseTestScenarioInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.ScenarioSentence <em>Scenario Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.ScenarioSentence
	 * @generated
	 */
	public Adapter createScenarioSentenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.SVOSentence <em>SVO Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.SVOSentence
	 * @generated
	 */
	public Adapter createSVOSentenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.ControlSentence <em>Control Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.ControlSentence
	 * @generated
	 */
	public Adapter createControlSentenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.ConditionSentence <em>Condition Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.ConditionSentence
	 * @generated
	 */
	public Adapter createConditionSentenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.Precondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.Precondition
	 * @generated
	 */
	public Adapter createPreconditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.Postcondition <em>Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.Postcondition
	 * @generated
	 */
	public Adapter createPostconditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.Condition
	 * @generated
	 */
	public Adapter createConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestRelationship <em>Test Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestRelationship
	 * @generated
	 */
	public Adapter createTestRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestInvocationRelationship <em>Test Invocation Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestInvocationRelationship
	 * @generated
	 */
	public Adapter createTestInvocationRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.DomainObject <em>Domain Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.DomainObject
	 * @generated
	 */
	public Adapter createDomainObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.NotionAttribute <em>Notion Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.NotionAttribute
	 * @generated
	 */
	public Adapter createNotionAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.NFTest <em>NF Test</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.NFTest
	 * @generated
	 */
	public Adapter createNFTestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.GUITest <em>GUI Test</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.GUITest
	 * @generated
	 */
	public Adapter createGUITestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.BLTest <em>BL Test</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.BLTest
	 * @generated
	 */
	public Adapter createBLTestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.Notion <em>Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.Notion
	 * @generated
	 */
	public Adapter createNotionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.DomainStatement <em>Domain Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.DomainStatement
	 * @generated
	 */
	public Adapter createDomainStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.CCondition <em>CCondition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.CCondition
	 * @generated
	 */
	public Adapter createCConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestValue <em>Test Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestValue
	 * @generated
	 */
	public Adapter createTestValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestParameter <em>Test Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestParameter
	 * @generated
	 */
	public Adapter createTestParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestInstance <em>Test Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestInstance
	 * @generated
	 */
	public Adapter createTestInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.NFTestInstance <em>NF Test Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.NFTestInstance
	 * @generated
	 */
	public Adapter createNFTestInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.GUITestInstance <em>GUI Test Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.GUITestInstance
	 * @generated
	 */
	public Adapter createGUITestInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.BLTestInstance <em>BL Test Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.BLTestInstance
	 * @generated
	 */
	public Adapter createBLTestInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.redset.emf.model.tsl.TestDataValuesContext <em>Test Data Values Context</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.redset.emf.model.tsl.TestDataValuesContext
	 * @generated
	 */
	public Adapter createTestDataValuesContextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TslAdapterFactory
