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
 * A representation of the model object '<em><b>DL Subset</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see rsldl.RsldlPackage#getDLSubset()
 * @model abstract="true"
 * @generated
 */
public interface DLSubset extends EObject {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLNotion> getDomainNotions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLRelationship> getDomainRelationships();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DLNotion getNotion(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model many="false" namesMany="false"
	 * @generated
	 */
	EList<DLNotion> getNotions(EList<String> names);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DLRelationship getRelationshipByAbbreviation(String abbreviation);
} // DLSubset
