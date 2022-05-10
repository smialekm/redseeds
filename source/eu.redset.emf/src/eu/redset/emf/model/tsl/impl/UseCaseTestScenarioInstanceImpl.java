/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TslPackage;
import eu.redset.emf.model.tsl.UseCaseTestScenarioInstance;

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
 * An implementation of the model object '<em><b>Use Case Test Scenario Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioInstanceImpl#getEReference3 <em>EReference3</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioInstanceImpl#getUcName <em>Uc Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioInstanceImpl#getUcTrail <em>Uc Trail</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UseCaseTestScenarioInstanceImpl extends UseCaseTestScenarioImpl implements UseCaseTestScenarioInstance {
	/**
	 * The cached value of the '{@link #getEReference3() <em>EReference3</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference3()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCaseSentence> eReference3;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseCaseTestScenarioInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.USE_CASE_TEST_SCENARIO_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestCaseSentence> getEReference3() {
		if (eReference3 == null) {
			eReference3 = new EObjectContainmentEList<TestCaseSentence>(TestCaseSentence.class, this, TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3);
		}
		return eReference3;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_NAME, oldUcName, ucName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_TRAIL, oldUcTrail, ucTrail));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3:
				return ((InternalEList<?>)getEReference3()).basicRemove(otherEnd, msgs);
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
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3:
				return getEReference3();
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_NAME:
				return getUcName();
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_TRAIL:
				return getUcTrail();
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
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3:
				getEReference3().clear();
				getEReference3().addAll((Collection<? extends TestCaseSentence>)newValue);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_NAME:
				setUcName((String)newValue);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_TRAIL:
				setUcTrail((String)newValue);
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
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3:
				getEReference3().clear();
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_NAME:
				setUcName(UC_NAME_EDEFAULT);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_TRAIL:
				setUcTrail(UC_TRAIL_EDEFAULT);
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
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3:
				return eReference3 != null && !eReference3.isEmpty();
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_NAME:
				return UC_NAME_EDEFAULT == null ? ucName != null : !UC_NAME_EDEFAULT.equals(ucName);
			case TslPackage.USE_CASE_TEST_SCENARIO_INSTANCE__UC_TRAIL:
				return UC_TRAIL_EDEFAULT == null ? ucTrail != null : !UC_TRAIL_EDEFAULT.equals(ucTrail);
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
		result.append(')');
		return result.toString();
	}

} //UseCaseTestScenarioInstanceImpl
