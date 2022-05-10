/**
 * 
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import org.eclipse.jface.viewers.IStructuredSelection;

import eu.redseeds.sc.query.ui.actions.ShowSliceAction;

class SCSimilarityResultShowSliceAction extends ShowSliceAction {
	private IStructuredSelection structuredSelection;

	public SCSimilarityResultShowSliceAction(IStructuredSelection structuredSelection) {
		super();
		this.structuredSelection = structuredSelection;
	}

	@Override
	protected IStructuredSelection getSelection() {
		IStructuredSelection result;
		if (structuredSelection != null) {
			result = structuredSelection;
			structuredSelection = null;
		} else {
			result = super.getSelection();
		}
		return result;
	}

}