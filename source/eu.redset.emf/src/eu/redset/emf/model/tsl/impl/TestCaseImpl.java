/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.CCondition;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TslPackage;

import eu.redset.emf.model.tsl.UseCaseTestScenarioInstance;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Case</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getEReference1 <em>EReference1</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getOrderNumber <em>Order Number</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getUcName <em>Uc Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getUcTrail <em>Uc Trail</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getUcScenarioName <em>Uc Scenario Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getSentences <em>Sentences</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseImpl#getOrderNumberGlobal <em>Order Number Global</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestCaseImpl extends TestInstanceImpl implements TestCase {
	/**
	 * The cached value of the '{@link #getEReference1() <em>EReference1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference1()
	 * @generated
	 * @ordered
	 */
	protected UseCaseTestScenarioInstance eReference1;

	/**
	 * The default value of the '{@link #getOrderNumber() <em>Order Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int ORDER_NUMBER_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getOrderNumber() <em>Order Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderNumber()
	 * @generated
	 * @ordered
	 */
	protected int orderNumber = ORDER_NUMBER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected CCondition precondition;

	/**
	 * The cached value of the '{@link #getPostcondition() <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
	protected CCondition postcondition;

	/**
	 * The default value of the '{@link #getUcName() <em>Uc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcName()
	 * @generated
	 * @ordered
	 */
	protected static final String UC_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUcName() <em>Uc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcName()
	 * @generated
	 * @ordered
	 */
	protected String ucName = UC_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUcTrail() <em>Uc Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcTrail()
	 * @generated
	 * @ordered
	 */
	protected static final String UC_TRAIL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUcTrail() <em>Uc Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcTrail()
	 * @generated
	 * @ordered
	 */
	protected String ucTrail = UC_TRAIL_EDEFAULT;

	/**
	 * The default value of the '{@link #getUcScenarioName() <em>Uc Scenario Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcScenarioName()
	 * @generated
	 * @ordered
	 */
	protected static final String UC_SCENARIO_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUcScenarioName() <em>Uc Scenario Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUcScenarioName()
	 * @generated
	 * @ordered
	 */
	protected String ucScenarioName = UC_SCENARIO_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSentences() <em>Sentences</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSentences()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCaseSentence> sentences;

	/**
	 * The default value of the '{@link #getOrderNumberGlobal() <em>Order Number Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderNumberGlobal()
	 * @generated
	 * @ordered
	 */
	protected static final String ORDER_NUMBER_GLOBAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrderNumberGlobal() <em>Order Number Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderNumberGlobal()
	 * @generated
	 * @ordered
	 */
	protected String orderNumberGlobal = ORDER_NUMBER_GLOBAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.TEST_CASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenarioInstance getEReference1() {
		return eReference1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEReference1(UseCaseTestScenarioInstance newEReference1, NotificationChain msgs) {
		UseCaseTestScenarioInstance oldEReference1 = eReference1;
		eReference1 = newEReference1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__EREFERENCE1, oldEReference1, newEReference1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEReference1(UseCaseTestScenarioInstance newEReference1) {
		if (newEReference1 != eReference1) {
			NotificationChain msgs = null;
			if (eReference1 != null)
				msgs = ((InternalEObject)eReference1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE__EREFERENCE1, null, msgs);
			if (newEReference1 != null)
				msgs = ((InternalEObject)newEReference1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE__EREFERENCE1, null, msgs);
			msgs = basicSetEReference1(newEReference1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__EREFERENCE1, newEReference1, newEReference1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderNumber(int newOrderNumber) {
		int oldOrderNumber = orderNumber;
		orderNumber = newOrderNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__ORDER_NUMBER, oldOrderNumber, orderNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CCondition getPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrecondition(CCondition newPrecondition, NotificationChain msgs) {
		CCondition oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__PRECONDITION, oldPrecondition, newPrecondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecondition(CCondition newPrecondition) {
		if (newPrecondition != precondition) {
			NotificationChain msgs = null;
			if (precondition != null)
				msgs = ((InternalEObject)precondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE__PRECONDITION, null, msgs);
			if (newPrecondition != null)
				msgs = ((InternalEObject)newPrecondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE__PRECONDITION, null, msgs);
			msgs = basicSetPrecondition(newPrecondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__PRECONDITION, newPrecondition, newPrecondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CCondition getPostcondition() {
		return postcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPostcondition(CCondition newPostcondition, NotificationChain msgs) {
		CCondition oldPostcondition = postcondition;
		postcondition = newPostcondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__POSTCONDITION, oldPostcondition, newPostcondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPostcondition(CCondition newPostcondition) {
		if (newPostcondition != postcondition) {
			NotificationChain msgs = null;
			if (postcondition != null)
				msgs = ((InternalEObject)postcondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE__POSTCONDITION, null, msgs);
			if (newPostcondition != null)
				msgs = ((InternalEObject)newPostcondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE__POSTCONDITION, null, msgs);
			msgs = basicSetPostcondition(newPostcondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__POSTCONDITION, newPostcondition, newPostcondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUcName() {
		return ucName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUcName(String newUcName) {
		String oldUcName = ucName;
		ucName = newUcName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__UC_NAME, oldUcName, ucName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUcTrail() {
		return ucTrail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUcTrail(String newUcTrail) {
		String oldUcTrail = ucTrail;
		ucTrail = newUcTrail;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__UC_TRAIL, oldUcTrail, ucTrail));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUcScenarioName() {
		return ucScenarioName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUcScenarioName(String newUcScenarioName) {
		String oldUcScenarioName = ucScenarioName;
		ucScenarioName = newUcScenarioName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__UC_SCENARIO_NAME, oldUcScenarioName, ucScenarioName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestCaseSentence> getSentences() {
		if (sentences == null) {
			sentences = new EObjectContainmentEList<TestCaseSentence>(TestCaseSentence.class, this, TslPackage.TEST_CASE__SENTENCES);
		}
		return sentences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOrderNumberGlobal() {
		return orderNumberGlobal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderNumberGlobal(String newOrderNumberGlobal) {
		String oldOrderNumberGlobal = orderNumberGlobal;
		orderNumberGlobal = newOrderNumberGlobal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE__ORDER_NUMBER_GLOBAL, oldOrderNumberGlobal, orderNumberGlobal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.TEST_CASE__EREFERENCE1:
				return basicSetEReference1(null, msgs);
			case TslPackage.TEST_CASE__PRECONDITION:
				return basicSetPrecondition(null, msgs);
			case TslPackage.TEST_CASE__POSTCONDITION:
				return basicSetPostcondition(null, msgs);
			case TslPackage.TEST_CASE__SENTENCES:
				return ((InternalEList<?>)getSentences()).basicRemove(otherEnd, msgs);
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
			case TslPackage.TEST_CASE__EREFERENCE1:
				return getEReference1();
			case TslPackage.TEST_CASE__ORDER_NUMBER:
				return getOrderNumber();
			case TslPackage.TEST_CASE__PRECONDITION:
				return getPrecondition();
			case TslPackage.TEST_CASE__POSTCONDITION:
				return getPostcondition();
			case TslPackage.TEST_CASE__UC_NAME:
				return getUcName();
			case TslPackage.TEST_CASE__UC_TRAIL:
				return getUcTrail();
			case TslPackage.TEST_CASE__UC_SCENARIO_NAME:
				return getUcScenarioName();
			case TslPackage.TEST_CASE__SENTENCES:
				return getSentences();
			case TslPackage.TEST_CASE__ORDER_NUMBER_GLOBAL:
				return getOrderNumberGlobal();
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
			case TslPackage.TEST_CASE__EREFERENCE1:
				setEReference1((UseCaseTestScenarioInstance)newValue);
				return;
			case TslPackage.TEST_CASE__ORDER_NUMBER:
				setOrderNumber((Integer)newValue);
				return;
			case TslPackage.TEST_CASE__PRECONDITION:
				setPrecondition((CCondition)newValue);
				return;
			case TslPackage.TEST_CASE__POSTCONDITION:
				setPostcondition((CCondition)newValue);
				return;
			case TslPackage.TEST_CASE__UC_NAME:
				setUcName((String)newValue);
				return;
			case TslPackage.TEST_CASE__UC_TRAIL:
				setUcTrail((String)newValue);
				return;
			case TslPackage.TEST_CASE__UC_SCENARIO_NAME:
				setUcScenarioName((String)newValue);
				return;
			case TslPackage.TEST_CASE__SENTENCES:
				getSentences().clear();
				getSentences().addAll((Collection<? extends TestCaseSentence>)newValue);
				return;
			case TslPackage.TEST_CASE__ORDER_NUMBER_GLOBAL:
				setOrderNumberGlobal((String)newValue);
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
			case TslPackage.TEST_CASE__EREFERENCE1:
				setEReference1((UseCaseTestScenarioInstance)null);
				return;
			case TslPackage.TEST_CASE__ORDER_NUMBER:
				setOrderNumber(ORDER_NUMBER_EDEFAULT);
				return;
			case TslPackage.TEST_CASE__PRECONDITION:
				setPrecondition((CCondition)null);
				return;
			case TslPackage.TEST_CASE__POSTCONDITION:
				setPostcondition((CCondition)null);
				return;
			case TslPackage.TEST_CASE__UC_NAME:
				setUcName(UC_NAME_EDEFAULT);
				return;
			case TslPackage.TEST_CASE__UC_TRAIL:
				setUcTrail(UC_TRAIL_EDEFAULT);
				return;
			case TslPackage.TEST_CASE__UC_SCENARIO_NAME:
				setUcScenarioName(UC_SCENARIO_NAME_EDEFAULT);
				return;
			case TslPackage.TEST_CASE__SENTENCES:
				getSentences().clear();
				return;
			case TslPackage.TEST_CASE__ORDER_NUMBER_GLOBAL:
				setOrderNumberGlobal(ORDER_NUMBER_GLOBAL_EDEFAULT);
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
			case TslPackage.TEST_CASE__EREFERENCE1:
				return eReference1 != null;
			case TslPackage.TEST_CASE__ORDER_NUMBER:
				return orderNumber != ORDER_NUMBER_EDEFAULT;
			case TslPackage.TEST_CASE__PRECONDITION:
				return precondition != null;
			case TslPackage.TEST_CASE__POSTCONDITION:
				return postcondition != null;
			case TslPackage.TEST_CASE__UC_NAME:
				return UC_NAME_EDEFAULT == null ? ucName != null : !UC_NAME_EDEFAULT.equals(ucName);
			case TslPackage.TEST_CASE__UC_TRAIL:
				return UC_TRAIL_EDEFAULT == null ? ucTrail != null : !UC_TRAIL_EDEFAULT.equals(ucTrail);
			case TslPackage.TEST_CASE__UC_SCENARIO_NAME:
				return UC_SCENARIO_NAME_EDEFAULT == null ? ucScenarioName != null : !UC_SCENARIO_NAME_EDEFAULT.equals(ucScenarioName);
			case TslPackage.TEST_CASE__SENTENCES:
				return sentences != null && !sentences.isEmpty();
			case TslPackage.TEST_CASE__ORDER_NUMBER_GLOBAL:
				return ORDER_NUMBER_GLOBAL_EDEFAULT == null ? orderNumberGlobal != null : !ORDER_NUMBER_GLOBAL_EDEFAULT.equals(orderNumberGlobal);
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
		result.append(" (orderNumber: ");
		result.append(orderNumber);
		result.append(", ucName: ");
		result.append(ucName);
		result.append(", ucTrail: ");
		result.append(ucTrail);
		result.append(", ucScenarioName: ");
		result.append(ucScenarioName);
		result.append(", orderNumberGlobal: ");
		result.append(orderNumberGlobal);
		result.append(')');
		return result.toString();
	}

} //TestCaseImpl
