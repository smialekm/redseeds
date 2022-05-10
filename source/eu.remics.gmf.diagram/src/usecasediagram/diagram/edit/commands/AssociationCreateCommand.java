package usecasediagram.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.remics.recovery.model.RecoveryModelHelper;

import usecasediagram.Actor;
import usecasediagram.Association;
import usecasediagram.UseCase;
import usecasediagram.UseCaseDiagram;
import usecasediagram.UsecasediagramFactory;
import usecasediagram.diagram.edit.policies.UseCaseDiagramBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class AssociationCreateCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final EObject source;

	/**
	 * @generated
	 */
	private final EObject target;

	/**
	 * @generated
	 */
	private final UseCaseDiagram container;

	/**
	 * @generated
	 */
	public AssociationCreateCommand(CreateRelationshipRequest request,
			EObject source, EObject target) {
		super(request.getLabel(), null, request);
		this.source = source;
		this.target = target;
		container = deduceContainer(source, target);
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (source == null && target == null) {
			return false;
		}
		if (source != null && false == source instanceof Actor) {
			return false;
		}
		if (target != null && false == target instanceof UseCase) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}
		return UseCaseDiagramBaseItemSemanticEditPolicy.getLinkConstraints()
				.canCreateAssociation_4002(getContainer(), getSource(),
						getTarget());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean canRedo() {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance(getContainer()
				.eResource());
		if (null != getContainer().eResource()
				&& null == rmh
						.getDependencyByVertexID(((Association) ((CreateElementRequest) getRequest())
								.getNewElement()).getId())) {
			return false;
		}
		return super.canRedo();
	}

	/**
	 * @generated NOT
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in create link command"); //$NON-NLS-1$
		}

		Association newElement = UsecasediagramFactory.eINSTANCE
				.createAssociation();
		getContainer().getAssociations().add(newElement);
		newElement.setSource(getSource());
		newElement.setTarget(getTarget());
		RecoveryModelHelper rmh = RecoveryModelHelper.instance(getContainer()
				.eResource());
		NamedElement supplier = null;
		shell: for (IsAllocatedTo alloc : ((RSLUseCase) rmh
				.getBussinessLayerFacade().getUseCaseByVertexID(
						getTarget().getId())).getAllocationToUMLList())
			for (Stereotype ster : alloc.getStereotypeList())
				if (ster.getName().equals(
						"DuplicatedUseCaseStructureForUseRelationship")) {
					supplier = (NamedElement) alloc.getAllocationTargetList()
							.get(0);
					break shell;
				}
		NamedElement client = null;
		shell: for (IsAllocatedTo alloc : ((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) rmh
				.getActorByVertexID(getSource().getId()))
				.getAllocationToUMLList())
			for (Stereotype ster : alloc.getStereotypeList())
				if (ster.getName().equals(
						"DuplicatedUseCaseStructureForUseRelationship")) {
					client = (NamedElement) alloc.getAllocationTargetList()
							.get(0);
					break shell;
				}
		boolean exist = false;
		for (Dependency dep : rmh.getBussinessLayerFacade()
				.getDependencyVertices())
			if (!dep.getSupplierList().isEmpty()
					&& dep.getSupplierList().get(0).getId() == supplier.getId()
					&& dep.getClientList().get(0).getId() == client.getId()) {
				exist = true;
				newElement.setId(dep.getId());
				break;
			}
		if (!exist) {
			Dependency de = rmh.getBussinessLayerFacade().createDependency();
			de.addSupplier(supplier);
			de.addClient(client);
			newElement.setId(de.getId());

			Stereotype stereotype = rmh.getBussinessLayerFacade()
					.createSclkernel$Stereotype();
			stereotype.setName("temporary");
			de.addStereotype(stereotype);
		}
		doConfigure(newElement, monitor, info);
		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);

	}

	/**
	 * @generated
	 */
	protected void doConfigure(Association newElement,
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		IElementType elementType = ((CreateElementRequest) getRequest())
				.getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(
				getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest())
				.getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE,
				getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET,
				getTarget());
		ICommand configureCommand = elementType
				.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**
	 * @generated
	 */
	protected void setElementToEdit(EObject element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated
	 */
	protected Actor getSource() {
		return (Actor) source;
	}

	/**
	 * @generated
	 */
	protected UseCase getTarget() {
		return (UseCase) target;
	}

	/**
	 * @generated
	 */
	public UseCaseDiagram getContainer() {
		return container;
	}

	/**
	 * Default approach is to traverse ancestors of the source to find instance of container.
	 * Modify with appropriate logic.
	 * @generated
	 */
	private static UseCaseDiagram deduceContainer(EObject source, EObject target) {
		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element
				.eContainer()) {
			if (element instanceof UseCaseDiagram) {
				return (UseCaseDiagram) element;
			}
		}
		return null;
	}

}
