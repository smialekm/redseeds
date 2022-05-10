package eu.redseeds.engine.navigator.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.Clipboard;

import eu.redseeds.engine.navigator.dnd.SCLElementTransfer;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

public class PasteSCLElementAction extends Action {
	
	protected Clipboard clipboard;
	protected StructuredViewer viewer;

	public PasteSCLElementAction(StructuredViewer viewer, Clipboard clipboard) {
		super("Cut");
	    this.viewer = viewer;
	    this.clipboard = clipboard;
	}
	
	public void run() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		//parent of the pasted object is the current selection,
		//or the viewer input if nothing is selected
		Object parent = (Object) sel.getFirstElement();
		if (parent == null)
			parent = (Object) viewer.getInput();
		Object[] objects = (Object[]) clipboard.getContents(SCLElementTransfer
				.getInstance());
		if (objects == null)
			return;
		for (int i = 0; i < objects.length; i++) {
			if(objects[i] instanceof RequirementDTO && parent instanceof RequirementsPackageDTO) {
				RequirementDTO req = (RequirementDTO)objects[i];
//				if (req.equals(parent) || parent.getParent(objects[i])) //not self-drop
//					return;
				req.setParent((RequirementsPackageDTO)parent);
			}
		}
		viewer.refresh();
	}

}
