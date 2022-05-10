/**
 *
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.PartInitException;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.query.ui.actions.FourTreeViewOpenAction;

class FourViewActionWorkbenchWindowActionDelegate extends FourTreeViewOpenAction {
	private ITreeSelection treeSelection;

	@Override
	protected void extraValidation(TreePath[] selectedPaths) throws PartInitException {

	}

	@Override
	protected SCProject getSCProject(ITreeSelection select) {
		return super.getSCProject(select);
	}

	@Override
	protected ITreeSelection getTreeSelection() {
		return treeSelection;
	}

	public void setTreeSelection(ITreeSelection treeSelection) {
		this.treeSelection = treeSelection;
	}

}