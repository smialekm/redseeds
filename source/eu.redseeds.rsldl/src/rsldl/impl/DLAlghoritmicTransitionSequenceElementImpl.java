/**
 */
package rsldl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import rsldl.DLAlghoritmicTransitionSequenceElement;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Alghoritmic Transition Sequence Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLAlghoritmicTransitionSequenceElementImpl#getSequent <em>Sequent</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DLAlghoritmicTransitionSequenceElementImpl extends EObjectImpl implements DLAlghoritmicTransitionSequenceElement {
	/**
	 * The cached value of the '{@link #getSequent() <em>Sequent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequent()
	 * @generated
	 * @ordered
	 */
	protected DLAlghoritmicTransitionSequenceElement sequent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLAlghoritmicTransitionSequenceElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLAlghoritmicTransitionSequenceElement getSequent() {
		return sequent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSequent(DLAlghoritmicTransitionSequenceElement newSequent, NotificationChain msgs) {
		DLAlghoritmicTransitionSequenceElement oldSequent = sequent;
		sequent = newSequent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT, oldSequent, newSequent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequent(DLAlghoritmicTransitionSequenceElement newSequent) {
		if (newSequent != sequent) {
			NotificationChain msgs = null;
			if (sequent != null)
				msgs = ((InternalEObject)sequent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT, null, msgs);
			if (newSequent != null)
				msgs = ((InternalEObject)newSequent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT, null, msgs);
			msgs = basicSetSequent(newSequent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT, newSequent, newSequent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT:
				return basicSetSequent(null, msgs);
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
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT:
				return getSequent();
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
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT:
				setSequent((DLAlghoritmicTransitionSequenceElement)newValue);
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
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT:
				setSequent((DLAlghoritmicTransitionSequenceElement)null);
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
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT:
				return sequent != null;
		}
		return super.eIsSet(featureID);
	}

} //DLAlghoritmicTransitionSequenceElementImpl
