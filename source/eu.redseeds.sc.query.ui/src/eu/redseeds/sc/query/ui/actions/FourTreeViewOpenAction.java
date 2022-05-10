package eu.redseeds.sc.query.ui.actions;

import java.util.List;

import org.eclipse.jface.action.IAction;
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
import org.eclipse.jface.dialogs.MessageDialog;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.query.ui.editors.FourTreeViewInput;

/**
 * Our sample action implements workbench action delegate. The action proxy will
 * be created by the workbench and shown in the UI. When the user tries to use
 * the action, this delegate will be created and execution will be delegated to
 * it.
 *
 * @see IWorkbenchWindowActionDelegate
 */
public class FourTreeViewOpenAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	private static String ID_PROJECT_EXPLORER = "eu.redseeds.engine.navigator.view";
	public static String ID_FOUR_TREE_VIEW = "eu.redseeds.sc.query.ui.editors.FourTreeView";

	/**
	 * The constructor.
	 */
	public FourTreeViewOpenAction() {
	}

	private boolean hasSameRoot(TreePath[] treePaths){
		boolean result=false;
		if(treePaths!=null && treePaths.length>0){
			Object root=treePaths[0].getFirstSegment();
			int falseCount=0;
			for (TreePath treePath : treePaths) {
				if(!root.equals(treePath.getFirstSegment())){
					falseCount++;
				}
			}
			result=falseCount==0;
		}
		return result;
	}

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 *
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		try {
//			// Receiving references to opened editors in active page
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorReference[] editorReferences = activePage.findEditors(null, ID_FOUR_TREE_VIEW, 2);

			ITreeSelection select=getTreeSelection();

			TreePath[] selectedPaths = select.getPaths();

			// 4-tree view can be opened if only one element is selected or all paths have the same root
			if (selectedPaths.length != 1 && !hasSameRoot(selectedPaths)) {
				throw new PartInitException("4-tree view can not be openet for current selection");
			}

			extraValidation(selectedPaths);

			select.getFirstElement();

			SCProject proj = getSCProject(select);

			FourTreeViewInput input = new FourTreeViewInput();
			input.setSoftwareCase(proj.getMainCase());

			input.setPath(selectedPaths[0]);
			int editorReferneceCount = editorReferences.length;
			if (editorReferneceCount == 0) {
				activePage.openEditor(input, ID_FOUR_TREE_VIEW, true);
			} else {
				boolean isOpened = false;
				for (int i = 0; i < editorReferneceCount; i++) {
					FourTreeViewInput receivedInput = (FourTreeViewInput) editorReferences[i].getEditorInput();
//					if (receivedInput.getPath().getLastSegment().toString().equals(selectedPaths[0].getLastSegment().toString())) {
					if (receivedInput.getPath().getFirstSegment().toString().equals(selectedPaths[0].getFirstSegment().toString())) {
						activePage.activate(editorReferences[i].getEditor(true));
						isOpened = true;
						break;
					}
				}
				if (!isOpened) {
					activePage.openEditor(input, ID_FOUR_TREE_VIEW, true);
				}
			}

		} catch (PartInitException e) {
			MessageDialog.openInformation(window.getShell(), "Current Software Case Browser", e.toString());
		}

	}

	protected SCProject getSCProject(ITreeSelection select) {
		return SCProjectContainer.instance().getSCProject(select.getFirstElement());
	}

	/**
	 * Override this method if you want to change extraValidation of ITreeSelection from {@link #getTreeSelection()}
	 * By default this method will check if 2-level element is selected
	 */
	protected void extraValidation(TreePath[] selectedPaths) throws PartInitException {
		// 4-tree view can be opened if 2-level element is selected (sc or
		// clipboard)
		if (selectedPaths[0].getSegmentCount() != 2) {
			throw new PartInitException("4-tree view can not be openet for current selection");
		}
	}
	/**
	 * Override this method (and optional {@link #extraValidation(TreePath[])} if you want to provide custom ITreeSelection
	 * @return ITreeSelection
	 */
	protected ITreeSelection getTreeSelection() {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		// Receiving selected elements and their paths from Project Explorer
		IViewPart projectExplorer = activePage.findView(ID_PROJECT_EXPLORER);
		ITreeSelection select = (ITreeSelection) projectExplorer.getSite().getWorkbenchWindow().getSelectionService().getSelection();
		return select;
	}

	/**
	 *
	 * Run method invoked when SC selected from query manager
	 *
	 * @param scProject
	 * @param selecteElements
	 */
	public void run(SCProject scProject, List<Object> selecteElements) {
		try {
			if (scProject == null || selecteElements == null) {
				return;
			}

			// Receiving references to opened editors in active page
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorReference[] editorReferences = activePage.findEditors(null, ID_FOUR_TREE_VIEW, 2);

			// // Creating selected elements and their paths from Project
			// Explorer
			TreePath[] selectedPaths = new TreePath[] { new TreePath(new Object[] { scProject.getEclipseProject().getName() }) };

			FourTreeViewInput input = new FourTreeViewInput();

			input.setSoftwareCase(scProject.getMainCase());
			input.setPreSelectedObjectsList(selecteElements);

			input.setPath(selectedPaths[0]);
			int editorReferneceCount = editorReferences.length;
			if (editorReferneceCount == 0) {
				activePage.openEditor(input, ID_FOUR_TREE_VIEW, true);
			} else {
				boolean isOpened = false;
				for (int i = 0; i < editorReferneceCount; i++) {
					FourTreeViewInput receivedInput = (FourTreeViewInput) editorReferences[i].getEditorInput();
					if (receivedInput.getPath().getLastSegment().toString().equals(selectedPaths[0].getLastSegment().toString())) {
						activePage.activate(editorReferences[i].getEditor(true));
						isOpened = true;
						break;
					}
				}
				if (!isOpened) {
					activePage.openEditor(input, ID_FOUR_TREE_VIEW, true);
				}
			}

		} catch (PartInitException e) {
			MessageDialog.openInformation(window.getShell(), "Current Software Case Browser", e.toString());
		}

	}

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 *
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 *
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to be able to provide parent shell
	 * for the message dialog.
	 *
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}