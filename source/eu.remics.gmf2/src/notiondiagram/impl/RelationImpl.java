/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram.impl;

import notiondiagram.Notion;
import notiondiagram.NotiondiagramPackage;
import notiondiagram.Relation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.preferences.MConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link notiondiagram.impl.RelationImpl#getTargetMultiplicity <em>Target Multiplicity</em>}</li>
 *   <li>{@link notiondiagram.impl.RelationImpl#getSourceMultiplicity <em>Source Multiplicity</em>}</li>
 *   <li>{@link notiondiagram.impl.RelationImpl#getId <em>Id</em>}</li>
 *   <li>{@link notiondiagram.impl.RelationImpl#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class RelationImpl extends AbstractRelationImpl implements Relation {
	/**
	 * The default value of the '{@link #getTargetMultiplicity() <em>Target Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_MULTIPLICITY_EDEFAULT = "1";

	/**
	 * The default value of the '{@link #getSourceMultiplicity() <em>Source Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_MULTIPLICITY_EDEFAULT = "1";

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
	 * The default value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected static final String STEREOTYPE_EDEFAULT = "";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotiondiagramPackage.Literals.RELATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getTargetMultiplicity() {
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource()))) return null;
		DomainElementRelationshipDTO der = rmh.getDomainElementRelationshipByVertexID(id);
		return null!=der?der.getSourceMultiplicity():null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setTargetMultiplicity(String newTargetMultiplicity) {
		String oldTargetMultiplicity = getTargetMultiplicity();
		RecoveryModelHelper rmh = null;
		if (null!=eResource() && null!=(rmh=RecoveryModelHelper.instance(eResource())) && (!MConfiguration.isCheckRelations() || rmh.getNotionByVertexID(source.getId()).getType().isEmpty() && rmh.getNotionByVertexID(target.getId()).getType().isEmpty())) rmh.getDomainElementRelationshipByVertexID(id).setSourceMultiplicity(newTargetMultiplicity);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.RELATION__TARGET_MULTIPLICITY, oldTargetMultiplicity, newTargetMultiplicity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getSourceMultiplicity() {
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource()))) return null;
		DomainElementRelationshipDTO der = rmh.getDomainElementRelationshipByVertexID(id);
		return null!=der?der.getTargetMultiplicity():null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSourceMultiplicity(String newSourceMultiplicity) {
		String oldSourceMultiplicity = getSourceMultiplicity();
		RecoveryModelHelper rmh = null;
		if (null!=eResource() && null!=(rmh=RecoveryModelHelper.instance(eResource())) && (!MConfiguration.isCheckRelations() || rmh.getNotionByVertexID(source.getId()).getType().isEmpty() && rmh.getNotionByVertexID(target.getId()).getType().isEmpty())) rmh.getDomainElementRelationshipByVertexID(id).setTargetMultiplicity(newSourceMultiplicity);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.RELATION__SOURCE_MULTIPLICITY, oldSourceMultiplicity, newSourceMultiplicity));
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
			eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.RELATION__ID, ID_EDEFAULT, id));
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getStereotype() {
		if (!MConfiguration.isCheckRelations()) return "";
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource()))) return null;
		DomainElementRelationshipDTO der = rmh.getDomainElementRelationshipByVertexID(id);
		return MNotion.getStereotype(der);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSource(Notion newSource) {
		Notion oldSource = source;
		RecoveryModelHelper rmh = null;
		if (null!=source && null!=newSource && null!=eResource() && null!=(rmh=RecoveryModelHelper.instance(eResource()))){
			((DomainElementRelationship) rmh
					.getDomainElementRelationshipByVertexID(getId()))
					.removeSource((DomainElement) ((DomainElementRelationship) rmh.getDomainElementRelationshipByVertexID(
									getId())).getSourceList().get(0));
			rmh
					.getDomainElementRelationshipByVertexID(getId())
					.setSource(
							rmh.getNotionByVertexID(
									newSource.getId()));
		}
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.ABSTRACT_RELATION__SOURCE, oldSource, source));
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setTarget(Notion newTarget) {
		Notion oldTarget = target;
		RecoveryModelHelper rmh = null;
		if (null!=target && null!=newTarget && null!=eResource() && null!=(rmh=RecoveryModelHelper.instance(eResource()))){
			((DomainElementRelationship) rmh
					.getDomainElementRelationshipByVertexID(getId()))
					.removeTarget((DomainElement) ((DomainElementRelationship) rmh.getDomainElementRelationshipByVertexID(
									getId())).getTargetList().get(0));
			rmh
					.getDomainElementRelationshipByVertexID(getId())
					.setTarget(
							rmh.getNotionByVertexID(
									newTarget.getId()));
			
			
		}
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.ABSTRACT_RELATION__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotiondiagramPackage.RELATION__TARGET_MULTIPLICITY:
				return getTargetMultiplicity();
			case NotiondiagramPackage.RELATION__SOURCE_MULTIPLICITY:
				return getSourceMultiplicity();
			case NotiondiagramPackage.RELATION__ID:
				return getId();
			case NotiondiagramPackage.RELATION__STEREOTYPE:
				return getStereotype();
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
			case NotiondiagramPackage.RELATION__TARGET_MULTIPLICITY:
				setTargetMultiplicity((String)newValue);
				return;
			case NotiondiagramPackage.RELATION__SOURCE_MULTIPLICITY:
				setSourceMultiplicity((String)newValue);
				return;
			case NotiondiagramPackage.RELATION__ID:
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
			case NotiondiagramPackage.RELATION__TARGET_MULTIPLICITY:
				setTargetMultiplicity(TARGET_MULTIPLICITY_EDEFAULT);
				return;
			case NotiondiagramPackage.RELATION__SOURCE_MULTIPLICITY:
				setSourceMultiplicity(SOURCE_MULTIPLICITY_EDEFAULT);
				return;
			case NotiondiagramPackage.RELATION__ID:
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
			case NotiondiagramPackage.RELATION__TARGET_MULTIPLICITY:
				return TARGET_MULTIPLICITY_EDEFAULT == null ? getTargetMultiplicity() != null : !TARGET_MULTIPLICITY_EDEFAULT.equals(getTargetMultiplicity());
			case NotiondiagramPackage.RELATION__SOURCE_MULTIPLICITY:
				return SOURCE_MULTIPLICITY_EDEFAULT == null ? getSourceMultiplicity() != null : !SOURCE_MULTIPLICITY_EDEFAULT.equals(getSourceMultiplicity());
			case NotiondiagramPackage.RELATION__ID:
				return id != ID_EDEFAULT;
			case NotiondiagramPackage.RELATION__STEREOTYPE:
				return STEREOTYPE_EDEFAULT == null ? getStereotype() != null : !STEREOTYPE_EDEFAULT.equals(getStereotype());
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
		result.append(" (TargetMultiplicity: ");
		result.append(getTargetMultiplicity());
		result.append(", SourceMultiplicity: ");
		result.append(getSourceMultiplicity());
		result.append(", id: ");
		result.append(id);
		result.append(", Stereotype: ");
		result.append(getStereotype());
		result.append(')');
		return result.toString();
	}

} //RelationImpl
