/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link notiondiagram.AbstractRelation#getSource <em>Source</em>}</li>
 *   <li>{@link notiondiagram.AbstractRelation#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see notiondiagram.NotiondiagramPackage#getAbstractRelation()
 * @model abstract="true"
 * @generated
 */
public interface AbstractRelation extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Notion)
	 * @see notiondiagram.NotiondiagramPackage#getAbstractRelation_Source()
	 * @model required="true"
	 * @generated
	 */
	Notion getSource();

	/**
	 * Sets the value of the '{@link notiondiagram.AbstractRelation#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Notion value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Notion)
	 * @see notiondiagram.NotiondiagramPackage#getAbstractRelation_Target()
	 * @model required="true"
	 * @generated
	 */
	Notion getTarget();

	/**
	 * Sets the value of the '{@link notiondiagram.AbstractRelation#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Notion value);

} // AbstractRelation
