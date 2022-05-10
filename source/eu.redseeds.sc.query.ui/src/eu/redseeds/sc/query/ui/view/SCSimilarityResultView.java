package eu.redseeds.sc.query.ui.view;

import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.sc.query.ui.view.composite.scSimilarityResult.SCSimilarityResultComposite;

/**
 * View with result for similarity query
 */
public class SCSimilarityResultView extends ViewPart implements ISimpleSortAbleView {

	public final static String VIEW_ID="eu.redseeds.sc.query.ui.view.SCSimilarityResultView";

	private SCSimilarityResultComposite similarityResultComposite;

	public SCSimilarityResultView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		similarityResultComposite = new SCSimilarityResultComposite(parent, this);
	}

	public void selectionInEditorChanged(SimilarSCProject similarSCProject) {
		similarityResultComposite.setInput(similarSCProject);
	}

	// TODO TP: set focus
	@Override
	public void setFocus() {
		// viewer.getControl().setFocus();
	}

	@Override
	public void setViewerSorter(ViewerSorter viewerSorter) {
		similarityResultComposite.setViewerSorter(viewerSorter);
	}


}
