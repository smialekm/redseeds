package eu.redseeds.sc.current.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainStatementDialog;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class AddDomainStatementAction implements
		IWorkbenchWindowActionDelegate, IViewActionDelegate {

	private IViewPart navigator;
	IWorkbenchPage activePage = null;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run(IAction action) {

		activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
		IStructuredSelection select = (IStructuredSelection) navigator
				.getViewSite().getSelectionProvider().getSelection();

		if (select.size() < 1) {
			return;
		}

		if (!(select.getFirstElement() instanceof NotionDTO)) {
			return;
		}

		try {
			NotionEditorInput notionInput = new NotionEditorInput();
			NotionDTO notion = (NotionDTO) select.getFirstElement();
			SCProject project = SCProjectContainer.instance().getSCProject(
					notion);

			notionInput.setNotionDTO(notion);
			NotionEditor notionEditor;

			notionEditor = (NotionEditor) activePage.openEditor(notionInput,
					NotionEditor.EDITOR_ID, false, 1);

			notionEditor.setNotion(notion);
			notionEditor.setFacade(project.getBusinessLayerFacade());
			notionEditor.setScProject(project);

			DomainStatementDialog dialog = new DomainStatementDialog(
					SCProjectHelper.getShell());
			dialog.setDomStat(null);
			dialog.setScProject(project);
			dialog.setNotion(notion);
			dialog.open();
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		navigator = view;
	}

}
