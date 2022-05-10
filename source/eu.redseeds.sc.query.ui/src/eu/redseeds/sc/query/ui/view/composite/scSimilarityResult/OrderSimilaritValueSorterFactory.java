package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class OrderSimilaritValueSorterFactory {
	public static ViewerSorter newAscendingSorter() {
		return new OrderAscendingSorter();
	}

	public static ViewerSorter newDescendingSorter() {
		return new OrderDescendingSorter();
	}

	private static class OrderAscendingSorter extends OrderSorter {

		@Override
		protected int compareRequirementMatch(Viewer viewer,IElementMatch em1,
				IElementMatch em2) {
			return Double.compare(em1.getSimilarityValue(), em2.getSimilarityValue());
		}
	}

	private static class OrderDescendingSorter extends OrderSorter {

		@Override
		protected int compareRequirementMatch(Viewer viewer,IElementMatch em1,
				IElementMatch em2) {
			return Double.compare(em2.getSimilarityValue(), em1.getSimilarityValue());
		}
	}
}