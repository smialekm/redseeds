package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.domain.SystemElementEditor;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;

public class SystemElementEditorControl extends Composite  {
	
	protected SystemElementEditor editor;

	private ModifyListener modListener = new ModifyListener(){
		@Override
		public void modifyText(ModifyEvent e) {
			editor.setDirty(true);
		}
	};
	
	public SystemElementEditorControl(SystemElementEditor editor, Composite parent) {
		super(parent, SWT.NONE);
		this.editor = editor;
	}

	public ModifyListener getModListener() {
		return modListener;
	}
	
	public void setClean() {
		editor.setDirty(false);
	}
	
	public void setDirty() {
		editor.setDirty(true);
	}

	public SystemElementEditor getEditor() {
		return editor;
	}

	public void setEditor(SystemElementEditor editor) {
		this.editor = editor;
	}
	
	public SystemElementDTO getSystemElementDTO() {
		return editor.getSysElemDTO();
	}
}
