/**
 *
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.query.ui.Activator;
import eu.redseeds.sc.query.ui.actions.FourTreeViewOpenAction;
import eu.redseeds.sc.query.ui.actions.ShowSliceAction;

class FourViewAction extends Action {
	private FourViewActionWorkbenchWindowActionDelegate fourTreeViewOpenAction = new FourViewActionWorkbenchWindowActionDelegate();
	private IStructuredSelection structuredSelection;

	public FourViewAction() {
		super("4-tree view");
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return Activator.getImageDescriptor("icons/fourTreeView.GIF");
	}

	@Override
	public void run() {
		fourTreeViewOpenAction.run(this);
		runSliceAction();
	}

	private void runSliceAction() {
		if(structuredSelection!=null){
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorReference[] editorReferences = activePage.findEditors(null, FourTreeViewOpenAction.ID_FOUR_TREE_VIEW, 2);
			if (editorReferences.length > 0) {
				ShowSliceAction showSliceAction = new SCSimilarityResultShowSliceAction(structuredSelection);
				IEditorReference editorReference = editorReferences[0];
				showSliceAction.setActiveEditor(null, editorReference.getEditor(true));
				showSliceAction.run(null);
			} else {
				Activator.log("There is no 4TreeView editor to open in slice", IStatus.WARNING);
			}
		}
	}

	public void setTreeSelection(ITreeSelection treeSelection) {
		if(treeSelection!=null){
			this.structuredSelection = new SCSimilarityStructuredSelectionAdapter(treeSelection);
			fourTreeViewOpenAction.setTreeSelection(new SCSimilarityTreeSelectionrAdapter(treeSelection));
		}
	}
}