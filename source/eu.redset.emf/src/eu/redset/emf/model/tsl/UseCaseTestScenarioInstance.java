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
 * A representation of the model object '<em><b>Use Case Test Scenario Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getEReference3 <em>EReference3</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getUcName <em>Uc Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getUcTrail <em>Uc Trail</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioInstance()
 * @model
 * @generated
 */
public interface UseCaseTestScenarioInstance extends UseCaseTestScenario {
	/**
	 * Returns the value of the '<em><b>EReference3</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestCaseSentence}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference3</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference3</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioInstance_EReference3()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestCaseSentence> getEReference3();

	/**
	 * Returns the value of the '<em><b>Uc Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uc Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uc Name</em>' attribute.
	 * @see #setUcName(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioInstance_UcName()
	 * @model
	 * @generated
	 */
	String getUcName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getUcName <em>Uc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uc Name</em>' attribute.
	 * @see #getUcName()
	 * @generated
	 */
	void setUcName(String value);

	/**
	 * Returns the value of the '<em><b>Uc Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uc Trail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uc Trail</em>' attribute.
	 * @see #setUcTrail(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenarioInstance_UcTrail()
	 * @model
	 * @generated
	 */
	String getUcTrail();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getUcTrail <em>Uc Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uc Trail</em>' attribute.
	 * @see #getUcTrail()
	 * @generated
	 */
	void setUcTrail(String value);

} // UseCaseTestScenarioInstance
