/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.TestInstance;
import eu.redset.emf.model.tsl.TslPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestInstanceImpl#getAttachedTests <em>Attached Tests</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestInstanceImpl extends TestImpl implements TestInstance {
	/**
	 * The cached value of the '{@link #getAttachedTests() <em>Attached Tests</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttachedTests()
	 * @generated
	 * @ordered
	 */
	protected EList<TestInstance> attachedTests;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.TEST_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestInstance> getAttachedTests() {
		if (attachedTests == null) {
			attachedTests = new EObjectContainmentEList<TestInstance>(TestInstance.class, this, TslPackage.TEST_INSTANCE__ATTACHED_TESTS);
		}
		return attachedTests;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.TEST_INSTANCE__ATTACHED_TESTS:
				return ((InternalEList<?>)getAttachedTests()).basicRemove(otherEnd, msgs);
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
			case TslPackage.TEST_INSTANCE__ATTACHED_TESTS:
				return getAttachedTests();
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
			case TslPackage.TEST_INSTANCE__ATTACHED_TESTS:
				getAttachedTests().clear();
				getAttachedTests().addAll((Collection<? extends TestInstance>)newValue);
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
			case TslPackage.TEST_INSTANCE__ATTACHED_TESTS:
				getAttachedTests().clear();
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
			case TslPackage.TEST_INSTANCE__ATTACHED_TESTS:
				return attachedTests != null && !attachedTests.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestInstanceImpl
