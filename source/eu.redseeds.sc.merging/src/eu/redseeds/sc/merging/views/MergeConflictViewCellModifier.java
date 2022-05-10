package eu.redseeds.sc.merging.views;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;

import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.sc.merging.dialogs.MergeConflictsDialog;

/**
 * Used by {@link MergeConflictsDialog} 
 *
 */
public class MergeConflictViewCellModifier implements ICellModifier {
	
	private TreeViewer treeViewer;
	
	public MergeConflictViewCellModifier(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean canModify(Object element, String property) {
		if(property.equalsIgnoreCase(MergeConflictsDialog.columnNames[3])) {//action
			return true;
		}
		return false;
	}

	@Override
	public Object getValue(Object element, String property) {
		if(property.equalsIgnoreCase(MergeConflictsDialog.columnNames[3])) {//action
			if(element instanceof MergeConflictObject) {
				MergeConflictObject conflict = (MergeConflictObject)element;
				return new Integer(conflict.getConflictAction()); 
			}
		}
		return null;
	}

	@Override
	public void modify(Object element, String property, Object value) {
		if(property.equalsIgnoreCase(MergeConflictsDialog.columnNames[3])) {//action
			Object data =((TreeItem)element).getData();
			if(data instanceof MergeConflictObject && value instanceof Integer) {
				if(((Integer)value).intValue() < 0) {
					return;
				}
				MergeConflictObject conflict = (MergeConflictObject)data;
				int newAction = ((Integer)value).intValue() + MergeConstants.MERGE_ACTION_BASE_ID + 1;
				conflict.setConflictAction(newAction);
				for(MergeConflictObject child : conflict.getChildren()) {
					child.setConflictAction(newAction);
				}
				treeViewer.refresh();
			}
		}
	}

}
