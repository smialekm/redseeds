/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLFeature#getNotion <em>Notion</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLFeature()
 * @model abstract="true"
 * @generated
 */
public interface DLFeature extends EObject {
	/**
	 * Returns the value of the '<em><b>Notion</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link rsldl.DLNotion#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notion</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notion</em>' container reference.
	 * @see #setNotion(DLNotion)
	 * @see rsldl.RsldlPackage#getDLFeature_Notion()
	 * @see rsldl.DLNotion#getFeatures
	 * @model opposite="features" required="true" transient="false"
	 * @generated
	 */
	DLNotion getNotion();

	/**
	 * Sets the value of the '{@link rsldl.DLFeature#getNotion <em>Notion</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notion</em>' container reference.
	 * @see #getNotion()
	 * @generated
	 */
	void setNotion(DLNotion value);

} // DLFeature
