package eu.redseeds.sc.editor.rsl.editors.domain;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditor;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.INewDomainObjectAddedListener;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public class PhrasePropertyEditor extends ViewPart implements ISaveablePart {

	private SCProject scProject;
	private NounPhraseControl nounPhraseControl;
	private VerbPhraseControl verbPhraseControl;
	private NounControl nounControl;
	private Composite currentControl;
	private Object incomingObject;
	private boolean isDirty = false;
	private Composite parent;
	private Object editor = null; 
	private ScrolledComposite sc;
	
	@Override
	public void createPartControl(Composite parent) {		
		this.parent = parent;
		parent.setLayout(new FillLayout());
		sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
	    
	}

	public void setContent(){

		if (editor instanceof NotionEditor || editor == null){
			verbPhraseControl = new VerbPhraseControl(sc, this, (PhraseDTO)incomingObject, scProject);
			verbPhraseControl.setSize(630, 210);
			sc.setContent(verbPhraseControl);
			currentControl=verbPhraseControl;
			
		}	
		
		if (editor instanceof UseCaseEditor){		
			if (incomingObject instanceof NounPhraseDTO){
				nounPhraseControl = new NounPhraseControl(sc, this, (NounPhraseDTO)incomingObject, scProject);
				nounPhraseControl.setSize(650, 180);
				sc.setContent(nounPhraseControl);
				currentControl=nounPhraseControl;
			}
			if (incomingObject instanceof VerbPhraseDTO){
				verbPhraseControl = new VerbPhraseControl(sc, this, (PhraseDTO)incomingObject, scProject);
				verbPhraseControl.setSize(630, 210);
				sc.setContent(verbPhraseControl);
				currentControl=verbPhraseControl;
			}	
		}
		
		if (editor instanceof DomainManagerEditor){		
			if (incomingObject instanceof TermDTO){
				nounControl = new NounControl(sc, (TermDTO)incomingObject);
				nounControl.setSize(650, 180);
				sc.setContent(nounControl);
				currentControl=nounControl;
			}
			
			if (incomingObject instanceof NounPhraseDTO && SCProjectContainer.instance().getSCProject(incomingObject).getBusinessLayerFacade().canBeActorOrSystemElement((NounPhraseDTO) incomingObject)){
					nounPhraseControl = new NounPhraseControl(sc, this, (NounPhraseDTO)incomingObject, scProject);
					nounPhraseControl.setSize(650, 180);
					sc.setContent(nounPhraseControl);
					currentControl=nounPhraseControl;
			}
			if (incomingObject instanceof NounPhraseDTO || incomingObject instanceof VerbPhraseDTO){
				verbPhraseControl = new VerbPhraseControl(sc, this, (PhraseDTO)incomingObject, scProject);
				verbPhraseControl.setSize(630, 210);
				sc.setContent(verbPhraseControl);
				currentControl=verbPhraseControl;
			}	
		}

		parent.layout();
	}
	
	public void clean(){
		if (nounPhraseControl != null){
			nounPhraseControl.dispose();
		}
		if (verbPhraseControl != null){
			verbPhraseControl.dispose();
		}
		if (nounControl != null){
			nounControl.dispose();
		}
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	public void setIncomingObject(Object phrase){		
			this.incomingObject = phrase;		
	}
	
	public Object getIncomingObject(){
		return incomingObject;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	public void setDirty(boolean isDirty){
		this.isDirty = isDirty;
		firePropertyChange(PROP_DIRTY);
	}
	
	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}

	public Object getEditor() {
		return editor;
	}

	public void setEditor(Object editor) {
		this.editor = editor;
	}
	
	public void addEventListener(INewDomainObjectAddedListener listener){
		if (currentControl instanceof VerbPhraseControl)
			((VerbPhraseControl) currentControl).addEventListener(listener);
		else if (currentControl instanceof NounPhraseControl)
			((NounPhraseControl) currentControl).addEventListener(listener);
	}
	
	public void refresh(){
		if (null!=currentControl){
			clean();
			setContent();
		}
	}
	
	public void removeEventListener(INewDomainObjectAddedListener listener){
		if (currentControl instanceof VerbPhraseControl) 
			((VerbPhraseControl) currentControl).removeEventListener(listener);
		else if (currentControl instanceof NounPhraseControl)
			((NounPhraseControl) currentControl).removeEventListener(listener);
	}
	
}
