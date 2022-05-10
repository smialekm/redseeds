/**
 */
package rsldl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Alghoritmic Transition Sequence Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLAlghoritmicTransitionSequenceElement#getSequent <em>Sequent</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLAlghoritmicTransitionSequenceElement()
 * @model abstract="true"
 * @generated
 */
public interface DLAlghoritmicTransitionSequenceElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Sequent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequent</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequent</em>' containment reference.
	 * @see #setSequent(DLAlghoritmicTransitionSequenceElement)
	 * @see rsldl.RsldlPackage#getDLAlghoritmicTransitionSequenceElement_Sequent()
	 * @model containment="true"
	 * @generated
	 */
	DLAlghoritmicTransitionSequenceElement getSequent();

	/**
	 * Sets the value of the '{@link rsldl.DLAlghoritmicTransitionSequenceElement#getSequent <em>Sequent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequent</em>' containment reference.
	 * @see #getSequent()
	 * @generated
	 */
	void setSequent(DLAlghoritmicTransitionSequenceElement value);

} // DLAlghoritmicTransitionSequenceElement
