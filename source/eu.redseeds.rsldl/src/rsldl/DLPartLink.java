/**
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Part Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLPartLink#getPartDereference <em>Part Dereference</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLPartLink()
 * @model
 * @generated
 */
public interface DLPartLink extends DLFeature {
	/**
	 * Returns the value of the '<em><b>Part Dereference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Part Dereference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Part Dereference</em>' reference.
	 * @see #setPartDereference(DLDereferenceLink)
	 * @see rsldl.RsldlPackage#getDLPartLink_PartDereference()
	 * @model required="true"
	 * @generated
	 */
	DLDereferenceLink getPartDereference();

	/**
	 * Sets the value of the '{@link rsldl.DLPartLink#getPartDereference <em>Part Dereference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Part Dereference</em>' reference.
	 * @see #getPartDereference()
	 * @generated
	 */
	void setPartDereference(DLDereferenceLink value);

} // DLPartLink
