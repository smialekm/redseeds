package eu.redseeds.sc.merging.conflicts;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.merging.MergeConstants;

/**
 * A container for all conflict-related information. MergeConflictObject are organised in parent-child structure 
 *
 */
public class MergeConflictObject {
	
	private Object currentObject;
	private Object pastObject;
	
	private int conflictType;
	
	private int conflictAction = 0;
	
	private MergeConflictObject parent = null;
	private List<MergeConflictObject> children = new ArrayList<MergeConflictObject>();

	public MergeConflictObject(Object currentObject, Object pastObject,
			int conflictType, int conflictAction) {
		this.currentObject = currentObject;
		this.pastObject = pastObject;
		this.conflictType = conflictType;
		this.conflictAction = conflictAction;
	}

	public Object getCurrentObject() {
		return currentObject;
	}

	public void setCurrentObject(Object currentObject) {
		this.currentObject = currentObject;
	}

	public Object getPastObject() {
		return pastObject;
	}

	public void setPastObject(Object pastObject) {
		this.pastObject = pastObject;
	}

	public int getConflictType() {
		return conflictType;
	}

	public void setConflictType(int conflictType) {
		this.conflictType = conflictType;
	}
	
	/**
	 * gets this conflict description
	 * @return
	 */
	public String getDescription() {
		if(MergeConstants.MERGE_CONFLICT_LABELS.length >= conflictType) {
			return MergeConstants.MERGE_CONFLICT_LABELS[conflictType];
		}
		return "no description";
	}
	
	@Override
	public String toString() {
		return "Current SC element: " + currentObject + ", past SC element: " + pastObject + ": " + getDescription();
	}

	public int getConflictAction() {
		return conflictAction;
	}

	public void setConflictAction(int conflictAction) {
		this.conflictAction = conflictAction;
	}

	public MergeConflictObject getParent() {
		return parent;
	}

	public void setParent(MergeConflictObject parent) {
		this.parent = parent;
	}
	
	public void addChild(MergeConflictObject child) {
		children.add(child);
	}
	
	public void removeChild(MergeConflictObject child) {
		children.remove(child);
	}
	
	public List<MergeConflictObject> getChildren() {
		return children;
	}

}
