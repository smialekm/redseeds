/**
 */
package rsldl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import rsldl.DLNamedLink;
import rsldl.DLReferencingElement;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Referencing Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLReferencingElementImpl#getSubjectLink <em>Subject Link</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DLReferencingElementImpl extends EObjectImpl implements DLReferencingElement {
	/**
	 * The cached value of the '{@link #getSubjectLink() <em>Subject Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjectLink()
	 * @generated
	 * @ordered
	 */
	protected DLNamedLink subjectLink;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLReferencingElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_REFERENCING_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLNamedLink getSubjectLink() {
		if (subjectLink != null && subjectLink.eIsProxy()) {
			InternalEObject oldSubjectLink = (InternalEObject)subjectLink;
			subjectLink = (DLNamedLink)eResolveProxy(oldSubjectLink);
			if (subjectLink != oldSubjectLink) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RsldlPackage.DL_REFERENCING_ELEMENT__SUBJECT_LINK, oldSubjectLink, subjectLink));
			}
		}
		return subjectLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLNamedLink basicGetSubjectLink() {
		return subjectLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubjectLink(DLNamedLink newSubjectLink) {
		DLNamedLink oldSubjectLink = subjectLink;
		subjectLink = newSubjectLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_REFERENCING_ELEMENT__SUBJECT_LINK, oldSubjectLink, subjectLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RsldlPackage.DL_REFERENCING_ELEMENT__SUBJECT_LINK:
				if (resolve) return getSubjectLink();
				return basicGetSubjectLink();
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
			case RsldlPackage.DL_REFERENCING_ELEMENT__SUBJECT_LINK:
				setSubjectLink((DLNamedLink)newValue);
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
			case RsldlPackage.DL_REFERENCING_ELEMENT__SUBJECT_LINK:
				setSubjectLink((DLNamedLink)null);
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
			case RsldlPackage.DL_REFERENCING_ELEMENT__SUBJECT_LINK:
				return subjectLink != null;
		}
		return super.eIsSet(featureID);
	}

} //DLReferencingElementImpl
