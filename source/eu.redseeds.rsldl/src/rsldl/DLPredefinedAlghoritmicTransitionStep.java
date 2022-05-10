/**
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Predefined Alghoritmic Transition Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLPredefinedAlghoritmicTransitionStep#getType <em>Type</em>}</li>
 *   <li>{@link rsldl.DLPredefinedAlghoritmicTransitionStep#getPattern <em>Pattern</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLPredefinedAlghoritmicTransitionStep()
 * @model
 * @generated
 */
public interface DLPredefinedAlghoritmicTransitionStep extends DLAlghoritmicTransitionStep {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLPredefinedStepType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLPredefinedStepType
	 * @see #setType(DLPredefinedStepType)
	 * @see rsldl.RsldlPackage#getDLPredefinedAlghoritmicTransitionStep_Type()
	 * @model
	 * @generated
	 */
	DLPredefinedStepType getType();

	/**
	 * Sets the value of the '{@link rsldl.DLPredefinedAlghoritmicTransitionStep#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLPredefinedStepType
	 * @see #getType()
	 * @generated
	 */
	void setType(DLPredefinedStepType value);

	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(String)
	 * @see rsldl.RsldlPackage#getDLPredefinedAlghoritmicTransitionStep_Pattern()
	 * @model
	 * @generated
	 */
	String getPattern();

	/**
	 * Sets the value of the '{@link rsldl.DLPredefinedAlghoritmicTransitionStep#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

} // DLPredefinedAlghoritmicTransitionStep
