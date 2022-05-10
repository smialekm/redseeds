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
 * A representation of the model object '<em><b>Use Case Test Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getSentences <em>Sentences</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getActor <em>Actor</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getPostcondition <em>Postcondition</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenario()
 * @model
 * @generated
 */
public interface UseCaseTestScenario extends Test {
	/**
	 * Returns the value of the '<em><b>Sentences</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sentences</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sentences</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenario_Sentences()
	 * @model containment="true"
	 * @generated
	 */
	EList<UseCaseTestScenarioSentence> getSentences();

	/**
	 * Returns the value of the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precondition</em>' containment reference.
	 * @see #setPrecondition(Condition)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenario_Precondition()
	 * @model containment="true"
	 * @generated
	 */
	Condition getPrecondition();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getPrecondition <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precondition</em>' containment reference.
	 * @see #getPrecondition()
	 * @generated
	 */
	void setPrecondition(Condition value);

	/**
	 * Returns the value of the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Postcondition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postcondition</em>' containment reference.
	 * @see #setPostcondition(Condition)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenario_Postcondition()
	 * @model containment="true"
	 * @generated
	 */
	Condition getPostcondition();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getPostcondition <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Postcondition</em>' containment reference.
	 * @see #getPostcondition()
	 * @generated
	 */
	void setPostcondition(Condition value);

	/**
	 * Returns the value of the '<em><b>Actor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actor</em>' attribute.
	 * @see #setActor(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTestScenario_Actor()
	 * @model
	 * @generated
	 */
	String getActor();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getActor <em>Actor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actor</em>' attribute.
	 * @see #getActor()
	 * @generated
	 */
	void setActor(String value);

} // UseCaseTestScenario
