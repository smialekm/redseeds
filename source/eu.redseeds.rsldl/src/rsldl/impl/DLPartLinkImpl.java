/**
 */
package rsldl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import rsldl.DLDereferenceLink;
import rsldl.DLPartLink;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Part Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLPartLinkImpl#getPartDereference <em>Part Dereference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLPartLinkImpl extends DLFeatureImpl implements DLPartLink {
	/**
	 * The cached value of the '{@link #getPartDereference() <em>Part Dereference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartDereference()
	 * @generated
	 * @ordered
	 */
	protected DLDereferenceLink partDereference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLPartLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_PART_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLDereferenceLink getPartDereference() {
		if (partDereference != null && partDereference.eIsProxy()) {
			InternalEObject oldPartDereference = (InternalEObject)partDereference;
			partDereference = (DLDereferenceLink)eResolveProxy(oldPartDereference);
			if (partDereference != oldPartDereference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RsldlPackage.DL_PART_LINK__PART_DEREFERENCE, oldPartDereference, partDereference));
			}
		}
		return partDereference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLDereferenceLink basicGetPartDereference() {
		return partDereference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartDereference(DLDereferenceLink newPartDereference) {
		DLDereferenceLink oldPartDereference = partDereference;
		partDereference = newPartDereference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_PART_LINK__PART_DEREFERENCE, oldPartDereference, partDereference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RsldlPackage.DL_PART_LINK__PART_DEREFERENCE:
				if (resolve) return getPartDereference();
				return basicGetPartDereference();
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
			case RsldlPackage.DL_PART_LINK__PART_DEREFERENCE:
				setPartDereference((DLDereferenceLink)newValue);
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
			case RsldlPackage.DL_PART_LINK__PART_DEREFERENCE:
				setPartDereference((DLDereferenceLink)null);
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
			case RsldlPackage.DL_PART_LINK__PART_DEREFERENCE:
				return partDereference != null;
		}
		return super.eIsSet(featureID);
	}

} //DLPartLinkImpl
