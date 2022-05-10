/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Alghoritmic Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLAlghoritmicTransition#getSequence <em>Sequence</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLAlghoritmicTransition()
 * @model
 * @generated
 */
public interface DLAlghoritmicTransition extends DLTransition {

	/**
	 * Returns the value of the '<em><b>Sequence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence</em>' containment reference.
	 * @see #setSequence(DLAlghoritmicTransitionSequenceElement)
	 * @see rsldl.RsldlPackage#getDLAlghoritmicTransition_Sequence()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DLAlghoritmicTransitionSequenceElement getSequence();

	/**
	 * Sets the value of the '{@link rsldl.DLAlghoritmicTransition#getSequence <em>Sequence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequence</em>' containment reference.
	 * @see #getSequence()
	 * @generated
	 */
	void setSequence(DLAlghoritmicTransitionSequenceElement value);
} // DLAlghoritmicTransition
