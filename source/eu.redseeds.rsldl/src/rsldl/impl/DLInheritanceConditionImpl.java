/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import rsldl.DLFeatureType;
import rsldl.DLInheritanceCondition;
import rsldl.DLNotion;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Inheritance Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLInheritanceConditionImpl#getParents <em>Parents</em>}</li>
 *   <li>{@link rsldl.impl.DLInheritanceConditionImpl#getFeatureType <em>Feature Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLInheritanceConditionImpl extends DLConditionImpl implements DLInheritanceCondition {
	/**
	 * The cached value of the '{@link #getParents() <em>Parents</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParents()
	 * @generated
	 * @ordered
	 */
	protected EList<DLNotion> parents;

	/**
	 * The default value of the '{@link #getFeatureType() <em>Feature Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureType()
	 * @generated
	 * @ordered
	 */
	protected static final DLFeatureType FEATURE_TYPE_EDEFAULT = DLFeatureType.PROVIDED;
	/**
	 * The cached value of the '{@link #getFeatureType() <em>Feature Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureType()
	 * @generated
	 * @ordered
	 */
	protected DLFeatureType featureType = FEATURE_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLInheritanceConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText() {
		String text = "";
		boolean first = true;
		for (DLNotion p:getParents()) {
			if (first) {
				text+=p.getName();
				first = false;
			} else 
				text+=" || "+p.getName();
		}	
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_INHERITANCE_CONDITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLNotion> getParents() {
		if (parents == null) {
			parents = new EObjectResolvingEList<DLNotion>(DLNotion.class, this, RsldlPackage.DL_INHERITANCE_CONDITION__PARENTS);
		}
		return parents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLFeatureType getFeatureType() {
		return featureType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeatureType(DLFeatureType newFeatureType) {
		DLFeatureType oldFeatureType = featureType;
		featureType = newFeatureType == null ? FEATURE_TYPE_EDEFAULT : newFeatureType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_INHERITANCE_CONDITION__FEATURE_TYPE, oldFeatureType, featureType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RsldlPackage.DL_INHERITANCE_CONDITION__PARENTS:
				return getParents();
			case RsldlPackage.DL_INHERITANCE_CONDITION__FEATURE_TYPE:
				return getFeatureType();
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
			case RsldlPackage.DL_INHERITANCE_CONDITION__PARENTS:
				getParents().clear();
				getParents().addAll((Collection<? extends DLNotion>)newValue);
				return;
			case RsldlPackage.DL_INHERITANCE_CONDITION__FEATURE_TYPE:
				setFeatureType((DLFeatureType)newValue);
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
			case RsldlPackage.DL_INHERITANCE_CONDITION__PARENTS:
				getParents().clear();
				return;
			case RsldlPackage.DL_INHERITANCE_CONDITION__FEATURE_TYPE:
				setFeatureType(FEATURE_TYPE_EDEFAULT);
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
			case RsldlPackage.DL_INHERITANCE_CONDITION__PARENTS:
				return parents != null && !parents.isEmpty();
			case RsldlPackage.DL_INHERITANCE_CONDITION__FEATURE_TYPE:
				return featureType != FEATURE_TYPE_EDEFAULT;
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
		result.append(" (featureType: ");
		result.append(featureType);
		result.append(')');
		return result.toString();
	}

} //DLInheritanceConditionImpl
