package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TableViewer;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;

public class OpenDomainStatementListener implements IOpenListener{
	
	private SCProject scProject;
	private NotionEditorControl control; 
	private TableViewer domStatTableViewer;
	
	public OpenDomainStatementListener(NotionEditorControl control,TableViewer domStatTableViewer){
		this.control = control;
		this.domStatTableViewer=domStatTableViewer;
	}

	@Override
	public void open(OpenEvent event) {
		if (scProject == null){
			this.scProject = control.getEditor().getScProject();
		}
		
		if (((IStructuredSelection)event.getSelection()).getFirstElement() instanceof DomainStatementDTO){
			DomainStatementDialog dialog = new DomainStatementDialog(SCProjectHelper.getShell());
			dialog.setDomStat((DomainStatementDTO)((IStructuredSelection)event.getSelection()).getFirstElement());
			dialog.setScProject(scProject);
			dialog.setNotion(control.getEditor().getNotion());
			dialog.open();
			domStatTableViewer.refresh();
			//scProject.save();
		}
		else {
			return;
		}
		
		
	}

	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}

}
