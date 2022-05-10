/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.Context;
import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestDataValuesContext;
import eu.redset.emf.model.tsl.TslPackage;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Case Sentence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseSentenceImpl#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseSentenceImpl#getScenarioSentence <em>Scenario Sentence</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseSentenceImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestCaseSentenceImpl#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestCaseSentenceImpl extends EObjectImpl implements TestCaseSentence {
	/**
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected UseCaseTestScenarioSentence eReference0;

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
	 * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected TestDataValuesContext context;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCaseSentenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.TEST_CASE_SENTENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenarioSentence getEReference0() {
		if (eReference0 != null && eReference0.eIsProxy()) {
			InternalEObject oldEReference0 = (InternalEObject)eReference0;
			eReference0 = (UseCaseTestScenarioSentence)eResolveProxy(oldEReference0);
			if (eReference0 != oldEReference0) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.TEST_CASE_SENTENCE__EREFERENCE0, oldEReference0, eReference0));
			}
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCaseTestScenarioSentence basicGetEReference0() {
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEReference0(UseCaseTestScenarioSentence newEReference0) {
		UseCaseTestScenarioSentence oldEReference0 = eReference0;
		eReference0 = newEReference0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE_SENTENCE__EREFERENCE0, oldEReference0, eReference0));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE, oldScenarioSentence, newScenarioSentence);
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
				msgs = ((InternalEObject)scenarioSentence).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE, null, msgs);
			if (newScenarioSentence != null)
				msgs = ((InternalEObject)newScenarioSentence).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE, null, msgs);
			msgs = basicSetScenarioSentence(newScenarioSentence, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE, newScenarioSentence, newScenarioSentence));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE_SENTENCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestDataValuesContext getContext() {
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContext(TestDataValuesContext newContext, NotificationChain msgs) {
		TestDataValuesContext oldContext = context;
		context = newContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE_SENTENCE__CONTEXT, oldContext, newContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContext(TestDataValuesContext newContext) {
		if (newContext != context) {
			NotificationChain msgs = null;
			if (context != null)
				msgs = ((InternalEObject)context).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE_SENTENCE__CONTEXT, null, msgs);
			if (newContext != null)
				msgs = ((InternalEObject)newContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TslPackage.TEST_CASE_SENTENCE__CONTEXT, null, msgs);
			msgs = basicSetContext(newContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_CASE_SENTENCE__CONTEXT, newContext, newContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE:
				return basicSetScenarioSentence(null, msgs);
			case TslPackage.TEST_CASE_SENTENCE__CONTEXT:
				return basicSetContext(null, msgs);
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
			case TslPackage.TEST_CASE_SENTENCE__EREFERENCE0:
				if (resolve) return getEReference0();
				return basicGetEReference0();
			case TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE:
				return getScenarioSentence();
			case TslPackage.TEST_CASE_SENTENCE__NAME:
				return getName();
			case TslPackage.TEST_CASE_SENTENCE__CONTEXT:
				return getContext();
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
			case TslPackage.TEST_CASE_SENTENCE__EREFERENCE0:
				setEReference0((UseCaseTestScenarioSentence)newValue);
				return;
			case TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE:
				setScenarioSentence((ScenarioSentence)newValue);
				return;
			case TslPackage.TEST_CASE_SENTENCE__NAME:
				setName((String)newValue);
				return;
			case TslPackage.TEST_CASE_SENTENCE__CONTEXT:
				setContext((TestDataValuesContext)newValue);
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
			case TslPackage.TEST_CASE_SENTENCE__EREFERENCE0:
				setEReference0((UseCaseTestScenarioSentence)null);
				return;
			case TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE:
				setScenarioSentence((ScenarioSentence)null);
				return;
			case TslPackage.TEST_CASE_SENTENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TslPackage.TEST_CASE_SENTENCE__CONTEXT:
				setContext((TestDataValuesContext)null);
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
			case TslPackage.TEST_CASE_SENTENCE__EREFERENCE0:
				return eReference0 != null;
			case TslPackage.TEST_CASE_SENTENCE__SCENARIO_SENTENCE:
				return scenarioSentence != null;
			case TslPackage.TEST_CASE_SENTENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TslPackage.TEST_CASE_SENTENCE__CONTEXT:
				return context != null;
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

} //TestCaseSentenceImpl
