/**
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Control Flow Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLControlFlowStatement#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLControlFlowStatement()
 * @model
 * @generated
 */
public interface DLControlFlowStatement extends DLAlghoritmicTransitionSequenceElement, DLReferencingElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLControlFlowStatementType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLControlFlowStatementType
	 * @see #setType(DLControlFlowStatementType)
	 * @see rsldl.RsldlPackage#getDLControlFlowStatement_Type()
	 * @model
	 * @generated
	 */
	DLControlFlowStatementType getType();

	/**
	 * Sets the value of the '{@link rsldl.DLControlFlowStatement#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLControlFlowStatementType
	 * @see #getType()
	 * @generated
	 */
	void setType(DLControlFlowStatementType value);

} // DLControlFlowStatement
