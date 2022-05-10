/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import rsldl.DLAttributeLink;
import rsldl.DLDereferenceLink;
import rsldl.DLDomain;
import rsldl.DLDomainElement;
import rsldl.DLFeature;
import rsldl.DLIdentityCondition;
import rsldl.DLInheritanceCondition;
import rsldl.DLNotion;
import rsldl.DLProperty;
import rsldl.DLTypeRole;
import rsldl.DLValidityCondition;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Notion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLNotionImpl#getDomains <em>Domains</em>}</li>
 *   <li>{@link rsldl.impl.DLNotionImpl#getType <em>Type</em>}</li>
 *   <li>{@link rsldl.impl.DLNotionImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link rsldl.impl.DLNotionImpl#getDereferences <em>Dereferences</em>}</li>
 *   <li>{@link rsldl.impl.DLNotionImpl#isDerived <em>Derived</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DLNotionImpl extends DLRelationshipParticipantImpl implements DLNotion {
	/**
	 * The cached value of the '{@link #getDomains() <em>Domains</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomains()
	 * @generated
	 * @ordered
	 */
	protected EList<DLDomain> domains;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final DLTypeRole TYPE_EDEFAULT = DLTypeRole.IDENTITY;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected DLTypeRole type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<DLFeature> features;

	/**
	 * The cached value of the '{@link #getDereferences() <em>Dereferences</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDereferences()
	 * @generated
	 * @ordered
	 */
	protected EList<DLDereferenceLink> dereferences;

	/**
	 * The default value of the '{@link #isDerived() <em>Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDerived()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DERIVED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDerived() <em>Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDerived()
	 * @generated
	 * @ordered
	 */
	protected boolean derived = DERIVED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLNotionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_NOTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLDomain> getDomains() {
		if (domains == null) {
			domains = new EObjectWithInverseResolvingEList.ManyInverse<DLDomain>(DLDomain.class, this, RsldlPackage.DL_NOTION__DOMAINS, RsldlPackage.DL_DOMAIN__ELEMENTS);
		}
		return domains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLTypeRole getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(DLTypeRole newType) {
		DLTypeRole oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_NOTION__TYPE, oldType, type));
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName.toLowerCase();
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_NOTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLFeature> getFeatures() {
		if (features == null) {
			features = new EObjectContainmentWithInverseEList<DLFeature>(DLFeature.class, this, RsldlPackage.DL_NOTION__FEATURES, RsldlPackage.DL_FEATURE__NOTION);
		}
		return features;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLDereferenceLink> getDereferences() {
		if (dereferences == null) {
			dereferences = new EObjectContainmentEList<DLDereferenceLink>(DLDereferenceLink.class, this, RsldlPackage.DL_NOTION__DEREFERENCES);
		}
		return dereferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDerived() {
		return derived;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDerived(boolean newDerived) {
		boolean oldDerived = derived;
		derived = newDerived;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_NOTION__DERIVED, oldDerived, derived));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<DLProperty> getDirectAttributes() {
		EList<DLProperty> result = new BasicEList<DLProperty>();
        for(DLFeature ft:getFeatures())
        	if(ft instanceof DLAttributeLink)
        		result.add(((DLAttributeLink) ft).getAttribute());
        return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<DLProperty> getAllAttributes() {
		EList<DLProperty> result = new BasicEList<DLProperty>();
        for(DLFeature ft:getFeatures())
        	if(ft instanceof DLAttributeLink)
        		result.add(((DLAttributeLink) ft).getAttribute());
        for (DLInheritanceCondition ic:getInheritanceConditions())
            for(DLNotion p:ic.getParents())
                for(DLProperty a:p.getAllAttributes())
                    if (!result.contains(a))
                        result.add(a);
        return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<DLInheritanceCondition> getInheritanceConditions() {
		EList<DLInheritanceCondition> result = new BasicEList<DLInheritanceCondition>();
        for(DLFeature ft:getFeatures())
        	if(ft instanceof DLInheritanceCondition)
            	result.add(((DLInheritanceCondition) ft));
        return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<DLAttributeLink> getAttributesLinks() {
		EList<DLAttributeLink> result = new BasicEList<DLAttributeLink>();
        for(DLFeature ft:getFeatures())
        	if(ft instanceof DLAttributeLink)
            	result.add(((DLAttributeLink) ft));
        return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<DLValidityCondition> getValidityConditions() {
		BasicEList<DLValidityCondition> result = new BasicEList<DLValidityCondition>();
        for(DLFeature ft:getFeatures())
        	if(ft instanceof DLValidityCondition)
            	result.add(((DLValidityCondition) ft));
        return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<DLIdentityCondition> getIdentityConditions() {
		EList<DLIdentityCondition> result = new BasicEList<DLIdentityCondition>();
        for(DLFeature ft:getFeatures())
        	if(ft instanceof DLIdentityCondition)
            	result.add(((DLIdentityCondition) ft));
        return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<DLAttributeLink> getAllAttributesLinks() {
		EList<DLAttributeLink> result = new BasicEList<DLAttributeLink>();
        for(DLFeature ft:getFeatures())
        	if(ft instanceof DLAttributeLink)
        		result.add((DLAttributeLink) ft);
        for (DLInheritanceCondition ic:getInheritanceConditions())
            for(DLNotion p:ic.getParents())
                for(DLAttributeLink al:p.getAllAttributesLinks())
                    if (!result.contains(al))
                        result.add(al);
        return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_NOTION__DOMAINS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDomains()).basicAdd(otherEnd, msgs);
			case RsldlPackage.DL_NOTION__FEATURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeatures()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_NOTION__DOMAINS:
				return ((InternalEList<?>)getDomains()).basicRemove(otherEnd, msgs);
			case RsldlPackage.DL_NOTION__FEATURES:
				return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
			case RsldlPackage.DL_NOTION__DEREFERENCES:
				return ((InternalEList<?>)getDereferences()).basicRemove(otherEnd, msgs);
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
			case RsldlPackage.DL_NOTION__DOMAINS:
				return getDomains();
			case RsldlPackage.DL_NOTION__TYPE:
				return getType();
			case RsldlPackage.DL_NOTION__FEATURES:
				return getFeatures();
			case RsldlPackage.DL_NOTION__DEREFERENCES:
				return getDereferences();
			case RsldlPackage.DL_NOTION__DERIVED:
				return isDerived();
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
			case RsldlPackage.DL_NOTION__DOMAINS:
				getDomains().clear();
				getDomains().addAll((Collection<? extends DLDomain>)newValue);
				return;
			case RsldlPackage.DL_NOTION__TYPE:
				setType((DLTypeRole)newValue);
				return;
			case RsldlPackage.DL_NOTION__FEATURES:
				getFeatures().clear();
				getFeatures().addAll((Collection<? extends DLFeature>)newValue);
				return;
			case RsldlPackage.DL_NOTION__DEREFERENCES:
				getDereferences().clear();
				getDereferences().addAll((Collection<? extends DLDereferenceLink>)newValue);
				return;
			case RsldlPackage.DL_NOTION__DERIVED:
				setDerived((Boolean)newValue);
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
			case RsldlPackage.DL_NOTION__DOMAINS:
				getDomains().clear();
				return;
			case RsldlPackage.DL_NOTION__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case RsldlPackage.DL_NOTION__FEATURES:
				getFeatures().clear();
				return;
			case RsldlPackage.DL_NOTION__DEREFERENCES:
				getDereferences().clear();
				return;
			case RsldlPackage.DL_NOTION__DERIVED:
				setDerived(DERIVED_EDEFAULT);
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
			case RsldlPackage.DL_NOTION__DOMAINS:
				return domains != null && !domains.isEmpty();
			case RsldlPackage.DL_NOTION__TYPE:
				return type != TYPE_EDEFAULT;
			case RsldlPackage.DL_NOTION__FEATURES:
				return features != null && !features.isEmpty();
			case RsldlPackage.DL_NOTION__DEREFERENCES:
				return dereferences != null && !dereferences.isEmpty();
			case RsldlPackage.DL_NOTION__DERIVED:
				return derived != DERIVED_EDEFAULT;
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
		if (baseClass == DLDomainElement.class) {
			switch (derivedFeatureID) {
				case RsldlPackage.DL_NOTION__DOMAINS: return RsldlPackage.DL_DOMAIN_ELEMENT__DOMAINS;
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
		if (baseClass == DLDomainElement.class) {
			switch (baseFeatureID) {
				case RsldlPackage.DL_DOMAIN_ELEMENT__DOMAINS: return RsldlPackage.DL_NOTION__DOMAINS;
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
		result.append(", derived: ");
		result.append(derived);
		result.append(')');
		return result.toString();
	}

} //DLNotionImpl
