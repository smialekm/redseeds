/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.DomainObjectType;
import eu.redset.emf.model.tsl.TestValue;
import eu.redset.emf.model.tsl.ObjectAttribute;
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
 * An implementation of the model object '<em><b>Domain Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.DomainObjectImpl#getInputData <em>Input Data</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.DomainObjectImpl#getDomainObjectType <em>Domain Object Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainObjectImpl extends TestInstanceImpl implements DomainObject {
	/**
	 * The cached value of the '{@link #getInputData() <em>Input Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputData()
	 * @generated
	 * @ordered
	 */
	protected EList<TestValue> inputData;

	/**
	 * The default value of the '{@link #getDomainObjectType() <em>Domain Object Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainObjectType()
	 * @generated
	 * @ordered
	 */
	protected static final DomainObjectType DOMAIN_OBJECT_TYPE_EDEFAULT = DomainObjectType.INPUT_DATA;
	/**
	 * The cached value of the '{@link #getDomainObjectType() <em>Domain Object Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainObjectType()
	 * @generated
	 * @ordered
	 */
	protected DomainObjectType domainObjectType = DOMAIN_OBJECT_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.DOMAIN_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestValue> getInputData() {
		if (inputData == null) {
			inputData = new EObjectContainmentEList<TestValue>(TestValue.class, this, TslPackage.DOMAIN_OBJECT__INPUT_DATA);
		}
		return inputData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainObjectType getDomainObjectType() {
		return domainObjectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainObjectType(DomainObjectType newDomainObjectType) {
		DomainObjectType oldDomainObjectType = domainObjectType;
		domainObjectType = newDomainObjectType == null ? DOMAIN_OBJECT_TYPE_EDEFAULT : newDomainObjectType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_TYPE, oldDomainObjectType, domainObjectType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.DOMAIN_OBJECT__INPUT_DATA:
				return ((InternalEList<?>)getInputData()).basicRemove(otherEnd, msgs);
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
			case TslPackage.DOMAIN_OBJECT__INPUT_DATA:
				return getInputData();
			case TslPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_TYPE:
				return getDomainObjectType();
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
			case TslPackage.DOMAIN_OBJECT__INPUT_DATA:
				getInputData().clear();
				getInputData().addAll((Collection<? extends TestValue>)newValue);
				return;
			case TslPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_TYPE:
				setDomainObjectType((DomainObjectType)newValue);
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
			case TslPackage.DOMAIN_OBJECT__INPUT_DATA:
				getInputData().clear();
				return;
			case TslPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_TYPE:
				setDomainObjectType(DOMAIN_OBJECT_TYPE_EDEFAULT);
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
			case TslPackage.DOMAIN_OBJECT__INPUT_DATA:
				return inputData != null && !inputData.isEmpty();
			case TslPackage.DOMAIN_OBJECT__DOMAIN_OBJECT_TYPE:
				return domainObjectType != DOMAIN_OBJECT_TYPE_EDEFAULT;
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
		result.append(" (domainObjectType: ");
		result.append(domainObjectType);
		result.append(')');
		return result.toString();
	}

} //DomainObjectImpl
