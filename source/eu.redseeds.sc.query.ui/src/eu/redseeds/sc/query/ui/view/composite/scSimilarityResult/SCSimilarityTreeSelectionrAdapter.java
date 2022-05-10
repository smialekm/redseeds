/**
 *
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;

class SCSimilarityTreeSelectionrAdapter implements ITreeSelection {


	private final ITreeSelection treeSelection;
	private final SCSimilarityStructuredSelectionAdapter structuredSelectionAdapter;

	public SCSimilarityTreeSelectionrAdapter(ITreeSelection selection) {
		super();
		if(selection==null){
			throw new NullPointerException("treeSelection cannot be null");
		}
		this.treeSelection = selection;
		this.structuredSelectionAdapter=new SCSimilarityStructuredSelectionAdapter(selection);
	}


	@Override
	public TreePath[] getPaths() {
		return SCSimilaritySelectionHelper.convertRequirementMatchsToPastRequirements(treeSelection.getPaths());
	}

	@Override
	public TreePath[] getPathsFor(Object element) {
		TreePath[] pathFor = treeSelection.getPathsFor(element);
		return SCSimilaritySelectionHelper.convertRequirementMatchsToPastRequirements(pathFor);
	}

	@Override
	public boolean equals(Object obj) {
		return structuredSelectionAdapter.equals(obj);
	}

	@Override
	public Object getFirstElement() {
		return structuredSelectionAdapter.getFirstElement();
	}

	@Override
	public int hashCode() {
		return structuredSelectionAdapter.hashCode();
	}

	@Override
	public boolean isEmpty() {
		return structuredSelectionAdapter.isEmpty();
	}

	@Override
	public Iterator iterator() {
		return structuredSelectionAdapter.iterator();
	}

	@Override
	public int size() {
		return structuredSelectionAdapter.size();
	}

	@Override
	public Object[] toArray() {
		return structuredSelectionAdapter.toArray();
	}

	@Override
	public List toList() {
		return structuredSelectionAdapter.toList();
	}

	@Override
	public String toString() {
		return structuredSelectionAdapter.toString();
	}

}