/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import rsldl.DLDiagram;
import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLDiagramImpl#getRelationshipParticipants <em>Relationship Participants</em>}</li>
 *   <li>{@link rsldl.impl.DLDiagramImpl#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link rsldl.impl.DLDiagramImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLDiagramImpl extends EObjectImpl implements DLDiagram {
	/**
	 * The cached value of the '{@link #getRelationshipParticipants() <em>Relationship Participants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationshipParticipants()
	 * @generated
	 * @ordered
	 */
	protected EList<DLRelationshipParticipant> relationshipParticipants;

	/**
	 * The cached value of the '{@link #getRelationships() <em>Relationships</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationships()
	 * @generated
	 * @ordered
	 */
	protected EList<DLRelationship> relationships;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLDiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLRelationshipParticipant> getRelationshipParticipants() {
		if (relationshipParticipants == null) {
			relationshipParticipants = new EObjectContainmentEList<DLRelationshipParticipant>(DLRelationshipParticipant.class, this, RsldlPackage.DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS);
		}
		return relationshipParticipants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLRelationship> getRelationships() {
		if (relationships == null) {
			relationships = new EObjectContainmentEList<DLRelationship>(DLRelationship.class, this, RsldlPackage.DL_DIAGRAM__RELATIONSHIPS);
		}
		return relationships;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_DIAGRAM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS:
				return ((InternalEList<?>)getRelationshipParticipants()).basicRemove(otherEnd, msgs);
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIPS:
				return ((InternalEList<?>)getRelationships()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS:
				return getRelationshipParticipants();
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIPS:
				return getRelationships();
			case RsldlPackage.DL_DIAGRAM__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS:
				getRelationshipParticipants().clear();
				getRelationshipParticipants().addAll((Collection<? extends DLRelationshipParticipant>)newValue);
				return;
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIPS:
				getRelationships().clear();
				getRelationships().addAll((Collection<? extends DLRelationship>)newValue);
				return;
			case RsldlPackage.DL_DIAGRAM__NAME:
				setName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS:
				getRelationshipParticipants().clear();
				return;
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIPS:
				getRelationships().clear();
				return;
			case RsldlPackage.DL_DIAGRAM__NAME:
				setName(NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS:
				return relationshipParticipants != null && !relationshipParticipants.isEmpty();
			case RsldlPackage.DL_DIAGRAM__RELATIONSHIPS:
				return relationships != null && !relationships.isEmpty();
			case RsldlPackage.DL_DIAGRAM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //DLDiagramImpl
