/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Domain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLDomain#getName <em>Name</em>}</li>
 *   <li>{@link rsldl.DLDomain#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLDomain()
 * @model
 * @generated
 */
public interface DLDomain extends DLSubset {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see rsldl.RsldlPackage#getDLDomain_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link rsldl.DLDomain#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link rsldl.DLDomainElement}.
	 * It is bidirectional and its opposite is '{@link rsldl.DLDomainElement#getDomains <em>Domains</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see rsldl.RsldlPackage#getDLDomain_Elements()
	 * @see rsldl.DLDomainElement#getDomains
	 * @model opposite="domains"
	 * @generated
	 */
	EList<DLDomainElement> getElements();

} // DLDomain
