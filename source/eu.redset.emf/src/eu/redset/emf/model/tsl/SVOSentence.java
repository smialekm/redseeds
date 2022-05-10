/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SVO Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.SVOSentence#getDirectObject <em>Direct Object</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.SVOSentence#getIndirectObject <em>Indirect Object</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.SVOSentence#getNumber <em>Number</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.SVOSentence#getPredicate <em>Predicate</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getSVOSentence()
 * @model
 * @generated
 */
public interface SVOSentence extends ScenarioSentence {
	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(int)
	 * @see eu.redset.emf.model.tsl.TslPackage#getSVOSentence_Number()
	 * @model
	 * @generated
	 */
	int getNumber();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.SVOSentence#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(int value);

	/**
	 * Returns the value of the '<em><b>Predicate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predicate</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predicate</em>' reference.
	 * @see #setPredicate(DomainStatement)
	 * @see eu.redset.emf.model.tsl.TslPackage#getSVOSentence_Predicate()
	 * @model
	 * @generated
	 */
	DomainStatement getPredicate();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.SVOSentence#getPredicate <em>Predicate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicate</em>' reference.
	 * @see #getPredicate()
	 * @generated
	 */
	void setPredicate(DomainStatement value);

	/**
	 * Returns the value of the '<em><b>Direct Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direct Object</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direct Object</em>' containment reference.
	 * @see #setDirectObject(DomainObject)
	 * @see eu.redset.emf.model.tsl.TslPackage#getSVOSentence_DirectObject()
	 * @model containment="true"
	 * @generated
	 */
	DomainObject getDirectObject();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.SVOSentence#getDirectObject <em>Direct Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direct Object</em>' containment reference.
	 * @see #getDirectObject()
	 * @generated
	 */
	void setDirectObject(DomainObject value);

	/**
	 * Returns the value of the '<em><b>Indirect Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indirect Object</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indirect Object</em>' containment reference.
	 * @see #setIndirectObject(DomainObject)
	 * @see eu.redset.emf.model.tsl.TslPackage#getSVOSentence_IndirectObject()
	 * @model containment="true"
	 * @generated
	 */
	DomainObject getIndirectObject();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.SVOSentence#getIndirectObject <em>Indirect Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indirect Object</em>' containment reference.
	 * @see #getIndirectObject()
	 * @generated
	 */
	void setIndirectObject(DomainObject value);

} // SVOSentence
