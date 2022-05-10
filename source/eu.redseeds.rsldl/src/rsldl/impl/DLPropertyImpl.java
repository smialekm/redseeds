/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import rsldl.DLNotion;
import rsldl.DLProperty;
import rsldl.DLPropertyValueType;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLPropertyImpl#getValueType <em>Value Type</em>}</li>
 *   <li>{@link rsldl.impl.DLPropertyImpl#getSetType <em>Set Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLPropertyImpl extends DLNotionImpl implements DLProperty {
	/**
	 * The default value of the '{@link #getValueType() <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueType()
	 * @generated
	 * @ordered
	 */
	protected static final DLPropertyValueType VALUE_TYPE_EDEFAULT = DLPropertyValueType.STRING;

	/**
	 * The cached value of the '{@link #getValueType() <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueType()
	 * @generated
	 * @ordered
	 */
	protected DLPropertyValueType valueType = VALUE_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSetType() <em>Set Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetType()
	 * @generated
	 * @ordered
	 */
	protected DLNotion setType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPropertyValueType getValueType() {
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setValueType(DLPropertyValueType newValueType) {
		DLPropertyValueType oldValueType = valueType;
		valueType = newValueType == null ? VALUE_TYPE_EDEFAULT : newValueType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_PROPERTY__VALUE_TYPE, oldValueType, valueType));
		if (!DLPropertyValueType.SET.equals(newValueType) && !DLPropertyValueType.ORDERED_SET.equals(newValueType) && (DLPropertyValueType.SET.equals(oldValueType) || DLPropertyValueType.ORDERED_SET.equals(oldValueType))) {
			DLNotion oldSetType = setType;
			setType = null;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_PROPERTY__SET_TYPE, oldSetType, null));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLNotion getSetType() {
		if (setType != null && setType.eIsProxy()) {
			InternalEObject oldSetType = (InternalEObject)setType;
			setType = (DLNotion)eResolveProxy(oldSetType);
			if (setType != oldSetType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RsldlPackage.DL_PROPERTY__SET_TYPE, oldSetType, setType));
			}
		}
		return setType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLNotion basicGetSetType() {
		return setType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSetType(DLNotion newSetType) {
		if(!DLPropertyValueType.SET.equals(getValueType()) && !DLPropertyValueType.ORDERED_SET.equals(getValueType()))
			return;
		DLNotion oldSetType = setType;
		setType = newSetType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_PROPERTY__SET_TYPE, oldSetType, setType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RsldlPackage.DL_PROPERTY__VALUE_TYPE:
				return getValueType();
			case RsldlPackage.DL_PROPERTY__SET_TYPE:
				if (resolve) return getSetType();
				return basicGetSetType();
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
			case RsldlPackage.DL_PROPERTY__VALUE_TYPE:
				setValueType((DLPropertyValueType)newValue);
				return;
			case RsldlPackage.DL_PROPERTY__SET_TYPE:
				setSetType((DLNotion)newValue);
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
			case RsldlPackage.DL_PROPERTY__VALUE_TYPE:
				setValueType(VALUE_TYPE_EDEFAULT);
				return;
			case RsldlPackage.DL_PROPERTY__SET_TYPE:
				setSetType((DLNotion)null);
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
			case RsldlPackage.DL_PROPERTY__VALUE_TYPE:
				return valueType != VALUE_TYPE_EDEFAULT;
			case RsldlPackage.DL_PROPERTY__SET_TYPE:
				return setType != null;
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
		result.append(" (valueType: ");
		result.append(valueType);
		result.append(')');
		return result.toString();
	}

} //DLPropertyImpl
