/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.util;

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
import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.TslPackage;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenarioInstance;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;
import eu.redset.emf.model.tsl.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see eu.redset.emf.model.tsl.TslPackage
 * @generated
 */
public class TslSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TslPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TslSwitch() {
		if (modelPackage == null) {
			modelPackage = TslPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TslPackage.TEST_SPECIFICATION: {
				TestSpecification testSpecification = (TestSpecification)theEObject;
				T result = caseTestSpecification(testSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_PACKAGE: {
				TestPackage testPackage = (TestPackage)theEObject;
				T result = caseTestPackage(testPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST: {
				Test test = (Test)theEObject;
				T result = caseTest(test);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.USE_CASE_TEST: {
				UseCaseTest useCaseTest = (UseCaseTest)theEObject;
				T result = caseUseCaseTest(useCaseTest);
				if (result == null) result = caseTest(useCaseTest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.USE_CASE_TEST_SCENARIO: {
				UseCaseTestScenario useCaseTestScenario = (UseCaseTestScenario)theEObject;
				T result = caseUseCaseTestScenario(useCaseTestScenario);
				if (result == null) result = caseTest(useCaseTestScenario);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE: {
				UseCaseTestScenarioSentence useCaseTestScenarioSentence = (UseCaseTestScenarioSentence)theEObject;
				T result = caseUseCaseTestScenarioSentence(useCaseTestScenarioSentence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_SCENARIO: {
				TestScenario testScenario = (TestScenario)theEObject;
				T result = caseTestScenario(testScenario);
				if (result == null) result = caseTest(testScenario);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_CASE: {
				TestCase testCase = (TestCase)theEObject;
				T result = caseTestCase(testCase);
				if (result == null) result = caseTestInstance(testCase);
				if (result == null) result = caseTest(testCase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_CASE_SENTENCE: {
				TestCaseSentence testCaseSentence = (TestCaseSentence)theEObject;
				T result = caseTestCaseSentence(testCaseSentence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE: {
				UseCaseTestScenarioInstance useCaseTestScenarioInstance = (UseCaseTestScenarioInstance)theEObject;
				T result = caseUseCaseTestScenarioInstance(useCaseTestScenarioInstance);
				if (result == null) result = caseUseCaseTestScenario(useCaseTestScenarioInstance);
				if (result == null) result = caseTest(useCaseTestScenarioInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.SCENARIO_SENTENCE: {
				ScenarioSentence scenarioSentence = (ScenarioSentence)theEObject;
				T result = caseScenarioSentence(scenarioSentence);
				if (result == null) result = caseTestInstance(scenarioSentence);
				if (result == null) result = caseTest(scenarioSentence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.SVO_SENTENCE: {
				SVOSentence svoSentence = (SVOSentence)theEObject;
				T result = caseSVOSentence(svoSentence);
				if (result == null) result = caseScenarioSentence(svoSentence);
				if (result == null) result = caseTestInstance(svoSentence);
				if (result == null) result = caseTest(svoSentence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.CONTROL_SENTENCE: {
				ControlSentence controlSentence = (ControlSentence)theEObject;
				T result = caseControlSentence(controlSentence);
				if (result == null) result = caseScenarioSentence(controlSentence);
				if (result == null) result = caseTestInstance(controlSentence);
				if (result == null) result = caseTest(controlSentence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.CONDITION_SENTENCE: {
				ConditionSentence conditionSentence = (ConditionSentence)theEObject;
				T result = caseConditionSentence(conditionSentence);
				if (result == null) result = caseScenarioSentence(conditionSentence);
				if (result == null) result = caseTestInstance(conditionSentence);
				if (result == null) result = caseTest(conditionSentence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.PRECONDITION: {
				Precondition precondition = (Precondition)theEObject;
				T result = casePrecondition(precondition);
				if (result == null) result = caseCondition(precondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.POSTCONDITION: {
				Postcondition postcondition = (Postcondition)theEObject;
				T result = casePostcondition(postcondition);
				if (result == null) result = caseCondition(postcondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.CONDITION: {
				Condition condition = (Condition)theEObject;
				T result = caseCondition(condition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_RELATIONSHIP: {
				TestRelationship testRelationship = (TestRelationship)theEObject;
				T result = caseTestRelationship(testRelationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_INVOCATION_RELATIONSHIP: {
				TestInvocationRelationship testInvocationRelationship = (TestInvocationRelationship)theEObject;
				T result = caseTestInvocationRelationship(testInvocationRelationship);
				if (result == null) result = caseTestRelationship(testInvocationRelationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.DOMAIN_OBJECT: {
				DomainObject domainObject = (DomainObject)theEObject;
				T result = caseDomainObject(domainObject);
				if (result == null) result = caseTestInstance(domainObject);
				if (result == null) result = caseTest(domainObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.NOTION_ATTRIBUTE: {
				NotionAttribute notionAttribute = (NotionAttribute)theEObject;
				T result = caseNotionAttribute(notionAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.NF_TEST: {
				NFTest nfTest = (NFTest)theEObject;
				T result = caseNFTest(nfTest);
				if (result == null) result = caseTest(nfTest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.GUI_TEST: {
				GUITest guiTest = (GUITest)theEObject;
				T result = caseGUITest(guiTest);
				if (result == null) result = caseTest(guiTest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.BL_TEST: {
				BLTest blTest = (BLTest)theEObject;
				T result = caseBLTest(blTest);
				if (result == null) result = caseTest(blTest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.NOTION: {
				Notion notion = (Notion)theEObject;
				T result = caseNotion(notion);
				if (result == null) result = caseTest(notion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.DOMAIN_STATEMENT: {
				DomainStatement domainStatement = (DomainStatement)theEObject;
				T result = caseDomainStatement(domainStatement);
				if (result == null) result = caseTest(domainStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.CCONDITION: {
				CCondition cCondition = (CCondition)theEObject;
				T result = caseCCondition(cCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_VALUE: {
				TestValue testValue = (TestValue)theEObject;
				T result = caseTestValue(testValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_PARAMETER: {
				TestParameter testParameter = (TestParameter)theEObject;
				T result = caseTestParameter(testParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_INSTANCE: {
				TestInstance testInstance = (TestInstance)theEObject;
				T result = caseTestInstance(testInstance);
				if (result == null) result = caseTest(testInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.NF_TEST_INSTANCE: {
				NFTestInstance nfTestInstance = (NFTestInstance)theEObject;
				T result = caseNFTestInstance(nfTestInstance);
				if (result == null) result = caseTestInstance(nfTestInstance);
				if (result == null) result = caseTest(nfTestInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.GUI_TEST_INSTANCE: {
				GUITestInstance guiTestInstance = (GUITestInstance)theEObject;
				T result = caseGUITestInstance(guiTestInstance);
				if (result == null) result = caseTestInstance(guiTestInstance);
				if (result == null) result = caseTest(guiTestInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.BL_TEST_INSTANCE: {
				BLTestInstance blTestInstance = (BLTestInstance)theEObject;
				T result = caseBLTestInstance(blTestInstance);
				if (result == null) result = caseTestInstance(blTestInstance);
				if (result == null) result = caseTest(blTestInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TslPackage.TEST_DATA_VALUES_CONTEXT: {
				TestDataValuesContext testDataValuesContext = (TestDataValuesContext)theEObject;
				T result = caseTestDataValuesContext(testDataValuesContext);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestSpecification(TestSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestPackage(TestPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTest(Test object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Use Case Test</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Case Test</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUseCaseTest(UseCaseTest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Use Case Test Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Case Test Scenario</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUseCaseTestScenario(UseCaseTestScenario object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Use Case Test Scenario Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Case Test Scenario Sentence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUseCaseTestScenarioSentence(UseCaseTestScenarioSentence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Scenario</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestScenario(TestScenario object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCase(TestCase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Case Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Case Sentence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCaseSentence(TestCaseSentence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Use Case Test Scenario Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Case Test Scenario Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUseCaseTestScenarioInstance(UseCaseTestScenarioInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scenario Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scenario Sentence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScenarioSentence(ScenarioSentence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SVO Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SVO Sentence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSVOSentence(SVOSentence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Control Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Control Sentence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControlSentence(ControlSentence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition Sentence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition Sentence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionSentence(ConditionSentence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Precondition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Precondition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrecondition(Precondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Postcondition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Postcondition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePostcondition(Postcondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCondition(Condition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestRelationship(TestRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Invocation Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Invocation Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestInvocationRelationship(TestInvocationRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDomainObject(DomainObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Notion Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Notion Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotionAttribute(NotionAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>NF Test</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>NF Test</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNFTest(NFTest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>GUI Test</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>GUI Test</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGUITest(GUITest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>BL Test</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>BL Test</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBLTest(BLTest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Notion</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Notion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotion(Notion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDomainStatement(DomainStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CCondition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CCondition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCCondition(CCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestValue(TestValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestParameter(TestParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestInstance(TestInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>NF Test Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>NF Test Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNFTestInstance(NFTestInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>GUI Test Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>GUI Test Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGUITestInstance(GUITestInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>BL Test Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>BL Test Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBLTestInstance(BLTestInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Data Values Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Data Values Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestDataValuesContext(TestDataValuesContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //TslSwitch
