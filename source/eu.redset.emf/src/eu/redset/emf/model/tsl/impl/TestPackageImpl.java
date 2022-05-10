/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.NonFunctionalTest;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TslPackage;

import eu.redset.emf.model.tsl.UseCaseTest;
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
 * An implementation of the model object '<em><b>Test Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestPackageImpl#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestPackageImpl#getEReference1 <em>EReference1</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestPackageImpl#getEReference3 <em>EReference3</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestPackageImpl#getNotions <em>Notions</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestPackageImpl#getNFTests <em>NF Tests</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestPackageImpl#getGUITests <em>GUI Tests</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestPackageImpl#getBLTests <em>BL Tests</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.TestPackageImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestPackageImpl extends EObjectImpl implements TestPackage {
	/**
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected EList<TestPackage> eReference0;

	/**
	 * The cached value of the '{@link #getEReference1() <em>EReference1</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference1()
	 * @generated
	 * @ordered
	 */
	protected EList<UseCaseTest> eReference1;

	/**
	 * The cached value of the '{@link #getEReference3() <em>EReference3</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference3()
	 * @generated
	 * @ordered
	 */
	protected EList<TestScenario> eReference3;

	/**
	 * The cached value of the '{@link #getNotions() <em>Notions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotions()
	 * @generated
	 * @ordered
	 */
	protected EList<Notion> notions;

	/**
	 * The cached value of the '{@link #getNFTests() <em>NF Tests</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNFTests()
	 * @generated
	 * @ordered
	 */
	protected EList<NFTest> nfTests;

	/**
	 * The cached value of the '{@link #getGUITests() <em>GUI Tests</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGUITests()
	 * @generated
	 * @ordered
	 */
	protected EList<GUITest> guiTests;

	/**
	 * The cached value of the '{@link #getBLTests() <em>BL Tests</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBLTests()
	 * @generated
	 * @ordered
	 */
	protected EList<BLTest> blTests;

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
	protected TestPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.TEST_PACKAGE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.TEST_PACKAGE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Notion> getNotions() {
		if (notions == null) {
			notions = new EObjectContainmentEList<Notion>(Notion.class, this, TslPackage.TEST_PACKAGE__NOTIONS);
		}
		return notions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NFTest> getNFTests() {
		if (nfTests == null) {
			nfTests = new EObjectContainmentEList<NFTest>(NFTest.class, this, TslPackage.TEST_PACKAGE__NF_TESTS);
		}
		return nfTests;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GUITest> getGUITests() {
		if (guiTests == null) {
			guiTests = new EObjectContainmentEList<GUITest>(GUITest.class, this, TslPackage.TEST_PACKAGE__GUI_TESTS);
		}
		return guiTests;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BLTest> getBLTests() {
		if (blTests == null) {
			blTests = new EObjectContainmentEList<BLTest>(BLTest.class, this, TslPackage.TEST_PACKAGE__BL_TESTS);
		}
		return blTests;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestScenario> getEReference3() {
		if (eReference3 == null) {
			eReference3 = new EObjectContainmentEList<TestScenario>(TestScenario.class, this, TslPackage.TEST_PACKAGE__EREFERENCE3);
		}
		return eReference3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestPackage> getEReference0() {
		if (eReference0 == null) {
			eReference0 = new EObjectContainmentEList<TestPackage>(TestPackage.class, this, TslPackage.TEST_PACKAGE__EREFERENCE0);
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UseCaseTest> getEReference1() {
		if (eReference1 == null) {
			eReference1 = new EObjectContainmentEList<UseCaseTest>(UseCaseTest.class, this, TslPackage.TEST_PACKAGE__EREFERENCE1);
		}
		return eReference1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.TEST_PACKAGE__EREFERENCE0:
				return ((InternalEList<?>)getEReference0()).basicRemove(otherEnd, msgs);
			case TslPackage.TEST_PACKAGE__EREFERENCE1:
				return ((InternalEList<?>)getEReference1()).basicRemove(otherEnd, msgs);
			case TslPackage.TEST_PACKAGE__EREFERENCE3:
				return ((InternalEList<?>)getEReference3()).basicRemove(otherEnd, msgs);
			case TslPackage.TEST_PACKAGE__NOTIONS:
				return ((InternalEList<?>)getNotions()).basicRemove(otherEnd, msgs);
			case TslPackage.TEST_PACKAGE__NF_TESTS:
				return ((InternalEList<?>)getNFTests()).basicRemove(otherEnd, msgs);
			case TslPackage.TEST_PACKAGE__GUI_TESTS:
				return ((InternalEList<?>)getGUITests()).basicRemove(otherEnd, msgs);
			case TslPackage.TEST_PACKAGE__BL_TESTS:
				return ((InternalEList<?>)getBLTests()).basicRemove(otherEnd, msgs);
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
			case TslPackage.TEST_PACKAGE__EREFERENCE0:
				return getEReference0();
			case TslPackage.TEST_PACKAGE__EREFERENCE1:
				return getEReference1();
			case TslPackage.TEST_PACKAGE__EREFERENCE3:
				return getEReference3();
			case TslPackage.TEST_PACKAGE__NOTIONS:
				return getNotions();
			case TslPackage.TEST_PACKAGE__NF_TESTS:
				return getNFTests();
			case TslPackage.TEST_PACKAGE__GUI_TESTS:
				return getGUITests();
			case TslPackage.TEST_PACKAGE__BL_TESTS:
				return getBLTests();
			case TslPackage.TEST_PACKAGE__NAME:
				return getName();
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
			case TslPackage.TEST_PACKAGE__EREFERENCE0:
				getEReference0().clear();
				getEReference0().addAll((Collection<? extends TestPackage>)newValue);
				return;
			case TslPackage.TEST_PACKAGE__EREFERENCE1:
				getEReference1().clear();
				getEReference1().addAll((Collection<? extends UseCaseTest>)newValue);
				return;
			case TslPackage.TEST_PACKAGE__EREFERENCE3:
				getEReference3().clear();
				getEReference3().addAll((Collection<? extends TestScenario>)newValue);
				return;
			case TslPackage.TEST_PACKAGE__NOTIONS:
				getNotions().clear();
				getNotions().addAll((Collection<? extends Notion>)newValue);
				return;
			case TslPackage.TEST_PACKAGE__NF_TESTS:
				getNFTests().clear();
				getNFTests().addAll((Collection<? extends NFTest>)newValue);
				return;
			case TslPackage.TEST_PACKAGE__GUI_TESTS:
				getGUITests().clear();
				getGUITests().addAll((Collection<? extends GUITest>)newValue);
				return;
			case TslPackage.TEST_PACKAGE__BL_TESTS:
				getBLTests().clear();
				getBLTests().addAll((Collection<? extends BLTest>)newValue);
				return;
			case TslPackage.TEST_PACKAGE__NAME:
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
			case TslPackage.TEST_PACKAGE__EREFERENCE0:
				getEReference0().clear();
				return;
			case TslPackage.TEST_PACKAGE__EREFERENCE1:
				getEReference1().clear();
				return;
			case TslPackage.TEST_PACKAGE__EREFERENCE3:
				getEReference3().clear();
				return;
			case TslPackage.TEST_PACKAGE__NOTIONS:
				getNotions().clear();
				return;
			case TslPackage.TEST_PACKAGE__NF_TESTS:
				getNFTests().clear();
				return;
			case TslPackage.TEST_PACKAGE__GUI_TESTS:
				getGUITests().clear();
				return;
			case TslPackage.TEST_PACKAGE__BL_TESTS:
				getBLTests().clear();
				return;
			case TslPackage.TEST_PACKAGE__NAME:
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
			case TslPackage.TEST_PACKAGE__EREFERENCE0:
				return eReference0 != null && !eReference0.isEmpty();
			case TslPackage.TEST_PACKAGE__EREFERENCE1:
				return eReference1 != null && !eReference1.isEmpty();
			case TslPackage.TEST_PACKAGE__EREFERENCE3:
				return eReference3 != null && !eReference3.isEmpty();
			case TslPackage.TEST_PACKAGE__NOTIONS:
				return notions != null && !notions.isEmpty();
			case TslPackage.TEST_PACKAGE__NF_TESTS:
				return nfTests != null && !nfTests.isEmpty();
			case TslPackage.TEST_PACKAGE__GUI_TESTS:
				return guiTests != null && !guiTests.isEmpty();
			case TslPackage.TEST_PACKAGE__BL_TESTS:
				return blTests != null && !blTests.isEmpty();
			case TslPackage.TEST_PACKAGE__NAME:
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

} //TestPackageImpl
