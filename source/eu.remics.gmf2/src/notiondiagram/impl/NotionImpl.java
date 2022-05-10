/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import notiondiagram.Notion;
import notiondiagram.NotiondiagramPackage;
import notiondiagram.Phrase;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.preferences.MConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Notion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link notiondiagram.impl.NotionImpl#getPhrases <em>Phrases</em>}</li>
 *   <li>{@link notiondiagram.impl.NotionImpl#getName <em>Name</em>}</li>
 *   <li>{@link notiondiagram.impl.NotionImpl#getId <em>Id</em>}</li>
 *   <li>{@link notiondiagram.impl.NotionImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NotionImpl extends EObjectImpl implements Notion {
	/**
	 * The cached value of the '{@link #getPhrases() <em>Phrases</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhrases()
	 * @generated
	 * @ordered
	 */
	protected EList<Phrase> phrases;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated NOT
	 * @ordered
	 */
	public static final String NAME_EDEFAULT = "notion";

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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NotionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotiondiagramPackage.Literals.NOTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Phrase> getPhrases() {
		if (phrases == null){
			phrases = new EObjectContainmentEList<Phrase>(Phrase.class, this, NotiondiagramPackage.NOTION__PHRASES);
		}
		if(MConfiguration.isHidePhrasesOnDiagrams()) return phrases;
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return phrases;
		for(int i=0; i<phrases.size(); i++){
			if(((PhraseImpl) phrases.get(i)).eIsSet(NotiondiagramPackage.PHRASE__ID) && null == rmh.getDomainStatementByVertexID(phrases.get(i).getId())){
				final int j = i;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(phrases.get(i));
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							phrases.remove(j);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
					i--;
				}
			}
		}
		Phrase phrase;
		if(null!=rmh.getNotionByVertexID(id)) for(DomainStatementDTO ds:rmh.getNotionByVertexID(id).getDomainStatementDTOList()){
			phrase=new PhraseImpl();
			phrase.setId(((DomainStatement)ds).getId());
			boolean contains = false;
			for (Phrase p: phrases) if (p.getId()==phrase.getId()){
				contains =true;
				break;
			}
			if(!contains){
				final Phrase p = phrase;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							phrases.add(p);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
				}
			}
		}
		return phrases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource()))) return null;
		NotionDTO not = rmh.getNotionByVertexID(id);
		return null!=not?not.getName():null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		if (null==newName || newName.replace('_', ' ').trim().isEmpty()) return;
		RecoveryModelHelper rmh = RecoveryModelHelper.instance(eResource());
		String oldName = rmh.getNotionByVertexID(id).getName();
		if(rmh.getNotionByVertexID(id).isNameUnique(newName.replace('_', ' ').trim()) || newName.equals(oldName.replace('_', ' ').trim())){
			if (rmh.getNotionByVertexID(id).getNonBasicNounLinksValues().size()>0){
				if(!MessageDialog.openQuestion(Display.getCurrent().getActiveShell(),
						"Non-basic forms", "Notion has a non-basics name forms. Renaming notion will its convert non-basic forms into a basic form. Do you want to continue?")) return;
			}
			NounDTO noun=rmh.getNotionByVertexID(id).getNamePhrase().getNoun();
			noun.setName(newName);
			if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag() && null!=newName && !newName.isEmpty()){
				try {
					noun.autoAddAndAssignSense();
				} catch (NullPointerException e){
					e.printStackTrace();
				}
			} else noun.autoAssignSense();
			for(NounPhrase np:rmh.getBussinessLayerFacade().getNounPhraseVertices()) 
				if (null!=((NounPhraseDTO) np).getNoun() && ((Noun) ((NounPhraseDTO) np).getNoun()).getId()==((Noun) noun).getId()){
					((NounPhraseDTO) np).setNoun(noun);
					((NounPhraseDTO) np).setNounText(noun.getName());
			}
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.NOTION__NAME, oldName, newName));
			
			Method method = null;
			try {
				method = SCProjectHelper.getDiagramRefreshHelper().getClass().getMethod("refreshPhrases", (Class<?>[])null);
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			Object classInvokedOn = SCProjectHelper.getDiagramRefreshHelper();
			try {
				method.invoke(classInvokedOn, (Object[])null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		else{
			MessageDialog.openError(Display.getCurrent().getActiveShell(),
					"Identical names", "Notion with the same name already exists! Please rename it.");
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
			eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.NOTION__ID, ID_EDEFAULT, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getType() {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance(eResource());
		if (null==rmh) return null;
		NotionDTO not = rmh.getNotionByVertexID(id);
		String kind=NotionTypesEnum.EMPTY;
		loop:
		for (Stereotype s:((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) not).getStereotypeList()) if (Arrays.asList(NotionTypesEnum.tags()).contains(s.getName())){
			for (NotionTypesEnum val:NotionTypesEnum.values())
				if (val.tag().equals(s.getName())){
					kind = val.toString().replace('_', ' ');
					if (s.getName().equals(NotionTypesEnum.Attribute.tag()) && ! not.getExtendedDataType().isEmpty())
						kind +=" ("+AttributeDataTypesEnum.getExtendedDataTypeToDisplay(not)+")";
					break loop;
				}
		}
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NotiondiagramPackage.NOTION__PHRASES:
				return ((InternalEList<?>)getPhrases()).basicRemove(otherEnd, msgs);
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
			case NotiondiagramPackage.NOTION__PHRASES:
				return getPhrases();
			case NotiondiagramPackage.NOTION__NAME:
				return getName();
			case NotiondiagramPackage.NOTION__ID:
				return getId();
			case NotiondiagramPackage.NOTION__TYPE:
				return getType();
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
			case NotiondiagramPackage.NOTION__PHRASES:
				getPhrases().clear();
				getPhrases().addAll((Collection<? extends Phrase>)newValue);
				return;
			case NotiondiagramPackage.NOTION__NAME:
				setName((String)newValue);
				return;
			case NotiondiagramPackage.NOTION__ID:
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
			case NotiondiagramPackage.NOTION__PHRASES:
				getPhrases().clear();
				return;
			case NotiondiagramPackage.NOTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case NotiondiagramPackage.NOTION__ID:
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
			case NotiondiagramPackage.NOTION__PHRASES:
				return !getPhrases().isEmpty();
			case NotiondiagramPackage.NOTION__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case NotiondiagramPackage.NOTION__ID:
				return id != ID_EDEFAULT;
			case NotiondiagramPackage.NOTION__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Name: ");
		result.append(getName());
		result.append(", Id: ");
		result.append(id);
		result.append(", Type: ");
		result.append(getType());
		result.append(')');
		return result.toString();
	}

} //NotionImpl
