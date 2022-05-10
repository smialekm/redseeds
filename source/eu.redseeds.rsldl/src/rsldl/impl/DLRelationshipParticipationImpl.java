/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.impl;

import static eu.redseeds.rsldl.util.Util.toLowerCamelCase;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipation;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.DLRelationshipParticipationMultiplicity;
import rsldl.DLRelationshipParticipationType;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Relationship Participation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLRelationshipParticipationImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link rsldl.impl.DLRelationshipParticipationImpl#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link rsldl.impl.DLRelationshipParticipationImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link rsldl.impl.DLRelationshipParticipationImpl#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link rsldl.impl.DLRelationshipParticipationImpl#getParentParticipation <em>Parent Participation</em>}</li>
 *   <li>{@link rsldl.impl.DLRelationshipParticipationImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLRelationshipParticipationImpl extends DLNamedLinkImpl implements DLRelationshipParticipation {
	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final DLRelationshipParticipationDirection DIRECTION_EDEFAULT = DLRelationshipParticipationDirection.UNDEFINED;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected DLRelationshipParticipationDirection direction = DIRECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final DLRelationshipParticipationMultiplicity MULTIPLICITY_EDEFAULT = DLRelationshipParticipationMultiplicity.SINGLE;

	/**
	 * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected DLRelationshipParticipationMultiplicity multiplicity = MULTIPLICITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParticipant() <em>Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipant()
	 * @generated
	 * @ordered
	 */
	protected DLRelationshipParticipant participant;

	/**
	 * The cached value of the '{@link #getParentParticipation() <em>Parent Participation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentParticipation()
	 * @generated
	 * @ordered
	 */
	protected DLRelationshipParticipation parentParticipation;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final DLRelationshipParticipationType TYPE_EDEFAULT = DLRelationshipParticipationType.STANDARD;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected DLRelationshipParticipationType type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLRelationshipParticipationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_RELATIONSHIP_PARTICIPATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipationDirection getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirection(DLRelationshipParticipationDirection newDirection) {
		DLRelationshipParticipationDirection oldDirection = direction;
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipationMultiplicity getMultiplicity() {
		return multiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiplicity(DLRelationshipParticipationMultiplicity newMultiplicity) {
		DLRelationshipParticipationMultiplicity oldMultiplicity = multiplicity;
		multiplicity = newMultiplicity == null ? MULTIPLICITY_EDEFAULT : newMultiplicity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__MULTIPLICITY, oldMultiplicity, multiplicity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipant getParticipant() {
		if (participant != null && participant.eIsProxy()) {
			InternalEObject oldParticipant = (InternalEObject)participant;
			participant = (DLRelationshipParticipant)eResolveProxy(oldParticipant);
			if (participant != oldParticipant) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT, oldParticipant, participant));
			}
		}
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipant basicGetParticipant() {
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setParticipant(DLRelationshipParticipant newParticipant) {
		DLRelationshipParticipant oldParticipant = participant;
		participant = newParticipant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT, oldParticipant, participant));
		if(null==name || name.isEmpty()) {
			String oldName = name;
			name = toLowerCamelCase(newParticipant.getName());
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__NAME, oldName, name));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationship getRelationship() {
		if (eContainerFeatureID() != RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP) return null;
		return (DLRelationship)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelationship(DLRelationship newRelationship, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRelationship, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelationship(DLRelationship newRelationship) {
		if (newRelationship != eInternalContainer() || (eContainerFeatureID() != RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP && newRelationship != null)) {
			if (EcoreUtil.isAncestor(this, newRelationship))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRelationship != null)
				msgs = ((InternalEObject)newRelationship).eInverseAdd(this, RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS, DLRelationship.class, msgs);
			msgs = basicSetRelationship(newRelationship, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP, newRelationship, newRelationship));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipation getParentParticipation() {
		if (parentParticipation != null && parentParticipation.eIsProxy()) {
			InternalEObject oldParentParticipation = (InternalEObject)parentParticipation;
			parentParticipation = (DLRelationshipParticipation)eResolveProxy(oldParentParticipation);
			if (parentParticipation != oldParentParticipation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION, oldParentParticipation, parentParticipation));
			}
		}
		return parentParticipation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipation basicGetParentParticipation() {
		return parentParticipation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentParticipation(DLRelationshipParticipation newParentParticipation) {
		DLRelationshipParticipation oldParentParticipation = parentParticipation;
		parentParticipation = newParentParticipation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION, oldParentParticipation, parentParticipation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipationType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(DLRelationshipParticipationType newType) {
		DLRelationshipParticipationType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRelationship((DLRelationship)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP:
				return basicSetRelationship(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP:
				return eInternalContainer().eInverseRemove(this, RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS, DLRelationship.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__DIRECTION:
				return getDirection();
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__MULTIPLICITY:
				return getMultiplicity();
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT:
				if (resolve) return getParticipant();
				return basicGetParticipant();
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP:
				return getRelationship();
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION:
				if (resolve) return getParentParticipation();
				return basicGetParentParticipation();
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__TYPE:
				return getType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__DIRECTION:
				setDirection((DLRelationshipParticipationDirection)newValue);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__MULTIPLICITY:
				setMultiplicity((DLRelationshipParticipationMultiplicity)newValue);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT:
				setParticipant((DLRelationshipParticipant)newValue);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP:
				setRelationship((DLRelationship)newValue);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION:
				setParentParticipation((DLRelationshipParticipation)newValue);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__TYPE:
				setType((DLRelationshipParticipationType)newValue);
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
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__MULTIPLICITY:
				setMultiplicity(MULTIPLICITY_EDEFAULT);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT:
				setParticipant((DLRelationshipParticipant)null);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP:
				setRelationship((DLRelationship)null);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION:
				setParentParticipation((DLRelationshipParticipation)null);
				return;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__TYPE:
				setType(TYPE_EDEFAULT);
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
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__MULTIPLICITY:
				return multiplicity != MULTIPLICITY_EDEFAULT;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT:
				return participant != null;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP:
				return getRelationship() != null;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION:
				return parentParticipation != null;
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__TYPE:
				return type != TYPE_EDEFAULT;
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
		result.append(" (direction: ");
		result.append(direction);
		result.append(", multiplicity: ");
		result.append(multiplicity);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public DLRelationshipParticipant getElement() {
		return getParticipant();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText() {
		return name;
	}

} //DLRelationshipParticipationImpl
