/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.emf.model.tsl.TslPackage;

import eu.redset.emf.model.tsl.UseCaseTest;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Invocation Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestInvocationRelationshipImpl#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestInvocationRelationshipImpl#getInvocationTarget <em>Invocation Target</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestInvocationRelationshipImpl#getUid <em>Uid</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestInvocationRelationshipImpl#getIncludeType <em>Include Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestInvocationRelationshipImpl extends TestRelationshipImpl implements TestInvocationRelationship {
	/**
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected TestRelationship eReference0;

	/**
	 * The cached value of the '{@link #getInvocationTarget() <em>Invocation Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvocationTarget()
	 * @generated
	 * @ordered
	 */
	protected UseCaseTest invocationTarget;

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
	 * The default value of the '{@link #getIncludeType() <em>Include Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludeType()
	 * @generated
	 * @ordered
	 */
	protected static final String INCLUDE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIncludeType() <em>Include Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludeType()
	 * @generated
	 * @ordered
	 */
	protected String includeType = INCLUDE_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestInvocationRelationshipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.TEST_INVOCATION_RELATIONSHIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestRelationship getEReference0() {
		if (eReference0 != null && eReference0.eIsProxy()) {
			InternalEObject oldEReference0 = (InternalEObject)eReference0;
			eReference0 = (TestRelationship)eResolveProxy(oldEReference0);
			if (eReference0 != oldEReference0) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.TEST_INVOCATION_RELATIONSHIP__EREFERENCE0, oldEReference0, eReference0));
			}
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestRelationship basicGetEReference0() {
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEReference0(TestRelationship newEReference0) {
		TestRelationship oldEReference0 = eReference0;
		eReference0 = newEReference0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_INVOCATION_RELATIONSHIP__EREFERENCE0, oldEReference0, eReference0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTest getInvocationTarget() {
		if (invocationTarget != null && invocationTarget.eIsProxy()) {
			InternalEObject oldInvocationTarget = (InternalEObject)invocationTarget;
			invocationTarget = (UseCaseTest)eResolveProxy(oldInvocationTarget);
			if (invocationTarget != oldInvocationTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET, oldInvocationTarget, invocationTarget));
			}
		}
		return invocationTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTest basicGetInvocationTarget() {
		return invocationTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvocationTarget(UseCaseTest newInvocationTarget) {
		UseCaseTest oldInvocationTarget = invocationTarget;
		invocationTarget = newInvocationTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET, oldInvocationTarget, invocationTarget));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_INVOCATION_RELATIONSHIP__UID, oldUid, uid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIncludeType() {
		return includeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncludeType(String newIncludeType) {
		String oldIncludeType = includeType;
		includeType = newIncludeType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_INVOCATION_RELATIONSHIP__INCLUDE_TYPE, oldIncludeType, includeType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__EREFERENCE0:
				if (resolve) return getEReference0();
				return basicGetEReference0();
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET:
				if (resolve) return getInvocationTarget();
				return basicGetInvocationTarget();
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__UID:
				return getUid();
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__INCLUDE_TYPE:
				return getIncludeType();
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
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__EREFERENCE0:
				setEReference0((TestRelationship)newValue);
				return;
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET:
				setInvocationTarget((UseCaseTest)newValue);
				return;
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__UID:
				setUid((String)newValue);
				return;
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__INCLUDE_TYPE:
				setIncludeType((String)newValue);
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
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__EREFERENCE0:
				setEReference0((TestRelationship)null);
				return;
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET:
				setInvocationTarget((UseCaseTest)null);
				return;
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__UID:
				setUid(UID_EDEFAULT);
				return;
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__INCLUDE_TYPE:
				setIncludeType(INCLUDE_TYPE_EDEFAULT);
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
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__EREFERENCE0:
				return eReference0 != null;
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET:
				return invocationTarget != null;
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__UID:
				return UID_EDEFAULT == null ? uid != null : !UID_EDEFAULT.equals(uid);
			case TslPackage.TEST_INVOCATION_RELATIONSHIP__INCLUDE_TYPE:
				return INCLUDE_TYPE_EDEFAULT == null ? includeType != null : !INCLUDE_TYPE_EDEFAULT.equals(includeType);
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
		result.append(" (uid: ");
		result.append(uid);
		result.append(", includeType: ");
		result.append(includeType);
		result.append(')');
		return result.toString();
	}

} //TestInvocationRelationshipImpl
