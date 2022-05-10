/**
 */
package rsldl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import rsldl.DLControlFlowStatement;
import rsldl.DLControlFlowStatementType;
import rsldl.DLNamedLink;
import rsldl.DLReferencingElement;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Control Flow Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLControlFlowStatementImpl#getSubjectLink <em>Subject Link</em>}</li>
 *   <li>{@link rsldl.impl.DLControlFlowStatementImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLControlFlowStatementImpl extends DLAlghoritmicTransitionSequenceElementImpl implements DLControlFlowStatement {
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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final DLControlFlowStatementType TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected DLControlFlowStatementType type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLControlFlowStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_CONTROL_FLOW_STATEMENT;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RsldlPackage.DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK, oldSubjectLink, subjectLink));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK, oldSubjectLink, subjectLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLControlFlowStatementType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(DLControlFlowStatementType newType) {
		DLControlFlowStatementType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_CONTROL_FLOW_STATEMENT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK:
				if (resolve) return getSubjectLink();
				return basicGetSubjectLink();
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__TYPE:
				return getType();
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
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK:
				setSubjectLink((DLNamedLink)newValue);
				return;
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__TYPE:
				setType((DLControlFlowStatementType)newValue);
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
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK:
				setSubjectLink((DLNamedLink)null);
				return;
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__TYPE:
				setType(TYPE_EDEFAULT);
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
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK:
				return subjectLink != null;
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__TYPE:
				return type != TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == DLReferencingElement.class) {
			switch (derivedFeatureID) {
				case RsldlPackage.DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK: return RsldlPackage.DL_REFERENCING_ELEMENT__SUBJECT_LINK;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == DLReferencingElement.class) {
			switch (baseFeatureID) {
				case RsldlPackage.DL_REFERENCING_ELEMENT__SUBJECT_LINK: return RsldlPackage.DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //DLControlFlowStatementImpl
