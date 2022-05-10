package eu.redseeds.sc.editor.rsl.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.editors.domain.PhrasePropertyEditor;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.INewDomainObjectAddedListener;

public class PropertiesOpenAction extends Action{

	IWorkbenchPage activePage = null;
	private Object phraseOrTerm = null;
	private SCProject project;
	private Object parent;
	private INewDomainObjectAddedListener listener;
	
	
	public PropertiesOpenAction(){
		activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(); 
	}
	
	public void setPhraseOrTerm(Object phraseOrTerm){
		this.phraseOrTerm = phraseOrTerm;
	}
	
	@Override
	public void run() {
		
		try {
			PhrasePropertyEditor view = (PhrasePropertyEditor)activePage.findView("eu.redseeds.sc.editor.rsl.editors.domain.PhrasePropertyEditor");
			
			if (view == null){
				view = (PhrasePropertyEditor)activePage.showView("eu.redseeds.sc.editor.rsl.editors.domain.PhrasePropertyEditor");
				view.setScProject(project);
				view.setEditor(parent);
				view.setIncomingObject(phraseOrTerm);
				view.setContent();
			} else {
				if (!phraseOrTerm.equals(view.getIncomingObject())){
					if (view.isDirty()){
						
						MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), 
								SWT.ICON_QUESTION  | SWT.YES | SWT.NO | SWT.CANCEL);

						confirmMB.setMessage("Do you want to save your changes?");

						confirmMB.setText("Confirm save");
						
						int resultMB = confirmMB.open();
						
						if(resultMB == SWT.CANCEL) {
							return; //do nothing
						}
						
						if(resultMB == SWT.YES) {
							view.doSave(new NullProgressMonitor());
							view.clean();
							view.setScProject(project);
							view.setEditor(parent);
							view.setIncomingObject(phraseOrTerm);
							view.setContent();
							view.setDirty(false);
						}
						
						if(resultMB == SWT.NO) {
							view.clean();
							view.setScProject(project);
							view.setEditor(parent);
							view.setIncomingObject(phraseOrTerm);
							view.setContent();
							view.setDirty(false);
						}
						
						
					} else {
						view.clean();
						view.setScProject(project);
						view.setEditor(parent);
						view.setIncomingObject(phraseOrTerm);
						view.setContent();
					}
				} else{
					view.clean();
					view.setScProject(project);
					view.setEditor(parent);
					view.setIncomingObject(phraseOrTerm);
					view.setContent();
				}
				((PhrasePropertyEditor) view).addEventListener(listener);
				view.setScProject(project);
				view.setEditor(parent);
				activePage.activate(view);
			}
			
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SCProject getProject() {
		return project;
	}

	public void setProject(SCProject project) {
		this.project = project;
	}
	
	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}
	
	public INewDomainObjectAddedListener getListener() {
		return listener;
	}

	public void setListener(INewDomainObjectAddedListener listener) {
		this.listener = listener;
	}

}
