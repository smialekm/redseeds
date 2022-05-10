/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram.impl;

import notiondiagram.AttributeRelation;
import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.NotiondiagramPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.remics.recovery.model.RecoveryModelHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link notiondiagram.impl.AttributeRelationImpl#getSourceId <em>Source Id</em>}</li>
 *   <li>{@link notiondiagram.impl.AttributeRelationImpl#getTargetId <em>Target Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeRelationImpl extends AbstractRelationImpl implements AttributeRelation {
	/**
	 * The default value of the '{@link #getSourceId() <em>Source Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceId()
	 * @generated
	 * @ordered
	 */
	protected static final int SOURCE_ID_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getSourceId() <em>Source Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceId()
	 * @generated
	 * @ordered
	 */
	protected int sourceId = SOURCE_ID_EDEFAULT;
	/**
	 * The default value of the '{@link #getTargetId() <em>Target Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetId()
	 * @generated
	 * @ordered
	 */
	protected static final int TARGET_ID_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getTargetId() <em>Target Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetId()
	 * @generated
	 * @ordered
	 */
	protected int targetId = TARGET_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeRelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotiondiagramPackage.Literals.ATTRIBUTE_RELATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSourceId() {
		return sourceId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTargetId() {
		return targetId;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSource(Notion newSource) {
		Notion oldSource = source;
		RecoveryModelHelper rmh = null;
		if (null != source && null != newSource && null!=eResource() && null!=(rmh=RecoveryModelHelper.instance(eResource()))) {
			if (null!=eContainer() && eContainer() instanceof NotionDiagram){
				boolean find=false;
				for (AttributeRelation ar:((NotionDiagram) eContainer()).getAttributeRelations()) if (ar.getSourceId()==newSource.getId() && ar.getTargetId()==getTargetId()){
					find=true;
					break;
				}
				if (find) return;
			}
			rmh
					.getNotionByVertexID(getTargetId())
					.removeNotionDTOAttribute(
							rmh.getNotionByVertexID(
									getSourceId()));
			rmh
					.getNotionByVertexID(getTargetId())
					.addNotionDTOAttribute(
							rmh.getNotionByVertexID(
									newSource.getId()));

		}
		source = newSource;
		if (null!=newSource) sourceId = newSource.getId();
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
		if (null != target && null != newTarget && null!=eResource() && null!=(rmh=RecoveryModelHelper.instance(eResource()))) {
			if (null!=eContainer() && eContainer() instanceof NotionDiagram){
				boolean find=false;
				for (AttributeRelation ar:((NotionDiagram) eContainer()).getAttributeRelations()) if (ar.getSourceId()==getSourceId() && ar.getTargetId()==newTarget.getId()){
					find=true;
					break;
				}
				if (find) return;
			}
			rmh
					.getNotionByVertexID(getTargetId())
					.removeNotionDTOAttribute(
							rmh.getNotionByVertexID(
									getSourceId()));
			rmh
					.getNotionByVertexID(newTarget.getId())
					.addNotionDTOAttribute(
							rmh.getNotionByVertexID(
									getSourceId()));
		}
		target = newTarget;
		if (null!=newTarget) targetId = newTarget.getId();
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
			case NotiondiagramPackage.ATTRIBUTE_RELATION__SOURCE_ID:
				return getSourceId();
			case NotiondiagramPackage.ATTRIBUTE_RELATION__TARGET_ID:
				return getTargetId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NotiondiagramPackage.ATTRIBUTE_RELATION__SOURCE_ID:
				return sourceId != SOURCE_ID_EDEFAULT;
			case NotiondiagramPackage.ATTRIBUTE_RELATION__TARGET_ID:
				return targetId != TARGET_ID_EDEFAULT;
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
		result.append(" (SourceId: ");
		result.append(sourceId);
		result.append(", TargetId: ");
		result.append(targetId);
		result.append(')');
		return result.toString();
	}

} //AttributeRelationImpl
