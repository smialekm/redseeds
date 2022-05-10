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

import usecasediagram.Actor;
import usecasediagram.UsecasediagramPackage;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.remics.recovery.model.RecoveryModelHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link usecasediagram.impl.ActorImpl#getName <em>Name</em>}</li>
 *   <li>{@link usecasediagram.impl.ActorImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorImpl extends EObjectImpl implements Actor {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated NOT
	 * @ordered
	 */
	public static final String NAME_EDEFAULT = "actor";
	
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
	protected ActorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsecasediagramPackage.Literals.ACTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource()))) return null;
		ActorDTO act = rmh.getActorByVertexID(id);
		return null!=act?act.getName():null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		if (null== newName || newName.replace('_', ' ').trim().isEmpty()) return;
		RecoveryModelHelper rmh = RecoveryModelHelper.instance(eResource());
		String oldName = rmh.getActorByVertexID(id).getName();
		if(rmh.getActorByVertexID(id).isNameUnique(newName.replace('_', ' ').trim()) || newName.equals(oldName.replace('_', ' ').trim())){
			if (rmh.getActorByVertexID(id).getNonBasicNounLinksValues().size()>0){
				if(!MessageDialog.openQuestion(Display.getCurrent().getActiveShell(),
						"Non-basic forms", "Actor has a non-basic name forms. Renaming actor will convert its non-basic forms into a basic form. Do you want to continue?")) return;
			}
			NounDTO noun=rmh.getActorByVertexID(id).getNounPhrase().getNoun();
			noun.setName(newName);
			if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag() && null!=newName && !newName.isEmpty()){
				try {
					noun.autoAddAndAssignSense();
				} catch (NullPointerException e){
					e.printStackTrace();
				}
			} else noun.autoAssignSense();
			for(NounPhrase np:rmh.getBussinessLayerFacade().getNounPhraseVertices()) if (null!=((NounPhraseDTO) np).getNoun() && ((Noun) ((NounPhraseDTO) np).getNoun()).getId()==((Noun) noun).getId()){
				((NounPhraseDTO) np).setNoun(noun);
				((NounPhraseDTO) np).setNounText(noun.getName());
			}
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, UsecasediagramPackage.ACTOR__NAME, oldName, newName));
		}
		else{
			MessageDialog.openError(Display.getCurrent().getActiveShell(),
					"Identical names", "Actor with the same name already exists on the diagram! Please rename it.");
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsecasediagramPackage.ACTOR__ID, ID_EDEFAULT, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsecasediagramPackage.ACTOR__NAME:
				return getName();
			case UsecasediagramPackage.ACTOR__ID:
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
			case UsecasediagramPackage.ACTOR__NAME:
				setName((String)newValue);
				return;
			case UsecasediagramPackage.ACTOR__ID:
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
			case UsecasediagramPackage.ACTOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UsecasediagramPackage.ACTOR__ID:
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
			case UsecasediagramPackage.ACTOR__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case UsecasediagramPackage.ACTOR__ID:
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
		result.append(rmh.getActorByVertexID(id).getName());
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ActorImpl
