package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import eu.redseeds.common.SCProjectHelper;

public class TermsContentProviderFactory {
	
	/**
	 * Creates an instance of IStructuredContentProvider class depending on manual or auto assignment style 
	 * @return An created instance.
	 */
	public static IStructuredContentProvider getTermsContentProvider() {
		
		if (SCProjectHelper.getSenseAutoAssigmentFlag()){
			return new TermsAutoAssignmentContentProvider();
		} else {
			return new TermsManualAssignmentContentProvider();
		}
	}

}
