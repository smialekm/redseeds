/**
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Dereference Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLDereferenceLink#getElementType <em>Element Type</em>}</li>
 *   <li>{@link rsldl.DLDereferenceLink#getPattern <em>Pattern</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLDereferenceLink()
 * @model
 * @generated
 */
public interface DLDereferenceLink extends DLNamedLink {
	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Type</em>' reference.
	 * @see #setElementType(DLNotion)
	 * @see rsldl.RsldlPackage#getDLDereferenceLink_ElementType()
	 * @model required="true"
	 * @generated
	 */
	DLNotion getElementType();

	/**
	 * Sets the value of the '{@link rsldl.DLDereferenceLink#getElementType <em>Element Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Type</em>' reference.
	 * @see #getElementType()
	 * @generated
	 */
	void setElementType(DLNotion value);

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
	 * @see rsldl.RsldlPackage#getDLDereferenceLink_Pattern()
	 * @model
	 * @generated
	 */
	String getPattern();

	/**
	 * Sets the value of the '{@link rsldl.DLDereferenceLink#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

} // DLDereferenceLink
