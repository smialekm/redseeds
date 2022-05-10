package eu.redseeds.sc.editor.rsl.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class TermEditor extends EditorPart {

	
	public static final String EDITOR_ID = "eu.redseeds.sc.editor.rsl.editors.TermEditor";
	private boolean dirty = false;
	private TermEditorControl termEditorControl;
	
	
	public TermEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		monitor.beginTask("Saving Terminology", 1);
		if (termEditorControl.save()) {
			dirty = false;
			firePropertyChange(PROP_DIRTY);
			//setTitleToolTip("");
			//setPartName("");
		}
		monitor.worked(1);
		monitor.done();
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
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		termEditorControl = new TermEditorControl(parent, this);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		firePropertyChange(PROP_DIRTY);
	}

}
