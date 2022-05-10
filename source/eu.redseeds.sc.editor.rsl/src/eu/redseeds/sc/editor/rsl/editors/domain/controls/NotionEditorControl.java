package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditor;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class NotionEditorControl extends Composite {
	
	protected NotionEditor editor;

	private ModifyListener modListener = new ModifyListener(){
		@Override
		public void modifyText(ModifyEvent e) {
			editor.setDirty(true);
			if (((Combo)getChildren()[22]).getSelectionIndex()!=5){
				if (((Combo)getChildren()[17]).indexOf(AttributeDataTypesEnum.EMPTY) == -1)
					((Combo)getChildren()[17]).add(AttributeDataTypesEnum.EMPTY,0);
				((Combo)getChildren()[17]).select(((Combo)getChildren()[17]).indexOf(AttributeDataTypesEnum.EMPTY));
				((Combo)getChildren()[17]).setEnabled(false);
			} else ((Combo)getChildren()[17]).setEnabled(true);
		}
	};
	
	public NotionEditorControl(NotionEditor editor, Composite parent) {
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

	public NotionEditor getEditor() {
		return editor;
	}
	
	public NotionDTO getNotionDTO() {
		return editor.getNotion();
	}

}
