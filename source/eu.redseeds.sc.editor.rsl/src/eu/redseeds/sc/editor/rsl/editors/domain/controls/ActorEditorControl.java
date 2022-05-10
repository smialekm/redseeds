package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditor;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;

public class ActorEditorControl extends Composite {
	
	protected ActorEditor editor;
	
//	private ActorDTO actor;
	
	private ModifyListener modListener = new ModifyListener(){
		@Override
		public void modifyText(ModifyEvent e) {
			editor.setDirty(true);
		}
	};
	
	public ActorEditorControl(ActorEditor editor, Composite parent) {
		super(parent, SWT.NONE);
		this.editor = editor;
	}
	
//	public void fillWithData(ActorDTO actor) {
//		this.actor = actor;
//	}

	public ModifyListener getModListener() {
		return modListener;
	}
	
	public void setClean() {
		editor.setDirty(false);
	}
	
	public void setDirty() {
		editor.setDirty(true);
	}
	
	public ActorDTO getActorDTO() {
		return editor.getActor();
	}

	public ActorEditor getEditor() {
		return editor;
	}

	public void setEditor(ActorEditor editor) {
		this.editor = editor;
	}

}
