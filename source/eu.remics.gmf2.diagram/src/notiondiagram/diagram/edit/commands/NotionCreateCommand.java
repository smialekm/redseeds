package notiondiagram.diagram.edit.commands;

import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.NotiondiagramFactory;
import notiondiagram.diagram.providers.NotionDiagramElementTypes;
import notiondiagram.impl.NotionImpl;

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
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.RecoveryModelHelper;

/**
 * @generated
 */
public class NotionCreateCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	public NotionCreateCommand(CreateElementRequest req) {
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
						.getNotionByVertexID(((Notion) ((CreateElementRequest) getRequest())
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
		Notion newElement;

		RecoveryModelHelper rmh = RecoveryModelHelper
				.instance(getElementToEdit().eResource());
		if (null != (newElement = (Notion) ((CreateElementRequest) getRequest())
				.getNewElement())) {
			boolean isDuplicate = false;
			for (Notion notion : ((NotionDiagram) getElementToEdit())
					.getNotions()) {
				if (newElement.getId() == notion.getId()
						&& rmh.getNotionByVertexID(newElement.getId())
								.getName().equals(notion.getName())) {
					MessageDialog
							.openError(Display.getCurrent().getActiveShell(),
									"Notion duplication",
									"Notion already exists on the diagram! Creation aborted.");
					isDuplicate = true;
					break;
				}
			}
			if (!isDuplicate) {
				((NotionDiagram) getElementToEdit()).getNotions().add(
						newElement);
				doConfigure(newElement, monitor, info);
			}
			return CommandResult.newOKCommandResult(newElement);
		}

		newElement = NotiondiagramFactory.eINSTANCE.createNotion();

		NotionDTO notion = rmh.getBussinessLayerFacade().createNotionDTO();
		notion.setName("");

		Stereotype stereotype = rmh.getBussinessLayerFacade()
				.createSclkernel$Stereotype();
		stereotype.setName("temporary");
		((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) notion)
				.addStereotype(stereotype);

		if (null != NotionImpl.NAME_EDEFAULT
				&& !NotionImpl.NAME_EDEFAULT.isEmpty()) {
			int i = 1;
			String s = NotionImpl.NAME_EDEFAULT;
			while (!notion.isNameUnique(s)) {
				s = NotionImpl.NAME_EDEFAULT + i++;
			}
			notion.setName(s);
			NounDTO noun = notion.getNamePhrase().getNoun();
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
				.setId(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) notion)
						.getId());

		NotionDiagram owner = (NotionDiagram) getElementToEdit();
		owner.getNotions().add(newElement);

		rmh.getNotionsPackageByVertexID(owner.getPackage())
				.addNotionDTO(notion);

		if (NotionDiagramElementTypes.Notion_2001 != ((CreateElementRequest) getRequest())
				.getElementType()) {
			IElementType type = ((CreateElementRequest) getRequest())
					.getElementType();
			stereotype = rmh.getBussinessLayerFacade()
					.createSclkernel$Stereotype();
			if (NotionDiagramElementTypes.Notion_2002 == type) {
				stereotype.setName(NotionTypesEnum.Screen.tag());
			} else if (NotionDiagramElementTypes.Notion_2003 == type) {
				stereotype.setName(NotionTypesEnum.Message.tag());
			} else if (NotionDiagramElementTypes.Notion_2004 == type) {
				stereotype.setName(NotionTypesEnum.Confirmation_Dialog.tag());
			} else if (NotionDiagramElementTypes.Notion_2005 == type) {
				stereotype.setName(NotionTypesEnum.Trigger.tag());
			} else if (NotionDiagramElementTypes.Notion_2006 == type) {
				stereotype.setName(NotionTypesEnum.Attribute.tag());
			} else if (NotionDiagramElementTypes.Notion_2007 == type) {
				stereotype.setName(NotionTypesEnum.Option.tag());
			} else if (NotionDiagramElementTypes.Notion_2008 == type) {
				stereotype.setName(NotionTypesEnum.List_View.tag());
			} else if (NotionDiagramElementTypes.Notion_2009 == type) {
				stereotype.setName(NotionTypesEnum.Simple_View.tag());
			} else if (NotionDiagramElementTypes.Notion_2010 == type) {
				stereotype.setName(NotionTypesEnum.Tree_View.tag());
			}
			((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) notion)
					.addStereotype(stereotype);
		}

		doConfigure(newElement, monitor, info);

		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

	/**
	 * @generated
	 */
	protected void doConfigure(Notion newElement, IProgressMonitor monitor,
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
