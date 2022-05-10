/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Case Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.TestCaseSentence#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCaseSentence#getScenarioSentence <em>Scenario Sentence</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCaseSentence#getName <em>Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCaseSentence#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getTestCaseSentence()
 * @model
 * @generated
 */
public interface TestCaseSentence extends EObject {
	/**
	 * Returns the value of the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference0</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference0</em>' reference.
	 * @see #setEReference0(UseCaseTestScenarioSentence)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCaseSentence_EReference0()
	 * @model
	 * @generated
	 */
	UseCaseTestScenarioSentence getEReference0();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCaseSentence#getEReference0 <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference0</em>' reference.
	 * @see #getEReference0()
	 * @generated
	 */
	void setEReference0(UseCaseTestScenarioSentence value);

	/**
	 * Returns the value of the '<em><b>Scenario Sentence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Sentence</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Sentence</em>' containment reference.
	 * @see #setScenarioSentence(ScenarioSentence)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCaseSentence_ScenarioSentence()
	 * @model containment="true"
	 * @generated
	 */
	ScenarioSentence getScenarioSentence();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCaseSentence#getScenarioSentence <em>Scenario Sentence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Sentence</em>' containment reference.
	 * @see #getScenarioSentence()
	 * @generated
	 */
	void setScenarioSentence(ScenarioSentence value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCaseSentence_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCaseSentence#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' containment reference.
	 * @see #setContext(TestDataValuesContext)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCaseSentence_Context()
	 * @model containment="true"
	 * @generated
	 */
	TestDataValuesContext getContext();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCaseSentence#getContext <em>Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' containment reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(TestDataValuesContext value);

} // TestCaseSentence
