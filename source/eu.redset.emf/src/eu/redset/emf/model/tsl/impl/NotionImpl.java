/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.DomainStatementTest;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.NotionAttribute;
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
 * An implementation of the model object '<em><b>Notion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.NotionImpl#getDomainStatements <em>Domain Statements</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.NotionImpl#getNotionName <em>Notion Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.NotionImpl#getNotionDescription <em>Notion Description</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.NotionImpl#getNotionTrail <em>Notion Trail</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.NotionImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.NotionImpl#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NotionImpl extends TestImpl implements Notion {
	/**
	 * The cached value of the '{@link #getDomainStatements() <em>Domain Statements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainStatements()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainStatement> domainStatements;
	/**
	 * The default value of the '{@link #getNotionName() <em>Notion Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotionName()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTION_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getNotionName() <em>Notion Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotionName()
	 * @generated
	 * @ordered
	 */
	protected String notionName = NOTION_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getNotionDescription() <em>Notion Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotionDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTION_DESCRIPTION_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getNotionDescription() <em>Notion Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotionDescription()
	 * @generated
	 * @ordered
	 */
	protected String notionDescription = NOTION_DESCRIPTION_EDEFAULT;
	/**
	 * The default value of the '{@link #getNotionTrail() <em>Notion Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotionTrail()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTION_TRAIL_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getNotionTrail() <em>Notion Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotionTrail()
	 * @generated
	 * @ordered
	 */
	protected String notionTrail = NOTION_TRAIL_EDEFAULT;
	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<NotionAttribute> attributes;
	/**
	 * The default value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected String uid = UID_EDEFAULT;
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
		return TslPackage.Literals.NOTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainStatement> getDomainStatements() {
		if (domainStatements == null) {
			domainStatements = new EObjectContainmentEList<DomainStatement>(DomainStatement.class, this, TslPackage.NOTION__DOMAIN_STATEMENTS);
		}
		return domainStatements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotionName() {
		return notionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotionName(String newNotionName) {
		String oldNotionName = notionName;
		notionName = newNotionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.NOTION__NOTION_NAME, oldNotionName, notionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotionDescription() {
		return notionDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotionDescription(String newNotionDescription) {
		String oldNotionDescription = notionDescription;
		notionDescription = newNotionDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.NOTION__NOTION_DESCRIPTION, oldNotionDescription, notionDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotionTrail() {
		return notionTrail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotionTrail(String newNotionTrail) {
		String oldNotionTrail = notionTrail;
		notionTrail = newNotionTrail;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.NOTION__NOTION_TRAIL, oldNotionTrail, notionTrail));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NotionAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<NotionAttribute>(NotionAttribute.class, this, TslPackage.NOTION__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUid(String newUid) {
		String oldUid = uid;
		uid = newUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.NOTION__UID, oldUid, uid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TslPackage.NOTION__DOMAIN_STATEMENTS:
				return ((InternalEList<?>)getDomainStatements()).basicRemove(otherEnd, msgs);
			case TslPackage.NOTION__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
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
			case TslPackage.NOTION__DOMAIN_STATEMENTS:
				return getDomainStatements();
			case TslPackage.NOTION__NOTION_NAME:
				return getNotionName();
			case TslPackage.NOTION__NOTION_DESCRIPTION:
				return getNotionDescription();
			case TslPackage.NOTION__NOTION_TRAIL:
				return getNotionTrail();
			case TslPackage.NOTION__ATTRIBUTES:
				return getAttributes();
			case TslPackage.NOTION__UID:
				return getUid();
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
			case TslPackage.NOTION__DOMAIN_STATEMENTS:
				getDomainStatements().clear();
				getDomainStatements().addAll((Collection<? extends DomainStatement>)newValue);
				return;
			case TslPackage.NOTION__NOTION_NAME:
				setNotionName((String)newValue);
				return;
			case TslPackage.NOTION__NOTION_DESCRIPTION:
				setNotionDescription((String)newValue);
				return;
			case TslPackage.NOTION__NOTION_TRAIL:
				setNotionTrail((String)newValue);
				return;
			case TslPackage.NOTION__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends NotionAttribute>)newValue);
				return;
			case TslPackage.NOTION__UID:
				setUid((String)newValue);
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
			case TslPackage.NOTION__DOMAIN_STATEMENTS:
				getDomainStatements().clear();
				return;
			case TslPackage.NOTION__NOTION_NAME:
				setNotionName(NOTION_NAME_EDEFAULT);
				return;
			case TslPackage.NOTION__NOTION_DESCRIPTION:
				setNotionDescription(NOTION_DESCRIPTION_EDEFAULT);
				return;
			case TslPackage.NOTION__NOTION_TRAIL:
				setNotionTrail(NOTION_TRAIL_EDEFAULT);
				return;
			case TslPackage.NOTION__ATTRIBUTES:
				getAttributes().clear();
				return;
			case TslPackage.NOTION__UID:
				setUid(UID_EDEFAULT);
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
			case TslPackage.NOTION__DOMAIN_STATEMENTS:
				return domainStatements != null && !domainStatements.isEmpty();
			case TslPackage.NOTION__NOTION_NAME:
				return NOTION_NAME_EDEFAULT == null ? notionName != null : !NOTION_NAME_EDEFAULT.equals(notionName);
			case TslPackage.NOTION__NOTION_DESCRIPTION:
				return NOTION_DESCRIPTION_EDEFAULT == null ? notionDescription != null : !NOTION_DESCRIPTION_EDEFAULT.equals(notionDescription);
			case TslPackage.NOTION__NOTION_TRAIL:
				return NOTION_TRAIL_EDEFAULT == null ? notionTrail != null : !NOTION_TRAIL_EDEFAULT.equals(notionTrail);
			case TslPackage.NOTION__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case TslPackage.NOTION__UID:
				return UID_EDEFAULT == null ? uid != null : !UID_EDEFAULT.equals(uid);
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
		result.append(" (notionName: ");
		result.append(notionName);
		result.append(", notionDescription: ");
		result.append(notionDescription);
		result.append(", notionTrail: ");
		result.append(notionTrail);
		result.append(", uid: ");
		result.append(uid);
		result.append(')');
		return result.toString();
	}

} //NotionImpl
