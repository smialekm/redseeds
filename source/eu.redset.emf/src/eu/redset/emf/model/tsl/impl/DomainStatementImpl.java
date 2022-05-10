/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.GUITest;
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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.impl.DomainStatementImpl#getDirectNotion <em>Direct Notion</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.DomainStatementImpl#getIndirectNotion <em>Indirect Notion</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.DomainStatementImpl#getPhraseName <em>Phrase Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.DomainStatementImpl#getPhraseDescription <em>Phrase Description</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.impl.DomainStatementImpl#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainStatementImpl extends TestImpl implements DomainStatement {
	/**
	 * The cached value of the '{@link #getDirectNotion() <em>Direct Notion</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectNotion()
	 * @generated
	 * @ordered
	 */
	protected Notion directNotion;

	/**
	 * The cached value of the '{@link #getIndirectNotion() <em>Indirect Notion</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndirectNotion()
	 * @generated
	 * @ordered
	 */
	protected Notion indirectNotion;

	/**
	 * The default value of the '{@link #getPhraseName() <em>Phrase Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhraseName()
	 * @generated
	 * @ordered
	 */
	protected static final String PHRASE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhraseName() <em>Phrase Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhraseName()
	 * @generated
	 * @ordered
	 */
	protected String phraseName = PHRASE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhraseDescription() <em>Phrase Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhraseDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String PHRASE_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhraseDescription() <em>Phrase Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhraseDescription()
	 * @generated
	 * @ordered
	 */
	protected String phraseDescription = PHRASE_DESCRIPTION_EDEFAULT;

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
	protected DomainStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TslPackage.Literals.DOMAIN_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Notion getDirectNotion() {
		if (directNotion != null && directNotion.eIsProxy()) {
			InternalEObject oldDirectNotion = (InternalEObject)directNotion;
			directNotion = (Notion)eResolveProxy(oldDirectNotion);
			if (directNotion != oldDirectNotion) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.DOMAIN_STATEMENT__DIRECT_NOTION, oldDirectNotion, directNotion));
			}
		}
		return directNotion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Notion basicGetDirectNotion() {
		return directNotion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirectNotion(Notion newDirectNotion) {
		Notion oldDirectNotion = directNotion;
		directNotion = newDirectNotion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.DOMAIN_STATEMENT__DIRECT_NOTION, oldDirectNotion, directNotion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Notion getIndirectNotion() {
		if (indirectNotion != null && indirectNotion.eIsProxy()) {
			InternalEObject oldIndirectNotion = (InternalEObject)indirectNotion;
			indirectNotion = (Notion)eResolveProxy(oldIndirectNotion);
			if (indirectNotion != oldIndirectNotion) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TslPackage.DOMAIN_STATEMENT__INDIRECT_NOTION, oldIndirectNotion, indirectNotion));
			}
		}
		return indirectNotion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Notion basicGetIndirectNotion() {
		return indirectNotion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndirectNotion(Notion newIndirectNotion) {
		Notion oldIndirectNotion = indirectNotion;
		indirectNotion = newIndirectNotion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.DOMAIN_STATEMENT__INDIRECT_NOTION, oldIndirectNotion, indirectNotion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhraseName() {
		return phraseName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhraseName(String newPhraseName) {
		String oldPhraseName = phraseName;
		phraseName = newPhraseName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.DOMAIN_STATEMENT__PHRASE_NAME, oldPhraseName, phraseName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhraseDescription() {
		return phraseDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhraseDescription(String newPhraseDescription) {
		String oldPhraseDescription = phraseDescription;
		phraseDescription = newPhraseDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.DOMAIN_STATEMENT__PHRASE_DESCRIPTION, oldPhraseDescription, phraseDescription));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TslPackage.DOMAIN_STATEMENT__UID, oldUid, uid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TslPackage.DOMAIN_STATEMENT__DIRECT_NOTION:
				if (resolve) return getDirectNotion();
				return basicGetDirectNotion();
			case TslPackage.DOMAIN_STATEMENT__INDIRECT_NOTION:
				if (resolve) return getIndirectNotion();
				return basicGetIndirectNotion();
			case TslPackage.DOMAIN_STATEMENT__PHRASE_NAME:
				return getPhraseName();
			case TslPackage.DOMAIN_STATEMENT__PHRASE_DESCRIPTION:
				return getPhraseDescription();
			case TslPackage.DOMAIN_STATEMENT__UID:
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
			case TslPackage.DOMAIN_STATEMENT__DIRECT_NOTION:
				setDirectNotion((Notion)newValue);
				return;
			case TslPackage.DOMAIN_STATEMENT__INDIRECT_NOTION:
				setIndirectNotion((Notion)newValue);
				return;
			case TslPackage.DOMAIN_STATEMENT__PHRASE_NAME:
				setPhraseName((String)newValue);
				return;
			case TslPackage.DOMAIN_STATEMENT__PHRASE_DESCRIPTION:
				setPhraseDescription((String)newValue);
				return;
			case TslPackage.DOMAIN_STATEMENT__UID:
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
			case TslPackage.DOMAIN_STATEMENT__DIRECT_NOTION:
				setDirectNotion((Notion)null);
				return;
			case TslPackage.DOMAIN_STATEMENT__INDIRECT_NOTION:
				setIndirectNotion((Notion)null);
				return;
			case TslPackage.DOMAIN_STATEMENT__PHRASE_NAME:
				setPhraseName(PHRASE_NAME_EDEFAULT);
				return;
			case TslPackage.DOMAIN_STATEMENT__PHRASE_DESCRIPTION:
				setPhraseDescription(PHRASE_DESCRIPTION_EDEFAULT);
				return;
			case TslPackage.DOMAIN_STATEMENT__UID:
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
			case TslPackage.DOMAIN_STATEMENT__DIRECT_NOTION:
				return directNotion != null;
			case TslPackage.DOMAIN_STATEMENT__INDIRECT_NOTION:
				return indirectNotion != null;
			case TslPackage.DOMAIN_STATEMENT__PHRASE_NAME:
				return PHRASE_NAME_EDEFAULT == null ? phraseName != null : !PHRASE_NAME_EDEFAULT.equals(phraseName);
			case TslPackage.DOMAIN_STATEMENT__PHRASE_DESCRIPTION:
				return PHRASE_DESCRIPTION_EDEFAULT == null ? phraseDescription != null : !PHRASE_DESCRIPTION_EDEFAULT.equals(phraseDescription);
			case TslPackage.DOMAIN_STATEMENT__UID:
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
		result.append(" (phraseName: ");
		result.append(phraseName);
		result.append(", phraseDescription: ");
		result.append(phraseDescription);
		result.append(", uid: ");
		result.append(uid);
		result.append(')');
		return result.toString();
	}

} //DomainStatementImpl
