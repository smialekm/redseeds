/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TslPackage;
import eu.redset.emf.model.tsl.UseCaseTest;

import eu.redset.emf.model.tsl.UseCaseTestScenario;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Case Test</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestImpl#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestImpl#getUcName <em>Uc Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestImpl#getUcTrail <em>Uc Trail</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestImpl#getInvocationSource <em>Invocation Source</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestImpl#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UseCaseTestImpl extends TestImpl implements UseCaseTest {
	/**
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected EList<UseCaseTestScenario> eReference0;

	/**
	 * The default value of the '{@link #getUcName() <em>Uc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcName()
	 * @generated
	 * @ordered
	 */
	protected static final String UC_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getUcName() <em>Uc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcName()
	 * @generated
	 * @ordered
	 */
	protected String ucName = UC_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getUcTrail() <em>Uc Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcTrail()
	 * @generated
	 * @ordered
	 */
	protected static final String UC_TRAIL_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getUcTrail() <em>Uc Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcTrail()
	 * @generated
	 * @ordered
	 */
	protected String ucTrail = UC_TRAIL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInvocationSource() <em>Invocation Source</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvocationSource()
	 * @generated
	 * @ordered
	 */
	protected EList<TestInvocationRelationship> invocationSource;

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
	protected UseCaseTestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.USE_CASE_TEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UseCaseTestScenario> getEReference0() {
		if (eReference0 == null) {
			eReference0 = new EObjectContainmentEList<UseCaseTestScenario>(UseCaseTestScenario.class, this, TslPackage.USE_CASE_TEST__EREFERENCE0);
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUcName() {
		return ucName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUcName(String newUcName) {
		String oldUcName = ucName;
		ucName = newUcName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST__UC_NAME, oldUcName, ucName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUcTrail() {
		return ucTrail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUcTrail(String newUcTrail) {
		String oldUcTrail = ucTrail;
		ucTrail = newUcTrail;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST__UC_TRAIL, oldUcTrail, ucTrail));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestInvocationRelationship> getInvocationSource() {
		if (invocationSource == null) {
			invocationSource = new EObjectContainmentEList<TestInvocationRelationship>(TestInvocationRelationship.class, this, TslPackage.USE_CASE_TEST__INVOCATION_SOURCE);
		}
		return invocationSource;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST__UID, oldUid, uid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.USE_CASE_TEST__EREFERENCE0:
				return ((InternalEList<?>)getEReference0()).basicRemove(otherEnd, msgs);
			case TslPackage.USE_CASE_TEST__INVOCATION_SOURCE:
				return ((InternalEList<?>)getInvocationSource()).basicRemove(otherEnd, msgs);
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
			case TslPackage.USE_CASE_TEST__EREFERENCE0:
				return getEReference0();
			case TslPackage.USE_CASE_TEST__UC_NAME:
				return getUcName();
			case TslPackage.USE_CASE_TEST__UC_TRAIL:
				return getUcTrail();
			case TslPackage.USE_CASE_TEST__INVOCATION_SOURCE:
				return getInvocationSource();
			case TslPackage.USE_CASE_TEST__UID:
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
			case TslPackage.USE_CASE_TEST__EREFERENCE0:
				getEReference0().clear();
				getEReference0().addAll((Collection<? extends UseCaseTestScenario>)newValue);
				return;
			case TslPackage.USE_CASE_TEST__UC_NAME:
				setUcName((String)newValue);
				return;
			case TslPackage.USE_CASE_TEST__UC_TRAIL:
				setUcTrail((String)newValue);
				return;
			case TslPackage.USE_CASE_TEST__INVOCATION_SOURCE:
				getInvocationSource().clear();
				getInvocationSource().addAll((Collection<? extends TestInvocationRelationship>)newValue);
				return;
			case TslPackage.USE_CASE_TEST__UID:
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
			case TslPackage.USE_CASE_TEST__EREFERENCE0:
				getEReference0().clear();
				return;
			case TslPackage.USE_CASE_TEST__UC_NAME:
				setUcName(UC_NAME_EDEFAULT);
				return;
			case TslPackage.USE_CASE_TEST__UC_TRAIL:
				setUcTrail(UC_TRAIL_EDEFAULT);
				return;
			case TslPackage.USE_CASE_TEST__INVOCATION_SOURCE:
				getInvocationSource().clear();
				return;
			case TslPackage.USE_CASE_TEST__UID:
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
			case TslPackage.USE_CASE_TEST__EREFERENCE0:
				return eReference0 != null && !eReference0.isEmpty();
			case TslPackage.USE_CASE_TEST__UC_NAME:
				return UC_NAME_EDEFAULT == null ? ucName != null : !UC_NAME_EDEFAULT.equals(ucName);
			case TslPackage.USE_CASE_TEST__UC_TRAIL:
				return UC_TRAIL_EDEFAULT == null ? ucTrail != null : !UC_TRAIL_EDEFAULT.equals(ucTrail);
			case TslPackage.USE_CASE_TEST__INVOCATION_SOURCE:
				return invocationSource != null && !invocationSource.isEmpty();
			case TslPackage.USE_CASE_TEST__UID:
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
		result.append(" (ucName: ");
		result.append(ucName);
		result.append(", ucTrail: ");
		result.append(ucTrail);
		result.append(", uid: ");
		result.append(uid);
		result.append(')');
		return result.toString();
	}

} //UseCaseTestImpl
