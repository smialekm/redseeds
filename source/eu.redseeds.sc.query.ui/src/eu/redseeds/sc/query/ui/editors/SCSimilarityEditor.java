package eu.redseeds.sc.query.ui.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class SCSimilarityEditor extends EditorPart {

	
	public static final String EDITOR_ID = "eu.redseeds.sc.query.ui.editors.SCSimilarityEditor";
	private SCSimilarityEditorControl scSimilarityEditorControl;
	
	
	public SCSimilarityEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		scSimilarityEditorControl = new SCSimilarityEditorControl(parent, this);
	}

	public void refreshEditor(){
		// TODO tstraszak: this method has to be filled in with the proper code
		scSimilarityEditorControl.layout();
	}
	
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
	}

}
