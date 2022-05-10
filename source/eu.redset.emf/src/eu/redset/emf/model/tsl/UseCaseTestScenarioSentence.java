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
 * A representation of the model object '<em><b>Use Case Test Scenario Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getEReference1 <em>EReference1</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getScenarioSentence <em>Scenario Sentence</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioSentence()
 * @model
 * @generated
 */
public interface UseCaseTestScenarioSentence extends EObject {
	/**
	 * Returns the value of the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference0</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference0</em>' reference.
	 * @see #setEReference0(UseCaseTestScenario)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioSentence_EReference0()
	 * @model
	 * @generated
	 */
	UseCaseTestScenario getEReference0();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getEReference0 <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference0</em>' reference.
	 * @see #getEReference0()
	 * @generated
	 */
	void setEReference0(UseCaseTestScenario value);

	/**
	 * Returns the value of the '<em><b>EReference1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference1</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference1</em>' reference.
	 * @see #setEReference1(UseCaseTestScenario)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioSentence_EReference1()
	 * @model
	 * @generated
	 */
	UseCaseTestScenario getEReference1();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getEReference1 <em>EReference1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference1</em>' reference.
	 * @see #getEReference1()
	 * @generated
	 */
	void setEReference1(UseCaseTestScenario value);

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
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioSentence_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Scenario Sentence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Sentence</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Sentence</em>' containment reference.
	 * @see #setScenarioSentence(ScenarioSentence)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioSentence_ScenarioSentence()
	 * @model containment="true"
	 * @generated
	 */
	ScenarioSentence getScenarioSentence();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getScenarioSentence <em>Scenario Sentence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Sentence</em>' containment reference.
	 * @see #getScenarioSentence()
	 * @generated
	 */
	void setScenarioSentence(ScenarioSentence value);

} // UseCaseTestScenarioSentence
