/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.TslPackage;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestSpecificationImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestSpecificationImpl#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestSpecificationImpl#getScUid <em>Sc Uid</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestSpecificationImpl#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestSpecificationImpl extends EObjectImpl implements TestSpecification {
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
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected EList<TestPackage> eReference0;

	/**
	 * The default value of the '{@link #getScUid() <em>Sc Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScUid()
	 * @generated
	 * @ordered
	 */
	protected static final String SC_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScUid() <em>Sc Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScUid()
	 * @generated
	 * @ordered
	 */
	protected String scUid = SC_UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected String uid = UID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.TEST_SPECIFICATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_SPECIFICATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestPackage> getEReference0() {
		if (eReference0 == null) {
			eReference0 = new EObjectContainmentEList<TestPackage>(TestPackage.class, this, TslPackage.TEST_SPECIFICATION__EREFERENCE0);
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getScUid() {
		return scUid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScUid(String newScUid) {
		String oldScUid = scUid;
		scUid = newScUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_SPECIFICATION__SC_UID, oldScUid, scUid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUid(String newUid) {
		String oldUid = uid;
		uid = newUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_SPECIFICATION__UID, oldUid, uid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.TEST_SPECIFICATION__EREFERENCE0:
				return ((InternalEList<?>)getEReference0()).basicRemove(otherEnd, msgs);
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
			case TslPackage.TEST_SPECIFICATION__NAME:
				return getName();
			case TslPackage.TEST_SPECIFICATION__EREFERENCE0:
				return getEReference0();
			case TslPackage.TEST_SPECIFICATION__SC_UID:
				return getScUid();
			case TslPackage.TEST_SPECIFICATION__UID:
				return getUid();
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
			case TslPackage.TEST_SPECIFICATION__NAME:
				setName((String)newValue);
				return;
			case TslPackage.TEST_SPECIFICATION__EREFERENCE0:
				getEReference0().clear();
				getEReference0().addAll((Collection<? extends TestPackage>)newValue);
				return;
			case TslPackage.TEST_SPECIFICATION__SC_UID:
				setScUid((String)newValue);
				return;
			case TslPackage.TEST_SPECIFICATION__UID:
				setUid((String)newValue);
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
			case TslPackage.TEST_SPECIFICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TslPackage.TEST_SPECIFICATION__EREFERENCE0:
				getEReference0().clear();
				return;
			case TslPackage.TEST_SPECIFICATION__SC_UID:
				setScUid(SC_UID_EDEFAULT);
				return;
			case TslPackage.TEST_SPECIFICATION__UID:
				setUid(UID_EDEFAULT);
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
			case TslPackage.TEST_SPECIFICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TslPackage.TEST_SPECIFICATION__EREFERENCE0:
				return eReference0 != null && !eReference0.isEmpty();
			case TslPackage.TEST_SPECIFICATION__SC_UID:
				return SC_UID_EDEFAULT == null ? scUid != null : !SC_UID_EDEFAULT.equals(scUid);
			case TslPackage.TEST_SPECIFICATION__UID:
				return UID_EDEFAULT == null ? uid != null : !UID_EDEFAULT.equals(uid);
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
		result.append(", scUid: ");
		result.append(scUid);
		result.append(", uid: ");
		result.append(uid);
		result.append(')');
		return result.toString();
	}

} //TestSpecificationImpl
