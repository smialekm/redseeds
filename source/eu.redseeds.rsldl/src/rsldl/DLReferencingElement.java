/**
 */
package rsldl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Referencing Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLReferencingElement#getSubjectLink <em>Subject Link</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLReferencingElement()
 * @model abstract="true"
 * @generated
 */
public interface DLReferencingElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Subject Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject Link</em>' reference.
	 * @see #setSubjectLink(DLNamedLink)
	 * @see rsldl.RsldlPackage#getDLReferencingElement_SubjectLink()
	 * @model
	 * @generated
	 */
	DLNamedLink getSubjectLink();

	/**
	 * Sets the value of the '{@link rsldl.DLReferencingElement#getSubjectLink <em>Subject Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject Link</em>' reference.
	 * @see #getSubjectLink()
	 * @generated
	 */
	void setSubjectLink(DLNamedLink value);

} // DLReferencingElement
