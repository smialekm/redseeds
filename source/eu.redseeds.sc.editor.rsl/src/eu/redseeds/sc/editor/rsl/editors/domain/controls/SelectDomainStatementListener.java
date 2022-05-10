package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.actions.PropertiesOpenAction;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;

public class SelectDomainStatementListener implements ISelectionChangedListener {

	private SCProject scProject;
	private NotionEditorControl control; 
	

	public SelectDomainStatementListener(NotionEditorControl control) {
		this.control = control;		
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		if (scProject == null){
			this.scProject = control.getEditor().getScProject();
		}
		if (((IStructuredSelection)event.getSelection()).getFirstElement() instanceof DomainStatementDTO){
			PropertiesOpenAction openPropertyAction = new PropertiesOpenAction();
			openPropertyAction.setProject(scProject);
			openPropertyAction.setParent(control.getEditor());
			DomainStatementDTO ds = (DomainStatementDTO)((IStructuredSelection)event.getSelection()).getFirstElement();
			openPropertyAction.setPhraseOrTerm(ds.getPhraseDTO());
			openPropertyAction.run();
		} else return;
		 
		
	}
	
	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}

}
