/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.TslPackage;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Case Test Scenario Sentence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioSentenceImpl#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioSentenceImpl#getEReference1 <em>EReference1</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioSentenceImpl#getScenarioSentence <em>Scenario Sentence</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioSentenceImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UseCaseTestScenarioSentenceImpl extends EObjectImpl implements UseCaseTestScenarioSentence {
	/**
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected UseCaseTestScenario eReference0;

	/**
	 * The cached value of the '{@link #getEReference1() <em>EReference1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference1()
	 * @generated
	 * @ordered
	 */
	protected UseCaseTestScenario eReference1;

	/**
	 * The cached value of the '{@link #getScenarioSentence() <em>Scenario Sentence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarioSentence()
	 * @generated
	 * @ordered
	 */
	protected ScenarioSentence scenarioSentence;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseCaseTestScenarioSentenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.USE_CASE_TEST_SCENARIO_SENTENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenario getEReference0() {
		if (eReference0 != null && eReference0.eIsProxy()) {
			InternalEObject oldEReference0 = (InternalEObject)eReference0;
			eReference0 = (UseCaseTestScenario)eResolveProxy(oldEReference0);
			if (eReference0 != oldEReference0) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0, oldEReference0, eReference0));
			}
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenario basicGetEReference0() {
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEReference0(UseCaseTestScenario newEReference0) {
		UseCaseTestScenario oldEReference0 = eReference0;
		eReference0 = newEReference0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0, oldEReference0, eReference0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenario getEReference1() {
		if (eReference1 != null && eReference1.eIsProxy()) {
			InternalEObject oldEReference1 = (InternalEObject)eReference1;
			eReference1 = (UseCaseTestScenario)eResolveProxy(oldEReference1);
			if (eReference1 != oldEReference1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1, oldEReference1, eReference1));
			}
		}
		return eReference1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenario basicGetEReference1() {
		return eReference1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEReference1(UseCaseTestScenario newEReference1) {
		UseCaseTestScenario oldEReference1 = eReference1;
		eReference1 = newEReference1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1, oldEReference1, eReference1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioSentence getScenarioSentence() {
		return scenarioSentence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScenarioSentence(ScenarioSentence newScenarioSentence, NotificationChain msgs) {
		ScenarioSentence oldScenarioSentence = scenarioSentence;
		scenarioSentence = newScenarioSentence;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE, oldScenarioSentence, newScenarioSentence);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScenarioSentence(ScenarioSentence newScenarioSentence) {
		if (newScenarioSentence != scenarioSentence) {
			NotificationChain msgs = null;
			if (scenarioSentence != null)
				msgs = ((InternalEObject)scenarioSentence).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE, null, msgs);
			if (newScenarioSentence != null)
				msgs = ((InternalEObject)newScenarioSentence).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE, null, msgs);
			msgs = basicSetScenarioSentence(newScenarioSentence, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE, newScenarioSentence, newScenarioSentence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE:
				return basicSetScenarioSentence(null, msgs);
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
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0:
				if (resolve) return getEReference0();
				return basicGetEReference0();
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1:
				if (resolve) return getEReference1();
				return basicGetEReference1();
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE:
				return getScenarioSentence();
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__NAME:
				return getName();
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
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0:
				setEReference0((UseCaseTestScenario)newValue);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1:
				setEReference1((UseCaseTestScenario)newValue);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE:
				setScenarioSentence((ScenarioSentence)newValue);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__NAME:
				setName((String)newValue);
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
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0:
				setEReference0((UseCaseTestScenario)null);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1:
				setEReference1((UseCaseTestScenario)null);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE:
				setScenarioSentence((ScenarioSentence)null);
				return;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__NAME:
				setName(NAME_EDEFAULT);
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
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0:
				return eReference0 != null;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1:
				return eReference1 != null;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE:
				return scenarioSentence != null;
			case TslPackage.USE_CASE_TEST_SCENARIO_SENTENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //UseCaseTestScenarioSentenceImpl
