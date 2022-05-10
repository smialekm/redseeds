/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.TestScenario#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestScenario#getEReference1 <em>EReference1</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getTestScenario()
 * @model
 * @generated
 */
public interface TestScenario extends Test {
	/**
	 * Returns the value of the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference0</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference0</em>' reference.
	 * @see #setEReference0(TestPackage)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestScenario_EReference0()
	 * @model
	 * @generated
	 */
	TestPackage getEReference0();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestScenario#getEReference0 <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference0</em>' reference.
	 * @see #getEReference0()
	 * @generated
	 */
	void setEReference0(TestPackage value);

	/**
	 * Returns the value of the '<em><b>EReference1</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference1</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestScenario_EReference1()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestCase> getEReference1();

} // TestScenario
