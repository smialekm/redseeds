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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import rsldl.DLAlghoritmicTransition;
import rsldl.DLDataBasedReference;
import rsldl.DLPatternBasedReference;
import rsldl.DLPatternBasedTransition;
import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipation;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLRelationshipImpl#getParticipations <em>Participations</em>}</li>
 *   <li>{@link rsldl.impl.DLRelationshipImpl#getName <em>Name</em>}</li>
 *   <li>{@link rsldl.impl.DLRelationshipImpl#getType <em>Type</em>}</li>
 *   <li>{@link rsldl.impl.DLRelationshipImpl#getAbbreviation <em>Abbreviation</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DLRelationshipImpl extends DLDomainElementImpl implements DLRelationship {
	/**
	 * The cached value of the '{@link #getParticipations() <em>Participations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipations()
	 * @generated
	 * @ordered
	 */
	protected EList<DLRelationshipParticipation> participations;

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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getAbbreviation() <em>Abbreviation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbbreviation()
	 * @generated
	 * @ordered
	 */
	protected static final String ABBREVIATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAbbreviation() <em>Abbreviation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbbreviation()
	 * @generated
	 * @ordered
	 */
	protected String abbreviation = ABBREVIATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLRelationshipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_RELATIONSHIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLRelationshipParticipation> getParticipations() {
		if (participations == null) {
			participations = new EObjectContainmentWithInverseEList<DLRelationshipParticipation>(DLRelationshipParticipation.class, this, RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS, RsldlPackage.DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP);
		}
		return participations;
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
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getType() {
		if (this instanceof DLDataBasedReference) return "Data Based Reference";
		else if (this instanceof DLPatternBasedReference) return "Pattern Based Reference";
		else if (this instanceof DLPatternBasedTransition) return "Pattern Based Transition";
		else if (this instanceof DLAlghoritmicTransition) return "Alghoritmic Transition";
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbbreviation(String newAbbreviation) {
		String oldAbbreviation = abbreviation;
		abbreviation = newAbbreviation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_RELATIONSHIP__ABBREVIATION, oldAbbreviation, abbreviation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParticipations()).basicAdd(otherEnd, msgs);
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
			case RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS:
				return ((InternalEList<?>)getParticipations()).basicRemove(otherEnd, msgs);
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
			case RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS:
				return getParticipations();
			case RsldlPackage.DL_RELATIONSHIP__NAME:
				return getName();
			case RsldlPackage.DL_RELATIONSHIP__TYPE:
				return getType();
			case RsldlPackage.DL_RELATIONSHIP__ABBREVIATION:
				return getAbbreviation();
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
			case RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS:
				getParticipations().clear();
				getParticipations().addAll((Collection<? extends DLRelationshipParticipation>)newValue);
				return;
			case RsldlPackage.DL_RELATIONSHIP__NAME:
				setName((String)newValue);
				return;
			case RsldlPackage.DL_RELATIONSHIP__ABBREVIATION:
				setAbbreviation((String)newValue);
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
			case RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS:
				getParticipations().clear();
				return;
			case RsldlPackage.DL_RELATIONSHIP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RsldlPackage.DL_RELATIONSHIP__ABBREVIATION:
				setAbbreviation(ABBREVIATION_EDEFAULT);
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
			case RsldlPackage.DL_RELATIONSHIP__PARTICIPATIONS:
				return participations != null && !participations.isEmpty();
			case RsldlPackage.DL_RELATIONSHIP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RsldlPackage.DL_RELATIONSHIP__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case RsldlPackage.DL_RELATIONSHIP__ABBREVIATION:
				return ABBREVIATION_EDEFAULT == null ? abbreviation != null : !ABBREVIATION_EDEFAULT.equals(abbreviation);
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
		result.append(", abbreviation: ");
		result.append(abbreviation);
		result.append(')');
		return result.toString();
	}

} //DLRelationshipImpl
