package notiondiagram.diagram.part;

import java.util.Arrays;

import notiondiagram.Notion;
import notiondiagram.diagram.edit.parts.NotionEditPart;
import notiondiagram.diagram.edit.parts.NotionTypeEditPart;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.RecoveryModelHelper;

/**
 * @generated NOT
 */
public class ConvertToConfirmationDialogNotionAction implements IObjectActionDelegate {

	private NotionEditPart selectedElement;

	public void run(IAction action) {
		Notion not = ((Notion) selectedElement.resolveSemanticElement());
		RecoveryModelHelper rmh = RecoveryModelHelper.instance(not.eResource());
		NotionDTO notion = rmh.getNotionByVertexID(not.getId());
		for (Stereotype s:((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) notion).getStereotypeList()) if (Arrays.asList(NotionTypesEnum.tags()).contains(s.getName())){
			s.delete();
		}
		Stereotype stereotype = rmh.getBussinessLayerFacade().createSclkernel$Stereotype();
		stereotype.setName(NotionTypesEnum.Confirmation_Dialog.tag());
		((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)notion).addStereotype(stereotype);
		notion.setDataType(null);
		for (Object ep:selectedElement.getChildren())
			if (ep instanceof NotionTypeEditPart){
				((NotionTypeEditPart) ep).refresh();
				break;
			}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		selectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof NotionEditPart) {
				selectedElement = (NotionEditPart) structuredSelection.getFirstElement();
			}
		}
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
