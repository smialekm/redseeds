/**
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Custom Alghoritmic Transition Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLCustomAlghoritmicTransitionStep#getAlternative <em>Alternative</em>}</li>
 *   <li>{@link rsldl.DLCustomAlghoritmicTransitionStep#getAction <em>Action</em>}</li>
 *   <li>{@link rsldl.DLCustomAlghoritmicTransitionStep#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLCustomAlghoritmicTransitionStep()
 * @model
 * @generated
 */
public interface DLCustomAlghoritmicTransitionStep extends DLAlghoritmicTransitionStep {
	/**
	 * Returns the value of the '<em><b>Alternative</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alternative</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alternative</em>' containment reference.
	 * @see #setAlternative(DLAlghoritmicTransitionSequenceElement)
	 * @see rsldl.RsldlPackage#getDLCustomAlghoritmicTransitionStep_Alternative()
	 * @model containment="true"
	 * @generated
	 */
	DLAlghoritmicTransitionSequenceElement getAlternative();

	/**
	 * Sets the value of the '{@link rsldl.DLCustomAlghoritmicTransitionStep#getAlternative <em>Alternative</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alternative</em>' containment reference.
	 * @see #getAlternative()
	 * @generated
	 */
	void setAlternative(DLAlghoritmicTransitionSequenceElement value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(DLTransitionPattern)
	 * @see rsldl.RsldlPackage#getDLCustomAlghoritmicTransitionStep_Action()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DLTransitionPattern getAction();

	/**
	 * Sets the value of the '{@link rsldl.DLCustomAlghoritmicTransitionStep#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(DLTransitionPattern value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(DLConditionPattern)
	 * @see rsldl.RsldlPackage#getDLCustomAlghoritmicTransitionStep_Condition()
	 * @model containment="true"
	 * @generated
	 */
	DLConditionPattern getCondition();

	/**
	 * Sets the value of the '{@link rsldl.DLCustomAlghoritmicTransitionStep#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(DLConditionPattern value);

} // DLCustomAlghoritmicTransitionStep
