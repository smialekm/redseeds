/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.DirectObject;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.IndirectObject;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.TslPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SVO Sentence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.SVOSentenceImpl#getDirectObject <em>Direct Object</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.SVOSentenceImpl#getIndirectObject <em>Indirect Object</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.SVOSentenceImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.SVOSentenceImpl#getPredicate <em>Predicate</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SVOSentenceImpl extends ScenarioSentenceImpl implements SVOSentence {
	/**
	 * The cached value of the '{@link #getDirectObject() <em>Direct Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectObject()
	 * @generated
	 * @ordered
	 */
	protected DomainObject directObject;

	/**
	 * The cached value of the '{@link #getIndirectObject() <em>Indirect Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndirectObject()
	 * @generated
	 * @ordered
	 */
	protected DomainObject indirectObject;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected int number = NUMBER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPredicate() <em>Predicate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredicate()
	 * @generated
	 * @ordered
	 */
	protected DomainStatement predicate;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SVOSentenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.SVO_SENTENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(int newNumber) {
		int oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.SVO_SENTENCE__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainStatement getPredicate() {
		if (predicate != null && predicate.eIsProxy()) {
			InternalEObject oldPredicate = (InternalEObject)predicate;
			predicate = (DomainStatement)eResolveProxy(oldPredicate);
			if (predicate != oldPredicate) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.SVO_SENTENCE__PREDICATE, oldPredicate, predicate));
			}
		}
		return predicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainStatement basicGetPredicate() {
		return predicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredicate(DomainStatement newPredicate) {
		DomainStatement oldPredicate = predicate;
		predicate = newPredicate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.SVO_SENTENCE__PREDICATE, oldPredicate, predicate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainObject getDirectObject() {
		return directObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDirectObject(DomainObject newDirectObject, NotificationChain msgs) {
		DomainObject oldDirectObject = directObject;
		directObject = newDirectObject;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TslPackage.SVO_SENTENCE__DIRECT_OBJECT, oldDirectObject, newDirectObject);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirectObject(DomainObject newDirectObject) {
		if (newDirectObject != directObject) {
			NotificationChain msgs = null;
			if (directObject != null)
				msgs = ((InternalEObject)directObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TslPackage.SVO_SENTENCE__DIRECT_OBJECT, null, msgs);
			if (newDirectObject != null)
				msgs = ((InternalEObject)newDirectObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TslPackage.SVO_SENTENCE__DIRECT_OBJECT, null, msgs);
			msgs = basicSetDirectObject(newDirectObject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.SVO_SENTENCE__DIRECT_OBJECT, newDirectObject, newDirectObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainObject getIndirectObject() {
		return indirectObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIndirectObject(DomainObject newIndirectObject, NotificationChain msgs) {
		DomainObject oldIndirectObject = indirectObject;
		indirectObject = newIndirectObject;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TslPackage.SVO_SENTENCE__INDIRECT_OBJECT, oldIndirectObject, newIndirectObject);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndirectObject(DomainObject newIndirectObject) {
		if (newIndirectObject != indirectObject) {
			NotificationChain msgs = null;
			if (indirectObject != null)
				msgs = ((InternalEObject)indirectObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TslPackage.SVO_SENTENCE__INDIRECT_OBJECT, null, msgs);
			if (newIndirectObject != null)
				msgs = ((InternalEObject)newIndirectObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TslPackage.SVO_SENTENCE__INDIRECT_OBJECT, null, msgs);
			msgs = basicSetIndirectObject(newIndirectObject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.SVO_SENTENCE__INDIRECT_OBJECT, newIndirectObject, newIndirectObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.SVO_SENTENCE__DIRECT_OBJECT:
				return basicSetDirectObject(null, msgs);
			case TslPackage.SVO_SENTENCE__INDIRECT_OBJECT:
				return basicSetIndirectObject(null, msgs);
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
			case TslPackage.SVO_SENTENCE__DIRECT_OBJECT:
				return getDirectObject();
			case TslPackage.SVO_SENTENCE__INDIRECT_OBJECT:
				return getIndirectObject();
			case TslPackage.SVO_SENTENCE__NUMBER:
				return getNumber();
			case TslPackage.SVO_SENTENCE__PREDICATE:
				if (resolve) return getPredicate();
				return basicGetPredicate();
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
			case TslPackage.SVO_SENTENCE__DIRECT_OBJECT:
				setDirectObject((DomainObject)newValue);
				return;
			case TslPackage.SVO_SENTENCE__INDIRECT_OBJECT:
				setIndirectObject((DomainObject)newValue);
				return;
			case TslPackage.SVO_SENTENCE__NUMBER:
				setNumber((Integer)newValue);
				return;
			case TslPackage.SVO_SENTENCE__PREDICATE:
				setPredicate((DomainStatement)newValue);
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
			case TslPackage.SVO_SENTENCE__DIRECT_OBJECT:
				setDirectObject((DomainObject)null);
				return;
			case TslPackage.SVO_SENTENCE__INDIRECT_OBJECT:
				setIndirectObject((DomainObject)null);
				return;
			case TslPackage.SVO_SENTENCE__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case TslPackage.SVO_SENTENCE__PREDICATE:
				setPredicate((DomainStatement)null);
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
			case TslPackage.SVO_SENTENCE__DIRECT_OBJECT:
				return directObject != null;
			case TslPackage.SVO_SENTENCE__INDIRECT_OBJECT:
				return indirectObject != null;
			case TslPackage.SVO_SENTENCE__NUMBER:
				return number != NUMBER_EDEFAULT;
			case TslPackage.SVO_SENTENCE__PREDICATE:
				return predicate != null;
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
		result.append(" (number: ");
		result.append(number);
		result.append(')');
		return result.toString();
	}

} //SVOSentenceImpl
