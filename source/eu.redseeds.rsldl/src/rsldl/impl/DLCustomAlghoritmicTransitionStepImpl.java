/**
 */
package rsldl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import rsldl.DLAlghoritmicTransitionSequenceElement;
import rsldl.DLConditionPattern;
import rsldl.DLCustomAlghoritmicTransitionStep;
import rsldl.DLTransitionPattern;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Custom Alghoritmic Transition Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLCustomAlghoritmicTransitionStepImpl#getAlternative <em>Alternative</em>}</li>
 *   <li>{@link rsldl.impl.DLCustomAlghoritmicTransitionStepImpl#getAction <em>Action</em>}</li>
 *   <li>{@link rsldl.impl.DLCustomAlghoritmicTransitionStepImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLCustomAlghoritmicTransitionStepImpl extends DLAlghoritmicTransitionStepImpl implements DLCustomAlghoritmicTransitionStep {
	/**
	 * The cached value of the '{@link #getAlternative() <em>Alternative</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlternative()
	 * @generated
	 * @ordered
	 */
	protected DLAlghoritmicTransitionSequenceElement alternative;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected DLTransitionPattern action;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected DLConditionPattern condition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLCustomAlghoritmicTransitionStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLAlghoritmicTransitionSequenceElement getAlternative() {
		return alternative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAlternative(DLAlghoritmicTransitionSequenceElement newAlternative, NotificationChain msgs) {
		DLAlghoritmicTransitionSequenceElement oldAlternative = alternative;
		alternative = newAlternative;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE, oldAlternative, newAlternative);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlternative(DLAlghoritmicTransitionSequenceElement newAlternative) {
		if (newAlternative != alternative) {
			NotificationChain msgs = null;
			if (alternative != null)
				msgs = ((InternalEObject)alternative).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE, null, msgs);
			if (newAlternative != null)
				msgs = ((InternalEObject)newAlternative).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE, null, msgs);
			msgs = basicSetAlternative(newAlternative, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE, newAlternative, newAlternative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLTransitionPattern getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAction(DLTransitionPattern newAction, NotificationChain msgs) {
		DLTransitionPattern oldAction = action;
		action = newAction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION, oldAction, newAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(DLTransitionPattern newAction) {
		if (newAction != action) {
			NotificationChain msgs = null;
			if (action != null)
				msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION, null, msgs);
			if (newAction != null)
				msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION, null, msgs);
			msgs = basicSetAction(newAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION, newAction, newAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLConditionPattern getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(DLConditionPattern newCondition, NotificationChain msgs) {
		DLConditionPattern oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(DLConditionPattern newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE:
				return basicSetAlternative(null, msgs);
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION:
				return basicSetAction(null, msgs);
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION:
				return basicSetCondition(null, msgs);
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
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE:
				return getAlternative();
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION:
				return getAction();
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION:
				return getCondition();
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
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE:
				setAlternative((DLAlghoritmicTransitionSequenceElement)newValue);
				return;
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION:
				setAction((DLTransitionPattern)newValue);
				return;
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION:
				setCondition((DLConditionPattern)newValue);
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
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE:
				setAlternative((DLAlghoritmicTransitionSequenceElement)null);
				return;
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION:
				setAction((DLTransitionPattern)null);
				return;
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION:
				setCondition((DLConditionPattern)null);
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
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE:
				return alternative != null;
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION:
				return action != null;
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION:
				return condition != null;
		}
		return super.eIsSet(featureID);
	}

} //DLCustomAlghoritmicTransitionStepImpl
