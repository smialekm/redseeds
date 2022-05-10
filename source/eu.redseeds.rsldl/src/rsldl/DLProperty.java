/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLProperty#getValueType <em>Value Type</em>}</li>
 *   <li>{@link rsldl.DLProperty#getSetType <em>Set Type</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLProperty()
 * @model
 * @generated
 */
public interface DLProperty extends DLNotion {
	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLPropertyValueType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' attribute.
	 * @see rsldl.DLPropertyValueType
	 * @see #setValueType(DLPropertyValueType)
	 * @see rsldl.RsldlPackage#getDLProperty_ValueType()
	 * @model
	 * @generated
	 */
	DLPropertyValueType getValueType();

	/**
	 * Sets the value of the '{@link rsldl.DLProperty#getValueType <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' attribute.
	 * @see rsldl.DLPropertyValueType
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(DLPropertyValueType value);

	/**
	 * Returns the value of the '<em><b>Set Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Set Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Set Type</em>' reference.
	 * @see #setSetType(DLNotion)
	 * @see rsldl.RsldlPackage#getDLProperty_SetType()
	 * @model
	 * @generated
	 */
	DLNotion getSetType();

	/**
	 * Sets the value of the '{@link rsldl.DLProperty#getSetType <em>Set Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Set Type</em>' reference.
	 * @see #getSetType()
	 * @generated
	 */
	void setSetType(DLNotion value);

} // DLProperty
