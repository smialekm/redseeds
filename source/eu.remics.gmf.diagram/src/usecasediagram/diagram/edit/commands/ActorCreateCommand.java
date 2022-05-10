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
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.RecoveryModelHelper;
import usecasediagram.Actor;
import usecasediagram.UseCaseDiagram;
import usecasediagram.UsecasediagramFactory;
import usecasediagram.impl.ActorImpl;

/**
 * @generated
 */
public class ActorCreateCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	public ActorCreateCommand(CreateElementRequest req) {
		super(req.getLabel(), null, req);
	}

	/**
	 * FIXME: replace with setElementToEdit()
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		return true;

	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean canRedo() {
		RecoveryModelHelper rmh = RecoveryModelHelper
				.instance(getElementToEdit().eResource());
		if (null != getElementToEdit().eResource()
				&& null == rmh
						.getActorByVertexID(((Actor) ((CreateElementRequest) getRequest())
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
		Actor newElement;

		RecoveryModelHelper rmh = RecoveryModelHelper
				.instance(getElementToEdit().eResource());
		if (null != (newElement = (Actor) ((CreateElementRequest) getRequest())
				.getNewElement())) {
			boolean isDuplicate = false;
			for (Actor act : ((UseCaseDiagram) getElementToEdit()).getActors()) {
				if (newElement.getId() == act.getId()
						&& rmh.getActorByVertexID(newElement.getId()).getName()
								.equals(act.getName())) {
					MessageDialog
							.openError(Display.getCurrent().getActiveShell(),
									"Actor duplication",
									"Actor already exists on the diagram! Creation aborted.");
					isDuplicate = true;
					break;
				}
			}
			if (!isDuplicate) {
				((UseCaseDiagram) getElementToEdit()).getActors().add(
						newElement);
				doConfigure(newElement, monitor, info);
			}
			return CommandResult.newOKCommandResult(newElement);
		}

		newElement = UsecasediagramFactory.eINSTANCE.createActor();

		ActorDTO act = rmh.getBussinessLayerFacade().createActorDTO();
		act.setName("");

		Stereotype stereotype = rmh.getBussinessLayerFacade()
				.createSclkernel$Stereotype();
		stereotype.setName("temporary");
		((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) act)
				.addStereotype(stereotype);

		if (null != ActorImpl.NAME_EDEFAULT
				&& !ActorImpl.NAME_EDEFAULT.isEmpty()) {
			int i = 1;
			String s = ActorImpl.NAME_EDEFAULT;
			while (!act.isNameUnique(s)) {
				s = ActorImpl.NAME_EDEFAULT + i++;
			}
			act.setName(s);
			NounDTO noun = act.getNounPhrase().getNoun();
			if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) {
				try {
					noun.autoAddAndAssignSense();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			} else
				noun.autoAssignSense();
		}

		newElement
				.setId(((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) act)
						.getId());

		UseCaseDiagram owner = (UseCaseDiagram) getElementToEdit();
		owner.getActors().add(newElement);

		rmh.getActorsPackage("Actors").addActorDTO(act);

		doConfigure(newElement, monitor, info);

		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

	/**
	 * @generated
	 */
	protected void doConfigure(Actor newElement, IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		IElementType elementType = ((CreateElementRequest) getRequest())
				.getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(
				getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest())
				.getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		ICommand configureCommand = elementType
				.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

}
