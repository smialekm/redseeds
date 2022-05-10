/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.DomainStatement#getDirectNotion <em>Direct Notion</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.DomainStatement#getIndirectNotion <em>Indirect Notion</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.DomainStatement#getPhraseName <em>Phrase Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.DomainStatement#getPhraseDescription <em>Phrase Description</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.DomainStatement#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatement()
 * @model
 * @generated
 */
public interface DomainStatement extends Test {
	/**
	 * Returns the value of the '<em><b>Direct Notion</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direct Notion</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direct Notion</em>' reference.
	 * @see #setDirectNotion(Notion)
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatement_DirectNotion()
	 * @model
	 * @generated
	 */
	Notion getDirectNotion();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.DomainStatement#getDirectNotion <em>Direct Notion</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direct Notion</em>' reference.
	 * @see #getDirectNotion()
	 * @generated
	 */
	void setDirectNotion(Notion value);

	/**
	 * Returns the value of the '<em><b>Indirect Notion</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indirect Notion</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indirect Notion</em>' reference.
	 * @see #setIndirectNotion(Notion)
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatement_IndirectNotion()
	 * @model
	 * @generated
	 */
	Notion getIndirectNotion();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.DomainStatement#getIndirectNotion <em>Indirect Notion</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indirect Notion</em>' reference.
	 * @see #getIndirectNotion()
	 * @generated
	 */
	void setIndirectNotion(Notion value);

	/**
	 * Returns the value of the '<em><b>Phrase Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phrase Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Phrase Name</em>' attribute.
	 * @see #setPhraseName(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatement_PhraseName()
	 * @model
	 * @generated
	 */
	String getPhraseName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.DomainStatement#getPhraseName <em>Phrase Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phrase Name</em>' attribute.
	 * @see #getPhraseName()
	 * @generated
	 */
	void setPhraseName(String value);

	/**
	 * Returns the value of the '<em><b>Phrase Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phrase Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Phrase Description</em>' attribute.
	 * @see #setPhraseDescription(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatement_PhraseDescription()
	 * @model
	 * @generated
	 */
	String getPhraseDescription();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.DomainStatement#getPhraseDescription <em>Phrase Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phrase Description</em>' attribute.
	 * @see #getPhraseDescription()
	 * @generated
	 */
	void setPhraseDescription(String value);

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
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatement_Uid()
	 * @model
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.DomainStatement#getUid <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uid</em>' attribute.
	 * @see #getUid()
	 * @generated
	 */
	void setUid(String value);

} // DomainStatement
