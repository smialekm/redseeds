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
 * A representation of the model object '<em><b>DL Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLRepository#getDomains <em>Domains</em>}</li>
 *   <li>{@link rsldl.DLRepository#getDiagrams <em>Diagrams</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLRepository()
 * @model
 * @generated
 */
public interface DLRepository extends DLSubset, DLDiagram {
	/**
	 * Returns the value of the '<em><b>Domains</b></em>' containment reference list.
	 * The list contents are of type {@link rsldl.DLDomain}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domains</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domains</em>' containment reference list.
	 * @see rsldl.RsldlPackage#getDLRepository_Domains()
	 * @model containment="true"
	 * @generated
	 */
	EList<DLDomain> getDomains();

	/**
	 * Returns the value of the '<em><b>Diagrams</b></em>' containment reference list.
	 * The list contents are of type {@link rsldl.DLDiagram}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagrams</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagrams</em>' containment reference list.
	 * @see rsldl.RsldlPackage#getDLRepository_Diagrams()
	 * @model containment="true"
	 * @generated
	 */
	EList<DLDiagram> getDiagrams();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DLDomain getDomain(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DLDiagram getDiagram(String name);

} // DLRepository
