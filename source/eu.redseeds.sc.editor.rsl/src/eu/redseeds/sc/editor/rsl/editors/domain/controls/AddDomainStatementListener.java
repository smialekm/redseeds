package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;


public class AddDomainStatementListener implements SelectionListener {

	private SCProject scProject;
	private NotionEditorControl control; 

	public AddDomainStatementListener(NotionEditorControl control){
		this.control = control;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		if (scProject == null) {
			this.scProject = control.getEditor().getScProject();
		}
		DomainStatementDialog dialog = new DomainStatementDialog(SCProjectHelper.getShell());
		dialog.setScProject(scProject);
		dialog.setNotion(control.getEditor().getNotion());
		dialog.setDomStat(null);
		dialog.open();
		control.getEditor().getViewerStatements().refresh();
	}
		

}
