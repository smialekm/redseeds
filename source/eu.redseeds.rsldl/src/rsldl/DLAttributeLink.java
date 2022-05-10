/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Attribute Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLAttributeLink#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link rsldl.DLAttributeLink#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLAttributeLink()
 * @model
 * @generated
 */
public interface DLAttributeLink extends DLFeature, DLNamedLink {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' reference.
	 * @see #setAttribute(DLProperty)
	 * @see rsldl.RsldlPackage#getDLAttributeLink_Attribute()
	 * @model required="true"
	 * @generated
	 */
	DLProperty getAttribute();

	/**
	 * Sets the value of the '{@link rsldl.DLAttributeLink#getAttribute <em>Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' reference.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(DLProperty value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLFeatureType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLFeatureType
	 * @see #setType(DLFeatureType)
	 * @see rsldl.RsldlPackage#getDLAttributeLink_Type()
	 * @model
	 * @generated
	 */
	DLFeatureType getType();

	/**
	 * Sets the value of the '{@link rsldl.DLAttributeLink#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLFeatureType
	 * @see #getType()
	 * @generated
	 */
	void setType(DLFeatureType value);

} // DLAttributeLink
