/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Notion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.Notion#getDomainStatements <em>Domain Statements</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.Notion#getNotionName <em>Notion Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.Notion#getNotionDescription <em>Notion Description</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.Notion#getNotionTrail <em>Notion Trail</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.Notion#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.Notion#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getNotion()
 * @model
 * @generated
 */
public interface Notion extends Test {
	/**
	 * Returns the value of the '<em><b>Domain Statements</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.DomainStatement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Statements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Statements</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getNotion_DomainStatements()
	 * @model containment="true"
	 * @generated
	 */
	EList<DomainStatement> getDomainStatements();

	/**
	 * Returns the value of the '<em><b>Notion Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notion Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notion Name</em>' attribute.
	 * @see #setNotionName(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getNotion_NotionName()
	 * @model
	 * @generated
	 */
	String getNotionName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.Notion#getNotionName <em>Notion Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notion Name</em>' attribute.
	 * @see #getNotionName()
	 * @generated
	 */
	void setNotionName(String value);

	/**
	 * Returns the value of the '<em><b>Notion Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notion Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notion Description</em>' attribute.
	 * @see #setNotionDescription(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getNotion_NotionDescription()
	 * @model
	 * @generated
	 */
	String getNotionDescription();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.Notion#getNotionDescription <em>Notion Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notion Description</em>' attribute.
	 * @see #getNotionDescription()
	 * @generated
	 */
	void setNotionDescription(String value);

	/**
	 * Returns the value of the '<em><b>Notion Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notion Trail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notion Trail</em>' attribute.
	 * @see #setNotionTrail(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getNotion_NotionTrail()
	 * @model
	 * @generated
	 */
	String getNotionTrail();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.Notion#getNotionTrail <em>Notion Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notion Trail</em>' attribute.
	 * @see #getNotionTrail()
	 * @generated
	 */
	void setNotionTrail(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.NotionAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getNotion_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<NotionAttribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uid</em>' attribute.
	 * @see #setUid(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getNotion_Uid()
	 * @model
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.Notion#getUid <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uid</em>' attribute.
	 * @see #getUid()
	 * @generated
	 */
	void setUid(String value);

} // Notion
