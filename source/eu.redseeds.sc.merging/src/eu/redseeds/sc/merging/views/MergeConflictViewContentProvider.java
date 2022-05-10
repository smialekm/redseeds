package eu.redseeds.sc.merging.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.sc.merging.dialogs.MergeConflictsDialog;

/**
 * Used by {@link MergeConflictsDialog} 
 *
 */
public class MergeConflictViewContentProvider implements ITreeContentProvider{

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			List<MergeConflictObject> result = new ArrayList<MergeConflictObject>();
			for(Object obj : (Object[])inputElement) {
				MergeConflictObject conf = (MergeConflictObject)obj;
				if(conf.getParent() == null) {
					result.add(conf);
				}
			}
			return result.toArray();
		}
		return new Object[0];
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MergeConflictObject) {
			MergeConflictObject conf = (MergeConflictObject)parentElement;
			return conf.getChildren().toArray();
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof MergeConflictObject) {
			MergeConflictObject conf = (MergeConflictObject)element;
			return conf.getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof MergeConflictObject) {
			MergeConflictObject conf = (MergeConflictObject)element;
			return conf.getChildren().size() > 0;
		}
		return false;
	}

}
