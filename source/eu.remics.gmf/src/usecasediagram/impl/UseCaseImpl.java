/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package usecasediagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import usecasediagram.UseCase;
import usecasediagram.UsecasediagramPackage;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.RecoveryModelHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Case</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link usecasediagram.impl.UseCaseImpl#getName <em>Name</em>}</li>
 *   <li>{@link usecasediagram.impl.UseCaseImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UseCaseImpl extends EObjectImpl implements UseCase {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated NOT
	 * @ordered
	 */
	public static final String NAME_EDEFAULT = "UseCase";	
	
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseCaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsecasediagramPackage.Literals.USE_CASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource()))) return null;
		UseCaseDTO uc = rmh.getBussinessLayerFacade().getUseCaseByVertexID(id);
		return null!=uc?uc.getName():null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		if (null==newName || newName.isEmpty()) return;
		RecoveryModelHelper rmh = RecoveryModelHelper.instance(eResource());
		String oldName = rmh.getBussinessLayerFacade().getUseCaseByVertexID(id).getName();
		UseCaseDTO uc = rmh.getBussinessLayerFacade().getUseCaseByVertexID(id);
		if(uc.isNameUnique(newName) || newName.equals(oldName)){
			uc.setName(newName);
			if (!uc.getConstrainedLanguageScenarioDTOList().isEmpty() && null!=uc.getConstrainedLanguageScenarioDTOList().get(0) && (null==uc.getConstrainedLanguageScenarioDTOList().get(0).getName() && null==oldName || uc.getConstrainedLanguageScenarioDTOList().get(0).getName().equals(oldName))){
				for(Stereotype stereotype : ((RSLUseCase) uc).getStereotypeList()){
					if(stereotype.getName().equals("temporary")){
						uc.getConstrainedLanguageScenarioDTOList().get(0).setName(newName);
						break;
					}
				}
			}
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, UsecasediagramPackage.USE_CASE__NAME, oldName, newName));
		}
		else {
			MessageDialog.openError(Display.getCurrent().getActiveShell(),
					"Identical names", "UseCase with the same name already exists! Please rename it.");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setId(int newId) {
		if (id!=ID_EDEFAULT) return;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsecasediagramPackage.USE_CASE__ID, ID_EDEFAULT, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsecasediagramPackage.USE_CASE__NAME:
				return getName();
			case UsecasediagramPackage.USE_CASE__ID:
				return getId();
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
			case UsecasediagramPackage.USE_CASE__NAME:
				setName((String)newValue);
				return;
			case UsecasediagramPackage.USE_CASE__ID:
				setId((Integer)newValue);
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
			case UsecasediagramPackage.USE_CASE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UsecasediagramPackage.USE_CASE__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UsecasediagramPackage.USE_CASE__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case UsecasediagramPackage.USE_CASE__ID:
				return id != ID_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		RecoveryModelHelper rmh = RecoveryModelHelper.instance(eResource());
		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(rmh.getBussinessLayerFacade().getUseCaseByVertexID(id).getName());
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //UseCaseImpl
