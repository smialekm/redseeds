/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestParameter;
import eu.redset.emf.model.tsl.TestRelationship;
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
 * An implementation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getType <em>Type</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getClassifierId <em>Classifier Id</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getContent <em>Content</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getTestPrecondition <em>Test Precondition</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getTestPostcondition <em>Test Postcondition</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getTestResult <em>Test Result</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getSource <em>Source</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestImpl#getTestParameters <em>Test Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestImpl extends EObjectImpl implements Test {
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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassifierId() <em>Classifier Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifierId()
	 * @generated
	 * @ordered
	 */
	protected static final Long CLASSIFIER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassifierId() <em>Classifier Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifierId()
	 * @generated
	 * @ordered
	 */
	protected Long classifierId = CLASSIFIER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected String content = CONTENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestPrecondition() <em>Test Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestPrecondition()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_PRECONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestPrecondition() <em>Test Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestPrecondition()
	 * @generated
	 * @ordered
	 */
	protected String testPrecondition = TEST_PRECONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestPostcondition() <em>Test Postcondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestPostcondition()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_POSTCONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestPostcondition() <em>Test Postcondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestPostcondition()
	 * @generated
	 * @ordered
	 */
	protected String testPostcondition = TEST_POSTCONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestResult() <em>Test Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestResult()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_RESULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestResult() <em>Test Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestResult()
	 * @generated
	 * @ordered
	 */
	protected String testResult = TEST_RESULT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected EList<TestRelationship> source;

	/**
	 * The cached value of the '{@link #getTestParameters() <em>Test Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<TestParameter> testParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.TEST;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getClassifierId() {
		return classifierId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassifierId(Long newClassifierId) {
		Long oldClassifierId = classifierId;
		classifierId = newClassifierId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST__CLASSIFIER_ID, oldClassifierId, classifierId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContent(String newContent) {
		String oldContent = content;
		content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST__CONTENT, oldContent, content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTestPrecondition() {
		return testPrecondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestPrecondition(String newTestPrecondition) {
		String oldTestPrecondition = testPrecondition;
		testPrecondition = newTestPrecondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST__TEST_PRECONDITION, oldTestPrecondition, testPrecondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTestPostcondition() {
		return testPostcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestPostcondition(String newTestPostcondition) {
		String oldTestPostcondition = testPostcondition;
		testPostcondition = newTestPostcondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST__TEST_POSTCONDITION, oldTestPostcondition, testPostcondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTestResult() {
		return testResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestResult(String newTestResult) {
		String oldTestResult = testResult;
		testResult = newTestResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST__TEST_RESULT, oldTestResult, testResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestRelationship> getSource() {
		if (source == null) {
			source = new EObjectContainmentEList<TestRelationship>(TestRelationship.class, this, TslPackage.TEST__SOURCE);
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestParameter> getTestParameters() {
		if (testParameters == null) {
			testParameters = new EObjectContainmentEList<TestParameter>(TestParameter.class, this, TslPackage.TEST__TEST_PARAMETERS);
		}
		return testParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.TEST__SOURCE:
				return ((InternalEList<?>)getSource()).basicRemove(otherEnd, msgs);
			case TslPackage.TEST__TEST_PARAMETERS:
				return ((InternalEList<?>)getTestParameters()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TslPackage.TEST__NAME:
				return getName();
			case TslPackage.TEST__TYPE:
				return getType();
			case TslPackage.TEST__DESCRIPTION:
				return getDescription();
			case TslPackage.TEST__CLASSIFIER_ID:
				return getClassifierId();
			case TslPackage.TEST__CONTENT:
				return getContent();
			case TslPackage.TEST__TEST_PRECONDITION:
				return getTestPrecondition();
			case TslPackage.TEST__TEST_POSTCONDITION:
				return getTestPostcondition();
			case TslPackage.TEST__TEST_RESULT:
				return getTestResult();
			case TslPackage.TEST__SOURCE:
				return getSource();
			case TslPackage.TEST__TEST_PARAMETERS:
				return getTestParameters();
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
			case TslPackage.TEST__NAME:
				setName((String)newValue);
				return;
			case TslPackage.TEST__TYPE:
				setType((String)newValue);
				return;
			case TslPackage.TEST__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case TslPackage.TEST__CLASSIFIER_ID:
				setClassifierId((Long)newValue);
				return;
			case TslPackage.TEST__CONTENT:
				setContent((String)newValue);
				return;
			case TslPackage.TEST__TEST_PRECONDITION:
				setTestPrecondition((String)newValue);
				return;
			case TslPackage.TEST__TEST_POSTCONDITION:
				setTestPostcondition((String)newValue);
				return;
			case TslPackage.TEST__TEST_RESULT:
				setTestResult((String)newValue);
				return;
			case TslPackage.TEST__SOURCE:
				getSource().clear();
				getSource().addAll((Collection<? extends TestRelationship>)newValue);
				return;
			case TslPackage.TEST__TEST_PARAMETERS:
				getTestParameters().clear();
				getTestParameters().addAll((Collection<? extends TestParameter>)newValue);
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
			case TslPackage.TEST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TslPackage.TEST__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case TslPackage.TEST__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case TslPackage.TEST__CLASSIFIER_ID:
				setClassifierId(CLASSIFIER_ID_EDEFAULT);
				return;
			case TslPackage.TEST__CONTENT:
				setContent(CONTENT_EDEFAULT);
				return;
			case TslPackage.TEST__TEST_PRECONDITION:
				setTestPrecondition(TEST_PRECONDITION_EDEFAULT);
				return;
			case TslPackage.TEST__TEST_POSTCONDITION:
				setTestPostcondition(TEST_POSTCONDITION_EDEFAULT);
				return;
			case TslPackage.TEST__TEST_RESULT:
				setTestResult(TEST_RESULT_EDEFAULT);
				return;
			case TslPackage.TEST__SOURCE:
				getSource().clear();
				return;
			case TslPackage.TEST__TEST_PARAMETERS:
				getTestParameters().clear();
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
			case TslPackage.TEST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TslPackage.TEST__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case TslPackage.TEST__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case TslPackage.TEST__CLASSIFIER_ID:
				return CLASSIFIER_ID_EDEFAULT == null ? classifierId != null : !CLASSIFIER_ID_EDEFAULT.equals(classifierId);
			case TslPackage.TEST__CONTENT:
				return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
			case TslPackage.TEST__TEST_PRECONDITION:
				return TEST_PRECONDITION_EDEFAULT == null ? testPrecondition != null : !TEST_PRECONDITION_EDEFAULT.equals(testPrecondition);
			case TslPackage.TEST__TEST_POSTCONDITION:
				return TEST_POSTCONDITION_EDEFAULT == null ? testPostcondition != null : !TEST_POSTCONDITION_EDEFAULT.equals(testPostcondition);
			case TslPackage.TEST__TEST_RESULT:
				return TEST_RESULT_EDEFAULT == null ? testResult != null : !TEST_RESULT_EDEFAULT.equals(testResult);
			case TslPackage.TEST__SOURCE:
				return source != null && !source.isEmpty();
			case TslPackage.TEST__TEST_PARAMETERS:
				return testParameters != null && !testParameters.isEmpty();
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
		result.append(", type: ");
		result.append(type);
		result.append(", description: ");
		result.append(description);
		result.append(", classifierId: ");
		result.append(classifierId);
		result.append(", content: ");
		result.append(content);
		result.append(", testPrecondition: ");
		result.append(testPrecondition);
		result.append(", testPostcondition: ");
		result.append(testPostcondition);
		result.append(", testResult: ");
		result.append(testResult);
		result.append(')');
		return result.toString();
	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		
	}

} //TestImpl
