/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Control Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.ControlSentence#getInvocation <em>Invocation</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getControlSentence()
 * @model
 * @generated
 */
public interface ControlSentence extends ScenarioSentence {
	/**
	 * Returns the value of the '<em><b>Invocation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invocation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invocation</em>' reference.
	 * @see #setInvocation(TestInvocationRelationship)
	 * @see eu.redset.emf.model.tsl.TslPackage#getControlSentence_Invocation()
	 * @model
	 * @generated
	 */
	TestInvocationRelationship getInvocation();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.ControlSentence#getInvocation <em>Invocation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invocation</em>' reference.
	 * @see #getInvocation()
	 * @generated
	 */
	void setInvocation(TestInvocationRelationship value);

} // ControlSentence
