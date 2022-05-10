/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Domain Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLDomainElement#getDomains <em>Domains</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLDomainElement()
 * @model
 * @generated
 */
public interface DLDomainElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Domains</b></em>' reference list.
	 * The list contents are of type {@link rsldl.DLDomain}.
	 * It is bidirectional and its opposite is '{@link rsldl.DLDomain#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domains</em>' reference list.
	 * @see rsldl.RsldlPackage#getDLDomainElement_Domains()
	 * @see rsldl.DLDomain#getElements
	 * @model opposite="elements"
	 * @generated
	 */
	EList<DLDomain> getDomains();

} // DLDomainElement
