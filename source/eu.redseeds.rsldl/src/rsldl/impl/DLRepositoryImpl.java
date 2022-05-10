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
import org.eclipse.emf.ecore.util.InternalEList;

import rsldl.DLDiagram;
import rsldl.DLDomain;
import rsldl.DLNotion;
import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRepository;
import rsldl.RsldlPackage;
import eu.redseeds.rsldl.util.Util;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link rsldl.impl.DLRepositoryImpl#getRelationshipParticipants <em>Relationship Participants</em>}</li>
 *   <li>{@link rsldl.impl.DLRepositoryImpl#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link rsldl.impl.DLRepositoryImpl#getName <em>Name</em>}</li>
 *   <li>{@link rsldl.impl.DLRepositoryImpl#getDomains <em>Domains</em>}</li>
 *   <li>{@link rsldl.impl.DLRepositoryImpl#getDiagrams <em>Diagrams</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DLRepositoryImpl extends DLSubsetImpl implements DLRepository {
	/**
	 * The cached value of the '{@link #getRelationshipParticipants() <em>Relationship Participants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationshipParticipants()
	 * @generated
	 * @ordered
	 */
	protected EList<DLRelationshipParticipant> relationshipParticipants;
	/**
	 * The cached value of the '{@link #getRelationships() <em>Relationships</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationships()
	 * @generated
	 * @ordered
	 */
	protected EList<DLRelationship> relationships;
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDomains() <em>Domains</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomains()
	 * @generated
	 * @ordered
	 */
	protected EList<DLDomain> domains;

	/**
	 * The cached value of the '{@link #getDiagrams() <em>Diagrams</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagrams()
	 * @generated
	 * @ordered
	 */
	protected EList<DLDiagram> diagrams;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLRelationshipParticipant> getRelationshipParticipants() {
		if (relationshipParticipants == null) {
			relationshipParticipants = new EObjectContainmentEList<DLRelationshipParticipant>(DLRelationshipParticipant.class, this, RsldlPackage.DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS);
		}
		return relationshipParticipants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLRelationship> getRelationships() {
		if (relationships == null) {
			relationships = new EObjectContainmentEList<DLRelationship>(DLRelationship.class, this, RsldlPackage.DL_REPOSITORY__RELATIONSHIPS);
		}
		return relationships;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RsldlPackage.DL_REPOSITORY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLDomain> getDomains() {
		if (domains == null) {
			domains = new EObjectContainmentEList<DLDomain>(DLDomain.class, this, RsldlPackage.DL_REPOSITORY__DOMAINS);
		}
		return domains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DLDiagram> getDiagrams() {
		if (diagrams == null) {
			diagrams = new EObjectContainmentEList<DLDiagram>(DLDiagram.class, this, RsldlPackage.DL_REPOSITORY__DIAGRAMS);
		}
		return diagrams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DLDomain getDomain(String name) {
		for(DLDomain d:getDomains())
			if(Util.equals(d.getName(), name))
				return d;
		return null;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DLDiagram getDiagram(String name) {
		for(DLDiagram d:getDiagrams())
			if(Util.equals(d.getName(), name))
				return d;
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<DLNotion> getDomainNotions() {
		EList<DLNotion> dn = new BasicEList<DLNotion>();
		for (DLRelationshipParticipant rp:getRelationshipParticipants())
			if(rp instanceof DLNotion)
				dn.add((DLNotion) rp);
		for(DLDiagram d:getDiagrams())
			for(DLRelationshipParticipant rp:d.getRelationshipParticipants())
				if(rp instanceof DLNotion)
					dn.add((DLNotion) rp);
		return dn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<DLRelationship> getDomainRelationships() {
		EList<DLRelationship> dr = new BasicEList<DLRelationship>();
		dr.addAll(getRelationships());
		for(DLDiagram d:getDiagrams())
			dr.addAll(d.getRelationships());
		return dr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS:
				return ((InternalEList<?>)getRelationshipParticipants()).basicRemove(otherEnd, msgs);
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIPS:
				return ((InternalEList<?>)getRelationships()).basicRemove(otherEnd, msgs);
			case RsldlPackage.DL_REPOSITORY__DOMAINS:
				return ((InternalEList<?>)getDomains()).basicRemove(otherEnd, msgs);
			case RsldlPackage.DL_REPOSITORY__DIAGRAMS:
				return ((InternalEList<?>)getDiagrams()).basicRemove(otherEnd, msgs);
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
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS:
				return getRelationshipParticipants();
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIPS:
				return getRelationships();
			case RsldlPackage.DL_REPOSITORY__NAME:
				return getName();
			case RsldlPackage.DL_REPOSITORY__DOMAINS:
				return getDomains();
			case RsldlPackage.DL_REPOSITORY__DIAGRAMS:
				return getDiagrams();
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
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS:
				getRelationshipParticipants().clear();
				getRelationshipParticipants().addAll((Collection<? extends DLRelationshipParticipant>)newValue);
				return;
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIPS:
				getRelationships().clear();
				getRelationships().addAll((Collection<? extends DLRelationship>)newValue);
				return;
			case RsldlPackage.DL_REPOSITORY__NAME:
				setName((String)newValue);
				return;
			case RsldlPackage.DL_REPOSITORY__DOMAINS:
				getDomains().clear();
				getDomains().addAll((Collection<? extends DLDomain>)newValue);
				return;
			case RsldlPackage.DL_REPOSITORY__DIAGRAMS:
				getDiagrams().clear();
				getDiagrams().addAll((Collection<? extends DLDiagram>)newValue);
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
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS:
				getRelationshipParticipants().clear();
				return;
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIPS:
				getRelationships().clear();
				return;
			case RsldlPackage.DL_REPOSITORY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RsldlPackage.DL_REPOSITORY__DOMAINS:
				getDomains().clear();
				return;
			case RsldlPackage.DL_REPOSITORY__DIAGRAMS:
				getDiagrams().clear();
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
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS:
				return relationshipParticipants != null && !relationshipParticipants.isEmpty();
			case RsldlPackage.DL_REPOSITORY__RELATIONSHIPS:
				return relationships != null && !relationships.isEmpty();
			case RsldlPackage.DL_REPOSITORY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RsldlPackage.DL_REPOSITORY__DOMAINS:
				return domains != null && !domains.isEmpty();
			case RsldlPackage.DL_REPOSITORY__DIAGRAMS:
				return diagrams != null && !diagrams.isEmpty();
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
		if (baseClass == DLDiagram.class) {
			switch (derivedFeatureID) {
				case RsldlPackage.DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS: return RsldlPackage.DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS;
				case RsldlPackage.DL_REPOSITORY__RELATIONSHIPS: return RsldlPackage.DL_DIAGRAM__RELATIONSHIPS;
				case RsldlPackage.DL_REPOSITORY__NAME: return RsldlPackage.DL_DIAGRAM__NAME;
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
		if (baseClass == DLDiagram.class) {
			switch (baseFeatureID) {
				case RsldlPackage.DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS: return RsldlPackage.DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS;
				case RsldlPackage.DL_DIAGRAM__RELATIONSHIPS: return RsldlPackage.DL_REPOSITORY__RELATIONSHIPS;
				case RsldlPackage.DL_DIAGRAM__NAME: return RsldlPackage.DL_REPOSITORY__NAME;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //DLRepositoryImpl
