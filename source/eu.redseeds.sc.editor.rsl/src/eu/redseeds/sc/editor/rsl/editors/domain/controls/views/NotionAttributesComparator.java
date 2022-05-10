package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public class NotionAttributesComparator extends ViewerComparator {
	
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction;
	private TableViewer notAttsTableViewer;
	
	public NotionAttributesComparator() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}
	
	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}
	
	public void setColumn(int column) {
		if(column == this.propertyIndex){
			direction  = 1 - direction;
		}
		else{
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	public void setViewer(TableViewer notAttsTableViewer) {
		this.notAttsTableViewer = notAttsTableViewer;
	}
	
	public TableViewer getViewer() {
		return notAttsTableViewer;
	}
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		int compareRes = super.compare(viewer, e1, e2);
		if(direction == DESCENDING)
			compareRes = -compareRes;
		
		return compareRes;
	}
}
