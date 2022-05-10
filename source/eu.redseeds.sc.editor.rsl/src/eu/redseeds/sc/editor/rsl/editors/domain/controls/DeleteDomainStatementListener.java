package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;


public class DeleteDomainStatementListener implements SelectionListener {

	private SCProject scProject;
	private NotionEditorControl control; 

	public DeleteDomainStatementListener(NotionEditorControl control){
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
		
		
		Table dsTable = control.getEditor().getViewerStatements().getTable();
		if(dsTable.getSelectionIndex()<0) {
			return;
		}
		TableItem selectedItem = dsTable.getItem(dsTable.getSelectionIndex());
		if(selectedItem.getData() == null) {
			return;
		}
		MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), 
				SWT.ICON_QUESTION  | SWT.YES | SWT.NO);
		confirmMB.setMessage("Are you sure you want to delete this Domain Statement?");
		confirmMB.setText("Confirm delete");
		if(confirmMB.open() == SWT.NO) {
			return; //do nothing
		}
		DomainStatementDTO ds = (DomainStatementDTO)selectedItem.getData();
		if (!ds.getPhraseDTO().equals(ds.getParent().getNamePhrase())) ds.delete();
		else MessageDialog.openError(Display.getCurrent().getActiveShell(),
				"Name Phrase", "You cannot delete name phrase!");
		scProject.save();
		control.getEditor().getViewerStatements().refresh();
		SCProjectHelper.refreshSCNavigator();
		
	}
		

}
