package notiondiagram.diagram.edit.commands;

import notiondiagram.AttributeRelation;
import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.NotiondiagramFactory;
import notiondiagram.diagram.edit.policies.NotionDiagramBaseItemSemanticEditPolicy;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

import eu.remics.recovery.model.RecoveryModelHelper;

/**
 * @generated
 */
public class AttributeRelationCreateCommand extends EditElementCommand {

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
	private final NotionDiagram container;

	/**
	 * @generated
	 */
	public AttributeRelationCreateCommand(CreateRelationshipRequest request,
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
		if (source != null && false == source instanceof Notion) {
			return false;
		}
		if (target != null && false == target instanceof Notion) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}
		return NotionDiagramBaseItemSemanticEditPolicy.getLinkConstraints()
				.canCreateAttributeRelation_4002(getContainer(), getSource(),
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
						.getNotionByVertexID(((AttributeRelation) ((CreateElementRequest) getRequest())
								.getNewElement()).getSourceId())
				|| null == rmh
						.getNotionByVertexID(((AttributeRelation) ((CreateElementRequest) getRequest())
								.getNewElement()).getTargetId())
				|| !rmh.getNotionByVertexID(
						((AttributeRelation) ((CreateElementRequest) getRequest())
								.getNewElement()).getTargetId())
						.getNotionDTOAttributeList()
						.contains(
								rmh.getNotionByVertexID(((AttributeRelation) ((CreateElementRequest) getRequest())
										.getNewElement()).getSourceId()))) {
			return false;
		}
		return super.canRedo();
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected IStatus doUndo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		//TODO
		return super.doUndo(monitor, info);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected IStatus doRedo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		//TODO
		return super.doRedo(monitor, info);
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

		AttributeRelation newElement = NotiondiagramFactory.eINSTANCE
				.createAttributeRelation();
		getContainer().getAttributeRelations().add(newElement);
		newElement.setSource(getSource());
		newElement.setTarget(getTarget());

		RecoveryModelHelper rmh = RecoveryModelHelper.instance(getContainer()
				.eResource());
		rmh.getNotionByVertexID(getTarget().getId()).addNotionDTOAttribute(
				rmh.getNotionByVertexID(getSource().getId()));

		doConfigure(newElement, monitor, info);
		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);

	}

	/**
	 * @generated
	 */
	protected void doConfigure(AttributeRelation newElement,
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
	protected Notion getSource() {
		return (Notion) source;
	}

	/**
	 * @generated
	 */
	protected Notion getTarget() {
		return (Notion) target;
	}

	/**
	 * @generated
	 */
	public NotionDiagram getContainer() {
		return container;
	}

	/**
	 * Default approach is to traverse ancestors of the source to find instance of container.
	 * Modify with appropriate logic.
	 * @generated
	 */
	private static NotionDiagram deduceContainer(EObject source, EObject target) {
		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element
				.eContainer()) {
			if (element instanceof NotionDiagram) {
				return (NotionDiagram) element;
			}
		}
		return null;
	}

}
