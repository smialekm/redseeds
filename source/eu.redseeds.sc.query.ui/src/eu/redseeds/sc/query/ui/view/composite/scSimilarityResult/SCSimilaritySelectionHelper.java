package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.TreePath;


class SCSimilaritySelectionHelper {

	@SuppressWarnings("unchecked")
	private static class SCSimilaritySelectionIteratorAdapter implements Iterator {
		private final Iterator iterator;

		public SCSimilaritySelectionIteratorAdapter(Iterator iterator) {
			super();
			this.iterator = iterator;
		}

		public boolean hasNext() {
			return iterator.hasNext();
		}

		public Object next() {
			return SCSimilaritySelectionHelper.convert(iterator.next());
		}

		public void remove() {
			iterator.remove();
		}
	}

	public static List<Object> convertRequirementMatchsToPastRequirements(List<Object> obj) {
		List<Object> result = new ArrayList<Object>();
		if (obj != null) {
			result = new ArrayList<Object>(obj.size());
			for (Iterator<Object> iterator = obj.iterator(); iterator.hasNext();) {
				Object objectAfterConv = convert(iterator.next());
				result.add(objectAfterConv);
			}
		}
		return result;
	}

	public static Object[] convertRequirementMatchsToPastRequirements(Object[] obj) {
		return obj != null ? convertRequirementMatchsToPastRequirements(Arrays.asList(obj)).toArray() : null;
	}

	public static TreePath[] convertRequirementMatchsToPastRequirements(TreePath[] treePaths) {
		if (treePaths != null) {
			List<TreePath> tmpResult = new ArrayList<TreePath>(treePaths.length);
			for (int i = 0; i < treePaths.length; i++) {
				TreePath orgPath = treePaths[i];
				List<Object> convSegments = new ArrayList<Object>(orgPath.getSegmentCount());
				for (int j = 0; j < orgPath.getSegmentCount(); j++) {
					convSegments.add(convert(orgPath.getSegment(j)));
				}
				tmpResult.add(new TreePath(convSegments.toArray()));
			}
			return tmpResult.toArray(new TreePath[0]);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Iterator getIteratorAdapter(Iterator iterator){
		return new SCSimilaritySelectionIteratorAdapter(iterator);
	}

	public static Object convert(Object obj) {
		if (obj instanceof IElementMatch) {
			IElementMatch requirementMatch = (IElementMatch) obj;
			return requirementMatch.getCurrentElement();
		}
		if(obj instanceof LeafHierarchyAware){
			LeafHierarchyAware leafHierarchyAware=(LeafHierarchyAware) obj;
			return leafHierarchyAware.getHierarchyAware();
		}
		return obj;
	}
}
