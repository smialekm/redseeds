package eu.redseeds.sc.editor.rsl.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.DomainManagerEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.DomainManagerEditorInput;

public class DomainManagerEditorOpenAction implements IWorkbenchWindowActionDelegate {

	private static String ID_PROJECT_EXPLORER = "eu.redseeds.engine.navigator.view";
	private IWorkbenchWindow window;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	@Override
	public void run(IAction action) {
		try {
			// Receiving references to opened editors in active page
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorReference[] editorReferences = activePage.findEditors(null, DomainManagerEditor.EDITOR_ID, 2);
			
			// Receiving selected elements and their paths from Project Explorer
			IViewPart projectExplorer = ((IViewPart)activePage.findView(ID_PROJECT_EXPLORER));
			ITreeSelection select = (ITreeSelection)projectExplorer.getSite().getWorkbenchWindow().getSelectionService().getSelection();
			TreePath[] selectedPaths = select.getPaths();
			
			// Domain Manager Editor can be opened if only one element is selected
			if (selectedPaths.length != 1){
				throw new PartInitException("4-tree view can not be openet for current selection");
			}
			
			// Domain Manager Editor can be opened if 2-level element is selected (sc or clipboard)
			if (selectedPaths[0].getSegmentCount() != 2){
				throw new PartInitException("4-tree view can not be openet for current selection");
			}
			
			DomainManagerEditorInput input = new DomainManagerEditorInput();
			select.getFirstElement();
			
			SCProject proj = SCProjectContainer.instance().getSCProject(select.getFirstElement());
		
			input.setSoftwareCase(proj.getMainCase());
			
			input.setPath(selectedPaths[0]);
			int editorReferneceCount = editorReferences.length;
			if (editorReferneceCount == 0) {
				activePage.openEditor(input, DomainManagerEditor.EDITOR_ID, true);
			} else {
				boolean isOpened = false;
				for (int i=0; i<editorReferneceCount; i++){
					DomainManagerEditorInput receivedInput = (DomainManagerEditorInput)editorReferences[i].getEditorInput();
					if (receivedInput.getPath().getLastSegment().toString().equals(selectedPaths[0].getLastSegment().toString())){
						activePage.activate(editorReferences[i].getEditor(true));
						isOpened=true;
						break;
					} 
				}
				if (!isOpened){
					activePage.openEditor(input, DomainManagerEditor.EDITOR_ID, true);
				}
			}

		} catch (PartInitException e) {
			MessageDialog.openInformation(window.getShell(),
					"Current Software Case Browser", e.toString());
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
