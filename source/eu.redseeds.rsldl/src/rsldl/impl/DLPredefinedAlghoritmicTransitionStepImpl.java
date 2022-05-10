/**
 */
package rsldl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import rsldl.DLPredefinedAlghoritmicTransitionStep;
import rsldl.DLPredefinedStepType;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Predefined Alghoritmic Transition Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLPredefinedAlghoritmicTransitionStepImpl#getType <em>Type</em>}</li>
 *   <li>{@link rsldl.impl.DLPredefinedAlghoritmicTransitionStepImpl#getPattern <em>Pattern</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLPredefinedAlghoritmicTransitionStepImpl extends DLAlghoritmicTransitionStepImpl implements DLPredefinedAlghoritmicTransitionStep {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final DLPredefinedStepType TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected DLPredefinedStepType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected String pattern = PATTERN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLPredefinedAlghoritmicTransitionStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPredefinedStepType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(DLPredefinedStepType newType) {
		DLPredefinedStepType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPattern(String newPattern) {
		String oldPattern = pattern;
		pattern = newPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__PATTERN, oldPattern, pattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__TYPE:
				return getType();
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__PATTERN:
				return getPattern();
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
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__TYPE:
				setType((DLPredefinedStepType)newValue);
				return;
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__PATTERN:
				setPattern((String)newValue);
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
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__PATTERN:
				setPattern(PATTERN_EDEFAULT);
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
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__TYPE:
				return type != TYPE_EDEFAULT;
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
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
		result.append(" (type: ");
		result.append(type);
		result.append(", pattern: ");
		result.append(pattern);
		result.append(')');
		return result.toString();
	}

} //DLPredefinedAlghoritmicTransitionStepImpl
