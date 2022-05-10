/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Invocation Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getInvocationTarget <em>Invocation Target</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getUid <em>Uid</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getIncludeType <em>Include Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getTestInvocationRelationship()
 * @model
 * @generated
 */
public interface TestInvocationRelationship extends TestRelationship {
	/**
	 * Returns the value of the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference0</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference0</em>' reference.
	 * @see #setEReference0(TestRelationship)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestInvocationRelationship_EReference0()
	 * @model
	 * @generated
	 */
	TestRelationship getEReference0();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getEReference0 <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference0</em>' reference.
	 * @see #getEReference0()
	 * @generated
	 */
	void setEReference0(TestRelationship value);

	/**
	 * Returns the value of the '<em><b>Invocation Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invocation Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invocation Target</em>' reference.
	 * @see #setInvocationTarget(UseCaseTest)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestInvocationRelationship_InvocationTarget()
	 * @model
	 * @generated
	 */
	UseCaseTest getInvocationTarget();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getInvocationTarget <em>Invocation Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invocation Target</em>' reference.
	 * @see #getInvocationTarget()
	 * @generated
	 */
	void setInvocationTarget(UseCaseTest value);

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
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestInvocationRelationship_Uid()
	 * @model
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getUid <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uid</em>' attribute.
	 * @see #getUid()
	 * @generated
	 */
	void setUid(String value);

	/**
	 * Returns the value of the '<em><b>Include Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include Type</em>' attribute.
	 * @see #setIncludeType(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestInvocationRelationship_IncludeType()
	 * @model
	 * @generated
	 */
	String getIncludeType();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getIncludeType <em>Include Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Include Type</em>' attribute.
	 * @see #getIncludeType()
	 * @generated
	 */
	void setIncludeType(String value);

} // TestInvocationRelationship
