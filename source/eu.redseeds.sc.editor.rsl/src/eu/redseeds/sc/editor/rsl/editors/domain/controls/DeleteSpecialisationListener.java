package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;

public class DeleteSpecialisationListener implements Listener {

	@Override
	public void handleEvent(Event event) {
		IProject currEclipseProject = SCProjectHelper.getIProject((IStructuredSelection)SCProjectHelper.getSelection());
		Table specsTable 
			= ((Table)(((Composite)((Button)event.widget).getParent())
					.getChildren()[DomainElementEditorControlFactory.NOTIONS_SPECIALISATIONS_TABLE_INDEX]));
		if(specsTable.getSelectionIndex()<0) {
			return;
		}
		TableItem selectedItem = specsTable.getItem(specsTable.getSelectionIndex());
		if(selectedItem.getData() == null) {
			return;
		}
		MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), 
				SWT.ICON_QUESTION  | SWT.YES | SWT.NO);
		confirmMB.setMessage("Are you sure you want to delete this specialization?");
		confirmMB.setText("Confirm delete");
		if(confirmMB.open() == SWT.NO) {
			return; //do nothing
		}
		NotionSpecialisationDTO specialisation = (NotionSpecialisationDTO)selectedItem.getData();
		specialisation.delete();
		SCProjectContainer.instance().getSCProject(currEclipseProject).save();
		specsTable.remove(specsTable.getSelectionIndex());
	}

}
