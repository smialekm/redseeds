package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;

class SCSimilarityStructuredSelectionAdapter implements IStructuredSelection {

	private final IStructuredSelection structuredSelection;

	public SCSimilarityStructuredSelectionAdapter(IStructuredSelection structuredSelection) {
		super();
		if(structuredSelection==null){
			throw new NullPointerException("structuredSelection cannot be null");
		}
		this.structuredSelection = structuredSelection;
	}
	@Override
	public Object getFirstElement() {
		Object firstElem = structuredSelection.getFirstElement();
		return SCSimilaritySelectionHelper.convert(firstElem);
	}
	@Override
	public boolean isEmpty() {
		return structuredSelection.isEmpty();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Iterator iterator() {
		return SCSimilaritySelectionHelper.getIteratorAdapter(structuredSelection.iterator());
	}
	@Override
	public int size() {
		return structuredSelection.size();
	}
	@Override
	public Object[] toArray() {
		return SCSimilaritySelectionHelper.convertRequirementMatchsToPastRequirements(structuredSelection.toArray());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List toList() {
		return SCSimilaritySelectionHelper.convertRequirementMatchsToPastRequirements(structuredSelection.toList());
	}

}
