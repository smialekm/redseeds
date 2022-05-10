package eu.redseeds.sc.editor.rsl.editors.domain;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.DomainPhrasesView;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.DomainSensesView;


public class DomainManagerEditor extends MultiPageEditorPart {

	
	public static final String EDITOR_ID = "eu.redseeds.sc.editor.rsl.editors.DomainManagerEditor";
	public static final String TAB_SENSES_MANAGER = "Senses Manager";
	public static final String TAB_PHRASES_MANAGER = "Phrases Manager";
	
	private DomainSensesView sensesManagerView;
	private DomainPhrasesView phrasesManagerView;
	private SCProject scProject;
	
	
	public DomainManagerEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(((DomainManagerEditorInput)input).getPath().getLastSegment().toString());
		scProject = SCProjectContainer.instance().getSCProject(((DomainManagerEditorInput)input).getPath().getLastSegment());
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	
	public void setDirty(boolean dirty) {

	}

	@Override
	protected void createPages() {

		
		sensesManagerView = new DomainSensesView(getContainer(), SWT.NONE, scProject);
		sensesManagerView.setParentEditor(this);
		phrasesManagerView = new DomainPhrasesView(getContainer(), SWT.NONE, scProject);
		phrasesManagerView.setParentEditor(this);
		int indexPage1 = addPage(sensesManagerView);
		int indexPage2 = addPage(phrasesManagerView);
		setPageText(indexPage1, TAB_SENSES_MANAGER);
		setPageText(indexPage2, TAB_PHRASES_MANAGER);
	}

}
