/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NF Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.NFTest#getNfrTrail <em>Nfr Trail</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.NFTest#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getNFTest()
 * @model
 * @generated
 */
public interface NFTest extends Test {

	/**
	 * Returns the value of the '<em><b>Nfr Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nfr Trail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nfr Trail</em>' attribute.
	 * @see #setNfrTrail(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getNFTest_NfrTrail()
	 * @model
	 * @generated
	 */
	String getNfrTrail();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.NFTest#getNfrTrail <em>Nfr Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nfr Trail</em>' attribute.
	 * @see #getNfrTrail()
	 * @generated
	 */
	void setNfrTrail(String value);

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
	 * @see eu.redset.emf.model.tsl.TslPackage#getNFTest_Uid()
	 * @model
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.NFTest#getUid <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uid</em>' attribute.
	 * @see #getUid()
	 * @generated
	 */
	void setUid(String value);
} // NFTest
