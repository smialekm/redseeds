/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package usecasediagram;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import de.uni_koblenz.jgralab.GraphElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Use Case Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link usecasediagram.UseCaseDiagram#getActors <em>Actors</em>}</li>
 *   <li>{@link usecasediagram.UseCaseDiagram#getAssociations <em>Associations</em>}</li>
 *   <li>{@link usecasediagram.UseCaseDiagram#getUsecases <em>Usecases</em>}</li>
 *   <li>{@link usecasediagram.UseCaseDiagram#getInvokes <em>Invokes</em>}</li>
 *   <li>{@link usecasediagram.UseCaseDiagram#getName <em>Name</em>}</li>
 *   <li>{@link usecasediagram.UseCaseDiagram#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see usecasediagram.UsecasediagramPackage#getUseCaseDiagram()
 * @model
 * @generated
 */
public interface UseCaseDiagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Actors</b></em>' containment reference list.
	 * The list contents are of type {@link usecasediagram.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actors</em>' containment reference list.
	 * @see usecasediagram.UsecasediagramPackage#getUseCaseDiagram_Actors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Actor> getActors();

	/**
	 * Returns the value of the '<em><b>Associations</b></em>' containment reference list.
	 * The list contents are of type {@link usecasediagram.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associations</em>' containment reference list.
	 * @see usecasediagram.UsecasediagramPackage#getUseCaseDiagram_Associations()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	EList<Association> getAssociations();

	/**
	 * Returns the value of the '<em><b>Usecases</b></em>' containment reference list.
	 * The list contents are of type {@link usecasediagram.UseCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usecases</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usecases</em>' containment reference list.
	 * @see usecasediagram.UsecasediagramPackage#getUseCaseDiagram_Usecases()
	 * @model containment="true"
	 * @generated
	 */
	EList<UseCase> getUsecases();

	/**
	 * Returns the value of the '<em><b>Invokes</b></em>' containment reference list.
	 * The list contents are of type {@link usecasediagram.Invoke}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invokes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invokes</em>' containment reference list.
	 * @see usecasediagram.UsecasediagramPackage#getUseCaseDiagram_Invokes()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	EList<Invoke> getInvokes();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see usecasediagram.UsecasediagramPackage#getUseCaseDiagram_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link usecasediagram.UseCaseDiagram#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' attribute.
	 * @see #setPackage(int)
	 * @see usecasediagram.UsecasediagramPackage#getUseCaseDiagram_Package()
	 * @model
	 * @generated
	 */
	int getPackage();

	/**
	 * Sets the value of the '{@link usecasediagram.UseCaseDiagram#getPackage <em>Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' attribute.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(int value);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public List<GraphElement> getGraphElements();

} // UseCaseDiagram
