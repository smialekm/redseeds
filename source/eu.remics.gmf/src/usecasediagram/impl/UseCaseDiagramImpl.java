/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package usecasediagram.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import usecasediagram.Actor;
import usecasediagram.Association;
import usecasediagram.Invoke;
import usecasediagram.UseCase;
import usecasediagram.UseCaseDiagram;
import usecasediagram.UsecasediagramPackage;
import de.uni_koblenz.jgralab.GraphElement;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditorInput;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MUseCase;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Case Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link usecasediagram.impl.UseCaseDiagramImpl#getActors <em>Actors</em>}</li>
 *   <li>{@link usecasediagram.impl.UseCaseDiagramImpl#getAssociations <em>Associations</em>}</li>
 *   <li>{@link usecasediagram.impl.UseCaseDiagramImpl#getUsecases <em>Usecases</em>}</li>
 *   <li>{@link usecasediagram.impl.UseCaseDiagramImpl#getInvokes <em>Invokes</em>}</li>
 *   <li>{@link usecasediagram.impl.UseCaseDiagramImpl#getName <em>Name</em>}</li>
 *   <li>{@link usecasediagram.impl.UseCaseDiagramImpl#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UseCaseDiagramImpl extends EObjectImpl implements UseCaseDiagram {
	/**
	 * The cached value of the '{@link #getActors() <em>Actors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActors()
	 * @generated
	 * @ordered
	 */
	protected EList<Actor> actors;
	
	/**
	 * The cached value of the '{@link #getAssociations() <em>Associations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> associations;

	/**
	 * The cached value of the '{@link #getUsecases() <em>Usecases</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsecases()
	 * @generated
	 * @ordered
	 */
	protected EList<UseCase> usecases;
	
	/**
	 * The cached value of the '{@link #getInvokes() <em>Invokes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvokes()
	 * @generated
	 * @ordered
	 */
	protected EList<Invoke> invokes;

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
	 * The default value of the '{@link #getPackage() <em>Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackage()
	 * @generated
	 * @ordered
	 */
	protected static final int PACKAGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPackage() <em>Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackage()
	 * @generated
	 * @ordered
	 */
	protected int package_ = PACKAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected List<GraphElement> deletedItems = new ArrayList<GraphElement>();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected UseCaseDiagramImpl() {
		super();
		eAdapters().add(new AdapterImpl() {
			public void notifyChanged(Notification notification) {
				RecoveryModelHelper rmh = RecoveryModelHelper.instance(eResource());
				if(RecoveryManagerHelper.isModelDeleteActionOccur()){
					switch (notification.getEventType()) {
						case Notification.REMOVE:
							if(notification.getOldValue() instanceof UseCase && null!=rmh.getBussinessLayerFacade().getUseCaseByVertexID(((UseCase) notification.getOldValue()).getId())){
								UseCaseDTO uc = rmh.getBussinessLayerFacade().getUseCaseByVertexID(((UseCase) notification.getOldValue()).getId());
								MUseCase.restoreRecoveredScenarios(uc);
								IEditorInput input = new UseCaseEditorInput();
								((UseCaseEditorInput) input).setUseCaseDTO(uc);
								IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
								IEditorPart editorPart = page.findEditor(input);
								page.closeEditor(editorPart, true);
								uc.delete();
								SCProjectHelper.refreshSCNavigator();
								SCProjectHelper.refreshUnassignedScenariosList();
							}
							else if(notification.getOldValue() instanceof Actor && null!=rmh.getActorByVertexID(((Actor) notification.getOldValue()).getId())){
								ActorDTO act = rmh.getActorByVertexID(((Actor) notification.getOldValue()).getId());
								IEditorInput input = new ActorEditorInput();
								((ActorEditorInput) input).setActorDTO(act);
								IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
								IEditorPart editorPart = page.findEditor(input);
								page.closeEditor(editorPart, true);
								act.delete();
								SCProjectHelper.refreshSCNavigator();
							}
							else if(notification.getOldValue() instanceof Association && null != rmh.getDependencyByVertexID(((Association) notification.getOldValue()).getId())){
								rmh.getDependencyByVertexID(((Association) notification.getOldValue()).getId()).delete();
							}
							break;
					}
				}
				switch (notification.getEventType()) {
					case Notification.REMOVE:
						if(notification.getOldValue() instanceof UseCase && null!=rmh.getBussinessLayerFacade().getUseCaseByVertexID(((UseCase) notification.getOldValue()).getId())){
							RSLUseCase uc = (RSLUseCase) rmh.getBussinessLayerFacade().getUseCaseByVertexID(((UseCase) notification.getOldValue()).getId());
							deletedItems.add(uc);
						}
						else if(notification.getOldValue() instanceof Actor && null!=rmh.getActorByVertexID(((Actor) notification.getOldValue()).getId())){
							eu.redseeds.scl.rsl.rsldomainelements.actors.Actor act = (eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) rmh.getActorByVertexID(((Actor) notification.getOldValue()).getId());
							deletedItems.add(act);
						}
						else if(notification.getOldValue() instanceof Association && null != rmh.getDependencyByVertexID(((Association) notification.getOldValue()).getId())){
							Dependency dep = rmh.getDependencyByVertexID(((Association) notification.getOldValue()).getId());
							deletedItems.add(dep);
						}
						break;
					case Notification.ADD:
						if(notification.getOldValue() instanceof UseCase && null!=rmh.getBussinessLayerFacade().getUseCaseByVertexID(((UseCase) notification.getOldValue()).getId())){
							RSLUseCase uc = (RSLUseCase) rmh.getBussinessLayerFacade().getUseCaseByVertexID(((UseCase) notification.getOldValue()).getId());
							if (deletedItems.contains(uc)) deletedItems.remove(uc);
						}
						else if(notification.getOldValue() instanceof Actor && null!=rmh.getActorByVertexID(((Actor) notification.getOldValue()).getId())){
							eu.redseeds.scl.rsl.rsldomainelements.actors.Actor act = (eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) rmh.getActorByVertexID(((Actor) notification.getOldValue()).getId());
							if (deletedItems.contains(act)) deletedItems.remove(act);
						}
						else if(notification.getOldValue() instanceof Association && null != rmh.getDependencyByVertexID(((Association) notification.getOldValue()).getId())){
							Dependency dep = rmh.getDependencyByVertexID(((Association) notification.getOldValue()).getId());
							if (deletedItems.contains(dep)) deletedItems.remove(dep);
						}
						break;
				}
			}
		});
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsecasediagramPackage.Literals.USE_CASE_DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Actor> getActors() {
		if (actors == null) {
			actors = new EObjectContainmentEList<Actor>(Actor.class, this, UsecasediagramPackage.USE_CASE_DIAGRAM__ACTORS);
		}
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return actors;
		for(int i = 0; i <actors.size();i++) if (null==rmh.getActorByVertexID(actors.get(i).getId())){
			final int j = i;
			TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(actors.get(i));
			if(editingDomain != null){
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

					@Override
					protected void doExecute() {
						actors.remove(j);
					}
					
					@Override
					public boolean canUndo() {
						return false;
					}
					
				});
				i--;
			}
		}
		return actors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Association> getAssociations() {
		if (null==associations){
			associations = new EObjectContainmentEList<Association>(Association.class, this, UsecasediagramPackage.USE_CASE_DIAGRAM__ASSOCIATIONS);
		}
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return associations;
		Map<Integer,Actor> actors = new HashMap<Integer,Actor>();
		Map<Integer,UseCase> usecases = new HashMap<Integer,UseCase>();
		if (null!=this.actors) for (Actor act:getActors()) actors.put(act.getId(),act);
		if (null!=this.usecases) for (UseCase uc:getUsecases()) usecases.put(uc.getId(),uc);
		for(int i=0; i<associations.size(); i++){
			if(((AssociationImpl) associations.get(i)).eIsSet(UsecasediagramPackage.ASSOCIATION__ID) && null == rmh.getDependencyByVertexID(associations.get(i).getId())){
				final int j = i;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(associations.get(i));
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							associations.remove(j);
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
		Association assoc;
		for (Dependency dep:rmh.getBussinessLayerFacade().getDependencyVertices()) if (dep.getClientList().size()>0 && dep.getClientList().get(0) instanceof eu.redseeds.scl.uml.usecases.Actor && !((eu.redseeds.scl.uml.usecases.Actor)dep.getClientList().get(0)).getAllocationToRSLList().isEmpty() && dep.getSupplierList().size()>0 && dep.getSupplierList().get(0) instanceof eu.redseeds.scl.uml.usecases.UseCase && !((eu.redseeds.scl.uml.usecases.UseCase)dep.getSupplierList().get(0)).getAllocationToRSLList().isEmpty()){
			Integer actorid = null;
			shell:
			for (IsAllocatedTo alloc:((eu.redseeds.scl.uml.usecases.Actor)dep.getClientList().get(0)).getAllocationToRSLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
					actorid=((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) alloc.getAllocationSourceList().get(0)).getId();
					break shell;
			}
			if (null==actorid) continue;
			Integer usecaseid = null;
			shell:
			for (IsAllocatedTo alloc:((eu.redseeds.scl.uml.usecases.UseCase)dep.getSupplierList().get(0)).getAllocationToRSLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
					usecaseid=((RSLUseCase) alloc.getAllocationSourceList().get(0)).getId();
					break shell;
			}
			if (null==usecaseid) continue;
			if (!actors.containsKey(actorid) || !usecases.containsKey(usecaseid)) continue;
			assoc = new AssociationImpl();
			assoc.setSource(actors.get(actorid));
			assoc.setTarget(usecases.get(usecaseid));
			assoc.setId(dep.getId());
			boolean contains = false;
			for (final Association a: associations) if (a.getId()==assoc.getId()){
				contains =true;
				if(a.getSource() == null || a.getSource().getId()!=assoc.getSource().getId()){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						final Actor source = assoc.getSource();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								a.setSource(source);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
				if(a.getTarget() == null || a.getTarget().getId()!=assoc.getTarget().getId()){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						final UseCase target = assoc.getTarget();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								a.setTarget(target);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
				break;
			}
			if (!contains){
				final Association as = assoc;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							associations.add(as);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
				}
			}
		}
		return associations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<UseCase> getUsecases() {
		if (usecases == null) {
			usecases = new EObjectContainmentEList<UseCase>(UseCase.class, this, UsecasediagramPackage.USE_CASE_DIAGRAM__USECASES);
		}
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return usecases;
		for(int i = 0; i <usecases.size();i++) if (null==rmh.getBussinessLayerFacade().getUseCaseByVertexID(usecases.get(i).getId())){
			final int j = i;
			TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(usecases.get(i));
			if(editingDomain != null){
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

					@Override
					protected void doExecute() {
						usecases.remove(j);
					}
					
					@Override
					public boolean canUndo() {
						return false;
					}
					
				});
				i--;
			}
		}
		return usecases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Invoke> getInvokes() {
		if (invokes == null){
			invokes = new EObjectContainmentEList<Invoke>(Invoke.class, this, UsecasediagramPackage.USE_CASE_DIAGRAM__INVOKES);
		}
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return invokes;
		Map<Integer,UseCase> usecases = new HashMap<Integer,UseCase>();
		if (null!=this.usecases) for (UseCase uc:getUsecases()) usecases.put(uc.getId(),uc);
		for(int i=0; i<invokes.size(); i++){
			if(((InvokeImpl) invokes.get(i)).eIsSet(UsecasediagramPackage.INVOKE__ID) && null == rmh.getInvocationRelationshipByVertexID(invokes.get(i).getId())){
				final int j = i;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(invokes.get(i));
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							invokes.remove(j);
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
		Invoke invoke;
		RSLUseCase tmpuc1,tmpuc2;
		for (InvocationRelationship rel : rmh.getBussinessLayerFacade().getInvocationRelationshipVertices()) if (null!=(tmpuc1=(RSLUseCase)((InvocationRelationshipDTO)rel).getSource()) && usecases.containsKey(tmpuc1.getId()) && null!=(tmpuc2=(RSLUseCase)((InvocationRelationshipDTO)rel).getTarget()) && usecases.containsKey(tmpuc2.getId())){
			invoke = new InvokeImpl();
			invoke.setSource(usecases.get(((RSLUseCase)((InvocationRelationshipDTO)rel).getSource()).getId()));
			invoke.setTarget(usecases.get(((RSLUseCase)((InvocationRelationshipDTO)rel).getTarget()).getId()));
			invoke.setId(((InvocationRelationship)rel).getId());
			boolean contains = false;
			for (final Invoke in: invokes) if (in.getId()==invoke.getId()){
				contains =true;
				if(in.getSource() == null || in.getSource().getId()!=invoke.getSource().getId()){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						final UseCase source = invoke.getSource();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								in.setSource(source);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
				if(in.getTarget() == null || in.getTarget().getId()!=invoke.getTarget().getId()){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						final UseCase target = invoke.getTarget();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								in.setTarget(target);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
				break;
			}
			if (!contains){
				final Invoke inv = invoke;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							invokes.add(inv);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
				}
			}
		}
		return invokes;
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
	 * @generated NOT
	 */
	public void setName(String newName) {
		if (null!=newName && newName.isEmpty()) return;
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsecasediagramPackage.USE_CASE_DIAGRAM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPackage() {
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setPackage(int newPackage) {
		RecoveryModelHelper rmh = null;
		if (null!=(rmh=RecoveryModelHelper.instance(eResource())) && null==rmh.getRequirementsPackageByVertexID(newPackage)) return;
		int oldPackage = package_;
		package_ = newPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsecasediagramPackage.USE_CASE_DIAGRAM__PACKAGE, oldPackage, package_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ACTORS:
				return ((InternalEList<?>)getActors()).basicRemove(otherEnd, msgs);
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ASSOCIATIONS:
				return ((InternalEList<?>)getAssociations()).basicRemove(otherEnd, msgs);
			case UsecasediagramPackage.USE_CASE_DIAGRAM__USECASES:
				return ((InternalEList<?>)getUsecases()).basicRemove(otherEnd, msgs);
			case UsecasediagramPackage.USE_CASE_DIAGRAM__INVOKES:
				return ((InternalEList<?>)getInvokes()).basicRemove(otherEnd, msgs);
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
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ACTORS:
				return getActors();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ASSOCIATIONS:
				return getAssociations();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__USECASES:
				return getUsecases();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__INVOKES:
				return getInvokes();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__NAME:
				return getName();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__PACKAGE:
				return getPackage();
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
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ACTORS:
				getActors().clear();
				getActors().addAll((Collection<? extends Actor>)newValue);
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ASSOCIATIONS:
				getAssociations().clear();
				getAssociations().addAll((Collection<? extends Association>)newValue);
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__USECASES:
				getUsecases().clear();
				getUsecases().addAll((Collection<? extends UseCase>)newValue);
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__INVOKES:
				getInvokes().clear();
				getInvokes().addAll((Collection<? extends Invoke>)newValue);
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__NAME:
				setName((String)newValue);
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__PACKAGE:
				setPackage((Integer)newValue);
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
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ACTORS:
				getActors().clear();
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ASSOCIATIONS:
				getAssociations().clear();
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__USECASES:
				getUsecases().clear();
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__INVOKES:
				getInvokes().clear();
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UsecasediagramPackage.USE_CASE_DIAGRAM__PACKAGE:
				setPackage(PACKAGE_EDEFAULT);
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
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ACTORS:
				return actors != null && !actors.isEmpty();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__ASSOCIATIONS:
				return actors != null && !actors.isEmpty() && usecases != null && !usecases.isEmpty() && !getAssociations().isEmpty();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__USECASES:
				return usecases != null && !usecases.isEmpty();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__INVOKES:
				return usecases != null && !usecases.isEmpty() && !getInvokes().isEmpty();
			case UsecasediagramPackage.USE_CASE_DIAGRAM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UsecasediagramPackage.USE_CASE_DIAGRAM__PACKAGE:
				return package_ != PACKAGE_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", package: ");
		result.append(package_);
		result.append(')');
		return result.toString();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public List<GraphElement> getGraphElements(){
		List<GraphElement> list = new ArrayList<GraphElement>();
		RecoveryModelHelper rmh = null;
		if (null!=(rmh=RecoveryModelHelper.instance(eResource()))){
			GraphElement elem=null;
			for (UseCase el: usecases){
				elem=(GraphElement) rmh.getBussinessLayerFacade().getUseCaseByVertexID(el.getId());
				if (null!=elem) list.add(elem);
			}
			for (Actor el: actors){
				elem=(GraphElement) rmh.getActorByVertexID(el.getId());
				if (null!=elem) list.add(elem);
			}
				for (Association el: associations){
					elem=(GraphElement) rmh.getDependencyByVertexID(el.getId());
					if (null!=elem) list.add(elem);
			}
			for (Invoke el: invokes){
				elem=(GraphElement) rmh.getInvocationRelationshipByVertexID(el.getId());
				if (null!=elem) list.add(elem);
			}
		}
		for (GraphElement ge:deletedItems){
			list.add(ge);
		}
		return list;
	}

} //UseCaseDiagramImpl
