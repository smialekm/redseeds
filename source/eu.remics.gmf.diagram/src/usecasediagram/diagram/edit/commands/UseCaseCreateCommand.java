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

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.RecoveryModelHelper;
import usecasediagram.UseCase;
import usecasediagram.UseCaseDiagram;
import usecasediagram.UsecasediagramFactory;
import usecasediagram.impl.UseCaseImpl;

/**
 * @generated
 */
public class UseCaseCreateCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	public UseCaseCreateCommand(CreateElementRequest req) {
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
				&& null == rmh.getBussinessLayerFacade().getUseCaseByVertexID(
						((UseCase) ((CreateElementRequest) getRequest())
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
		UseCase newElement;

		RecoveryModelHelper rmh = RecoveryModelHelper
				.instance(getElementToEdit().eResource());
		if (null != (newElement = (UseCase) ((CreateElementRequest) getRequest())
				.getNewElement())) {
			boolean isDuplicate = false;
			for (UseCase uc : ((UseCaseDiagram) getElementToEdit())
					.getUsecases()) {
				if (newElement.getId() == uc.getId()
						&& rmh.getBussinessLayerFacade()
								.getUseCaseByVertexID(newElement.getId())
								.getName().equals(uc.getName())) {
					MessageDialog
							.openError(Display.getCurrent().getActiveShell(),
									"UseCase duplication",
									"UseCase already exists on the diagram! Creation aborted.");
					isDuplicate = true;
					break;
				}
			}
			if (!isDuplicate) {
				((UseCaseDiagram) getElementToEdit()).getUsecases().add(
						newElement);
				doConfigure(newElement, monitor, info);
			}
			return CommandResult.newOKCommandResult(newElement);
		}

		newElement = UsecasediagramFactory.eINSTANCE.createUseCase();

		UseCaseDTO uc = rmh.getBussinessLayerFacade().createUseCaseDTO();
		uc.setName("");

		Stereotype stereotype = rmh.getBussinessLayerFacade()
				.createSclkernel$Stereotype();
		stereotype.setName("temporary");
		((RSLUseCase) uc).addStereotype(stereotype);

		if (null != UseCaseImpl.NAME_EDEFAULT
				&& !UseCaseImpl.NAME_EDEFAULT.isEmpty()) {
			int i = 1;
			String s = UseCaseImpl.NAME_EDEFAULT;
			while (!uc.isNameUnique(s)) {
				s = UseCaseImpl.NAME_EDEFAULT + i++;
			}
			uc.setName(s);
		}
		newElement.setId(((RSLUseCase) uc).getId());
		ConstrainedLanguageScenarioDTO scen = rmh.getBussinessLayerFacade()
				.createConstrainedLanguageScenarioDTO();
		PreconditionSentenceDTO pre = rmh.getBussinessLayerFacade()
				.createPreconditionSentenceDTO();
		pre.setSentenceText("");
		scen.addScenarioStep(pre);
		SVOSentenceDTO fir = rmh.getBussinessLayerFacade()
				.createSVOSentenceDTO();
		fir.setSentenceText("");
		scen.addScenarioStep(fir);
		uc.addConstrainedLanguageScenario(scen);
		scen.setName(null != uc.getName() ? uc.getName() : "");

		UseCaseDiagram owner = (UseCaseDiagram) getElementToEdit();
		owner.getUsecases().add(newElement);

		rmh.getRequirementsPackageByVertexID(owner.getPackage()).addUseCase(uc);

		doConfigure(newElement, monitor, info);

		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

	/**
	 * @generated
	 */
	protected void doConfigure(UseCase newElement, IProgressMonitor monitor,
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
