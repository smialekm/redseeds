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
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class RemoveNotionAttributeListener implements Listener {

	@Override
	public void handleEvent(Event event) {
		IProject currEclipseProject = SCProjectHelper.getIProject((IStructuredSelection)SCProjectHelper.getSelection());
		Table notAttsTable 
			= ((Table)(((Composite)((Button)event.widget).getParent())
					.getChildren()[DomainElementEditorControlFactory.NOTION_ATTRIBUTES_TABLE_INDEX]));
		if(notAttsTable.getSelectionIndex()<0) {
			return;
		}
		TableItem selectedItem = notAttsTable.getItem(notAttsTable.getSelectionIndex());
		if(selectedItem.getData() == null) {
			return;
		}
		MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), 
				SWT.ICON_QUESTION  | SWT.YES | SWT.NO);
		confirmMB.setMessage("Are you sure you want to remove this attribute?");
		confirmMB.setText("Confirm delete");
		if(confirmMB.open() == SWT.NO) {
			return; //do nothing
		}
		NotionDTO notionAttribute = (NotionDTO)selectedItem.getData();
		NotionDTO parentNotion = ((NotionEditorControl) notAttsTable.getParent()).getNotionDTO();
		parentNotion.removeNotionDTOAttribute(notionAttribute);
		SCProjectContainer.instance().getSCProject(currEclipseProject).save();
		notAttsTable.remove(notAttsTable.getSelectionIndex());

	}

}
