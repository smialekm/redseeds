package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;


import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import eu.redseeds.sc.query.engine.RequirementMatch;

/**
 * This class provides elements form tree and convert RequirementMatch to
 * {@link RequirementMatch#getPastRequirement()}. This is needed to open
 * RequirementMatch in useCase editor.
 */
class SCSimilarityTreeSelectionProviderAdapter implements ISelectionProvider {
	private final TreeViewer selectionProvider;

	public SCSimilarityTreeSelectionProviderAdapter(TreeViewer treeViewer) {
		super();
		this.selectionProvider = treeViewer;
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	}

	public ISelection getSelection() {
		return new SCSimilarityTreeSelectionrAdapter((ITreeSelection) selectionProvider.getSelection());
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
	}

	public void setSelection(ISelection selection) {
	}
}