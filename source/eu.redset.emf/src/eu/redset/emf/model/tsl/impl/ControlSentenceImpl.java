/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TslPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Control Sentence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.ControlSentenceImpl#getInvocation <em>Invocation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ControlSentenceImpl extends ScenarioSentenceImpl implements ControlSentence {
	/**
	 * The cached value of the '{@link #getInvocation() <em>Invocation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvocation()
	 * @generated
	 * @ordered
	 */
	protected TestInvocationRelationship invocation;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlSentenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.CONTROL_SENTENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestInvocationRelationship getInvocation() {
		if (invocation != null && invocation.eIsProxy()) {
			InternalEObject oldInvocation = (InternalEObject)invocation;
			invocation = (TestInvocationRelationship)eResolveProxy(oldInvocation);
			if (invocation != oldInvocation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.CONTROL_SENTENCE__INVOCATION, oldInvocation, invocation));
			}
		}
		return invocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestInvocationRelationship basicGetInvocation() {
		return invocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvocation(TestInvocationRelationship newInvocation) {
		TestInvocationRelationship oldInvocation = invocation;
		invocation = newInvocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.CONTROL_SENTENCE__INVOCATION, oldInvocation, invocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TslPackage.CONTROL_SENTENCE__INVOCATION:
				if (resolve) return getInvocation();
				return basicGetInvocation();
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
			case TslPackage.CONTROL_SENTENCE__INVOCATION:
				setInvocation((TestInvocationRelationship)newValue);
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
			case TslPackage.CONTROL_SENTENCE__INVOCATION:
				setInvocation((TestInvocationRelationship)null);
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
			case TslPackage.CONTROL_SENTENCE__INVOCATION:
				return invocation != null;
		}
		return super.eIsSet(featureID);
	}

} //ControlSentenceImpl
