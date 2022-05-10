/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.Context#getTestValue <em>Test Value</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.Context#getParentTestScenario <em>Parent Test Scenario</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getContext()
 * @model
 * @generated
 */
public interface Context extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Value</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Value</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Value</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getContext_TestValue()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestValue> getTestValue();

	/**
	 * Returns the value of the '<em><b>Parent Test Scenario</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Test Scenario</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Test Scenario</em>' reference.
	 * @see #setParentTestScenario(TestScenario)
	 * @see eu.redset.emf.model.tsl.TslPackage#getContext_ParentTestScenario()
	 * @model
	 * @generated
	 */
	TestScenario getParentTestScenario();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.Context#getParentTestScenario <em>Parent Test Scenario</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Test Scenario</em>' reference.
	 * @see #getParentTestScenario()
	 * @generated
	 */
	void setParentTestScenario(TestScenario value);

} // Context
