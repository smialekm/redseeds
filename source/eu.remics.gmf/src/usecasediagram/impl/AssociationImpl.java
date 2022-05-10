/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package usecasediagram.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.remics.recovery.model.RecoveryModelHelper;

import usecasediagram.Actor;
import usecasediagram.Association;
import usecasediagram.UseCase;
import usecasediagram.UsecasediagramPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link usecasediagram.impl.AssociationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link usecasediagram.impl.AssociationImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link usecasediagram.impl.AssociationImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationImpl extends EObjectImpl implements Association {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Actor source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected UseCase target;

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
	protected AssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsecasediagramPackage.Literals.ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Actor)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsecasediagramPackage.ASSOCIATION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSource(Actor newSource) {
		Actor oldSource = source;
		RecoveryModelHelper rmh = null;
		if (null != source && null!=newSource && null!=eResource() && null!=(rmh=RecoveryModelHelper.instance(eResource()))) {
			NamedElement supplier = null;
			shell: for (IsAllocatedTo alloc : ((RSLUseCase) rmh.getBussinessLayerFacade()
					.getUseCaseByVertexID(target.getId()))
					.getAllocationToUMLList())
				for (Stereotype ster : alloc.getStereotypeList())
					if (ster.getName().equals(
							"DuplicatedUseCaseStructureForUseRelationship")) {
						supplier = (NamedElement) alloc
								.getAllocationTargetList().get(0);
						break shell;
					}
			NamedElement client = null;
			shell: for (IsAllocatedTo alloc : ((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) rmh
					.getActorByVertexID(oldSource.getId()))
					.getAllocationToUMLList())
				for (Stereotype ster : alloc.getStereotypeList())
					if (ster.getName().equals(
							"DuplicatedUseCaseStructureForUseRelationship")) {
						client = (NamedElement) alloc.getAllocationTargetList()
								.get(0);
						break shell;
					}
			for (Dependency dep : rmh
					.getBussinessLayerFacade().getDependencyVertices())
				if (!dep.getSupplierList().isEmpty()
						&& dep.getSupplierList().get(0).getId() == supplier
								.getId() && !dep.getClientList().isEmpty()
						&& dep.getClientList().get(0).getId() == client.getId()) {
					dep.removeClient(dep.getClientList().get(0));
					NamedElement newclient = null;
					shell: for (IsAllocatedTo alloc : ((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) rmh.getActorByVertexID(
									newSource.getId()))
							.getAllocationToUMLList())
						for (Stereotype ster : alloc.getStereotypeList())
							if (ster.getName()
									.equals("DuplicatedUseCaseStructureForUseRelationship")) {
								newclient = (NamedElement) alloc
										.getAllocationTargetList().get(0);
								break shell;
							}
					dep.addClient(newclient);
					break;
				}
		}
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UsecasediagramPackage.ASSOCIATION__SOURCE, oldSource,
					source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCase getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (UseCase)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsecasediagramPackage.ASSOCIATION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCase basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setTarget(UseCase newTarget) {
		UseCase oldTarget = target;
		RecoveryModelHelper rmh = null;
		if (null != target && null!=newTarget && null!=eResource() && null!=(rmh=RecoveryModelHelper.instance(eResource()))) {
			NamedElement supplier = null;
			shell: for (IsAllocatedTo alloc : ((RSLUseCase) rmh.getBussinessLayerFacade()
					.getUseCaseByVertexID(oldTarget.getId()))
					.getAllocationToUMLList())
				for (Stereotype ster : alloc.getStereotypeList())
					if (ster.getName().equals(
							"DuplicatedUseCaseStructureForUseRelationship")) {
						supplier = (NamedElement) alloc
								.getAllocationTargetList().get(0);
						break shell;
					}
			NamedElement client = null;
			shell: for (IsAllocatedTo alloc : ((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) rmh.getActorByVertexID(source.getId()))
					.getAllocationToUMLList())
				for (Stereotype ster : alloc.getStereotypeList())
					if (ster.getName().equals(
							"DuplicatedUseCaseStructureForUseRelationship")) {
						client = (NamedElement) alloc.getAllocationTargetList()
								.get(0);
						break shell;
					}
			for (Dependency dep : rmh
					.getBussinessLayerFacade().getDependencyVertices())
				if (!dep.getSupplierList().isEmpty()
						&& dep.getSupplierList().get(0).getId() == supplier
								.getId() && !dep.getClientList().isEmpty()
						&& dep.getClientList().get(0).getId() == client.getId()) {
					dep.removeSupplier(dep.getSupplierList().get(0));
					NamedElement newsupplier = null;
					shell: for (IsAllocatedTo alloc : ((RSLUseCase) rmh.getBussinessLayerFacade()
							.getUseCaseByVertexID(newTarget.getId()))
							.getAllocationToUMLList())
						for (Stereotype ster : alloc.getStereotypeList())
							if (ster.getName()
									.equals("DuplicatedUseCaseStructureForUseRelationship")) {
								newsupplier = (NamedElement) alloc
										.getAllocationTargetList().get(0);
								break shell;
							}
					dep.addSupplier(newsupplier);
					break;
				}
		}
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UsecasediagramPackage.ASSOCIATION__TARGET, oldTarget,
					target));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsecasediagramPackage.ASSOCIATION__ID, ID_EDEFAULT, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsecasediagramPackage.ASSOCIATION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case UsecasediagramPackage.ASSOCIATION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case UsecasediagramPackage.ASSOCIATION__ID:
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
			case UsecasediagramPackage.ASSOCIATION__SOURCE:
				setSource((Actor)newValue);
				return;
			case UsecasediagramPackage.ASSOCIATION__TARGET:
				setTarget((UseCase)newValue);
				return;
			case UsecasediagramPackage.ASSOCIATION__ID:
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
			case UsecasediagramPackage.ASSOCIATION__SOURCE:
				setSource((Actor)null);
				return;
			case UsecasediagramPackage.ASSOCIATION__TARGET:
				setTarget((UseCase)null);
				return;
			case UsecasediagramPackage.ASSOCIATION__ID:
				setId(ID_EDEFAULT);
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
			case UsecasediagramPackage.ASSOCIATION__SOURCE:
				return source != null;
			case UsecasediagramPackage.ASSOCIATION__TARGET:
				return target != null;
			case UsecasediagramPackage.ASSOCIATION__ID:
				return id != ID_EDEFAULT;
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //AssociationImpl
